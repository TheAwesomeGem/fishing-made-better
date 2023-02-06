package net.theawesomegem.fishingmadebetter;

import net.minecraftforge.fml.common.Loader;
import net.theawesomegem.fishingmadebetter.common.data.FishData;
import net.theawesomegem.fishingmadebetter.common.data.FishData.BaitEnum;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.resources.I18n;

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
        
        fishDataMap.put("item.Fish.Bluegill.name", FishData.createData(
                "item.Fish.Bluegill.name",//Fish ID
                "aquaculture:fish",//Item
                0,//Metadata
                "item.Fish.Bluegill.desc",//Description
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
        
        fishDataMap.put("item.Fish.Perch.name", FishData.createData(
        		"item.Fish.Perch.name",//Fish ID
                "aquaculture:fish",//Item
                1,//Metadata
                "item.Fish.Perch.desc",//Description
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
        
        fishDataMap.put("item.Fish.Gar.name", FishData.createData(
        		"item.Fish.Gar.name",//Fish ID
                "aquaculture:fish",//Item
                2,//Metadata
                "item.Fish.Gar.desc",//Description
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
        
        fishDataMap.put("item.Fish.Bass.name", FishData.createData(
        		"item.Fish.Bass.name",//Fish ID
                "aquaculture:fish",//Item
                3,//Metadata
                "item.Fish.Bass.desc",//Description
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
        
        fishDataMap.put("item.Fish.Muskellunge.name", FishData.createData(
        		"item.Fish.Muskellunge.name",//Fish ID
                "aquaculture:fish",//Item
                4,//Metadata
                "item.Fish.Muskellunge.desc",//Description
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
        
        fishDataMap.put("item.Fish.Brown_Trout.name", FishData.createData(
        		"item.Fish.Brown_Trout.name",//Fish ID
                "aquaculture:fish",//Item
                5,//Metadata
                "item.Fish.Brown_Trout.desc",//Description
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

        fishDataMap.put("item.Fish.Catfish.name", FishData.createData(
        		"item.Fish.Catfish.name",//Fish ID
                "aquaculture:fish",//Item
                6,//Metadata
                "item.Fish.Catfish.desc",//Description
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
        
        fishDataMap.put("item.Fish.Carp.name", FishData.createData(
        		"item.Fish.Carp.name",//Fish ID
                "aquaculture:fish",//Item
                7,//Metadata
                "item.Fish.Carp.desc",//Description
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
        
        fishDataMap.put("item.Fish.Blowfish.name", FishData.createData(
        		"item.Fish.Blowfish.name",//Fish ID
                "aquaculture:fish",//Item
                8,//Metadata
                "item.Fish.Blowfish.desc",//Description
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
        
        fishDataMap.put("item.Fish.Red_Grouper.name", FishData.createData(
        		"item.Fish.Red_Grouper.name",//Fish ID
                "aquaculture:fish",//Item
                9,//Metadata
                "item.Fish.Red_Grouper.desc",//Description
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
        
        fishDataMap.put("item.Fish.Salmon.name", FishData.createData(
        		"item.Fish.Salmon.name",//Fish ID
                "aquaculture:fish",//Item
                10,//Metadata
                "item.Fish.Salmon.desc",//Description
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
        
        fishDataMap.put("item.Fish.Tuna.name", FishData.createData(
        		"item.Fish.Tuna.name",//Fish ID
                "aquaculture:fish",//Item
                11,//Metadata
                "item.Fish.Tuna.desc",//Description
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
        
        fishDataMap.put("item.Fish.Swordfish.name", FishData.createData(
        		"item.Fish.Swordfish.name",//Fish ID
                "aquaculture:fish",//Item
                12,//Metadata
                "item.Fish.Swordfish.desc",//Description
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
        
        fishDataMap.put("item.Fish.Shark.name", FishData.createData(
        		"item.Fish.Shark.name",//Fish ID
                "aquaculture:fish",//Item
                13,//Metadata
                "item.Fish.Shark.desc",//Description
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
        
        fishDataMap.put("item.Fish.Whale.name", FishData.createData(
        		"item.Fish.Whale.name",//Fish ID
                "aquaculture:fish",//Item
                14,//Metadata
                "item.Fish.Whale.desc",//Description
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
        
        fishDataMap.put("item.Fish.Squid.name", FishData.createData(
        		"item.Fish.Squid.name",//Fish ID
                "aquaculture:fish",//Item
                15,//Metadata
                "item.Fish.Squid.desc",//Description
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
        
        fishDataMap.put("item.Fish.Jellyfish.name", FishData.createData(
        		"item.Fish.Jellyfish.name",//Fish ID
                "aquaculture:fish",//Item
                16,//Metadata
                "item.Fish.Jellyfish.desc",//Description
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
        
        fishDataMap.put("item.Fish.Frog.name", FishData.createData(
        		"item.Fish.Frog.name",//Fish ID
                "aquaculture:fish",//Item
                17,//Metadata
                "item.Fish.Frog.desc",//Description
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
        
        fishDataMap.put("item.Fish.Turtle.name", FishData.createData(
        		"item.Fish.Turtle.name",//Fish ID
                "aquaculture:fish",//Item
                18,//Metadata
                "item.Fish.Turtle.desc",//Description
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
        
        fishDataMap.put("item.Fish.Leech.name", FishData.createData(
        		"item.Fish.Leech.name",//Fish ID
                "aquaculture:fish",//Item
                19,//Metadata
                "item.Fish.Leech.desc",//Description
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
        
        fishDataMap.put("item.Fish.Pirahna.name", FishData.createData(
        		"item.Fish.Pirahna.name",//Fish ID
                "aquaculture:fish",//Item
                20,//Metadata
                "item.Fish.Pirahna.desc",//Description
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
        
        fishDataMap.put("item.Fish.Electric_Eel.name", FishData.createData(
        		"item.Fish.Electric_Eel.name",//Fish ID
                "aquaculture:fish",//Item
                21,//Metadata
                "item.Fish.Electric_Eel.desc",//Description
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
        
        fishDataMap.put("item.Fish.Tambaqui.name", FishData.createData(
        		"item.Fish.Tambaqui.name",//Fish ID
                "aquaculture:fish",//Item
                22,//Metadata
                "item.Fish.Tambaqui.desc",//Description
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
        
        fishDataMap.put("item.Fish.Arapaima.name", FishData.createData(
        		"item.Fish.Arapaima.name",//Fish ID
                "aquaculture:fish",//Item
                23,//Metadata
                "item.Fish.Arapaima.desc",//Description
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
        
        fishDataMap.put("item.Fish.Cod.name", FishData.createData(
        		"item.Fish.Cod.name",//Fish ID
                "aquaculture:fish",//Item
                24,//Metadata
                "item.Fish.Cod.desc",//Description
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
        
        fishDataMap.put("item.Fish.Pollock.name", FishData.createData(
        		"item.Fish.Pollock.name",//Fish ID
                "aquaculture:fish",//Item
                25,//Metadata
                "item.Fish.Pollock.desc",//Description
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
        
        fishDataMap.put("item.Fish.Herring.name", FishData.createData(
        		"item.Fish.Herring.name",//Fish ID
                "aquaculture:fish",//Item
                26,//Metadata
                "item.Fish.Herring.desc",//Description
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
        
        fishDataMap.put("item.Fish.Halibut.name", FishData.createData(
        		"item.Fish.Halibut.name",//Fish ID
                "aquaculture:fish",//Item
                27,//Metadata
                "item.Fish.Halibut.desc",//Description
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
        
        fishDataMap.put("item.Fish.Pink_Salmon.name", FishData.createData(
        		"item.Fish.Pink_Salmon.name",//Fish ID
                "aquaculture:fish",//Item
                28,//Metadata
                "item.Fish.Pink_Salmon.desc",//Description
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
        
        fishDataMap.put("item.Fish.Rainbow_Trout.name", FishData.createData(
        		"item.Fish.Rainbow_Trout.name",//Fish ID
                "aquaculture:fish",//Item
                29,//Metadata
                "item.Fish.Rainbow_Trout.desc",//Description
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
        
        fishDataMap.put("item.Fish.Blackfish.name", FishData.createData(
        		"item.Fish.Blackfish.name",//Fish ID
                "aquaculture:fish",//Item
                30,//Metadata
                "item.Fish.Blackfish.desc",//Description
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
        
        fishDataMap.put("item.Fish.Capitaine.name", FishData.createData(
        		"item.Fish.Capitaine.name",//Fish ID
                "aquaculture:fish",//Item
                31,//Metadata
                "item.Fish.Capitaine.desc",//Description
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
        
        fishDataMap.put("item.Fish.Boulti.name", FishData.createData(
        		"item.Fish.Boulti.name",//Fish ID
                "aquaculture:fish",//Item
                32,//Metadata
                "item.Fish.Boulti.desc",//Description
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

        fishDataMap.put("item.Fish.Bagrid.name", FishData.createData(
        		"item.Fish.Bagrid.name",//Fish ID
                "aquaculture:fish",//Item
                33,//Metadata
                "item.Fish.Bagrid.desc",//Description
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
        
        fishDataMap.put("item.Fish.Syndontis.name", FishData.createData(
        		"item.Fish.Syndontis.name",//Fish ID
                "aquaculture:fish",//Item
                34,//Metadata
                "item.Fish.Syndontis.desc",//Description
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
        
        fishDataMap.put("item.Fish.Red_Shrooma.name", FishData.createData(
        		"item.Fish.Red_Shrooma.name",//Fish ID
                "aquaculture:fish",//Item
                35,//Metadata
                "item.Fish.Red_Shrooma.desc",//Description
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
        
        fishDataMap.put("item.Fish.Brown_Shrooma.name", FishData.createData(
        		"item.Fish.Brown_Shrooma.name",//Fish ID
                "aquaculture:fish",//Item
                36,//Metadata
                "item.Fish.Brown_Shrooma.desc",//Description
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
        
        fishDataMap.put("item.Fish.Goldfish.name", FishData.createData(
        		"item.Fish.Goldfish.name",//Fish ID
                "aquaculture:fish",//Item
                37,//Metadata
                "item.Fish.Goldfish.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.squid.name", FishData.createData(
        		"item.advanced-fishing.squid.name",//Fish ID
                "advanced-fishing:fish",//Item
                23,//Metadata
                "item.advanced-fishing.squid.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.catfish.name", FishData.createData(
        		"item.advanced-fishing.catfish.name",//Fish ID
                "advanced-fishing:fish",//Item
                36,//Metadata
                "item.advanced-fishing.catfish.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.pike.name", FishData.createData(
        		"item.advanced-fishing.pike.name",//Fish ID
                "advanced-fishing:fish",//Item
                37,//Metadata
                "item.advanced-fishing.pike.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.explosive_crucian.name", FishData.createData(
        		"item.advanced-fishing.explosive_crucian.name",//Fish ID
                "advanced-fishing:fish",//Item
                16,//Metadata
                "item.advanced-fishing.explosive_crucian.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.angelfish.name", FishData.createData(
        		"item.advanced-fishing.angelfish.name",//Fish ID
                "advanced-fishing:fish",//Item
                19,//Metadata
                "item.advanced-fishing.angelfish.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.blue_jellyfish.name", FishData.createData(
        		"item.advanced-fishing.blue_jellyfish.name",//Fish ID
                "advanced-fishing:fish",//Item
                0,//Metadata
                "item.advanced-fishing.blue_jellyfish.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.sponge_eater.name", FishData.createData(
        		"item.advanced-fishing.sponge_eater.name",//Fish ID
                "advanced-fishing:fish",//Item
                21,//Metadata
                "item.advanced-fishing.sponge_eater.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.abyssal_lurker.name", FishData.createData(
        		"item.advanced-fishing.abyssal_lurker.name",//Fish ID
                "advanced-fishing:fish",//Item
                33,//Metadata
                "item.advanced-fishing.abyssal_lurker.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.angler_fish.name", FishData.createData(
        		"item.advanced-fishing.angler_fish.name",//Fish ID
                "advanced-fishing:fish",//Item
                20,//Metadata
                "item.advanced-fishing.angler_fish.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.green_jellyfish.name", FishData.createData(
        		"item.advanced-fishing.green_jellyfish.name",//Fish ID
                "advanced-fishing:fish",//Item
                39,//Metadata
                "item.advanced-fishing.green_jellyfish.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.bone_fish.name", FishData.createData(
        		"item.advanced-fishing.bone_fish.name",//Fish ID
                "advanced-fishing:fish",//Item
                40,//Metadata
                "item.advanced-fishing.bone_fish.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.spookyfin.name", FishData.createData(
        		"item.advanced-fishing.spookyfin.name",//Fish ID
                "advanced-fishing:fish",//Item
                42,//Metadata
                "item.advanced-fishing.spookyfin.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.cursed_koi.name", FishData.createData(
        		"item.advanced-fishing.cursed_koi.name",//Fish ID
                "advanced-fishing:fish",//Item
                41,//Metadata
                "item.advanced-fishing.cursed_koi.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.piranha.name", FishData.createData(
        		"item.advanced-fishing.piranha.name",//Fish ID
                "advanced-fishing:fish",//Item
                4,//Metadata
                "item.advanced-fishing.piranha.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.sparkling_eel.name", FishData.createData(
        		"item.advanced-fishing.sparkling_eel.name",//Fish ID
                "advanced-fishing:fish",//Item
                18,//Metadata
                "item.advanced-fishing.sparkling_eel.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.mandarinfish.name", FishData.createData(
        		"item.advanced-fishing.mandarinfish.name",//Fish ID
                "advanced-fishing:fish",//Item
                26,//Metadata
                "item.advanced-fishing.mandarinfish.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.sandy_bass.name", FishData.createData(
        		"item.advanced-fishing.sandy_bass.name",//Fish ID
                "advanced-fishing:fish",//Item
                25,//Metadata
                "item.advanced-fishing.sandy_bass.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.golden_koi.name", FishData.createData(
        		"item.advanced-fishing.golden_koi.name",//Fish ID
                "advanced-fishing:fish",//Item
                5,//Metadata
                "item.advanced-fishing.golden_koi.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.sunfish.name", FishData.createData(
        		"item.advanced-fishing.sunfish.name",//Fish ID
                "advanced-fishing:fish",//Item
                34,//Metadata
                "item.advanced-fishing.sunfish.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.snowy_walleye.name", FishData.createData(
        		"item.advanced-fishing.snowy_walleye.name",//Fish ID
                "advanced-fishing:fish",//Item
                22,//Metadata
                "item.advanced-fishing.snowy_walleye.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.frost_minnow.name", FishData.createData(
        		"item.advanced-fishing.frost_minnow.name",//Fish ID
                "advanced-fishing:fish",//Item
                3,//Metadata
                "item.advanced-fishing.frost_minnow.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.glacier_anchovy.name", FishData.createData(
        		"item.advanced-fishing.glacier_anchovy.name",//Fish ID
                "advanced-fishing:fish",//Item
                35,//Metadata
                "item.advanced-fishing.glacier_anchovy.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.ruffe.name", FishData.createData(
        		"item.advanced-fishing.ruffe.name",//Fish ID
                "advanced-fishing:fish",//Item
                17,//Metadata
                "item.advanced-fishing.ruffe.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.mud_tuna.name", FishData.createData(
        		"item.advanced-fishing.mud_tuna.name",//Fish ID
                "advanced-fishing:fish",//Item
                2,//Metadata
                "item.advanced-fishing.mud_tuna.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.swamp_plaice.name", FishData.createData(
        		"item.advanced-fishing.swamp_plaice.name",//Fish ID
                "advanced-fishing:fish",//Item
                30,//Metadata
                "item.advanced-fishing.swamp_plaice.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.red_shroomfin.name", FishData.createData(
        		"item.advanced-fishing.red_shroomfin.name",//Fish ID
                "advanced-fishing:fish",//Item
                27,//Metadata
                "item.advanced-fishing.red_shroomfin.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.brown_shroomfin.name", FishData.createData(
        		"item.advanced-fishing.brown_shroomfin.name",//Fish ID
                "advanced-fishing:fish",//Item
                28,//Metadata
                "item.advanced-fishing.brown_shroomfin.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.specular_snapper.name", FishData.createData(
        		"item.advanced-fishing.specular_snapper.name",//Fish ID
                "advanced-fishing:fish",//Item
                6,//Metadata
                "item.advanced-fishing.specular_snapper.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.cave_trout.name", FishData.createData(
        		"item.advanced-fishing.cave_trout.name",//Fish ID
                "advanced-fishing:fish",//Item
                7,//Metadata
                "item.advanced-fishing.cave_trout.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.crystal_mullet.name", FishData.createData(
        		"item.advanced-fishing.crystal_mullet.name",//Fish ID
                "advanced-fishing:fish",//Item
                31,//Metadata
                "item.advanced-fishing.crystal_mullet.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.charged_bullhead.name", FishData.createData(
        		"item.advanced-fishing.charged_bullhead.name",//Fish ID
                "advanced-fishing:fish",//Item
                32,//Metadata
                "item.advanced-fishing.charged_bullhead.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.ender_shad.name", FishData.createData(
        		"item.advanced-fishing.ender_shad.name",//Fish ID
                "advanced-fishing:fish",//Item
                13,//Metadata
                "item.advanced-fishing.ender_shad.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.pearl_sardine.name", FishData.createData(
        		"item.advanced-fishing.pearl_sardine.name",//Fish ID
                "advanced-fishing:fish",//Item
                14,//Metadata
                "item.advanced-fishing.pearl_sardine.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.chorus_koi.name", FishData.createData(
        		"item.advanced-fishing.chorus_koi.name",//Fish ID
                "advanced-fishing:fish",//Item
                15,//Metadata
                "item.advanced-fishing.chorus_koi.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.obsidian_bream.name", FishData.createData(
        		"item.advanced-fishing.obsidian_bream.name",//Fish ID
                "advanced-fishing:fish",//Item
                8,//Metadata
                "item.advanced-fishing.obsidian_bream.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.magma_jellyfish.name", FishData.createData(
        		"item.advanced-fishing.magma_jellyfish.name",//Fish ID
                "advanced-fishing:fish",//Item
                1,//Metadata
                "item.advanced-fishing.magma_jellyfish.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.nether_sturgeon.name", FishData.createData(
        		"item.advanced-fishing.nether_sturgeon.name",//Fish ID
                "advanced-fishing:fish",//Item
                9,//Metadata
                "item.advanced-fishing.nether_sturgeon.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.withered_crucian.name", FishData.createData(
        		"item.advanced-fishing.withered_crucian.name",//Fish ID
                "advanced-fishing:fish",//Item
                24,//Metadata
                "item.advanced-fishing.withered_crucian.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.quartz_chub.name", FishData.createData(
        		"item.advanced-fishing.quartz_chub.name",//Fish ID
                "advanced-fishing:fish",//Item
                10,//Metadata
                "item.advanced-fishing.quartz_chub.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.fungi_catfish.name", FishData.createData(
        		"item.advanced-fishing.fungi_catfish.name",//Fish ID
                "advanced-fishing:fish",//Item
                29,//Metadata
                "item.advanced-fishing.fungi_catfish.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.flarefin_koi.name", FishData.createData(
        		"item.advanced-fishing.flarefin_koi.name",//Fish ID
                "advanced-fishing:fish",//Item
                11,//Metadata
                "item.advanced-fishing.flarefin_koi.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.blaze_pike.name", FishData.createData(
        		"item.advanced-fishing.blaze_pike.name",//Fish ID
                "advanced-fishing:fish",//Item
                12,//Metadata
                "item.advanced-fishing.blaze_pike.desc",//Description
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
    	
    	fishDataMap.put("item.advanced-fishing.magikarp.name", FishData.createData(
        		"item.advanced-fishing.magikarp.name",//Fish ID
                "advanced-fishing:fish",//Item
                38,//Metadata
                "item.advanced-fishing.magikarp.desc",//Description
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

        fishDataMap.put("item.fish.clownfish.raw.name", FishData.createData(
                "item.fish.clownfish.raw.name",//Fish ID
                "minecraft:fish",//Item
                2,//Metadata
                "item.fish.clownfish.raw.desc",//Description
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

        fishDataMap.put("item.fish.pufferfish.raw.name", FishData.createData(
                "item.fish.pufferfish.raw.name",
                "minecraft:fish",
                3,
                "item.fish.pufferfish.raw.desc",
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

        fishDataMap.put("item.fish.cod.raw.name", FishData.createData(
        		"item.fish.cod.raw.name",
                "minecraft:fish",
                0,
                "item.fish.cod.raw.desc",
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

        fishDataMap.put("item.fish.salmon.raw.name", FishData.createData(
                "item.fish.salmon.raw.name",
                "minecraft:fish",
                1,
                "item.fish.salmon.raw.desc",
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
