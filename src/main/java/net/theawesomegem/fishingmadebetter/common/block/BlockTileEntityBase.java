package net.theawesomegem.fishingmadebetter.common.block;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * Created by TheAwesomeGem on 12/23/2017.
 */
public abstract class BlockTileEntityBase<TE extends TileEntity> extends BlockBase {
    public BlockTileEntityBase(Material material, String blockName) {
        super(material, blockName);
    }

    public abstract Class<TE> getTileEntityClass();

    @SuppressWarnings("unchecked")
	public TE getTileEntity(IBlockAccess world, BlockPos pos) {
        return (TE) world.getTileEntity(pos);
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Nullable
    @Override
    public abstract TE createTileEntity(World world, IBlockState state);
}
