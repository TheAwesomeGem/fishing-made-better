package net.theawesomegem.fishingmadebetter.common.entity;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFMBLavaFishHook extends EntityFMBCustomFishHook {
	
    @SideOnly(Side.CLIENT)
    public EntityFMBLavaFishHook(World worldIn, EntityPlayer p_i47290_2_, double x, double y, double z) {
        super(worldIn, p_i47290_2_, x, y, z);
        this.isImmuneToFire = true;
    }

    public EntityFMBLavaFishHook(World worldIn, EntityPlayer fishingPlayer) {
        super(worldIn, fishingPlayer);
        this.isImmuneToFire = true;
    }
    
    public EntityFMBLavaFishHook(World worldIn) {
    	super(worldIn);
    	this.isImmuneToFire = true;
    }
    
    @Override
    public boolean isInWater() {
    	return this.isInLava();
    }
    
    @Override
    public float getLiquidHeight(World worldIn, BlockPos blockpos) {
    	IBlockState iblockstate = worldIn.getBlockState(blockpos);
    	if(iblockstate.getMaterial() == MaterialLiquid.LAVA) return BlockLiquid.getBlockLiquidHeight(iblockstate, worldIn, blockpos);
    	return 0.0f;
    }
    
    @Override
    public double getLiquidMotion() {
    	return 0.08D;
    }
    
    @Override
    public SoundEvent getSoundEvent() {
    	return SoundEvents.ENTITY_ENDEREYE_DEATH;
    }
    
    @Override
    public EnumParticleTypes getBubbleParticle() {
    	return EnumParticleTypes.SMOKE_NORMAL;
    }
    @Override
    public EnumParticleTypes getWakeParticle() {
    	return EnumParticleTypes.SMOKE_LARGE;
    }
    @Override
    public EnumParticleTypes getSplashParticle() {
    	return EnumParticleTypes.LAVA;
    }
}
