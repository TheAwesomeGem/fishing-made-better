package net.theawesomegem.fishingmadebetter.common.item.tracker;

import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.theawesomegem.fishingmadebetter.ModInfo;
import net.theawesomegem.fishingmadebetter.common.capability.fishing.FishingCapabilityProvider;
import net.theawesomegem.fishingmadebetter.common.capability.fishing.IFishingData;
import net.theawesomegem.fishingmadebetter.common.data.FishData.FishingLiquid;
import net.theawesomegem.fishingmadebetter.common.registry.FMBCreativeTab;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by TheAwesomeGem on 1/3/2018.
 */
public abstract class ItemFishTracker extends Item {
    public enum TrackingVision {
        BAD,
        NORMAL,
        BEST
        ;
    	
        public static int getMinRarity(TrackingVision enumTracking) {
        	switch(enumTracking) {
        	case BAD	:	return 40;
        	case NORMAL :	return 20;
        	case BEST	:	return 0;
        	default		:	return 40;
        	}
        }
    }
    
    protected final TrackingVision trackingVision;
    protected final FishingLiquid trackingLiquid;
    protected final int maxDepth;

    public ItemFishTracker(TrackingVision trackingVision, FishingLiquid trackingLiquid, int maxDepth, String name) {
        super();

        this.setCreativeTab(FMBCreativeTab.instance);
        this.setRegistryName(name);
        this.setUnlocalizedName(ModInfo.MOD_ID + "." + name);
        this.trackingVision = trackingVision;
        this.trackingLiquid = trackingLiquid;
        this.maxDepth = maxDepth;
    }

    public TrackingVision getTrackingVision() {
        return trackingVision;
    }

    public int getMaxDepth() {
        return maxDepth;
    }
    
    public FishingLiquid getLiquidEnum() {
    	return trackingLiquid;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
    	ItemStack itemstack = playerIn.getHeldItem(handIn);
        if(worldIn.isRemote) return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);

        IFishingData fishingData = playerIn.getCapability(FishingCapabilityProvider.FISHING_DATA_CAP, null);
        if(fishingData == null) return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);

        if(fishingData.getTimeSinceTracking() > 0) return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);

        RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);
        if(raytraceresult == null) return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
        else if(raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) return new ActionResult<ItemStack>(EnumActionResult.PASS, itemstack);
        
        BlockPos pos = raytraceresult.getBlockPos();
        IBlockState blockClicked = worldIn.getBlockState(pos);
        
        if(trackingLiquid.equals(FishingLiquid.VOID)) {
        	if(pos.getY() > 3) {
        		playerIn.sendMessage(new TextComponentString("This probe can only be used on blocks near the Void."));
        		return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
        	}
        }
        else if(trackingLiquid.equals(FishingLiquid.WATER)) {
        	if(blockClicked.getMaterial() != Material.WATER) {
        		playerIn.sendMessage(new TextComponentString("This probe can only work in Water."));
        		return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
        	}
        }
        else if(trackingLiquid.equals(FishingLiquid.LAVA)) {
        	if(blockClicked.getMaterial() != Material.LAVA) {
        		playerIn.sendMessage(new TextComponentString("This probe can only work in Lava."));
        		return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
        	}
        } 
        
        if(blockClicked.getMaterial() == MaterialLiquid.WATER) {
        	int waterCount = 0;
        	for(BlockPos posit : BlockPos.getAllInBox(pos.getX()-2, pos.getY()-3, pos.getZ()-2, pos.getX()+2, pos.getY(), pos.getZ()+2)) {
        		Material mat = worldIn.getBlockState(posit).getMaterial();
        		if(mat == MaterialLiquid.WATER) waterCount++;
        		if(waterCount >= 25) break;
        	}
        	if(waterCount < 25) {
        		playerIn.sendMessage(new TextComponentString("It is too shallow at this location for the probe to work properly."));
        		return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
        	}
        }
        if(blockClicked.getMaterial() == MaterialLiquid.LAVA) {
        	int lavaCount = 0;
        	for(BlockPos posit : BlockPos.getAllInBox(pos.getX()-2, pos.getY()-3, pos.getZ()-2, pos.getX()+2, pos.getY(), pos.getZ()+2)) {
        		Material mat = worldIn.getBlockState(posit).getMaterial();
        		if(mat == MaterialLiquid.LAVA) lavaCount++;
        		if(lavaCount >= 25) break;
        	}
        	if(lavaCount < 25) {
        		playerIn.sendMessage(new TextComponentString("It is too shallow at this location for the probe to work properly."));
        		return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
        	}
        }

        playerIn.sendMessage(new TextComponentString("Started tracking. Might give inaccurate results if you move."));

        fishingData.setTimeSinceTracking(playerIn.world.getTotalWorldTime());

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Max Depth: " + maxDepth);

        String rarityText;
        if(TrackingVision.BAD.equals(getTrackingVision())) 			rarityText = "Cannot detect rare fishes";
        else if (TrackingVision.NORMAL.equals(getTrackingVision())) rarityText = "Can detect rare fishes";
        else if (TrackingVision.BEST.equals(getTrackingVision())) 	rarityText = "Can detect the rarest and most elusive fishes";
        else rarityText = "Tracking value not found";
        tooltip.add(rarityText);
        
        String liquidText;
        if(FishingLiquid.WATER.equals(getLiquidEnum())) 			liquidText = "Can probe Water";
        else if (FishingLiquid.LAVA.equals(getLiquidEnum())) 		liquidText = "Can probe Lava";
        else if (FishingLiquid.VOID.equals(getLiquidEnum())) 		liquidText = "Can probe the Void";
        else liquidText = "Can probe anything";
        tooltip.add(liquidText);
        
        tooltip.add("Right-click to probe for fish");
    }

    @SideOnly(Side.CLIENT)
    public void registerItemModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(ModInfo.MOD_ID + ":tracker/" + getRegistryName().getResourcePath(), "inventory"));
    }
}
