package net.theawesomegem.fishingmadebetter.common.item;

import net.theawesomegem.fishingmadebetter.ModInfo;

public class ItemIronFishTracker extends ItemFishTracker {
    public ItemIronFishTracker() {
        super(TrackingVision.Bad, 25);

        this.setRegistryName("fish_tracker");
        this.setUnlocalizedName(ModInfo.MOD_ID + ".fish_tracker");
    }
}
