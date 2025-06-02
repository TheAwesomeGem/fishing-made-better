package net.theawesomegem.fishingmadebetter.common.entity;

import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.theawesomegem.fishingmadebetter.common.item.fishingrod.ItemBetterFishingRod;

@SuppressWarnings("rawtypes")
public class EntityFMBCustomFishHook extends EntityFishHook {
	static enum State
    {
        FLYING,
        HOOKED_IN_ENTITY,
        BOBBING;
    }
	
	private int ticksInGround;
	private int ticksInAir;
	private float fishApproachingAngle;
	private int lureSpeed;
	private static final DataParameter<Integer> DATA_HOOKED_ENTITY = EntityDataManager.<Integer>createKey(EntityFishHook.class, DataSerializers.VARINT);
	
	final static Field currentStateField = ObfuscationReflectionHelper.findField(EntityFishHook.class, "field_190627_av");//
    final static Field inGroundField = ObfuscationReflectionHelper.findField(EntityFishHook.class, "field_146051_au");
    final static Field ticksCatchableField = ObfuscationReflectionHelper.findField(EntityFishHook.class, "field_146045_ax");//
    final static Field ticksCatchableDelayField = ObfuscationReflectionHelper.findField(EntityFishHook.class, "field_146038_az");//
    final static Field ticksCaughtDelayField = ObfuscationReflectionHelper.findField(EntityFishHook.class, "field_146040_ay");//
    
    //now thats what i call scuffed
    final static Class stateEnumField = EntityFishHook.class.getDeclaredClasses()[0];
    final static Object objFlying = stateEnumField.getEnumConstants()[0];
    final static Object objHooked = stateEnumField.getEnumConstants()[1];
    final static Object objBobbing = stateEnumField.getEnumConstants()[2];
    
    @SideOnly(Side.CLIENT)
    public EntityFMBCustomFishHook(World worldIn, EntityPlayer p_i47290_2_, double x, double y, double z) {
        super(worldIn, p_i47290_2_, x, y, z);
    }

    public EntityFMBCustomFishHook(World worldIn, EntityPlayer fishingPlayer) {
        super(worldIn, fishingPlayer);
    }
    
    public EntityFMBCustomFishHook(World worldIn) {
        super(worldIn, null);
    }
    
    public float getLiquidHeight(World worldIn, BlockPos blockpos) {
    	IBlockState iblockstate = worldIn.getBlockState(blockpos);
    	
    	if(iblockstate.getMaterial() == MaterialLiquid.WATER) {
    		return BlockLiquid.getBlockLiquidHeight(iblockstate, worldIn, blockpos);
    	}
    	return 0.0f;
    }
    
    public double getLiquidMotion() {
    	return 0.2D;
    }
    
    public SoundEvent getSoundEvent() {
    	return SoundEvents.ENTITY_BOBBER_SPLASH;
    }
    
    public EnumParticleTypes getBubbleParticle() {
    	return EnumParticleTypes.WATER_BUBBLE;
    }
    public EnumParticleTypes getWakeParticle() {
    	return EnumParticleTypes.WATER_WAKE;
    }
    public EnumParticleTypes getSplashParticle() {
    	return EnumParticleTypes.WATER_SPLASH;
    }
    
    @Override
    public void setLureSpeed(int p_191516_1_)
    {
        this.lureSpeed = p_191516_1_;
    }
    
    @Override
    protected void entityInit()
    {
        this.getDataManager().register(DATA_HOOKED_ENTITY, Integer.valueOf(0));
    }

    @Override
    public void notifyDataManagerChange(DataParameter<?> key)
    {
        if (DATA_HOOKED_ENTITY.equals(key))
        {
            int i = ((Integer)this.getDataManager().get(DATA_HOOKED_ENTITY)).intValue();
            this.caughtEntity = i > 0 ? this.world.getEntityByID(i - 1) : null;
        }

        super.notifyDataManagerChange(key);
    }
    
    @Override
    public void onUpdate() {
    	if(!this.world.isRemote) this.setFlag(6, this.isGlowing());
    	this.onEntityUpdate();//Should bypass FishHook update?
    	
        if(this.getAngler() == null) this.setDead();
        else if(this.world.isRemote || !shouldStopFishing()) {
            if(getInGround(this)) {
                this.ticksInGround++;

                if(this.ticksInGround >= 1200) {
                    this.setDead();
                    return;
                }
            }
            
            BlockPos blockpos = new BlockPos(this);
            float f = getLiquidHeight(this.world, blockpos);
            
            if(getHookState(this) == State.FLYING) {
                if(this.caughtEntity != null) {
                    this.motionX = 0.0D;
                    this.motionY = 0.0D;
                    this.motionZ = 0.0D;
                    setHookState(this, (Enum) objHooked);
                    return;
                }

                if(f > 0.0F) {
                    this.motionX *= 0.3D;
                    this.motionY *= 0.2D;
                    this.motionZ *= 0.3D;
                    setHookState(this, (Enum) objBobbing);
                    return;
                }

                if(!this.world.isRemote) checkCollision();

                if(!getInGround(this) && !this.onGround && !this.collidedHorizontally) this.ticksInAir++;
                else {
                    this.ticksInAir = 0;
                    this.motionX = 0.0D;
                    this.motionY = 0.0D;
                    this.motionZ = 0.0D;
                }
            }
            else {
                if(getHookState(this) == State.HOOKED_IN_ENTITY) {
                    if(this.caughtEntity != null) {
                        if(this.caughtEntity.isDead) {
                            this.caughtEntity = null;
                            setHookState(this, (Enum) objFlying);
                        }
                        else {
                            this.posX = this.caughtEntity.posX;
                            double d2 = (double)this.caughtEntity.height;
                            this.posY = this.caughtEntity.getEntityBoundingBox().minY + d2 * 0.8D;
                            this.posZ = this.caughtEntity.posZ;
                            this.setPosition(this.posX, this.posY, this.posZ);
                        }
                    }
                    return;
                }
                else if(getHookState(this) == State.BOBBING) {
                    this.motionX *= 0.9D;
                    this.motionZ *= 0.9D;
                    double d0 = this.posY + this.motionY - (double)blockpos.getY() - (double)f;
                    
                    if(Math.abs(d0) < 0.1D) d0 += Math.signum(d0) * 0.1D;
                    
                    this.motionY -= d0 * (double)this.rand.nextFloat() * 0.2D;

                    if (!this.world.isRemote && f > 0.0F) this.catchingFish(blockpos);
                }
            }

            if(getLiquidHeight(this.world, blockpos) == 0) this.motionY -= 0.03D;

            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            updateRotation();
            this.motionX *= 0.92D;
            this.motionY *= 0.92D;
            this.motionZ *= 0.92D;
            this.setPosition(this.posX, this.posY, this.posZ);
        }
    }
    
    private void catchingFish(BlockPos pos) {
        WorldServer worldserver = (WorldServer)this.world;
        int i = 1;
        BlockPos blockpos = pos.up();

        if(this.rand.nextFloat() < 0.25F && this.world.isRainingAt(blockpos)) ++i;

        if(this.rand.nextFloat() < 0.5F && !this.world.canSeeSky(blockpos)) --i;

        if(getTicksCatchable(this) > 0) {
            setTicksCatchable(this, getTicksCatchable(this)-1);

            if(getTicksCatchable(this) <= 0) {
                setTicksCaughtDelay(this, 0);
                setTicksCatchableDelay(this, 0);
            }
            else{
            	this.motionY -= getLiquidMotion() * (double)this.rand.nextFloat() * (double)this.rand.nextFloat();
            }
        }
        else if(getTicksCatchableDelay(this) > 0) {
            setTicksCatchableDelay(this, getTicksCatchableDelay(this)-i);

            if(getTicksCatchableDelay(this) > 0) {
                this.fishApproachingAngle = (float)((double)this.fishApproachingAngle + this.rand.nextGaussian() * 4.0D);
                float f = this.fishApproachingAngle * 0.017453292F;
                float f1 = MathHelper.sin(f);
                float f2 = MathHelper.cos(f);
                double d0 = this.posX + (double)(f1 * (float)getTicksCatchableDelay(this) * 0.1F);
                double d1 = (double)((float)MathHelper.floor(this.getEntityBoundingBox().minY) + 1.0F);
                double d2 = this.posZ + (double)(f2 * (float)getTicksCatchableDelay(this) * 0.1F);
                
                BlockPos newPos = new BlockPos(d0, d1 - 1.0D, d2);
                if(getLiquidHeight(worldserver, newPos) != 0) {
                    if(this.rand.nextFloat() < 0.15F) worldserver.spawnParticle(getBubbleParticle(), d0, d1 - 0.10000000149011612D, d2, 1, (double)f1, 0.1D, (double)f2, 0.0D);

                    float f3 = f1 * 0.04F;
                    float f4 = f2 * 0.04F;
                    worldserver.spawnParticle(getWakeParticle(), d0, d1, d2, 0, (double)f4, 0.01D, (double)(-f3), 1.0D);
                    worldserver.spawnParticle(getWakeParticle(), d0, d1, d2, 0, (double)(-f4), 0.01D, (double)f3, 1.0D);
                }
            }
            else {
                this.motionY = (double)(-0.4F * MathHelper.nextFloat(this.rand, 0.6F, 1.0F));
                this.playSound(getSoundEvent(), 0.25F, 1.0F + (this.rand.nextFloat() - this.rand.nextFloat()) * 0.4F);
                double d3 = this.getEntityBoundingBox().minY + 0.5D;
                worldserver.spawnParticle(getBubbleParticle(), this.posX, d3, this.posZ, (int)(1.0F + this.width * 20.0F), (double)this.width, 0.0D, (double)this.width, 0.20000000298023224D);
                worldserver.spawnParticle(getWakeParticle(), this.posX, d3, this.posZ, (int)(1.0F + this.width * 20.0F), (double)this.width, 0.0D, (double)this.width, 0.20000000298023224D);
                setTicksCatchable(this, MathHelper.getInt(this.rand, 20, 40));
            }
        }
        else if(getTicksCaughtDelay(this) > 0) {
        	setTicksCaughtDelay(this, getTicksCaughtDelay(this)-i);
            float f5 = 0.15F;

            if(getTicksCaughtDelay(this) < 20) f5 = (float)((double)f5 + (double)(20 - getTicksCaughtDelay(this)) * 0.05D);
            else if (getTicksCaughtDelay(this) < 40) f5 = (float)((double)f5 + (double)(40 - getTicksCaughtDelay(this)) * 0.02D);
            else if (getTicksCaughtDelay(this) < 60) f5 = (float)((double)f5 + (double)(60 - getTicksCaughtDelay(this)) * 0.01D);

            if(this.rand.nextFloat() < f5) {
                float f6 = MathHelper.nextFloat(this.rand, 0.0F, 360.0F) * 0.017453292F;
                float f7 = MathHelper.nextFloat(this.rand, 25.0F, 60.0F);
                double d4 = this.posX + (double)(MathHelper.sin(f6) * f7 * 0.1F);
                double d5 = (double)((float)MathHelper.floor(this.getEntityBoundingBox().minY) + 1.0F);
                double d6 = this.posZ + (double)(MathHelper.cos(f6) * f7 * 0.1F);
                
                BlockPos newPos = new BlockPos((int) d4, (int) d5 - 1, (int) d6);
                if(getLiquidHeight(worldserver, newPos) != 0) worldserver.spawnParticle(getSplashParticle(), d4, d5, d6, 2 + this.rand.nextInt(2), 0.10000000149011612D, 0.0D, 0.10000000149011612D, 0.0D);
            }

            if(getTicksCaughtDelay(this) <= 0) {
                this.fishApproachingAngle = MathHelper.nextFloat(this.rand, 0.0F, 360.0F);
                setTicksCatchableDelay(this, MathHelper.getInt(this.rand, 20, 80));
            }
        }
        else {
        	setTicksCaughtDelay(this, MathHelper.getInt(this.rand, 100, 600));
        	setTicksCaughtDelay(this, getTicksCaughtDelay(this)-this.lureSpeed * 20 * 5);
        }
    }
    
    private boolean shouldStopFishing() {
        ItemStack itemstack = this.getAngler().getHeldItemMainhand();
        ItemStack itemstack1 = this.getAngler().getHeldItemOffhand();
        boolean flag = itemstack.getItem() instanceof ItemBetterFishingRod;
        boolean flag1 = itemstack1.getItem() instanceof ItemBetterFishingRod;

        if(!this.getAngler().isDead && this.getAngler().isEntityAlive() && flag != flag1 && this.getDistanceSq(this.getAngler()) <= 1024.0D) return false;
        else {
            this.setDead();
            return true;
        }
    }
    
    private void updateRotation() {
        float f = MathHelper.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
        this.rotationYaw = (float)(MathHelper.atan2(this.motionX, this.motionZ) * (180D / Math.PI));

        for(this.rotationPitch = (float)(MathHelper.atan2(this.motionY, (double)f) * (180D / Math.PI)); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F) ;

        while(this.rotationPitch - this.prevRotationPitch >= 180.0F)  this.prevRotationPitch += 360.0F;

        while(this.rotationYaw - this.prevRotationYaw < -180.0F) this.prevRotationYaw -= 360.0F;

        while(this.rotationYaw - this.prevRotationYaw >= 180.0F) this.prevRotationYaw += 360.0F;

        this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
        this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
    }
    
    private void checkCollision() {
        Vec3d vec3d = new Vec3d(this.posX, this.posY, this.posZ);
        Vec3d vec3d1 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
        RayTraceResult raytraceresult = this.world.rayTraceBlocks(vec3d, vec3d1, false, true, false);
        vec3d = new Vec3d(this.posX, this.posY, this.posZ);
        vec3d1 = new Vec3d(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);

        if(raytraceresult != null) vec3d1 = new Vec3d(raytraceresult.hitVec.x, raytraceresult.hitVec.y, raytraceresult.hitVec.z);

        Entity entity = null;
        List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox().expand(this.motionX, this.motionY, this.motionZ).grow(1.0D));
        double d0 = 0.0D;

        for(Entity entity1 : list) {
            if(this.canBeHooked(entity1) && (entity1 != this.getAngler() || this.ticksInAir >= 5)) {
                AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow(0.30000001192092896D);
                RayTraceResult raytraceresult1 = axisalignedbb.calculateIntercept(vec3d, vec3d1);

                if(raytraceresult1 != null) {
                    double d1 = vec3d.squareDistanceTo(raytraceresult1.hitVec);

                    if(d1 < d0 || d0 == 0.0D) {
                        entity = entity1;
                        d0 = d1;
                    }
                }
            }
        }

        if(entity != null) raytraceresult = new RayTraceResult(entity);

        if(raytraceresult != null && raytraceresult.typeOfHit != RayTraceResult.Type.MISS) {
            if(raytraceresult.typeOfHit == RayTraceResult.Type.ENTITY) {
                this.caughtEntity = raytraceresult.entityHit;
                setHookedEntity();
            }
            else setInGround(this, true);
        }
    }
    
    private void setHookedEntity() {
        this.getDataManager().set(DATA_HOOKED_ENTITY, Integer.valueOf(this.caughtEntity.getEntityId() + 1));
    }
    
    private State getHookState(EntityFishHook hook) {
    	if(currentStateField == null) return null;
    	
        try {
        	currentStateField.setAccessible(true);
            Enum hookEnum = (Enum) currentStateField.get(hook);
            int hookStateOrdinal = hookEnum.ordinal();
            currentStateField.setAccessible(false);
            return State.values()[hookStateOrdinal];
        } 
        catch(IllegalAccessException e) {
        	currentStateField.setAccessible(false);
            e.printStackTrace();
            return null;
        }
    }
    private void setHookState(EntityFishHook hook, Enum state) {
    	if(currentStateField == null) return;
    	
        try {
        	currentStateField.setAccessible(true);
            currentStateField.set(hook, state);
            currentStateField.setAccessible(false);
            return;
        } 
        catch(IllegalAccessException e) {
        	currentStateField.setAccessible(false);
            e.printStackTrace();
            return;
        }
    }
    
    private Boolean getInGround(EntityFishHook hook) {
    	if(inGroundField == null) return null;
    	
        try {
        	inGroundField.setAccessible(true);
            boolean inGround = inGroundField.getBoolean(hook);
            inGroundField.setAccessible(false);
            return inGround;
        } 
        catch(IllegalAccessException e) {
        	inGroundField.setAccessible(false);
            e.printStackTrace();
            return null;
        }
    }
    private void setInGround(EntityFishHook hook, Boolean value) {
    	if(inGroundField == null) return;
    	
        try {
        	inGroundField.setAccessible(true);
        	inGroundField.setBoolean(hook, value);
        	inGroundField.setAccessible(false);
            return;
        } 
        catch(IllegalAccessException e) {
        	inGroundField.setAccessible(false);
            e.printStackTrace();
            return;
        }
    }
    
    private void setTicksCatchable(EntityFishHook hook, int ticks) {
        if(ticksCatchableField == null) return;

        try {
        	ticksCatchableField.setAccessible(true);
        	ticksCatchableField.setInt(hook, ticks);
        	ticksCatchableField.setAccessible(false);
        } 
        catch(IllegalArgumentException | IllegalAccessException e) {
        	ticksCatchableField.setAccessible(false);
            e.printStackTrace();
        }
    }
    private int getTicksCatchable(EntityFishHook hook) {
        if(ticksCatchableField == null) return 0;

        try {
        	ticksCatchableField.setAccessible(true);
            int ticks = ticksCatchableField.getInt(hook);
            ticksCatchableField.setAccessible(false);
            return ticks;
        } 
        catch(IllegalArgumentException | IllegalAccessException e) {
        	ticksCatchableField.setAccessible(false);
            e.printStackTrace();
            return 0;
        }
    }
    
    private void setTicksCatchableDelay(EntityFishHook hook, int ticks) {
        if(ticksCatchableDelayField == null) return;

        try {
        	ticksCatchableDelayField.setAccessible(true);
        	ticksCatchableDelayField.setInt(hook, ticks);
        	ticksCatchableDelayField.setAccessible(false);
        } 
        catch(IllegalArgumentException | IllegalAccessException e) {
        	ticksCatchableDelayField.setAccessible(false);
            e.printStackTrace();
        }
    }
    private int getTicksCatchableDelay(EntityFishHook hook) {
        if(ticksCatchableDelayField == null) return 0;

        try {
        	ticksCatchableDelayField.setAccessible(true);
            int ticks = ticksCatchableDelayField.getInt(hook);
            ticksCatchableDelayField.setAccessible(false);
            return ticks;
        } 
        catch(IllegalArgumentException | IllegalAccessException e) {
        	ticksCatchableDelayField.setAccessible(false);
            e.printStackTrace();
            return 0;
        }
    }
    
    private void setTicksCaughtDelay(EntityFishHook hook, int ticks) {
        if(ticksCaughtDelayField == null) return;

        try {
        	ticksCaughtDelayField.setAccessible(true);
        	ticksCaughtDelayField.setInt(hook, ticks);
        	ticksCaughtDelayField.setAccessible(false);
        } 
        catch(IllegalArgumentException | IllegalAccessException e) {
        	ticksCaughtDelayField.setAccessible(false);
            e.printStackTrace();
        }
    }
    private int getTicksCaughtDelay(EntityFishHook hook) {
        if(ticksCaughtDelayField == null) return 0;

        try {
        	ticksCaughtDelayField.setAccessible(true);
            int ticks = ticksCaughtDelayField.getInt(hook);
            ticksCaughtDelayField.setAccessible(false);
            return ticks;
        } 
        catch(IllegalArgumentException | IllegalAccessException e) {
        	ticksCaughtDelayField.setAccessible(false);
            e.printStackTrace();
            return 0;
        }
    }
}
