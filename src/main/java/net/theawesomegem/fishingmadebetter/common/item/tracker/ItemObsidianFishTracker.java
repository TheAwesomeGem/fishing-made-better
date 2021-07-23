package net.theawesomegem.fishingmadebetter.common.item.tracker;

import net.theawesomegem.fishingmadebetter.common.data.FishData.FishingLiquid;

public class ItemObsidianFishTracker extends ItemFishTracker {
    public ItemObsidianFishTracker() {
        super(TrackingVision.BEST, FishingLiquid.LAVA, 100, "fish_tracker_obsidian");
    }
}
