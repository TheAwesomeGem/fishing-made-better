package net.theawesomegem.fishingmadebetter.common.block.tileentity;

import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.theawesomegem.fishingmadebetter.common.capability.world.ChunkCapabilityProvider;
import net.theawesomegem.fishingmadebetter.common.capability.world.IChunkFishingData;
import net.theawesomegem.fishingmadebetter.common.capability.world.PopulationData;
import net.theawesomegem.fishingmadebetter.common.configuration.ConfigurationManager;
import net.theawesomegem.fishingmadebetter.common.configuration.CustomFishConfigurationHandler;
import net.theawesomegem.fishingmadebetter.common.data.FishData;
import net.theawesomegem.fishingmadebetter.util.TimeUtil;

import javax.annotation.Nullable;
import java.util.Map;

/**
 * Created by TheAwesomeGem on 1/2/2018.
 */
public class TileEntityBaitBox extends TileEntity implements ITickable {
    private long baitUpdateTime;
    private ItemStackHandler inventory = new ItemStackHandler(36);

    @Override
    public void onLoad() {
        if (world.isRemote) {
            return;
        }

        baitUpdateTime = world.getTotalWorldTime() + TimeUtil.minutesToMinecraftTicks(ConfigurationManager.baitBoxUpdateInterval);
    }

    @Override
    public void update() {
        if (world.getBlockState(pos).getBlock() != this.getBlockType()) {
            return;
        }

        if (world.isRemote) {
            return;
        }

        if (world.getTotalWorldTime() > baitUpdateTime) {
            IChunkFishingData chunkFishingData = world.getChunkFromBlockCoords(getPos()).getCapability(ChunkCapabilityProvider.CHUNK_FISHING_DATA_CAP, null);

            if (chunkFishingData == null) {
                return;
            }

            baitUpdateTime = world.getTotalWorldTime() + TimeUtil.minutesToMinecraftTicks(ConfigurationManager.baitBoxUpdateInterval);
            long currentTime = world.getTotalWorldTime();

            for (PopulationData populationData : chunkFishingData.getFishes(currentTime).values()) {
                if(!populationData.isHungry(currentTime)){
                    continue;
                }

                FishData fishData = CustomFishConfigurationHandler.fishDataMap.get(populationData.getFishType());

                if (fishData == null) {
                    continue;
                }

                checkBait(fishData.baitItemMap, populationData.getQuantity(), populationData);
            }
        }
    }

    private void checkBait(Map<String, Integer> baitItemMap, int population, PopulationData populationData) {
        int itemsLeft = population;

        for (int i = 0; i < inventory.getSlots(); i++) {
            if (itemsLeft < 1) {
                return;
            }

            ItemStack itemStack = inventory.getStackInSlot(i);

            if (itemStack.isEmpty()) {
                continue;
            }

            Item item = itemStack.getItem();
            String id = Item.REGISTRY.getNameForObject(item).toString();

            if (baitItemMap.containsKey(id) && itemStack.getMetadata() == baitItemMap.get(id)) {
                int itemsToTake = itemsLeft > itemStack.getMaxStackSize() ? itemStack.getMaxStackSize() : itemsLeft;

                itemsLeft -= itemsToTake;

                inventory.extractItem(i, itemsToTake, false);

                populationData.setLastEatenTime(world.getTotalWorldTime());
            }
        }
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY || super.hasCapability(capability, facing);
    }

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
