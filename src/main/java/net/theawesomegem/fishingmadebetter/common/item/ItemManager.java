package net.theawesomegem.fishingmadebetter.common.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import net.theawesomegem.fishingmadebetter.common.item.fishingrod.ItemWoodFishingRod;
import net.theawesomegem.fishingmadebetter.common.item.fishslice.ItemCookedFishSlice;
import net.theawesomegem.fishingmadebetter.common.item.fishslice.ItemFishSlice;
import net.theawesomegem.fishingmadebetter.common.item.fishslice.ItemRawFishSlice;
import net.theawesomegem.fishingmadebetter.common.item.scalingknife.ItemDiamondScalingKnife;
import net.theawesomegem.fishingmadebetter.common.item.scalingknife.ItemIronScalingKnife;
import net.theawesomegem.fishingmadebetter.common.item.scalingknife.ItemScalingKnife;
import net.theawesomegem.fishingmadebetter.common.item.scalingknife.ItemWoodScalingKnife;
import net.theawesomegem.fishingmadebetter.common.item.tracker.ItemDiamondFishTracker;
import net.theawesomegem.fishingmadebetter.common.item.tracker.ItemFishTracker;
import net.theawesomegem.fishingmadebetter.common.item.tracker.ItemGoldFishTracker;
import net.theawesomegem.fishingmadebetter.common.item.tracker.ItemIronFishTracker;
import net.theawesomegem.fishingmadebetter.common.item.tracker.ItemObsidianFishTracker;
import net.theawesomegem.fishingmadebetter.common.item.tracker.ItemVoidFishTracker;
import net.theawesomegem.fishingmadebetter.common.item.attachment.bobber.ItemBobber;
import net.theawesomegem.fishingmadebetter.common.item.attachment.bobber.ItemBobberBasic;
import net.theawesomegem.fishingmadebetter.common.item.attachment.bobber.ItemBobberHeavy;
import net.theawesomegem.fishingmadebetter.common.item.attachment.bobber.ItemBobberLightweight;
import net.theawesomegem.fishingmadebetter.common.item.attachment.bobber.ItemBobberObsidian;
import net.theawesomegem.fishingmadebetter.common.item.attachment.bobber.ItemBobberVoid;
import net.theawesomegem.fishingmadebetter.common.item.attachment.hook.ItemHook;
import net.theawesomegem.fishingmadebetter.common.item.attachment.hook.ItemHookBarbed;
import net.theawesomegem.fishingmadebetter.common.item.attachment.hook.ItemHookBasic;
import net.theawesomegem.fishingmadebetter.common.item.attachment.hook.ItemHookFatty;
import net.theawesomegem.fishingmadebetter.common.item.attachment.hook.ItemHookMagnetic;
import net.theawesomegem.fishingmadebetter.common.item.attachment.hook.ItemHookShiny;
import net.theawesomegem.fishingmadebetter.common.item.attachment.reel.ItemReel;
import net.theawesomegem.fishingmadebetter.common.item.attachment.reel.ItemReelBasic;
import net.theawesomegem.fishingmadebetter.common.item.attachment.reel.ItemReelFast;
import net.theawesomegem.fishingmadebetter.common.item.attachment.reel.ItemReelLong;
import net.theawesomegem.fishingmadebetter.common.item.filletknife.ItemDiamondFilletKnife;
import net.theawesomegem.fishingmadebetter.common.item.filletknife.ItemFilletKnife;
import net.theawesomegem.fishingmadebetter.common.item.filletknife.ItemIronFilletKnife;
import net.theawesomegem.fishingmadebetter.common.item.filletknife.ItemWoodFilletKnife;
import net.theawesomegem.fishingmadebetter.common.item.fishingrod.ItemBetterFishingRod;
import net.theawesomegem.fishingmadebetter.common.item.fishingrod.ItemDiamondFishingRod;
import net.theawesomegem.fishingmadebetter.common.item.fishingrod.ItemIronFishingRod;

/**
 * Created by TheAwesomeGem on 12/21/2017.
 */
public class ItemManager {
    public static final ItemBetterFishingRod FISHING_ROD_WOOD = new ItemWoodFishingRod();
    public static final ItemBetterFishingRod FISHING_ROD_IRON = new ItemIronFishingRod();
    public static final ItemBetterFishingRod FISHING_ROD_DIAMOND = new ItemDiamondFishingRod();

    public static final ItemFilletKnife FILLET_KNIFE_WOOD = new ItemWoodFilletKnife();
    public static final ItemFilletKnife FILLET_KNIFE_IRON = new ItemIronFilletKnife();
    public static final ItemFilletKnife FILLET_KNIFE_DIAMOND = new ItemDiamondFilletKnife();
    
    public static final ItemScalingKnife SCALING_KNIFE_WOOD = new ItemWoodScalingKnife();
    public static final ItemScalingKnife SCALING_KNIFE_IRON = new ItemIronScalingKnife();
    public static final ItemScalingKnife SCALING_KNIFE_DIAMOND = new ItemDiamondScalingKnife();
    
    public static final ItemFishSlice FISH_SLICE_RAW = new ItemRawFishSlice();
    public static final ItemFishSlice FISH_SLICE_COOKED = new ItemCookedFishSlice();
    
    public static final ItemFishTracker FISH_TRACKER_IRON = new ItemIronFishTracker();
    public static final ItemFishTracker FISH_TRACKER_GOLD = new ItemGoldFishTracker();
    public static final ItemFishTracker FISH_TRACKER_DIAMOND = new ItemDiamondFishTracker();
    public static final ItemFishTracker FISH_TRACKER_OBSIDIAN = new ItemObsidianFishTracker();
    public static final ItemFishTracker FISH_TRACKER_VOID = new ItemVoidFishTracker();
    
    public static final ItemReel REEL_BASIC = new ItemReelBasic();
    public static final ItemReel REEL_FAST = new ItemReelFast();
    public static final ItemReel REEL_LONG = new ItemReelLong();
    
    public static final ItemBobber BOBBER_BASIC = new ItemBobberBasic();
    public static final ItemBobber BOBBER_OBSIDIAN = new ItemBobberObsidian();
    public static final ItemBobber BOBBER_VOID = new ItemBobberVoid();
    public static final ItemBobber BOBBER_HEAVY = new ItemBobberHeavy();
    public static final ItemBobber BOBBER_LIGHTWEIGHT = new ItemBobberLightweight();
    
    public static final ItemHook HOOK_BASIC = new ItemHookBasic();
    public static final ItemHook HOOK_BARBED = new ItemHookBarbed();
    public static final ItemHook HOOK_FATTY = new ItemHookFatty();
    public static final ItemHook HOOK_SHINY = new ItemHookShiny();
    public static final ItemHook HOOK_MAGNETIC = new ItemHookMagnetic();

    public static final ItemFishBucket FISH_BUCKET = new ItemFishBucket();
	public static final ItemBaitBucket BAIT_BUCKET = new ItemBaitBucket();

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
        		FISHING_ROD_WOOD, 
        		FISHING_ROD_IRON, 
        		FISHING_ROD_DIAMOND, 
        		FILLET_KNIFE_WOOD,
        		FILLET_KNIFE_IRON,
        		FILLET_KNIFE_DIAMOND, 
        		SCALING_KNIFE_WOOD,
        		SCALING_KNIFE_IRON,
        		SCALING_KNIFE_DIAMOND, 
        		FISH_SLICE_RAW,
        		FISH_SLICE_COOKED, 
        		FISH_TRACKER_IRON, 
        		FISH_TRACKER_GOLD, 
        		FISH_TRACKER_DIAMOND,
        		FISH_TRACKER_OBSIDIAN,
        		FISH_TRACKER_VOID,
        		REEL_BASIC,
        		REEL_FAST,
        		REEL_LONG,
        		BOBBER_BASIC,
        		BOBBER_OBSIDIAN,
        		BOBBER_VOID,
        		BOBBER_HEAVY,
        		BOBBER_LIGHTWEIGHT,
        		HOOK_BASIC,
        		HOOK_BARBED,
        		HOOK_FATTY,
        		HOOK_SHINY,
        		HOOK_MAGNETIC,
        		FISH_BUCKET,
				BAIT_BUCKET
        		);
    }
    
    public static ItemBetterFishingRod[] rodList() {
    	return new ItemBetterFishingRod[] {
    			FISHING_ROD_WOOD, 
        		FISHING_ROD_IRON, 
        		FISHING_ROD_DIAMOND
    	};
    }
    
    public static ItemReel[] reelAttachmentList() {
    	return new ItemReel[] {
    			REEL_BASIC,
        		REEL_FAST,
        		REEL_LONG
    	};
    }
    
    public static ItemBobber[] bobberAttachmentList() {
    	return new ItemBobber[] {
    			BOBBER_BASIC,
        		BOBBER_OBSIDIAN,
        		BOBBER_VOID,
        		BOBBER_HEAVY,
        		BOBBER_LIGHTWEIGHT
    	};
    }
    
    public static ItemHook[] hookAttachmentList() {
    	return new ItemHook[] {
    			HOOK_BASIC,
        		HOOK_BARBED,
        		HOOK_FATTY,
        		HOOK_SHINY,
        		HOOK_MAGNETIC
    	};
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels() {
    	FILLET_KNIFE_WOOD.registerItemModel(FILLET_KNIFE_WOOD);
		FILLET_KNIFE_IRON.registerItemModel(FILLET_KNIFE_IRON);
		FILLET_KNIFE_DIAMOND.registerItemModel(FILLET_KNIFE_DIAMOND);
		
        SCALING_KNIFE_WOOD.registerItemModel(SCALING_KNIFE_WOOD);
		SCALING_KNIFE_IRON.registerItemModel(SCALING_KNIFE_IRON);
		SCALING_KNIFE_DIAMOND.registerItemModel(SCALING_KNIFE_DIAMOND);
		
        FISH_SLICE_RAW.registerItemModel(FISH_SLICE_RAW);
        FISH_SLICE_COOKED.registerItemModel(FISH_SLICE_COOKED);
        
        FISH_TRACKER_IRON.registerItemModel(FISH_TRACKER_IRON);
        FISH_TRACKER_GOLD.registerItemModel(FISH_TRACKER_GOLD);
        FISH_TRACKER_DIAMOND.registerItemModel(FISH_TRACKER_DIAMOND);
        FISH_TRACKER_OBSIDIAN.registerItemModel(FISH_TRACKER_OBSIDIAN);
        FISH_TRACKER_VOID.registerItemModel(FISH_TRACKER_VOID);

        FISH_BUCKET.registerItemModel(FISH_BUCKET);
		BAIT_BUCKET.registerItemModel(BAIT_BUCKET);
    }
}
