package net.theawesomegem.fishingmadebetter.common.block;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.theawesomegem.fishingmadebetter.common.block.tileentity.TileEntityBaitBox;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by TheAwesomeGem on 1/2/2018.
 */
public class BlockBaitBox extends BlockTileEntityBase<TileEntityBaitBox> {
    public BlockBaitBox() {
        super(Material.WOOD, "baitbox");

        this.setHardness(2.0F);
        this.setSoundType(SoundType.WOOD);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!hand.equals(EnumHand.MAIN_HAND))
            return false;

        player.setActiveHand(EnumHand.MAIN_HAND);
        player.swingArm(EnumHand.MAIN_HAND);

        if (world.isRemote) {
            return false;
        }

        TileEntity tileentity = world.getTileEntity(pos);

        if(tileentity == null){
            return false;
        }

        IItemHandler inventory = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        if(inventory == null){
            return false;
        }

        ItemStack itemStack = player.getHeldItemMainhand();

        if(!itemStack.isEmpty()) {
            int freeSlot = -1;
            int nextSlot = -1;
            int lastSlot = -1;

            for(int i = 0; i < inventory.getSlots(); i++){
                ItemStack stack = inventory.getStackInSlot(i);

                if(stack.isEmpty()){
                    freeSlot = i;
                    break;
                }

                if(isItemStackEqual(itemStack, stack) && (stack.getMaxStackSize() - stack.getCount()) >= itemStack.getCount()){
                    nextSlot = i;
                }

                if(isItemStackEqual(itemStack, stack) && (stack.getCount() < stack.getMaxStackSize())){
                    lastSlot = i;
                }
            }

            if(freeSlot != -1){
                inventory.insertItem(freeSlot, itemStack, false);
                player.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
            } else if(nextSlot != -1){
                inventory.insertItem(nextSlot, itemStack, false);
                player.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
            } else if(lastSlot != -1){
                int count = inventory.insertItem(nextSlot, itemStack, false).getCount();
                ItemStack newStack = itemStack.copy();
                newStack.setCount(count);
                player.setHeldItem(EnumHand.MAIN_HAND, newStack);
            } else {
                player.sendMessage(new TextComponentString("Bait Box is full!"));
            }

            return true;
        } else {
            Map<String, Integer> baitAmountMap = new HashMap<>();

            for (int i = 0; i < inventory.getSlots(); i++) {
                ItemStack stack = inventory.getStackInSlot(i);

                if (stack.isEmpty()) {
                    continue;
                }

                String name = stack.getDisplayName();

                if (baitAmountMap.containsKey(stack.getDisplayName())) {
                    baitAmountMap.put(name, baitAmountMap.get(name) + stack.getCount());
                } else {
                    baitAmountMap.put(name, stack.getCount());
                }
            }

            if(baitAmountMap.isEmpty()){
                player.sendMessage(new TextComponentString("Bait Box is empty!"));
            } else {
                for (Map.Entry<String, Integer> baitEntry : baitAmountMap.entrySet()) {
                    player.sendMessage(new TextComponentString(String.format("%s: %d", baitEntry.getKey(), baitEntry.getValue())));
                }

                player.sendMessage(new TextComponentString(" "));
            }

            return true;
        }
    }


    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntity tileentity = world.getTileEntity(pos);

        IItemHandler inventory = tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

        for (int i = 0; i < inventory.getSlots(); i++) {
            ItemStack stack = inventory.getStackInSlot(i);
            EntityItem entityIn;

            if (!stack.isEmpty() && !world.isRemote) {
                entityIn = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                entityIn.setDefaultPickupDelay();

                world.spawnEntity(entityIn);
            }
        }

        super.breakBlock(world, pos, state);
    }

    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public Class<TileEntityBaitBox> getTileEntityClass() {
        return TileEntityBaitBox.class;
    }

    @Nullable
    @Override
    public TileEntityBaitBox createTileEntity(World world, IBlockState state) {
        return new TileEntityBaitBox();
    }

    private boolean isItemStackEqual(ItemStack itemStack, ItemStack other)
    {
        if (itemStack.getMaxStackSize() != other.getMaxStackSize())
        {
            return false;
        }
        else if (itemStack.getItem() != other.getItem())
        {
            return false;
        }
        else if (itemStack.getItemDamage() != other.getItemDamage())
        {
            return false;
        }
        else if (itemStack.getTagCompound() == null && other.getTagCompound() != null)
        {
            return false;
        }
        else
        {
            return (itemStack.getTagCompound() == null || itemStack.getTagCompound().equals(other.getTagCompound())) && itemStack.areCapsCompatible(other);
        }
    }
}
