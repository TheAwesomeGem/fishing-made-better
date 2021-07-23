package net.theawesomegem.fishingmadebetter.common.item.tracker;

import net.theawesomegem.fishingmadebetter.common.data.FishData.FishingLiquid;

public class ItemVoidFishTracker extends ItemFishTracker {
    public ItemVoidFishTracker() {
        super(TrackingVision.BEST, FishingLiquid.VOID, 100, "fish_tracker_void");
    }
}
