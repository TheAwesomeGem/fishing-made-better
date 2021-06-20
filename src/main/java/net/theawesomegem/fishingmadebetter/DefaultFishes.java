package net.theawesomegem.fishingmadebetter;

import net.theawesomegem.fishingmadebetter.common.data.FishData;

import java.util.HashMap;
import java.util.Map;

public class DefaultFishes {
    public static Map<String, Map<String, FishData>> getDefaultFishMap(){
        Map<String, Map<String, FishData>> fishDataMap = new HashMap<>();

        fishDataMap.put("vanilla", getVanillaFishes());
        fishDataMap.put("advancedfishing", getAdvancedFishingFishes());
        fishDataMap.put("aquaculture", getAquacultureFishes());
        fishDataMap.put("harvestcraft", getHarvestCraftFishes());

        return fishDataMap;
    }

    private static Map<String, FishData> getHarvestCraftFishes() {
        return new HashMap<>();
    }

    private static Map<String, FishData> getAquacultureFishes() {
        return new HashMap<>();
    }

    private static Map<String, FishData> getAdvancedFishingFishes() {
        return new HashMap<>();
    }

    private static Map<String, FishData> getVanillaFishes() {
        Map<String, FishData> fishDataMap = new HashMap<>();

        fishDataMap.put("Clownfish", FishData.createData("Clownfish",
                "minecraft:fish", 2, "a fish with bright orange with white stripes and black edges",
                900, 1300, 2, 8, 1, 4, 1, 5,
                FishData.TimeToFish.ANY, false, 30, 10, 20, 5, 2, true,
                false, new int[]{0, 10, 24}, 10, "minecraft:emerald", 0, true,
                new HashMap<String, Integer>(){{ put("minecraft:carrot", 0); }}
        ));

        fishDataMap.put("Pufferfish", FishData.createData("Pufferfish",
                "minecraft:fish", 3, "a large brown tadpole, with bulging eyes and an elongated snout",
                1500, 2300, 25, 35, 2, 4, 20, 30,
                FishData.TimeToFish.ANY, false, 80, 2, 4, 4, 1, true,
                false, new int[]{0, 10, 24}, 8, "minecraft:arrow", 0, true,
                new HashMap<String, Integer>(){{ put("minecraft:dye", 0); }}
        ));

        fishDataMap.put("Cod", FishData.createData("Cod",
                "minecraft:fish", 0, "a relatively large yellowish and stout bodied fish with a large head and long chin barbel",
                2000, 2800, 25, 40, 2, 5, 20, 25,
                FishData.TimeToFish.ANY, false, 200, 4, 6, 3, 1, true,
                false, new int[]{0, 10, 24}, 12, "minecraft:slime_ball", 0, true,
                new HashMap<String, Integer>(){{ put("minecraft:red_mushroom", 0); }}
        ));

        fishDataMap.put("Salmon", FishData.createData("Salmon",
                "minecraft:fish", 1, "a huge silvery body fish with some dark crosses and spots on the head, body, and fins",
                1750, 3000, 40, 48, 1, 4, 45, 60,
                FishData.TimeToFish.ANY, false, 50, 14, 24, 4, 1, true,
                false, new int[]{0, 10, 24}, 12, "minecraft:iron_ingot", 0, true,
                new HashMap<String, Integer>(){{ put("minecraft:fish", 0); }}
        ));

        return fishDataMap;
    }
}
