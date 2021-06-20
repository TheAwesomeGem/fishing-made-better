package net.theawesomegem.fishingmadebetter.common.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import net.theawesomegem.fishingmadebetter.common.item.fishingrod.ItemBasicFishingRod;
import net.theawesomegem.fishingmadebetter.common.item.fishingrod.ItemBetterFishingRod;
import net.theawesomegem.fishingmadebetter.common.item.fishingrod.ItemDiamondFishingRod;
import net.theawesomegem.fishingmadebetter.common.item.fishingrod.ItemIronFishingRod;

/**
 * Created by TheAwesomeGem on 12/21/2017.
 */
public class ItemManager {
    public static final ItemBetterFishingRod FISHING_ROD_BASIC = new ItemBasicFishingRod();
    public static final ItemBetterFishingRod FISHING_ROD_IRON = new ItemIronFishingRod();
    public static final ItemBetterFishingRod FISHING_ROD_DIAMOND = new ItemDiamondFishingRod();

    public static final ItemKnife KNIFE = new ItemKnife();
    public static final ItemScaleRemover SCALE_REMOVER = new ItemScaleRemover();
    public static final ItemFishSlice FISH_SLICE = new ItemFishSlice();
    public static final ItemIronFishTracker FISH_TRACKER_IRON = new ItemIronFishTracker();
    public static final ItemGoldFishTracker FISH_TRACKER_GOLD = new ItemGoldFishTracker();
    public static final ItemDiamondFishTracker FISH_TRACKER_DIAMOND = new ItemDiamondFishTracker();

    public static final ItemFishBucket FISH_BUCKET = new ItemFishBucket();

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(FISHING_ROD_BASIC, FISHING_ROD_IRON, FISHING_ROD_DIAMOND, KNIFE, SCALE_REMOVER, FISH_SLICE, FISH_TRACKER_IRON, FISH_TRACKER_GOLD, FISH_TRACKER_DIAMOND, FISH_BUCKET);
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels() {
        FISHING_ROD_BASIC.registerItemModel(FISHING_ROD_BASIC);
        FISHING_ROD_IRON.registerItemModel(FISHING_ROD_IRON);
        FISHING_ROD_DIAMOND.registerItemModel(FISHING_ROD_DIAMOND);

        KNIFE.registerItemModel(KNIFE);
        SCALE_REMOVER.registerItemModel(SCALE_REMOVER);
        FISH_SLICE.registerItemModel(FISH_SLICE);

        FISH_TRACKER_IRON.registerItemModel(FISH_TRACKER_IRON);
        FISH_TRACKER_GOLD.registerItemModel(FISH_TRACKER_GOLD);
        FISH_TRACKER_DIAMOND.registerItemModel(FISH_TRACKER_DIAMOND);

        FISH_BUCKET.registerItemModel(FISH_BUCKET);
    }
}
