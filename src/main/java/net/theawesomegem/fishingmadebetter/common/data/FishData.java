package net.theawesomegem.fishingmadebetter.common.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class FishData {
    public enum TimeToFish {
        @SerializedName("day")      DAY,
        @SerializedName("night")    NIGHT,
        @SerializedName("any")      ANY
    }
    
    public enum FishingLiquid {
    	@SerializedName("water") 	WATER,
    	@SerializedName("lava")		LAVA,
    	@SerializedName("void")		VOID,
    	@SerializedName("any")		ANY
    }
    
    public enum BaitEnum {
    	@SerializedName("fruit") FRUIT,
    	@SerializedName("grain") GRAIN,
    	@SerializedName("vegetable") VEGETABLE,
    	@SerializedName("meatNormal") MEAT_NORMAL,
    	@SerializedName("meatExtra") MEAT_EXTRA,
    	@SerializedName("smallPredatorAQC") SMALL_PREDATOR_AQC,
    	@SerializedName("largePredatorAQC") LARGE_PREDATOR_AQC,
    	@SerializedName("oceanPredatorAQC") OCEAN_PREDATOR_AQC,
    	@SerializedName("herbivoreAQC") HERBIVORE_AQC;
    	
		Map<String, Integer[]> fruit = new HashMap<String, Integer[]>(){{
        	put("minecraft:apple", new Integer[] {0});
        	put("minecraft:melon", new Integer[] {0});
        	}};
    	
    	Map<String, Integer[]> grain = new HashMap<String, Integer[]>(){{
        	put("minecraft:wheat_seeds", new Integer[] {0});
        	put("minecraft:pumpkin_seeds", new Integer[] {0});
        	put("minecraft:melon_seeds", new Integer[] {0});
        	put("minecraft:beetroot_seeds", new Integer[] {0});
    	}};
    	
    	Map<String, Integer[]> vegetable = new HashMap<String, Integer[]>(){{
        	put("minecraft:carrot", new Integer[] {0});
        	put("minecraft:potato", new Integer[] {0});
        	put("minecraft:beetroot", new Integer[] {0});
    	}};
    	
    	Map<String, Integer[]> meatNormal = new HashMap<String, Integer[]>(){{
        	put("minecraft:fish", new Integer[] {2});
        	put("minecraft:spider_eye", new Integer[] {0});
        	put("minecraft:rotten_flesh", new Integer[] {0});
    	}};
    	
    	Map<String, Integer[]> meatExtra = new HashMap<String, Integer[]>(){{
        	put("minecraft:porkchop", new Integer[] {0});
        	put("minecraft:beef", new Integer[] {0});
        	put("minecraft:chicken", new Integer[] {0});
        	put("minecraft:rabbit", new Integer[] {0});
        	put("minecraft:mutton", new Integer[] {0});
    	}};
    	
    	Map<String, Integer[]> small_predator_aqc = new HashMap<String, Integer[]>(){{
        	put("aquaculture:fish", new Integer[] {19, 37});
    	}};
    	
    	Map<String, Integer[]> large_predator_aqc = new HashMap<String, Integer[]>(){{
    		put("aquaculture:fish", new Integer[] {0, 1, 17});
    	}};
    	
    	Map<String, Integer[]> ocean_predator_aqc = new HashMap<String, Integer[]>(){{
    		put("aquaculture:fish", new Integer[] {9, 10, 15, 16, 26});
    	}};

    	Map<String, Integer[]> herbivore_aqc = new HashMap<String, Integer[]>(){{
    		put("aquaculture:food", new Integer[] {0, 1});
    	}};
        	
    	public Map<String, Integer[]> getValues(BaitEnum value) {
    		switch(value) {
    		case FRUIT : return fruit;
    		case GRAIN : return grain;
    		case VEGETABLE : return vegetable;
    		case MEAT_NORMAL : return meatNormal;
    		case MEAT_EXTRA : return meatExtra;
    		case SMALL_PREDATOR_AQC : return small_predator_aqc;
    		case LARGE_PREDATOR_AQC : return large_predator_aqc;
    		case OCEAN_PREDATOR_AQC : return ocean_predator_aqc;
    		case HERBIVORE_AQC : return herbivore_aqc;
    		default : return vegetable;
    		}
    	}
    }

    public String fishId;
    public String itemId;
    public int itemMetaData;
    public String description;
    public int minFishTime;
    public int maxFishTime;
    public int minErrorVariance;
    public int maxErrorVariance;
    public int minWeight;
    public int maxWeight;
    public TimeToFish time;
    public FishingLiquid liquid;
    public boolean rainRequired;
    public boolean thunderRequired;
    public int rarity;
    public int minDeepLevel;
    public int maxDeepLevel;
    public int minYLevel;
    public int maxYLevel;
    public int maxLightLevel;
    public int reproductionTime;
    public int eatingFrequency;
    public boolean trackable;
    public boolean biomeBlacklist;
    public boolean dimensionBlacklist;
    public List<String> biomeTagList = new ArrayList<>(); 
    public List<Integer> dimensionList = new ArrayList<>();
    public int timeOutsideOfWater;
    public boolean allowScaling;
    public String scalingItem;
    public int scalingItemMetadata;
    public boolean scalingUseWeight;
    public boolean allowFillet;
    public boolean defaultFillet;
    public String filletItem;
    public int filletItemMetadata;
    public boolean filletUseWeight;
    public Map<String, Integer[]> baitItemMap = new HashMap<>();

    public static FishData createData(String fishId
            , String itemId
            , int itemMetaData
            , String description
            , int minFishTime
            , int maxFishTime
            , int minErrorVariance
            , int maxErrorVariance
            , int minWeight
            , int maxWeight
            , TimeToFish time
            , FishingLiquid liquid
            , boolean rainRequired
            , boolean thunderRequired
            , int rarity
            , int minDeepLevel
            , int maxDeepLevel
            , int minYLevel
            , int maxYLevel
            , int maxLightLevel
            , int reproductionTime
            , int eatingFrequency
            , boolean trackable
            , boolean biomeBlacklist
            , boolean dimensionBlacklist
            , String[] biomeTagList
            , int[] dimensionList
            , int timeOutsideOfWater
            , boolean allowScaling
            , String scalingItem
            , int scalingItemMetadata
            , boolean scalingUseWeight
            , boolean allowFillet
            , boolean defaultFillet
            , String filletItem
            , int filletItemMetadata
            , boolean filletUseWeight
            , BaitEnum[] baitEnumArray) {
        FishData fishData = new FishData();

        fishData.fishId = fishId;
        fishData.itemId = itemId;
        fishData.itemMetaData = itemMetaData;
        fishData.description = description;
        fishData.minFishTime = minFishTime;
        fishData.maxFishTime = maxFishTime;
        fishData.minErrorVariance = minErrorVariance;
        fishData.maxErrorVariance = maxErrorVariance;
        fishData.minWeight = minWeight;
        fishData.maxWeight = maxWeight;
        fishData.time = time;
        fishData.liquid = liquid;
        fishData.rainRequired = rainRequired;
        fishData.thunderRequired = thunderRequired;
        fishData.rarity = rarity;
        fishData.minDeepLevel = minDeepLevel;
        fishData.maxDeepLevel = maxDeepLevel;
        fishData.minYLevel = minYLevel;
        fishData.maxYLevel = maxYLevel;
        fishData.maxLightLevel = maxLightLevel;
        fishData.reproductionTime = reproductionTime;
        fishData.eatingFrequency = eatingFrequency;
        fishData.trackable = trackable;
        fishData.biomeBlacklist = biomeBlacklist;
        fishData.dimensionBlacklist = dimensionBlacklist;
        fishData.biomeTagList = new ArrayList<>();
        for (String tag : biomeTagList) fishData.biomeTagList.add(tag);
        fishData.dimensionList = new ArrayList<>();
        for (int dim : dimensionList) fishData.dimensionList.add(dim);
        fishData.timeOutsideOfWater = timeOutsideOfWater;
        fishData.allowScaling = allowScaling;
        fishData.scalingItem = scalingItem;
        fishData.scalingItemMetadata = scalingItemMetadata;
        fishData.scalingUseWeight = scalingUseWeight;
        fishData.allowFillet = allowFillet;
        fishData.defaultFillet = defaultFillet;
        fishData.filletItem = filletItem;
        fishData.filletItemMetadata = filletItemMetadata;
        fishData.filletUseWeight = filletUseWeight;
        for (BaitEnum entry : baitEnumArray) fishData.baitItemMap.putAll(entry.getValues(entry));

        return fishData;
    }
}
