package net.theawesomegem.fishingmadebetter.common.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.theawesomegem.fishingmadebetter.common.capability.fishing.FishingCapabilityProvider;
import net.theawesomegem.fishingmadebetter.common.capability.fishing.IFishingData;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by TheAwesomeGem on 1/3/2018.
 */
public abstract class ItemFishTracker extends Item {
    public enum TrackingVision {
        Bad,
        Normal,
        Best
    }

    protected final TrackingVision trackingVision;
    protected final int maxDepth;

    public ItemFishTracker(TrackingVision trackingVision, int maxDepth) {
        super();

        this.trackingVision = trackingVision;
        this.maxDepth = maxDepth;
    }

    public TrackingVision getTrackingVision() {
        return trackingVision;
    }

    public int getMaxDepth() {
        return maxDepth;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        if (worldIn.isRemote) {
            return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
        }

        if (!playerIn.isOverWater()) {
            playerIn.sendMessage(new TextComponentString("You must be over water to track fishes."));
            return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
        }

        IFishingData fishingData = playerIn.getCapability(FishingCapabilityProvider.FISHING_DATA_CAP, null);

        if (fishingData == null) {
            return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
        }

        if (fishingData.getTimeSinceTracking() > 0) {
            return new ActionResult<>(EnumActionResult.FAIL, playerIn.getHeldItem(handIn));
        }

        playerIn.sendMessage(new TextComponentString("Started tracking. Might give inaccurate results if you move."));

        fishingData.setTimeSinceTracking(playerIn.world.getTotalWorldTime());

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Max Depth: " + maxDepth);

        String rarityText;

        if (TrackingVision.Bad.equals(getTrackingVision())) {
            rarityText = "Cannot track rare fishes";
        } else if (TrackingVision.Normal.equals(getTrackingVision())) {
            rarityText = "Can track rare fishes";
        } else {
            rarityText = "Can track the most rarest and elusive fishes";
        }

        tooltip.add(rarityText);

        tooltip.add("Right-click whilst on top of the water to track fishes nearby.");
    }

    @SideOnly(Side.CLIENT)
    public void registerItemModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
