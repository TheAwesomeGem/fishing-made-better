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

    @Config.Name("Reeling Hud")
    @Config.Comment("Enable/Disable the reeling hud.")
    public static boolean reelingHud = true;

    @Config.Name("Reeling Hold")
    @Config.Comment("Allows you to hold the reeling key instead of pressing it.")
    public static boolean reelingHold = false;

    @Config.Name("Magnetic Fishing")
    @Config.Comment("Enable/Disable fishes automatically dropping near you.")
    public static boolean magneticFishing = true;

    @Config.Name("Custom Fish Slicing")
    @Config.Comment("Allows the fish to be cut into custom slices instead of fishes.")
    public static boolean customFishSlicing = false;

    @Config.Name("Reeling Meter Position")
    @Config.Comment("Move the Reeling Meter.")
    public static ReelingHubPos reelingHubPos = ReelingHubPos.TOP_CENTER;

    @Config.Name("Reeling Meter X-Offset")
    @Config.Comment("Changes the X-Offset of the reeling meter.")
    public static int reelingMeterXOffset = 0;

    @Config.Name("Reeling Meter Y-Offset")
    @Config.Comment("Changes the Y-Offset of the reeling meter.")
    public static int reelingMeterYOffset = 4;

    @Config.Name("Fish Regeneration Time")
    @Config.Comment("Get fish regeneration time in a chunk in minutes.")
    public static int fishRegenerationTime = 60;

    @Config.Name("Fish Reproduction Time")
    @Config.Comment("Get how fast it should lower a reproduction tick of fishes in a chunk in minutes.")
    public static int fishReproductionTime = 4;

    @Config.Name("Bait Box Update Interval")
    @Config.Comment("Check when to update the bait box in minutes.")
    public static int baitBoxUpdateInterval = 5;

    @Config.Name("Tracking Time")
    @Config.Comment("Time it takes to track fishes in seconds.")
    public static int trackingTime = 8;

    @Config.Name("Fish Bucket")
    @Config.Comment("Enable/disable fish bucket.")
    public static boolean enableFishBucket = true;

    @Config.Name("Random Population Factor")
    @Config.Comment("The random factor that gets added/substracted from the rarity to count population.")
    public static int randomPopulationFactor = 24;

    @Config.Name("Require Correct Line")
    @Config.Comment("When the distance is 0 and you are about to catch the fish, does the reel also needs to be correct? (Only for Difficulty)")
    public static boolean requireCorrectLine = false;

    @Config.Name("Loose Bait")
    @Config.Comment("Loose the bait whenever a fish bites to it.")
    public static boolean looseBait = true;

    @Mod.EventBusSubscriber
    private static class EventHandler {
        @SubscribeEvent
        public static void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent e) {
            if (e.getModID().equals(ModInfo.MOD_ID)) {
                ConfigManager.sync(ModInfo.MOD_ID, Config.Type.INSTANCE);
            }
        }
    }
}
