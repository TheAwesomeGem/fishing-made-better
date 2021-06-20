package net.theawesomegem.fishingmadebetter.common.item;

import net.theawesomegem.fishingmadebetter.ModInfo;

public class ItemDiamondFishTracker extends ItemFishTracker {
    public ItemDiamondFishTracker() {
        super(TrackingVision.Best, 100);

        this.setRegistryName("fish_tracker_diamond");
        this.setUnlocalizedName(ModInfo.MOD_ID + ".fish_tracker_diamond");
    }
}
