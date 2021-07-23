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
import net.minecraft.world.World;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.theawesomegem.fishingmadebetter.common.block.tileentity.TileEntityBaitBox;
import net.theawesomegem.fishingmadebetter.common.registry.FMBCreativeTab;

import javax.annotation.Nullable;
import java.util.Random;

/**
 * Created by TheAwesomeGem on 1/2/2018.
 */
public class BlockBaitBox extends BlockTileEntityBase<TileEntityBaitBox> {
    public BlockBaitBox() {
        super(Material.WOOD, "baitbox");

        this.setCreativeTab(FMBCreativeTab.instance);
        this.setHardness(2.0F);
        this.setSoundType(SoundType.WOOD);
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (world.isRemote || !hand.equals(EnumHand.MAIN_HAND)) return true;

        TileEntity tileEntity = world.getTileEntity(pos);
        if(tileEntity instanceof TileEntityBaitBox) {
        	((TileEntityBaitBox)tileEntity).handleRightClick(world, pos, state, player);
        }
        
        return true;
    }

    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(this);
    }

    @Override
    public void breakBlock(World world, BlockPos pos, IBlockState state) {
        TileEntity tileEntity = world.getTileEntity(pos);
        if(tileEntity instanceof TileEntityBaitBox) {
        	IItemHandler inventory = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);

            for(int i = 0; i < inventory.getSlots(); i++) {
                ItemStack stack = inventory.getStackInSlot(i);
                EntityItem entityIn;

                if(!stack.isEmpty() && !world.isRemote) {
                    entityIn = new EntityItem(world, pos.getX(), pos.getY(), pos.getZ(), stack);
                    entityIn.setDefaultPickupDelay();

                    world.spawnEntity(entityIn);
                }
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
}
