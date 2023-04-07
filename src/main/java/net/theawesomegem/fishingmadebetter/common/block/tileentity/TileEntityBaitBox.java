package net.theawesomegem.fishingmadebetter.common.block.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.*;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.theawesomegem.fishingmadebetter.BetterFishUtil;
import net.theawesomegem.fishingmadebetter.common.capability.world.ChunkCapabilityProvider;
import net.theawesomegem.fishingmadebetter.common.capability.world.IChunkFishingData;
import net.theawesomegem.fishingmadebetter.common.capability.world.PopulationData;
import net.theawesomegem.fishingmadebetter.common.configuration.ConfigurationManager;
import net.theawesomegem.fishingmadebetter.common.configuration.CustomConfigurationHandler;
import net.theawesomegem.fishingmadebetter.common.data.FishData;
import net.theawesomegem.fishingmadebetter.util.TimeUtil;

import javax.annotation.Nullable;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by TheAwesomeGem on 1/2/2018.
 */
public class TileEntityBaitBox extends TileEntity implements ITickable {
    private long baitUpdateTime;
    private int timer = 0;
    private ItemStackHandler inventory = new ItemStackHandler(8);//TODO: make configurable
    
    @Override
    public void onLoad() {
        if(world.isRemote) return;

        updateBaitTime(world.getTotalWorldTime());
    }

    @Override
    public void update() {
        if(world.isRemote) return;

        if(timer%20 == 0) timer=0;
        
        if(timer == 0) updateOnSecond(world.getTotalWorldTime());
        timer++;
    }
    
    public void handleRightClick(World worldIn, BlockPos pos, IBlockState state, EntityPlayer player) {
        ItemStack itemStack = player.getHeldItemMainhand();

        if(!itemStack.isEmpty()) {
            int freeSlot = -1;
            
            if(!BetterFishUtil.isValidBait(itemStack)) {//Hopefully whitelists correctly
            	player.sendMessage(new TextComponentTranslation("notif.fishingmadebetter.baitbox.bait_not_valid"));
            	return;
            }
            
            for(int i = 0; i < inventory.getSlots() && itemStack.getCount() > 0; i++) {
                ItemStack stack = inventory.getStackInSlot(i);

                if(stack.isEmpty()) {
                    freeSlot = i;
                    continue;
                }

                if(isItemStackEqual(itemStack, stack) && (stack.getMaxStackSize() - stack.getCount()) > 0) itemStack = inventory.insertItem(i, itemStack, false);
            }

            if(itemStack.getCount() > 0 && freeSlot != -1) itemStack = inventory.insertItem(freeSlot, itemStack, false);
            
            if(itemStack.getCount() > 0) {
            	player.setHeldItem(EnumHand.MAIN_HAND, itemStack);
            	player.sendMessage(new TextComponentTranslation("notif.fishingmadebetter.baitbox.full"));
            }
            else player.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
            
            return;
        } 
        else {
            Map<String, Integer> baitAmountMap = new HashMap<>();

            for(int i = 0; i < inventory.getSlots(); i++) {
                ItemStack stack = inventory.getStackInSlot(i);

                if(stack.isEmpty()) {
                    continue;
                }

                //String name = stack.getDisplayName();
                // Save lang keys into the map, so they can be translated when sending messages.
                String baitLangKey = BetterFishUtil.getBaitLangKey(stack.getItem().getRegistryName().toString(), stack.getMetadata());
                String name = baitLangKey == null ? stack.getDisplayName() : baitLangKey; // Shouldn't need this check, but just in case

                if(baitAmountMap.containsKey(name)) baitAmountMap.put(name, baitAmountMap.get(name) + stack.getCount());
                else baitAmountMap.put(name, stack.getCount());
            }

            if(baitAmountMap.isEmpty()) player.sendMessage(new TextComponentTranslation("notif.fishingmadebetter.baitbox.empty"));
            else {
            	for(Map.Entry<String, Integer> baitEntry : baitAmountMap.entrySet()) {
            		//player.sendMessage(new TextComponentString(String.format("%s: %d", baitEntry.getKey(), baitEntry.getValue())));
                    player.sendMessage((new TextComponentTranslation(baitEntry.getKey()).appendText(String.format(": %d", baitEntry.getValue()))));
            	}
            }
        }
    }
    
    private void updateOnSecond(long worldTime) {
    	if(worldTime > baitUpdateTime) {
            IChunkFishingData chunkFishingData = world.getChunk(getPos()).getCapability(ChunkCapabilityProvider.CHUNK_FISHING_DATA_CAP, null);
            if(chunkFishingData == null) return;

            updateBaitTime(worldTime);

            for (PopulationData populationData : chunkFishingData.getFishes(worldTime).values()) {
                if(!populationData.isHungry(worldTime)) continue;

                FishData fishData = CustomConfigurationHandler.fishDataMap.get(populationData.getFishType());
                if(fishData == null) continue;

                checkBait(fishData.baitItemMap, populationData.getQuantity(), populationData, worldTime, (fishData.maxWeight+fishData.minWeight)/2);
            }
        }
    }

    private boolean isItemStackEqual(ItemStack itemStack, ItemStack other)
    {
        if(!ItemStack.areItemsEqual(itemStack, other)) return false;
        else if (itemStack.getTagCompound() == null && other.getTagCompound() != null) return false;
        else return (itemStack.getTagCompound() == null || itemStack.getTagCompound().equals(other.getTagCompound())) && itemStack.areCapsCompatible(other);
    }
    
    private void updateBaitTime(long worldTime) {
    	baitUpdateTime = worldTime + TimeUtil.minutesToMinecraftTicks(ConfigurationManager.server.baitBoxUpdateInterval);
    }

    private void checkBait(Map<String, Integer[]> baitItemMap, int population, PopulationData populationData, long worldTime, int avgWeight) {
        if(population < 2) return;
        int itemsLeft = Math.min(16, Math.max(1, avgWeight/100));

        for(int i = 0; i < inventory.getSlots(); i++) {
        	if(itemsLeft<=0) break;
            ItemStack itemStack = inventory.getStackInSlot(i);

            if(itemStack.isEmpty()) continue;

            String id = itemStack.getItem().getRegistryName().toString();

            if(ConfigurationManager.server.simpleBait || (baitItemMap.containsKey(id) && Arrays.asList(baitItemMap.get(id)).contains(itemStack.getMetadata()))) {
                int itemsToTake = itemsLeft > itemStack.getCount() ? itemStack.getCount() : itemsLeft;

                itemsLeft -= itemsToTake;

                inventory.extractItem(i, itemsToTake, false);
            }
        }
        if(itemsLeft<=0) populationData.setLastEatenTime(worldTime);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

    @SuppressWarnings("unchecked")
	@Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY ? (T) inventory : super.getCapability(capability, facing);
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newSate) {
        return oldState.getBlock() != newSate.getBlock();
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setLong("baitUpdateTime", baitUpdateTime);
        compound.setTag("inventory", inventory.serializeNBT());

        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        baitUpdateTime = compound.getLong("baitUpdateTime");
        inventory.deserializeNBT(compound.getCompoundTag("inventory"));

        super.readFromNBT(compound);
    }
}
