package net.theawesomegem.fishingmadebetter.common.item;

import net.theawesomegem.fishingmadebetter.ModInfo;

public class ItemGoldFishTracker extends ItemFishTracker {
    public ItemGoldFishTracker() {
        super(TrackingVision.Normal, 50);

        this.setRegistryName("fish_tracker_gold");
        this.setUnlocalizedName(ModInfo.MOD_ID + ".fish_tracker_gold");
    }
}
