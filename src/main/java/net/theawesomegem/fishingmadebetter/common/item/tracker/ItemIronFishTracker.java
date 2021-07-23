package net.theawesomegem.fishingmadebetter.common.item.tracker;

import net.theawesomegem.fishingmadebetter.common.data.FishData.FishingLiquid;

public class ItemIronFishTracker extends ItemFishTracker {
    public ItemIronFishTracker() {
        super(TrackingVision.BAD, FishingLiquid.WATER, 50, "fish_tracker_iron");
    }
}
