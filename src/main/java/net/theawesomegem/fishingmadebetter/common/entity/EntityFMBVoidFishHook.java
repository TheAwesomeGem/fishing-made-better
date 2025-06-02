package net.theawesomegem.fishingmadebetter.common.entity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityFMBVoidFishHook extends EntityFMBCustomFishHook {
	
    @SideOnly(Side.CLIENT)
    public EntityFMBVoidFishHook(World worldIn, EntityPlayer p_i47290_2_, double x, double y, double z) {
        super(worldIn, p_i47290_2_, x, y, z);
    }

    public EntityFMBVoidFishHook(World worldIn, EntityPlayer fishingPlayer) {
        super(worldIn, fishingPlayer);
    }
    
    public EntityFMBVoidFishHook(World worldIn) {
    	super(worldIn);
    }
    
    @Override
    public boolean isInWater() {
    	return this.posY<0;
    }
    
    @Override
    public float getLiquidHeight(World worldIn, BlockPos blockpos) {
    	if(this.posY<0) return 0.8888888f;
    	return 0.0f;
    }
    
    @Override
    public double getLiquidMotion() {
    	return 0.08D;
    }
    
    @Override
    public SoundEvent getSoundEvent() {
    	return SoundEvents.ITEM_BUCKET_FILL_LAVA;
    }
    
    @Override
    public EnumParticleTypes getBubbleParticle() {
    	return EnumParticleTypes.DRAGON_BREATH;
    }
    @Override
    public EnumParticleTypes getWakeParticle() {
    	return EnumParticleTypes.ENCHANTMENT_TABLE;
    }
    @Override
    public EnumParticleTypes getSplashParticle() {
    	return EnumParticleTypes.PORTAL;
    }
}
