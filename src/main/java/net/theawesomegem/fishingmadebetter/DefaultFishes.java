package net.theawesomegem.fishingmadebetter;

import net.minecraftforge.fml.common.Loader;
import net.theawesomegem.fishingmadebetter.common.data.FishData;
import net.theawesomegem.fishingmadebetter.common.data.FishData.BaitEnum;

import java.util.HashMap;
import java.util.Map;

public class DefaultFishes {//TODO: all this shit
	
    public static Map<String, Map<String, FishData>> getDefaultFishMap(){
        Map<String, Map<String, FishData>> fishDataMap = new HashMap<>();

        fishDataMap.put("vanilla", getVanillaFishes());
        if(Loader.isModLoaded("advanced-fishing")) fishDataMap.put("advancedfishing", getAdvancedFishingFishes());
        if(Loader.isModLoaded("aquaculture")) fishDataMap.put("aquaculture", getAquacultureFishes());

        return fishDataMap;
    }

    private static Map<String, FishData> getAquacultureFishes() {
        Map<String, FishData> fishDataMap = new HashMap<>();
        
        //Freshwater
        
        fishDataMap.put("Bluegill", FishData.createData(
        		"Bluegill",//Fish ID
                "aquaculture:fish",//Item
                0,//Metadata
                "a small yellow and blue fish",//Description
                800,//minFishTime
                1000,//maxFishTime
                3,//minErrorVariance
                5,//maxErrorVariance
                1,//minWeight
                2,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                50,//Fish rarity
                2,//minDeepLevel
                25,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                9,//reproductionTime
                4,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "PLAINS", "FOREST", "RIVER", "SWAMP" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                10,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                false,//Fillet uses weight
                new BaitEnum[] { BaitEnum.VEGETABLE, BaitEnum.MEAT_NORMAL, BaitEnum.HERBIVORE_AQC, BaitEnum.SMALL_PREDATOR_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Perch", FishData.createData(
        		"Perch",//Fish ID
                "aquaculture:fish",//Item
                1,//Metadata
                "a long, rounded fish with rough scales",//Description
                800,//minFishTime
                1000,//maxFishTime
                3,//minErrorVariance
                5,//maxErrorVariance
                1,//minWeight
                2,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                40,//Fish rarity
                2,//minDeepLevel
                25,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                9,//reproductionTime
                4,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "PLAINS", "FOREST", "RIVER", "SWAMP" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                10,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                false,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.SMALL_PREDATOR_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Gar", FishData.createData(
        		"Gar",//Fish ID
                "aquaculture:fish",//Item
                2,//Metadata
                "a long, armored fish with elongated jaws filled with sharp teeth",//Description
                1600,//minFishTime
                2000,//maxFishTime
                1,//minErrorVariance
                3,//maxErrorVariance
                10,//minWeight
                40,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                10,//Fish rarity
                30,//minDeepLevel
                60,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                20,//reproductionTime
                8,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "RIVER", "SWAMP" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                20,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:flint",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.VEGETABLE, BaitEnum.MEAT_NORMAL, BaitEnum.SMALL_PREDATOR_AQC, BaitEnum.LARGE_PREDATOR_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Bass", FishData.createData(
        		"Bass",//Fish ID
                "aquaculture:fish",//Item
                3,//Metadata
                "a decently sized black and green fish",//Description
                1200,//minFishTime
                1600,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                5,//minWeight
                10,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                20,//Fish rarity
                20,//minDeepLevel
                40,//maxDeepLevel
                55,//minYLevel
                140,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "PLAINS", "FOREST", "RIVER", "SWAMP", "MOUNTAIN" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.SMALL_PREDATOR_AQC, BaitEnum.LARGE_PREDATOR_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Muskellunge", FishData.createData(
        		"Muskellunge",//Fish ID
                "aquaculture:fish",//Item
                4,//Metadata
                "a large aggressive silvery fish",//Description
                1600,//minFishTime
                2000,//maxFishTime
                1,//minErrorVariance
                3,//maxErrorVariance
                10,//minWeight
                40,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                6,//Fish rarity
                20,//minDeepLevel
                80,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                20,//reproductionTime
                8,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "PLAINS", "FOREST", "RIVER" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                20,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.SMALL_PREDATOR_AQC, BaitEnum.LARGE_PREDATOR_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Brown Trout", FishData.createData(
        		"Brown Trout",//Fish ID
                "aquaculture:fish",//Item
                5,//Metadata
                "a common, medium sized brown fish",//Description
                1200,//minFishTime
                1600,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                10,//minWeight
                20,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                12,//Fish rarity
                10,//minDeepLevel
                50,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "PLAINS", "FOREST", "RIVER", "SWAMP", "MOUNTAIN", "OCEAN" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                20,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.SMALL_PREDATOR_AQC }//Valid bait items
        ));

        fishDataMap.put("Catfish", FishData.createData(
        		"Catfish",//Fish ID
                "aquaculture:fish",//Item
                6,//Metadata
                "a large, widemouthed fish with whisker-like barbels",//Description
                1600,//minFishTime
                2200,//maxFishTime
                1,//minErrorVariance
                4,//maxErrorVariance
                20,//minWeight
                50,//maxWeight
                FishData.TimeToFish.NIGHT,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                4,//Fish rarity
                5,//minDeepLevel
                40,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                20,//reproductionTime
                8,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "PLAINS", "FOREST", "RIVER", "SWAMP" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                20,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.VEGETABLE, BaitEnum.MEAT_NORMAL, BaitEnum.HERBIVORE_AQC, BaitEnum.SMALL_PREDATOR_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Carp", FishData.createData(
        		"Carp",//Fish ID
                "aquaculture:fish",//Item
                7,//Metadata
                "a large, brown, invasive fish",//Description
                1800,//minFishTime
                2200,//maxFishTime
                1,//minErrorVariance
                3,//maxErrorVariance
                30,//minWeight
                50,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                2,//Fish rarity
                30,//minDeepLevel
                80,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "PLAINS", "FOREST", "RIVER" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                20,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.VEGETABLE, BaitEnum.GRAIN, BaitEnum.MEAT_NORMAL, BaitEnum.HERBIVORE_AQC, BaitEnum.SMALL_PREDATOR_AQC }//Valid bait items
        ));
        
        //Saltwater
        
        fishDataMap.put("Blowfish", FishData.createData(
        		"Blowfish",//Fish ID
                "aquaculture:fish",//Item
                8,//Metadata
                "a small, brown, boney fish that inflates when threatened",//Description
                1100,
                1500,
                2,
                4,
                5,
                12,
                FishData.TimeToFish.ANY,
                FishData.FishingLiquid.WATER,
                false,
                false,
                6,
                10,
                30,
                55,
                90,
                16,
                12,
                5,
                true,
                false,
                true,
                new String[] { "RIVER", "SWAMP" },
                new int[]{ -1, 1},
                20,
                true,
                "minecraft:arrow",
                0,
                true,
                true,
                true,
                "",
                0,
                true,
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.HERBIVORE_AQC, BaitEnum.SMALL_PREDATOR_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Red Grouper", FishData.createData(
        		"Red Grouper",//Fish ID
                "aquaculture:fish",//Item
                9,//Metadata
                "a decently sized reddish-brown ray-finned fish",//Description
                1400,//minFishTime
                1800,//maxFishTime
                1,//minErrorVariance
                4,//maxErrorVariance
                15,//minWeight
                25,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                30,//Fish rarity
                40,//minDeepLevel
                80,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                20,//reproductionTime
                8,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "OCEAN", "BEACH" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                20,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.SMALL_PREDATOR_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Arctic Salmon", FishData.createData(
        		"Arctic Salmon",//Fish ID
                "aquaculture:fish",//Item
                10,//Metadata
                "a large, silvery-red fish known for their taste",//Description
                1600,//minFishTime
                2000,//maxFishTime
                1,//minErrorVariance
                3,//maxErrorVariance
                10,//minWeight
                40,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                8,//Fish rarity
                40,//minDeepLevel
                80,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                20,//reproductionTime
                8,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "OCEAN", "BEACH", "COLD" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                20,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.SMALL_PREDATOR_AQC, BaitEnum.LARGE_PREDATOR_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Tuna", FishData.createData(
        		"Tuna",//Fish ID
                "aquaculture:fish",//Item
                11,//Metadata
                "a very large blue-finned fish",//Description
                2200,//minFishTime
                2600,//maxFishTime
                1,//minErrorVariance
                2,//maxErrorVariance
                50,//minWeight
                300,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                2,//Fish rarity
                80,//minDeepLevel
                130,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                30,//reproductionTime
                11,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "OCEAN" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.SMALL_PREDATOR_AQC, BaitEnum.LARGE_PREDATOR_AQC, BaitEnum.OCEAN_PREDATOR_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Swordfish", FishData.createData(
        		"Swordfish",//Fish ID
                "aquaculture:fish",//Item
                12,//Metadata
                "a large fish with a large pointed bill",//Description
                2200,//minFishTime
                2600,//maxFishTime
                1,//minErrorVariance
                2,//maxErrorVariance
                50,//minWeight
                200,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                3,//Fish rarity
                80,//minDeepLevel
                130,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                30,//reproductionTime
                11,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "OCEAN" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.SMALL_PREDATOR_AQC, BaitEnum.LARGE_PREDATOR_AQC, BaitEnum.OCEAN_PREDATOR_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Shark", FishData.createData(
        		"Shark",//Fish ID
                "aquaculture:fish",//Item
                13,//Metadata
                "a very large predator",//Description
                2000,//minFishTime
                2400,//maxFishTime
                1,//minErrorVariance
                2,//maxErrorVariance
                500,//minWeight
                800,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                1,//Fish rarity
                100,//minDeepLevel
                150,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                45,//reproductionTime
                16,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "OCEAN" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.SMALL_PREDATOR_AQC, BaitEnum.LARGE_PREDATOR_AQC, BaitEnum.OCEAN_PREDATOR_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Whale", FishData.createData(
        		"Whale",//Fish ID
                "aquaculture:fish",//Item
                14,//Metadata
                "a massive sea-dwelling mammal",//Description
                2800,//minFishTime
                3200,//maxFishTime
                1,//minErrorVariance
                1,//maxErrorVariance
                1000,//minWeight
                1500,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                1,//Fish rarity
                120,//minDeepLevel
                150,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                60,//reproductionTime
                21,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "OCEAN" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                20,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                false,//Use default fillets
                "aquaculture:food",//Custom fillet item
                2,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.SMALL_PREDATOR_AQC, BaitEnum.OCEAN_PREDATOR_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Giant Squid", FishData.createData(
        		"Giant Squid",//Fish ID
                "aquaculture:fish",//Item
                15,//Metadata
                "a very large fleshy sea-organism with long tentacles",//Description
                2200,//minFishTime
                2400,//maxFishTime
                1,//minErrorVariance
                2,//maxErrorVariance
                50,//minWeight
                200,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                2,//Fish rarity
                80,//minDeepLevel
                130,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                30,//reproductionTime
                11,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "OCEAN" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                10,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:dye",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.SMALL_PREDATOR_AQC, BaitEnum.LARGE_PREDATOR_AQC, BaitEnum.OCEAN_PREDATOR_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Jellyfish", FishData.createData(
        		"Jellyfish",//Fish ID
                "aquaculture:fish",//Item
                16,//Metadata
                "a gelatinous sea-organism with venomous tentacles",//Description
                1400,//minFishTime
                1800,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                10,//minWeight
                100,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                5,//Fish rarity
                50,//minDeepLevel
                100,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                30,//reproductionTime
                11,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "OCEAN", "BEACH" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                10,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:slime_ball",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                false,//Allow filleting
                false,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                false,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.HERBIVORE_AQC, BaitEnum.SMALL_PREDATOR_AQC }//Valid bait items
        ));
        
        //Brackish
        
        fishDataMap.put("Frog", FishData.createData(
        		"Frog",//Fish ID
                "aquaculture:fish",//Item
                17,//Metadata
                "a small amphibian with good jumping capability",//Description
                600,//minFishTime
                1000,//maxFishTime
                2,//minErrorVariance
                5,//maxErrorVariance
                1,//minWeight
                1,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                8,//Fish rarity
                1,//minDeepLevel
                15,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                9,//reproductionTime
                4,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "SWAMP", "WET" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                30,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                false,//Use default fillets
                "aquaculture:food",//Custom fillet item
                7,//Custom fillet item metadata
                false,//Fillet uses weight
                new BaitEnum[] { BaitEnum.VEGETABLE, BaitEnum.MEAT_NORMAL, BaitEnum.HERBIVORE_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Turtle", FishData.createData(
        		"Turtle",//Fish ID
                "aquaculture:fish",//Item
                18,//Metadata
                "a small reptile with a hard shell for protection",//Description
                800,//minFishTime
                1200,//maxFishTime
                2,//minErrorVariance
                5,//maxErrorVariance
                1,//minWeight
                5,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                4,//Fish rarity
                1,//minDeepLevel
                15,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "SWAMP", "WET" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                30,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                false,//Allow filleting
                false,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                false,//Fillet uses weight
                new BaitEnum[] { BaitEnum.VEGETABLE, BaitEnum.MEAT_NORMAL, BaitEnum.HERBIVORE_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Leech", FishData.createData(
        		"Leech",//Fish ID
                "aquaculture:fish",//Item
                19,//Metadata
                "a parasitic insect that feeds on blood",//Description
                400,//minFishTime
                800,//maxFishTime
                3,//minErrorVariance
                6,//maxErrorVariance
                1,//minWeight
                1,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                20,//Fish rarity
                1,//minDeepLevel
                5,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                5,//reproductionTime
                2,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "SWAMP", "WET" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                30,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                false,//Allow filleting
                false,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                false,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.MEAT_EXTRA }//Valid bait items
        ));
        
        //Tropical
        
        fishDataMap.put("Piranha", FishData.createData(
        		"Piranha",//Fish ID
                "aquaculture:fish",//Item
                20,//Metadata
                "a small vicious predator fish",//Description
                400,//minFishTime
                800,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                1,//minWeight
                3,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                10,//Fish rarity
                5,//minDeepLevel
                20,//maxDeepLevel
                55,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "JUNGLE" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                10,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.MEAT_EXTRA, BaitEnum.SMALL_PREDATOR_AQC, BaitEnum.LARGE_PREDATOR_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Electric Eel", FishData.createData(
        		"Electric Eel",//Fish ID
                "aquaculture:fish",//Item
                21,//Metadata
                "a snake-like fish that shocks it's prey",//Description
                1000,//minFishTime
                1200,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                5,//minWeight
                20,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                5,//Fish rarity
                2,//minDeepLevel
                25,//maxDeepLevel
                55,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "JUNGLE" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:glowstone_dust",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.HERBIVORE_AQC, BaitEnum.SMALL_PREDATOR_AQC, BaitEnum.LARGE_PREDATOR_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Tambaqui", FishData.createData(
        		"Tambaqui",//Fish ID
                "aquaculture:fish",//Item
                22,//Metadata
                "a large freshwater fish similiar in appearance to the piranha",//Description
                1600,//minFishTime
                1800,//maxFishTime
                1,//minErrorVariance
                4,//maxErrorVariance
                15,//minWeight
                40,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                10,//Fish rarity
                10,//minDeepLevel
                40,//maxDeepLevel
                55,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                30,//reproductionTime
                11,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "JUNGLE" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.FRUIT, BaitEnum.GRAIN, BaitEnum.HERBIVORE_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Arapaima", FishData.createData(
        		"Arapaima",//Fish ID
                "aquaculture:fish",//Item
                23,//Metadata
                "a very large, sleek, freshwater fish that has to breath surface air occasionaly",//Description
                1800,//minFishTime
                2200,//maxFishTime
                1,//minErrorVariance
                2,//maxErrorVariance
                100,//minWeight
                200,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                4,//Fish rarity
                25,//minDeepLevel
                75,//maxDeepLevel
                55,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                45,//reproductionTime
                16,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "JUNGLE" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.FRUIT, BaitEnum.GRAIN, BaitEnum.MEAT_NORMAL, BaitEnum.HERBIVORE_AQC, BaitEnum.SMALL_PREDATOR_AQC, BaitEnum.LARGE_PREDATOR_AQC }//Valid bait items
        ));
        
        //Arctic
        
        fishDataMap.put("Polar Cod", FishData.createData(
        		"Polar Cod",//Fish ID
                "aquaculture:fish",//Item
                24,//Metadata
                "a large cold-dwelling fish",//Description
                1800,//minFishTime
                2200,//maxFishTime
                1,//minErrorVariance
                3,//maxErrorVariance
                30,//minWeight
                80,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                8,//Fish rarity
                40,//minDeepLevel
                80,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                30,//reproductionTime
                11,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "COLD" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                20,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.SMALL_PREDATOR_AQC, BaitEnum.LARGE_PREDATOR_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Pollock", FishData.createData(
        		"Pollock",//Fish ID
                "aquaculture:fish",//Item
                25,//Metadata
                "a decent-sized silvery fish often used for food",//Description
                1200,//minFishTime
                1600,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                10,//minWeight
                20,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                30,//Fish rarity
                20,//minDeepLevel
                100,//maxDeepLevel
                55,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                20,//reproductionTime
                8,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "COLD" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.VEGETABLE, BaitEnum.MEAT_NORMAL, BaitEnum.HERBIVORE_AQC, BaitEnum.SMALL_PREDATOR_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Herring", FishData.createData(
        		"Herring",//Fish ID
                "aquaculture:fish",//Item
                26,//Metadata
                "a small, silvery fish that travels in large schools",//Description
                1200,//minFishTime
                1600,//maxFishTime
                3,//minErrorVariance
                5,//maxErrorVariance
                1,//minWeight
                1,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                60,//Fish rarity
                20,//minDeepLevel
                60,//maxDeepLevel
                55,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                12,//reproductionTime
                5,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "COLD" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                10,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.GRAIN, BaitEnum.VEGETABLE, BaitEnum.HERBIVORE_AQC}//Valid bait items
        ));
        
        fishDataMap.put("Halibut", FishData.createData(
        		"Halibut",//Fish ID
                "aquaculture:fish",//Item
                27,//Metadata
                "a large brown fish that hides by laying on the seafloor",//Description
                1800,//minFishTime
                2200,//maxFishTime
                1,//minErrorVariance
                3,//maxErrorVariance
                10,//minWeight
                100,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                20,//Fish rarity
                50,//minDeepLevel
                100,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                30,//reproductionTime
                11,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "COLD" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                20,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.SMALL_PREDATOR_AQC, BaitEnum.LARGE_PREDATOR_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Chinook Salmon", FishData.createData(
        		"Chinook Salmon",//Fish ID
                "aquaculture:fish",//Item
                28,//Metadata
                "a very large salmon species",//Description
                1800,//minFishTime
                2200,//maxFishTime
                1,//minErrorVariance
                3,//maxErrorVariance
                40,//minWeight
                80,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                16,//Fish rarity
                25,//minDeepLevel
                100,//maxDeepLevel
                55,//minYLevel
                120,//maxYLevel
                16,//maxLightLevel
                30,//reproductionTime
                11,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "COLD" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                20,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.SMALL_PREDATOR_AQC, BaitEnum.LARGE_PREDATOR_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Rainbow Trout", FishData.createData(
        		"Rainbow Trout",//Fish ID
                "aquaculture:fish",//Item
                29,//Metadata
                "a decent-sized irridescent fish",//Description
                1200,//minFishTime
                1400,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                1,//minWeight
                10,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                30,//Fish rarity
                10,//minDeepLevel
                40,//maxDeepLevel
                55,//minYLevel
                120,//maxYLevel
                16,//maxLightLevel
                20,//reproductionTime
                8,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "COLD", "RIVER" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                20,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.SMALL_PREDATOR_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Tautog", FishData.createData(
        		"Tautog",//Fish ID
                "aquaculture:fish",//Item
                30,//Metadata
                "a small, black fish with powerful crushing jaws",//Description
                1200,//minFishTime
                1400,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                1,//minWeight
                5,//maxWeight
                FishData.TimeToFish.DAY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                40,//Fish rarity
                5,//minDeepLevel
                40,//maxDeepLevel
                55,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "COLD", "BEACH" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                20,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.SMALL_PREDATOR_AQC }//Valid bait items
        ));
        
        //Arid
        
        fishDataMap.put("Capitaine", FishData.createData(
        		"Capitaine",//Fish ID
                "aquaculture:fish",//Item
                31,//Metadata
                "a very large fish known as the water elephant",//Description
                2200,//minFishTime
                2400,//maxFishTime
                1,//minErrorVariance
                2,//maxErrorVariance
                80,//minWeight
                200,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                8,//Fish rarity
                50,//minDeepLevel
                125,//maxDeepLevel
                55,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                45,//reproductionTime
                16,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "DRY", "SANDY" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                20,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.SMALL_PREDATOR_AQC, BaitEnum.LARGE_PREDATOR_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Boulti", FishData.createData(
        		"Boulti",//Fish ID
                "aquaculture:fish",//Item
                32,//Metadata
                "a small fish known as the mango fish",//Description
                1200,//minFishTime
                1400,//maxFishTime
                3,//minErrorVariance
                5,//maxErrorVariance
                2,//minWeight
                6,//maxWeight
                FishData.TimeToFish.DAY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                60,//Fish rarity
                10,//minDeepLevel
                40,//maxDeepLevel
                55,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "DRY", "SANDY" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.GRAIN, BaitEnum.HERBIVORE_AQC, BaitEnum.SMALL_PREDATOR_AQC }//Valid bait items
        ));

        fishDataMap.put("Bagrid", FishData.createData(
        		"Bagrid",//Fish ID
                "aquaculture:fish",//Item
                33,//Metadata
                "a small catfish species common in arid enviroments",//Description
                1200,//minFishTime
                1400,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                5,//minWeight
                10,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                60,//Fish rarity
                10,//minDeepLevel
                40,//maxDeepLevel
                55,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "DRY", "SANDY" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.VEGETABLE, BaitEnum.GRAIN, BaitEnum.HERBIVORE_AQC, BaitEnum.SMALL_PREDATOR_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Synodontis", FishData.createData(
        		"Synodontis",//Fish ID
                "aquaculture:fish",//Item
                34,//Metadata
                "a yellow catfish covered in black spots",//Description
                800,//minFishTime
                1000,//maxFishTime
                3,//minErrorVariance
                5,//maxErrorVariance
                1,//minWeight
                2,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                80,//Fish rarity
                5,//minDeepLevel
                30,//maxDeepLevel
                55,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                12,//reproductionTime
                5,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "DRY", "SANDY" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                10,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.VEGETABLE, BaitEnum.GRAIN, BaitEnum.HERBIVORE_AQC, BaitEnum.SMALL_PREDATOR_AQC }//Valid bait items
        ));
        
        //Mushroom
        
        fishDataMap.put("Red Shrooma", FishData.createData(
        		"Red Shrooma",//Fish ID
                "aquaculture:fish",//Item
                35,//Metadata
                "a strange fish covered in red fungal growth",//Description
                1600,//minFishTime
                2000,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                10,//minWeight
                30,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                40,//Fish rarity
                15,//minDeepLevel
                50,//maxDeepLevel
                55,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "MUSHROOM" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:red_mushroom",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.VEGETABLE, BaitEnum.GRAIN, BaitEnum.HERBIVORE_AQC }//Valid bait items
        ));
        
        fishDataMap.put("Brown Shrooma", FishData.createData(
        		"Brown Shrooma",//Fish ID
                "aquaculture:fish",//Item
                36,//Metadata
                "a strange fish covered in brown fungal growth",//Description
                1600,//minFishTime
                2000,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                10,//minWeight
                30,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                60,//Fish rarity
                15,//minDeepLevel
                50,//maxDeepLevel
                55,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "MUSHROOM" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:brown_mushroom",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.VEGETABLE, BaitEnum.GRAIN, BaitEnum.HERBIVORE_AQC }//Valid bait items
        ));
        
        //Goldfish
        
        fishDataMap.put("Goldfish", FishData.createData(
        		"Goldfish",//Fish ID
                "aquaculture:fish",//Item
                37,//Metadata
                "an invasive freshwater fish with shiny gold scales",//Description
                600,//minFishTime
                800,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                1,//minWeight
                1,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                20,//Fish rarity
                3,//minDeepLevel
                30,//maxDeepLevel
                55,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "RIVER", "PLAINS", "FOREST", "DRY", "SANDY", "JUNGLE" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:gold_nugget",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                false,//Allow filleting
                false,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                false,//Fillet uses weight
                new BaitEnum[] { BaitEnum.VEGETABLE, BaitEnum.FRUIT, BaitEnum.GRAIN, BaitEnum.MEAT_NORMAL, BaitEnum.HERBIVORE_AQC }//Valid bait items
        ));
        
        return fishDataMap;
    }

    private static Map<String, FishData> getAdvancedFishingFishes() {
    	Map<String, FishData> fishDataMap = new HashMap<>();
    	
    	fishDataMap.put("Squid", FishData.createData(
        		"Squid",//Fish ID
                "advanced-fishing:fish",//Item
                23,//Metadata
                "a decent-sized invertebrate with long tentacles",//Description
                1600,//minFishTime
                1800,//maxFishTime
                3,//minErrorVariance
                5,//maxErrorVariance
                10,//minWeight
                40,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                8,//Fish rarity
                40,//minDeepLevel
                100,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                20,//reproductionTime
                7,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "OCEAN", "BEACH" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                10,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:dye",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL }//Valid bait items
        ));
    	
    	fishDataMap.put("Brown Bullhead", FishData.createData(
        		"Brown Bullhead",//Fish ID
                "advanced-fishing:fish",//Item
                36,//Metadata
                "a social fish known as the mud cat",//Description
                1400,//minFishTime
                1800,//maxFishTime
                3,//minErrorVariance
                6,//maxErrorVariance
                1,//minWeight
                4,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                20,//Fish rarity
                5,//minDeepLevel
                30,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "PLAINS", "FOREST", "RIVER", "SWAMP" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                20,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.VEGETABLE, BaitEnum.MEAT_NORMAL }//Valid bait items
        ));
    	
    	fishDataMap.put("Pike", FishData.createData(
        		"Pike",//Fish ID
                "advanced-fishing:fish",//Item
                37,//Metadata
                "a long, pointed, carnivorous fish",//Description
                1400,//minFishTime
                1600,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                1,//minWeight
                10,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                5,//Fish rarity
                20,//minDeepLevel
                60,//maxDeepLevel
                55,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "PLAINS", "FOREST", "RIVER", "SWAMP" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.MEAT_EXTRA }//Valid bait items
        ));
    	
    	fishDataMap.put("Nitric Crucian", FishData.createData(
        		"Nitric Crucian",//Fish ID
                "advanced-fishing:fish",//Item
                16,//Metadata
                "a small, green fish with volatile, powdery scales",//Description
                1000,//minFishTime
                1300,//maxFishTime
                3,//minErrorVariance
                5,//maxErrorVariance
                1,//minWeight
                3,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                10,//Fish rarity
                5,//minDeepLevel
                40,//maxDeepLevel
                35,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "PLAINS", "FOREST", "RIVER", "DRY", "SANDY" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:gunpowder",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.MEAT_EXTRA }//Valid bait items
        ));
    	
    	fishDataMap.put("Angelfish", FishData.createData(
        		"Angelfish",//Fish ID
                "advanced-fishing:fish",//Item
                19,//Metadata
                "a small, triangular fish typically kept as a pet",//Description
                800,//minFishTime
                1000,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                1,//minWeight
                1,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                8,//Fish rarity
                3,//minDeepLevel
                20,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "JUNGLE" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                false,//Fillet uses weight
                new BaitEnum[] { BaitEnum.VEGETABLE, BaitEnum.FRUIT, BaitEnum.GRAIN }//Valid bait items
        ));
    	
    	fishDataMap.put("Bluebottle Jellyfish", FishData.createData(
        		"Bluebottle Jellyfish",//Fish ID
                "advanced-fishing:fish",//Item
                0,//Metadata
                "a gelatinous sea-organism known as the floating terror",//Description
                1400,//minFishTime
                1800,//maxFishTime
                3,//minErrorVariance
                5,//maxErrorVariance
                1,//minWeight
                3,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                5,//Fish rarity
                30,//minDeepLevel
                80,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                20,//reproductionTime
                7,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "OCEAN", "BEACH" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                6,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                false,//Use default fillets
                "minecraft:slime_ball",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL }//Valid bait items
        ));
    	
    	fishDataMap.put("Bluehead Porifcian", FishData.createData(
        		"Bluehead Porifcian",//Fish ID
                "advanced-fishing:fish",//Item
                21,//Metadata
                "a small, colorful fish that typically feeds on and lives around sponge",//Description
                1000,//minFishTime
                1200,//maxFishTime
                3,//minErrorVariance
                5,//maxErrorVariance
                1,//minWeight
                1,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                6,//Fish rarity
                50,//minDeepLevel
                100,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "OCEAN", "BEACH" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                false,//Use default fillets
                "minecraft:sponge",//Custom fillet item
                0,//Custom fillet item metadata
                false,//Fillet uses weight
                new BaitEnum[] { BaitEnum.VEGETABLE, BaitEnum.FRUIT, BaitEnum.GRAIN }//Valid bait items
        ));
    	
    	fishDataMap.put("Abyssal Lurker", FishData.createData(
        		"Abyssal Lurker",//Fish ID
                "advanced-fishing:fish",//Item
                33,//Metadata
                "a snake-like, deep-sea fish covered in prismarine lancets",//Description
                1400,//minFishTime
                1600,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                2,//minWeight
                8,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                3,//Fish rarity
                120,//minDeepLevel
                140,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                20,//reproductionTime
                7,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "OCEAN" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                10,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:prismarine_shard",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.MEAT_EXTRA }//Valid bait items
        ));
    	
    	fishDataMap.put("Prismatic Angler", FishData.createData(
        		"Prismatic Angler",//Fish ID
                "advanced-fishing:fish",//Item
                20,//Metadata
                "a large deep-sea fish with massive fangs that uses shiny crystals to attract prey",//Description
                1600,//minFishTime
                1800,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                10,//minWeight
                40,//maxWeight
                FishData.TimeToFish.NIGHT,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                3,//Fish rarity
                120,//minDeepLevel
                140,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                20,//reproductionTime
                7,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "OCEAN" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                10,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:prismarine_crystals",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.MEAT_EXTRA }//Valid bait items
        ));
    	
    	fishDataMap.put("Fluorescent Slimefish", FishData.createData(
        		"Fluorescent Slimefish",//Fish ID
                "advanced-fishing:fish",//Item
                39,//Metadata
                "a green, fluorescent mass of biowaste that seems to have formed into a pseudo-jellyfish",//Description
                1200,//minFishTime
                1600,//maxFishTime
                3,//minErrorVariance
                5,//maxErrorVariance
                1,//minWeight
                3,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                15,//Fish rarity
                5,//minDeepLevel
                40,//maxDeepLevel
                25,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                20,//reproductionTime
                7,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "DEAD", "SPOOKY" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                6,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:slime_ball",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                false,//Allow filleting
                false,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                false,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL }//Valid bait items
        ));
    	
    	fishDataMap.put("Bonefish", FishData.createData(
        		"Bonefish",//Fish ID
                "advanced-fishing:fish",//Item
                40,//Metadata
                "a reanimated mass of fish bones",//Description
                1300,//minFishTime
                1600,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                2,//minWeight
                5,//maxWeight
                FishData.TimeToFish.NIGHT,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                20,//Fish rarity
                5,//minDeepLevel
                40,//maxDeepLevel
                25,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                20,//reproductionTime
                7,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "DEAD", "SPOOKY" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                6,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                false,//Use default fillets
                "minecraft:bone",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.MEAT_EXTRA }//Valid bait items
        ));
    	
    	fishDataMap.put("Phantasmal Tetra", FishData.createData(
        		"Phantasmal Tetra",//Fish ID
                "advanced-fishing:fish",//Item
                42,//Metadata
                "a rare transulscent fish, speculated to be a phantom of a dead fish",//Description
                1200,//minFishTime
                1400,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                1,//minWeight
                4,//maxWeight
                FishData.TimeToFish.NIGHT,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                6,//Fish rarity
                5,//minDeepLevel
                40,//maxDeepLevel
                25,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                20,//reproductionTime
                7,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "DEAD", "SPOOKY" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                6,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                false,//Use default fillets
                "minecraft:ghast_tear",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.MEAT_EXTRA }//Valid bait items
        ));
    	
    	fishDataMap.put("Cursed Koi", FishData.createData(
        		"Cursed Koi",//Fish ID
                "advanced-fishing:fish",//Item
                41,//Metadata
                "a rare, emerald-scaled fish that is said to curse you if you cause its death",//Description
                1200,//minFishTime
                1400,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                1,//minWeight
                5,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                6,//Fish rarity
                5,//minDeepLevel
                40,//maxDeepLevel
                25,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                25,//reproductionTime
                9,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "DEAD", "SPOOKY" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                10,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:emerald",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.GRAIN, BaitEnum.VEGETABLE, BaitEnum.FRUIT }//Valid bait items
        ));
    	
    	fishDataMap.put("Mudskinned Piranha", FishData.createData(
        		"Mudskinned Piranha",//Fish ID
                "advanced-fishing:fish",//Item
                4,//Metadata
                "a vicious predator fish that lays on the muddy riverbed waiting for prey to swim by",//Description
                600,//minFishTime
                1000,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                3,//minWeight
                10,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                8,//Fish rarity
                10,//minDeepLevel
                30,//maxDeepLevel
                55,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "JUNGLE" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                10,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:clay_ball",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.MEAT_EXTRA }//Valid bait items
        ));
    	
    	fishDataMap.put("Sparkling Eel", FishData.createData(
        		"Sparkling Eel",//Fish ID
                "advanced-fishing:fish",//Item
                18,//Metadata
                "a snake-like fish that dazzles its prey with shiny, reflective scales",//Description
                1000,//minFishTime
                1200,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                5,//minWeight
                20,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                5,//Fish rarity
                2,//minDeepLevel
                25,//maxDeepLevel
                55,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "JUNGLE" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:glowstone_dust",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL }//Valid bait items
        ));
    	
    	fishDataMap.put("Mandarin Dragonet", FishData.createData(
        		"Mandarin Dragonet",//Fish ID
                "advanced-fishing:fish",//Item
                26,//Metadata
                "a small, brightly colored fish said to grant knowledge",//Description
                800,//minFishTime
                1000,//maxFishTime
                1,//minErrorVariance
                3,//maxErrorVariance
                1,//minWeight
                1,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                6,//Fish rarity
                10,//minDeepLevel
                20,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                20,//reproductionTime
                7,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "JUNGLE" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                false,//Use default fillets
                "minecraft:experience_bottle",//Custom fillet item
                0,//Custom fillet item metadata
                false,//Fillet uses weight
                new BaitEnum[] { BaitEnum.VEGETABLE, BaitEnum.FRUIT, BaitEnum.GRAIN, BaitEnum.MEAT_NORMAL }//Valid bait items
        ));
    	
    	fishDataMap.put("Barred Sand Bass", FishData.createData(
        		"Barred Sand Bass",//Fish ID
                "advanced-fishing:fish",//Item
                25,//Metadata
                "a decently sized, pale, yellow fish with gritty scales",//Description
                1200,//minFishTime
                1600,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                5,//minWeight
                10,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                15,//Fish rarity
                20,//minDeepLevel
                40,//maxDeepLevel
                55,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "DRY", "SANDY", "BEACH" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:sand",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.GRAIN }//Valid bait items
        ));
    	
    	fishDataMap.put("Golden Koi", FishData.createData(
        		"Golden Koi",//Fish ID
                "advanced-fishing:fish",//Item
                5,//Metadata
                "a decently sized golden fish with prized scales",//Description
                800,//minFishTime
                1000,//maxFishTime
                1,//minErrorVariance
                3,//maxErrorVariance
                1,//minWeight
                8,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                8,//Fish rarity
                20,//minDeepLevel
                40,//maxDeepLevel
                55,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "DRY", "SANDY" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:gold_nugget",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.GRAIN, BaitEnum.VEGETABLE, BaitEnum.FRUIT }//Valid bait items
        ));
    	
    	fishDataMap.put("Sunfish", FishData.createData(
        		"Sunfish",//Fish ID
                "advanced-fishing:fish",//Item
                34,//Metadata
                "an extremely bright-yellow colored flatfish",//Description
                1400,//minFishTime
                1600,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                3,//minWeight
                10,//maxWeight
                FishData.TimeToFish.DAY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                14,//Fish rarity
                10,//minDeepLevel
                30,//maxDeepLevel
                55,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "DRY", "SANDY", "PLAINS" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:dye",//Scaling item
                11,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.FRUIT }//Valid bait items
        ));
    	
    	fishDataMap.put("Snowy Walleye", FishData.createData(
        		"Snowy Walleye",//Fish ID
                "advanced-fishing:fish",//Item
                22,//Metadata
                "an arctic-dwelling fish with powdery scales that resemble snowflakes",//Description
                1000,//minFishTime
                1400,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                3,//minWeight
                8,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                20,//Fish rarity
                10,//minDeepLevel
                40,//maxDeepLevel
                55,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "COLD" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                10,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:snowball",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.GRAIN, BaitEnum.VEGETABLE }//Valid bait items
        ));
    	
    	fishDataMap.put("Frost Minnow", FishData.createData(
        		"Frost Minnow",//Fish ID
                "advanced-fishing:fish",//Item
                3,//Metadata
                "a small, cold fish that tends to accumulate ice on its scales",//Description
                800,//minFishTime
                1000,//maxFishTime
                3,//minErrorVariance
                5,//maxErrorVariance
                1,//minWeight
                1,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                30,//Fish rarity
                20,//minDeepLevel
                60,//maxDeepLevel
                55,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "COLD" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                10,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:ice",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                false,//Fillet uses weight
                new BaitEnum[] { BaitEnum.GRAIN, BaitEnum.VEGETABLE }//Valid bait items
        ));
    	
    	fishDataMap.put("Glacier Anchovy", FishData.createData(
        		"Glacier Anchovy",//Fish ID
                "advanced-fishing:fish",//Item
                35,//Metadata
                "a tiny arctic fish, often found as large schools frozen in ice",//Description
                600,//minFishTime
                800,//maxFishTime
                3,//minErrorVariance
                5,//maxErrorVariance
                1,//minWeight
                1,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                20,//Fish rarity
                50,//minDeepLevel
                100,//maxDeepLevel
                55,//minYLevel
                100,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "COLD" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                10,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                false,//Use default fillets
                "minecraft:packed_ice",//Custom fillet item
                0,//Custom fillet item metadata
                false,//Fillet uses weight
                new BaitEnum[] { BaitEnum.GRAIN, BaitEnum.VEGETABLE }//Valid bait items
        ));
    	
    	fishDataMap.put("Ruffe", FishData.createData(
        		"Ruffe",//Fish ID
                "advanced-fishing:fish",//Item
                17,//Metadata
                "a small, invasive, bottom-dwelling fish",//Description
                1000,//minFishTime
                1200,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                1,//minWeight
                1,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                30,//Fish rarity
                5,//minDeepLevel
                15,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "SWAMP" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                false,//Fillet uses weight
                new BaitEnum[] { BaitEnum.GRAIN, BaitEnum.FRUIT, BaitEnum.VEGETABLE, BaitEnum.MEAT_NORMAL }//Valid bait items
        ));
    	
    	fishDataMap.put("Mud Tuna", FishData.createData(
        		"Mud Tuna",//Fish ID
                "advanced-fishing:fish",//Item
                2,//Metadata
                "a large fish often indistinguishable from the surrounding mud and clay",//Description
                1400,//minFishTime
                1800,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                20,//minWeight
                40,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                8,//Fish rarity
                20,//minDeepLevel
                40,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                20,//reproductionTime
                7,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "SWAMP" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:clay_ball",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.GRAIN, BaitEnum.VEGETABLE }//Valid bait items
        ));
    	
    	fishDataMap.put("Green-Finned Plaice", FishData.createData(
        		"Green-Finned Plaice",//Fish ID
                "advanced-fishing:fish",//Item
                30,//Metadata
                "a small fish that disguises itself among the lilypads",//Description
                1200,//minFishTime
                1400,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                1,//minWeight
                2,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                10,//Fish rarity
                3,//minDeepLevel
                10,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "SWAMP" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                false,//Use default fillets
                "minecraft:waterlily",//Custom fillet item
                0,//Custom fillet item metadata
                false,//Fillet uses weight
                new BaitEnum[] { BaitEnum.VEGETABLE }//Valid bait items
        ));
    	
    	fishDataMap.put("Red Fungalfin", FishData.createData(
        		"Red Fungalfin",//Fish ID
                "advanced-fishing:fish",//Item
                27,//Metadata
                "a strange, cave-dwelling fish covered in red fungal growth that hides from light",//Description
                1200,//minFishTime
                1400,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                2,//minWeight
                8,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                15,//Fish rarity
                10,//minDeepLevel
                40,//maxDeepLevel
                15,//minYLevel
                55,//maxYLevel
                8,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                true,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:red_mushroom",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.VEGETABLE, BaitEnum.GRAIN }//Valid bait items
        ));
    	
    	fishDataMap.put("Brown Fungalfin", FishData.createData(
        		"Brown Fungalfin",//Fish ID
                "advanced-fishing:fish",//Item
                28,//Metadata
                "a strange, cave-dwelling fish covered in brown fungal growth that hides from light",//Description
                1200,//minFishTime
                1400,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                2,//minWeight
                8,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                15,//Fish rarity
                10,//minDeepLevel
                40,//maxDeepLevel
                15,//minYLevel
                55,//maxYLevel
                8,//maxLightLevel
                15,//reproductionTime
                6,//eatingFrequency
                true,//Trackable
                true,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:brown_mushroom",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.VEGETABLE, BaitEnum.GRAIN }//Valid bait items
        ));
    	
    	fishDataMap.put("Specular Snapper", FishData.createData(
        		"Specular Snapper",//Fish ID
                "advanced-fishing:fish",//Item
                6,//Metadata
                "a large, cave-dwelling fish typically used as food for a hungry explorer",//Description
                1400,//minFishTime
                1800,//maxFishTime
                1,//minErrorVariance
                4,//maxErrorVariance
                10,//minWeight
                30,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                10,//Fish rarity
                20,//minDeepLevel
                50,//maxDeepLevel
                25,//minYLevel
                55,//maxYLevel
                16,//maxLightLevel
                20,//reproductionTime
                7,//eatingFrequency
                true,//Trackable
                true,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.VEGETABLE, BaitEnum.GRAIN, BaitEnum.MEAT_NORMAL }//Valid bait items
        ));
    	
    	fishDataMap.put("Carbonic Trout", FishData.createData(
        		"Carbonic Trout",//Fish ID
                "advanced-fishing:fish",//Item
                7,//Metadata
                "a strange cave-fish that seems to be more coal than fish",//Description
                1200,//minFishTime
                1600,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                5,//minWeight
                10,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                20,//Fish rarity
                30,//minDeepLevel
                60,//maxDeepLevel
                15,//minYLevel
                45,//maxYLevel
                16,//maxLightLevel
                20,//reproductionTime
                7,//eatingFrequency
                true,//Trackable
                true,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                false,//Use default fillets
                "minecraft:coal",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.VEGETABLE, BaitEnum.MEAT_NORMAL }//Valid bait items
        ));
    	
    	fishDataMap.put("Lazulicanth", FishData.createData(
        		"Lazulicanth",//Fish ID
                "advanced-fishing:fish",//Item
                31,//Metadata
                "a pretty, blue, cave-fish covered in gem-like scales",//Description
                1200,//minFishTime
                1400,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                2,//minWeight
                8,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                10,//Fish rarity
                40,//minDeepLevel
                80,//maxDeepLevel
                5,//minYLevel
                30,//maxYLevel
                16,//maxLightLevel
                20,//reproductionTime
                7,//eatingFrequency
                true,//Trackable
                true,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:dye",//Scaling item
                4,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.VEGETABLE, BaitEnum.MEAT_NORMAL }//Valid bait items
        ));
    	
    	fishDataMap.put("Charged Bullhead", FishData.createData(
        		"Charged Bullhead",//Fish ID
                "advanced-fishing:fish",//Item
                32,//Metadata
                "a shimmering, red, cave-fish that seems to electrically charge the waters it swims in",//Description
                1200,//minFishTime
                1400,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                2,//minWeight
                8,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                10,//Fish rarity
                40,//minDeepLevel
                80,//maxDeepLevel
                5,//minYLevel
                30,//maxYLevel
                16,//maxLightLevel
                20,//reproductionTime
                7,//eatingFrequency
                true,//Trackable
                true,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:redstone",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.VEGETABLE, BaitEnum.MEAT_NORMAL }//Valid bait items
        ));
    	
    	fishDataMap.put("Ender Synanceia", FishData.createData(
        		"Ender Synanceia",//Fish ID
                "advanced-fishing:fish",//Item
                13,//Metadata
                "a chalky, white fish found in the depths covered in venomous, stony spines",//Description
                1600,//minFishTime
                1800,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                5,//minWeight
                15,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.VOID,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                30,//Fish rarity
                40,//minDeepLevel
                80,//maxDeepLevel
                -1,//minYLevel
                30,//maxYLevel
                16,//maxLightLevel
                30,//reproductionTime
                11,//eatingFrequency
                true,//Trackable
                true,//Biome list is blacklist?
                false,//Dimension list is blacklist?
                new String[] { },//Biome Tag List
                new int[]{ 1 },//Dimension list
                5,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:end_stone",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.GRAIN, BaitEnum.VEGETABLE, BaitEnum.MEAT_NORMAL}//Valid bait items
        ));
    	
    	fishDataMap.put("Pearlfish", FishData.createData(
        		"Pearlfish",//Fish ID
                "advanced-fishing:fish",//Item
                14,//Metadata
                "a small, pearlescent fish that seems to teleport around the depths of the void",//Description
                1200,//minFishTime
                1400,//maxFishTime
                1,//minErrorVariance
                4,//maxErrorVariance
                1,//minWeight
                2,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.VOID,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                15,//Fish rarity
                60,//minDeepLevel
                100,//maxDeepLevel
                -1,//minYLevel
                30,//maxYLevel
                16,//maxLightLevel
                30,//reproductionTime
                11,//eatingFrequency
                true,//Trackable
                true,//Biome list is blacklist?
                false,//Dimension list is blacklist?
                new String[] { },//Biome Tag List
                new int[]{ 1 },//Dimension list
                5,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                true,//Allow filleting
                false,//Use default fillets
                "minecraft:ender_pearl",//Custom fillet item
                0,//Custom fillet item metadata
                false,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL }//Valid bait items
        ));
    	
    	fishDataMap.put("Fruiting Voidfin", FishData.createData(
        		"Fruiting Voidfin",//Fish ID
                "advanced-fishing:fish",//Item
                15,//Metadata
                "a fish covered in fruiting fungal growths prevalent in the murky void",//Description
                1400,//minFishTime
                1800,//maxFishTime
                2,//minErrorVariance
                4,//maxErrorVariance
                2,//minWeight
                8,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.VOID,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                4,//Fish rarity
                80,//minDeepLevel
                120,//maxDeepLevel
                -1,//minYLevel
                30,//maxYLevel
                16,//maxLightLevel
                30,//reproductionTime
                11,//eatingFrequency
                true,//Trackable
                true,//Biome list is blacklist?
                false,//Dimension list is blacklist?
                new String[] { },//Biome Tag List
                new int[]{ 1 },//Dimension list
                5,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:chorus_fruit",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.FRUIT, BaitEnum.MEAT_NORMAL }//Valid bait items
        ));
    	
    	fishDataMap.put("Midnight Bream", FishData.createData(
        		"Midnight Bream",//Fish ID
                "advanced-fishing:fish",//Item
                8,//Metadata
                "a pitch-black colored fish with razor-sharp, hardened scales",//Description
                1200,//minFishTime
                1400,//maxFishTime
                1,//minErrorVariance
                3,//maxErrorVariance
                2,//minWeight
                6,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.LAVA,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                15,//Fish rarity
                20,//minDeepLevel
                60,//maxDeepLevel
                0,//minYLevel
                140,//maxYLevel
                16,//maxLightLevel
                20,//reproductionTime
                7,//eatingFrequency
                true,//Trackable
                true,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { },//Biome Tag List
                new int[]{ 1 },//Dimension list
                8,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:obsidian",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.MEAT_EXTRA }//Valid bait items
        ));
    	
    	fishDataMap.put("Magmatic Jellyfish", FishData.createData(
        		"Magmatic Jellyfish",//Fish ID
                "advanced-fishing:fish",//Item
                1,//Metadata
                "a smoldering mass of slime and lava-like tendrils",//Description
                1200,//minFishTime
                1400,//maxFishTime
                1,//minErrorVariance
                3,//maxErrorVariance
                1,//minWeight
                4,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.LAVA,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                10,//Fish rarity
                20,//minDeepLevel
                60,//maxDeepLevel
                0,//minYLevel
                140,//maxYLevel
                16,//maxLightLevel
                20,//reproductionTime
                7,//eatingFrequency
                true,//Trackable
                true,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { },//Biome Tag List
                new int[]{ 1 },//Dimension list
                8,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                false,//Use default fillets
                "minecraft:magma_cream",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.MEAT_EXTRA }//Valid bait items
        ));
    	
    	fishDataMap.put("Wrackke", FishData.createData(
        		"Wrackke",//Fish ID
                "advanced-fishing:fish",//Item
                9,//Metadata
                "a clump of netherrack that fell into lava and seems to have come to life",//Description
                1200,//minFishTime
                1400,//maxFishTime
                1,//minErrorVariance
                3,//maxErrorVariance
                2,//minWeight
                6,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.LAVA,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                15,//Fish rarity
                10,//minDeepLevel
                40,//maxDeepLevel
                0,//minYLevel
                140,//maxYLevel
                16,//maxLightLevel
                20,//reproductionTime
                7,//eatingFrequency
                true,//Trackable
                true,//Biome list is blacklist?
                false,//Dimension list is blacklist?
                new String[] { },//Biome Tag List
                new int[]{ -1 },//Dimension list
                5,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                false,//Use default fillets
                "minecraft:netherrack",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.MEAT_EXTRA }//Valid bait items
        ));
    	
    	fishDataMap.put("Withered Crucian", FishData.createData(
        		"Withered Crucian",//Fish ID
                "advanced-fishing:fish",//Item
                24,//Metadata
                "one of the many souls trapped inside soulsand, returned as a fish",//Description
                1000,//minFishTime
                1200,//maxFishTime
                1,//minErrorVariance
                3,//maxErrorVariance
                1,//minWeight
                5,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.LAVA,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                10,//Fish rarity
                20,//minDeepLevel
                60,//maxDeepLevel
                0,//minYLevel
                140,//maxYLevel
                16,//maxLightLevel
                20,//reproductionTime
                7,//eatingFrequency
                true,//Trackable
                true,//Biome list is blacklist?
                false,//Dimension list is blacklist?
                new String[] { },//Biome Tag List
                new int[]{ -1 },//Dimension list
                5,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                false,//Use default fillets
                "minecraft:soul_sand",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.GRAIN, BaitEnum.FRUIT, BaitEnum.VEGETABLE }//Valid bait items
        ));
    	
    	fishDataMap.put("Quartzander", FishData.createData(
        		"Quartzander",//Fish ID
                "advanced-fishing:fish",//Item
                10,//Metadata
                "a large, lava-dwelling fish covered in protective quartzite scales",//Description
                1400,//minFishTime
                1800,//maxFishTime
                1,//minErrorVariance
                3,//maxErrorVariance
                8,//minWeight
                16,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.LAVA,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                10,//Fish rarity
                40,//minDeepLevel
                100,//maxDeepLevel
                0,//minYLevel
                140,//maxYLevel
                16,//maxLightLevel
                25,//reproductionTime
                9,//eatingFrequency
                true,//Trackable
                true,//Biome list is blacklist?
                false,//Dimension list is blacklist?
                new String[] { },//Biome Tag List
                new int[]{ -1 },//Dimension list
                5,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:quartz",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL }//Valid bait items
        ));
    	
    	fishDataMap.put("Warthead", FishData.createData(
        		"Warthead",//Fish ID
                "advanced-fishing:fish",//Item
                29,//Metadata
                "a large, sedentary, lava-dwelling fish covered in many fungal growths",//Description
                1400,//minFishTime
                1800,//maxFishTime
                1,//minErrorVariance
                3,//maxErrorVariance
                8,//minWeight
                16,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.LAVA,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                10,//Fish rarity
                40,//minDeepLevel
                100,//maxDeepLevel
                0,//minYLevel
                140,//maxYLevel
                16,//maxLightLevel
                25,//reproductionTime
                9,//eatingFrequency
                true,//Trackable
                true,//Biome list is blacklist?
                false,//Dimension list is blacklist?
                new String[] { },//Biome Tag List
                new int[]{ -1 },//Dimension list
                5,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:nether_wart",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL }//Valid bait items
        ));
    	
    	fishDataMap.put("Glowfin", FishData.createData(
        		"Glowfin",//Fish ID
                "advanced-fishing:fish",//Item
                11,//Metadata
                "a fast, shiny fish that radiates light",//Description
                1000,//minFishTime
                1400,//maxFishTime
                1,//minErrorVariance
                3,//maxErrorVariance
                2,//minWeight
                8,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.LAVA,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                5,//Fish rarity
                80,//minDeepLevel
                120,//maxDeepLevel
                0,//minYLevel
                140,//maxYLevel
                16,//maxLightLevel
                25,//reproductionTime
                9,//eatingFrequency
                true,//Trackable
                true,//Biome list is blacklist?
                false,//Dimension list is blacklist?
                new String[] { },//Biome Tag List
                new int[]{ -1 },//Dimension list
                5,//Time alive outside of water
                true,//Allow scaling?
                "minecraft:glowstone_dust",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.MEAT_EXTRA }//Valid bait items
        ));
    	
    	fishDataMap.put("Blazing Chichlid", FishData.createData(
        		"Blazing Chichlid",//Fish ID
                "advanced-fishing:fish",//Item
                12,//Metadata
                "a small fish that seems to be made out of pure heat",//Description
                800,//minFishTime
                1200,//maxFishTime
                1,//minErrorVariance
                3,//maxErrorVariance
                1,//minWeight
                4,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.LAVA,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                5,//Fish rarity
                100,//minDeepLevel
                140,//maxDeepLevel
                0,//minYLevel
                140,//maxYLevel
                16,//maxLightLevel
                25,//reproductionTime
                9,//eatingFrequency
                true,//Trackable
                true,//Biome list is blacklist?
                false,//Dimension list is blacklist?
                new String[] { },//Biome Tag List
                new int[]{ -1 },//Dimension list
                5,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                true,//Allow filleting
                false,//Use default fillets
                "minecraft:blaze_rod",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.MEAT_NORMAL, BaitEnum.MEAT_EXTRA }//Valid bait items
        ));
    	
    	fishDataMap.put("Magikarp", FishData.createData(
        		"Magikarp",//Fish ID
                "advanced-fishing:fish",//Item
                38,//Metadata
                "a splashy fish",//Description
                1400,//minFishTime
                1600,//maxFishTime
                1,//minErrorVariance
                2,//maxErrorVariance
                100,//minWeight
                200,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                1,//Fish rarity
                120,//minDeepLevel
                140,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                40,//reproductionTime
                14,//eatingFrequency
                false,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "RIVER" },//Biome Tag List
                new int[]{ -1, 1 },//Dimension list
                5,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                true,//Use weight when scaling
                false,//Allow filleting
                true,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                true,//Fillet uses weight
                new BaitEnum[] { BaitEnum.FRUIT }//Valid bait items
        ));
    	
        return fishDataMap;
    }

    private static Map<String, FishData> getVanillaFishes() {
        Map<String, FishData> fishDataMap = new HashMap<>();

        fishDataMap.put("Clownfish", FishData.createData(
        		"Clownfish",//Fish ID
                "minecraft:fish",//Item
                2,//Metadata
                "a bright orange fish with white stripes and black edges",//Description
                600,//minFishTime
                800,//maxFishTime
                3,//minErrorVariance
                5,//maxErrorVariance
                1,//minWeight
                1,//maxWeight
                FishData.TimeToFish.ANY,//Time of day for fish
                FishData.FishingLiquid.WATER,//Liquid to fish in
                false,//Rain required
                false,//Thunder required
                15,//Fish rarity
                5,//minDeepLevel
                25,//maxDeepLevel
                55,//minYLevel
                80,//maxYLevel
                16,//maxLightLevel
                9,//reproductionTime
                4,//eatingFrequency
                true,//Trackable
                false,//Biome list is blacklist?
                true,//Dimension list is blacklist?
                new String[] { "OCEAN", "BEACH" },//Biome Tag List
                new int[]{ -1, 1},//Dimension list
                15,//Time alive outside of water
                false,//Allow scaling?
                "",//Scaling item
                0,//Scaling item Metadata
                false,//Use weight when scaling
                false,//Allow filleting
                false,//Use default fillets
                "",//Custom fillet item
                0,//Custom fillet item metadata
                false,//Fillet uses weight
                new BaitEnum[] { BaitEnum.VEGETABLE }//Valid bait items
        ));

        fishDataMap.put("Pufferfish", FishData.createData(
        		"Pufferfish",
                "minecraft:fish",
                3,
                "a small, yellow, thorny fish that inflates when threatened",
                1200,
                1600,
                2,
                4,
                8,
                14,
                FishData.TimeToFish.ANY,
                FishData.FishingLiquid.WATER,
                false,
                false,
                6,
                10,
                30,
                55,
                80,
                16,
                12,
                5,
                true,
                false,
                true,
                new String[] { "OCEAN", "BEACH" },
                new int[]{ -1, 1},
                20,
                true,
                "minecraft:arrow",
                0,
                true,
                true,
                true,
                "",
                0,
                true,
                new BaitEnum[] { BaitEnum.MEAT_NORMAL }//Valid bait items
        ));

        fishDataMap.put("Cod", FishData.createData(
        		"Cod",
                "minecraft:fish",
                0,
                "a large grey-green stout bodied fish with a large head and long chin barbel",
                1600,
                2000,
                1,
                3,
                10,
                40,
                FishData.TimeToFish.ANY,
                FishData.FishingLiquid.WATER,
                false,
                false,
                20,
                40,
                100,
                55,
                80,
                16,
                20,
                8,
                true,
                false,
                true,
                new String[] { "OCEAN", "COLD" },
                new int[]{ -1, 1},
                20,
                false,
                "",
                0,
                false,
                true,
                true,
                "",
                0,
                true,
                new BaitEnum[] { BaitEnum.MEAT_NORMAL }//Valid bait items
        ));

        fishDataMap.put("Red Salmon", FishData.createData(
        		"Red Salmon",
                "minecraft:fish",
                1,
                "a decent sized red fish with a hooked jaw",
                1000,
                1400,
                1,
                4,
                2,
                8,
                FishData.TimeToFish.ANY,
                FishData.FishingLiquid.WATER,
                false,
                false,
                20,
                10,
                60,
                55,
                120,
                16,
                15,
                6,
                true,
                false,
                true,
                new String[] { "OCEAN", "BEACH", "COLD", "RIVER", "WATER", "MOUNTAIN" },
                new int[]{ -1, 1},
                20,
                false,
                "",
                0,
                false,
                true,
                true,
                "",
                0,
                true,
                new BaitEnum[] { BaitEnum.MEAT_NORMAL }//Valid bait items
        ));

        return fishDataMap;
    }
}
