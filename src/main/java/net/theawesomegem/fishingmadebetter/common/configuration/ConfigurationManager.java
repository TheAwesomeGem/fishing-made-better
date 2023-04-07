package net.theawesomegem.fishingmadebetter.common.configuration;

import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.ConfigManager;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.theawesomegem.fishingmadebetter.ModInfo;

/**
 * Created by TheAwesomeGem on 12/28/2017.
 */
@Config(modid = ModInfo.MOD_ID)
public class ConfigurationManager {
    public enum ReelingHubPos
    {
        TOP_LEFT,
        TOP_CENTER,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_CENTER,
        BOTTOM_RIGHT
    }
    
    @Config.Comment("Server Config")
    @Config.Name("Server")
    @Config.LangKey("config.fishingmadebetter.server")
    public static final ServerConfig server = new ServerConfig();
    
    public static class ServerConfig {
        @Config.Name("Fish Bucket")
        @Config.Comment("Enable the fish bucket?")
        @Config.LangKey("config.fishingmadebetter.server.enableFishBucket")
        public boolean enableFishBucket = true;

        @Config.Name("Automatically Catch Fish When Ready")
        @Config.Comment("When the distance left is 0m and you can catch the fish, automatically force the player to right-click to reel in?")
        @Config.LangKey("config.fishingmadebetter.server.autoReel")
        public boolean autoReel = true;

        @Config.Name("Magnetic Fishing")
        @Config.Comment("Do fish drop on your player when you successfully reel them in?")
        @Config.LangKey("config.fishingmadebetter.server.magneticFishing")
        public boolean magneticFishing = true;

        @Config.Name("Lose Bait")
        @Config.Comment("Lose the bait whenever a fish bites your hook.")
        @Config.LangKey("config.fishingmadebetter.server.looseBait")
        public boolean looseBait = true;
        
        @Config.Name("Simple Bait")
        @Config.Comment("All bait listed in the fish configs work for all fish, baited rods catch fish faster and all bait can be used in the bait box.")
        @Config.LangKey("config.fishingmadebetter.server.simpleBait")
        public boolean simpleBait = true;
        
        @Config.Name("Regenerate Empty Chunks")
        @Config.Comment("Attempt to regenerate fish if a chunk is empty?")
        @Config.LangKey("config.fishingmadebetter.server.regenerateEmptyChunks")
        public boolean regenerateEmptyChunks = false;
        
        @Config.Name("Disable Minigame")
        @Config.Comment("Skips the minigame when a fish bites the hook and just reels it in instead.")
        @Config.LangKey("config.fishingmadebetter.server.skipMinigame")
        public boolean skipMinigame = false;
        
        @Config.Name("Aquaculture Recipe Override")
        @Config.Comment("Replace/remove/override/add Aquaculture recipes to work better with this mod? (Does nothing if Aquaculture is not installed)")
        @Config.LangKey("config.fishingmadebetter.server.aquacultureRecipeOverride")
        public boolean aquacultureRecipeOverride = true;
        
        @Config.Name("AdvancedFishing Recipe Override")
        @Config.Comment("Replace/remove/override/add AdvancedFishing recipes to work better with this mod? (Does nothing if AdvancedFishing is not installed)")
        @Config.LangKey("config.fishingmadebetter.server.advancedFishingRecipeOverride")
        public boolean advancedFishingRecipeOverride = true;
        
        @Config.Name("Charm Salvage Patch")
        @Config.Comment("Attempt to patch the dupe bug caused by Charm's Salvage enchant on rods with attachments? (Will cause attachments to be voided if you drop a rod at 0 durability remaining)")
        @Config.LangKey("config.fishingmadebetter.server.charmSalvagePatch")
        public boolean charmSalvagePatch = true;
        
        @Config.Name("LevelUpReloaded Patch")
        @Config.Comment("Attempt to patch LevelUpReloaded using the wrong event for fishing loot. (Built for version 1.1.23, likely won't work with newer rewrite versions currently)")
        @Config.LangKey("config.fishingmadebetter.server.levelUpPatch")
        public boolean levelUpPatch = true;
        
        @Config.Name("Fish Regeneration Time")
        @Config.RangeInt(min=0)
        @Config.Comment("After a chunk is emptied, how long until it attempts to regenerate the fish population. (Minutes)")
        @Config.LangKey("config.fishingmadebetter.server.fishRegenerationTime")
        public int fishRegenerationTime = 180;

        @Config.Name("Fish Reproduction Time")
        @Config.RangeInt(min=0)
        @Config.Comment("How often the fish population reproduction time should be ticked up. (Minutes) (Ticks required per fish set in Fish-Config)")
        @Config.LangKey("config.fishingmadebetter.server.fishReproductionTime")
        public int fishReproductionTime = 1;

        @Config.Name("Bait Box Update Interval")
        @Config.RangeInt(min=0)
        @Config.Comment("How often a bait-box checks for hungry fish. (Minutes)")
        @Config.LangKey("config.fishingmadebetter.server.baitBoxUpdateInterval")
        public int baitBoxUpdateInterval = 5;

        @Config.Name("Tracking Time")
        @Config.RangeInt(min=0)
        @Config.Comment("Time it takes to probe for fish with the tracker in seconds.")
        @Config.LangKey("config.fishingmadebetter.server.trackingTime")
        public int trackingTime = 5;

        @Config.Name("Random Population Factor")
        @Config.RangeInt(min=0)
        @Config.Comment("When populating chunks, each population is increased or decreased by a random percent chosen between 0 and this.")
        @Config.LangKey("config.fishingmadebetter.server.randomPopulationFactor")
        public int randomPopulationFactor = 50;
        
        @Config.Name("Base Treasure Chance")
        @Config.Comment("Base chance for a fishing rod to get extra treasure. (Out of 100)")
        @Config.LangKey("config.fishingmadebetter.server.baseTreasureChance")
        public int baseTreasureChance = 15;
        
        @Config.Name("Base Fishing Time-To-Bobber")
        @Config.Comment("How many ticks should the time-to-bobber take before applying modifiers. (Ticks)")
        @Config.LangKey("config.fishingmadebetter.server.baseTimeToBobber")
        public float baseTimeToBobber = 900F;
    }
    
    @Config.Comment("Client Config")
    @Config.Name("Client")
    @Config.LangKey("config.fishingmadebetter.client")
    public static final ClientConfig client = new ClientConfig();
    
    public static class ClientConfig {
        @Config.Name("Reeling Hud")
        @Config.Comment("Enable/Disable the reeling hud. (Why would you disable it?)")
        @Config.LangKey("config.fishingmadebetter.client.reelingHud")
        public boolean reelingHud = true;

        @Config.Name("Reeling Meter Position")
        @Config.Comment("Move the Reeling Meter.")
        @Config.LangKey("config.fishingmadebetter.client.reelingHubPos")
        public ReelingHubPos reelingHubPos = ReelingHubPos.TOP_CENTER;

        @Config.Name("Reeling Meter X-Offset")
        @Config.Comment("Changes the X-Offset of the reeling meter.")
        @Config.LangKey("config.fishingmadebetter.client.reelingMeterXOffset")
        public int reelingMeterXOffset = 0;

        @Config.Name("Reeling Meter Y-Offset")
        @Config.Comment("Changes the Y-Offset of the reeling meter.")
        @Config.LangKey("config.fishingmadebetter.client.reelingMeterYOffset")
        public int reelingMeterYOffset = 4;
    }

    @Mod.EventBusSubscriber(modid = ModInfo.MOD_ID)
    private static class EventHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent e) {
            if (e.getModID().equals(ModInfo.MOD_ID)) {
                ConfigManager.sync(ModInfo.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }
}
