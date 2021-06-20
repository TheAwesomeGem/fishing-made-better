package net.theawesomegem.fishingmadebetter.common.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FishData {
    public enum TimeToFish {
        @SerializedName("day")      DAY,
        @SerializedName("night")    NIGHT,
        @SerializedName("any")      ANY
    }

    public String fishId;
    public String itemId;
    public int itemMetaData;
    public String description;
    public int minFishTime;
    public int maxFishTime;
    public int minReelAmount;
    public int maxReelAmount;
    public int minErrorVariance;
    public int maxErrorVariance;
    public int minWeight;
    public int maxWeight;
    public TimeToFish time;
    public boolean rainRequired;
    public int rarity;
    public int minDeepLevel;
    public int maxDeepLevel;
    public int reproductionTime;
    public int eatingFrequency;
    public boolean trackable;
    public boolean biomeBlacklist;
    public List<Integer> biomeList = new ArrayList<>();
    public int timeOutsideOfWater;
    public String resultingItem;
    public int resultingItemMetadata;
    public boolean resultQuantityUseWeight;
    public Map<String, Integer> baitItemMap = new HashMap<>();

    public static FishData createData(String fishId
            , String itemId
            , int itemMetaData
            , String description
            , int minFishTime
            , int maxFishTime
            , int minReelAmount
            , int maxReelAmount
            , int minErrorVariance
            , int maxErrorVariance
            , int minWeight
            , int maxWeight
            , TimeToFish time
            , boolean rainRequired
            , int rarity
            , int minDeepLevel
            , int maxDeepLevel
            , int reproductionTime
            , int eatingFrequency
            , boolean trackable
            , boolean biomeBlacklist
            , int[] biomeList
            , int timeOutsideOfWater
            , String resultingItem
            , int resultingItemMetadata
            , boolean resultQuantityUseWeight
            , Map<String, Integer> baitItemMap) {
        FishData fishData = new FishData();

        fishData.fishId = fishId;
        fishData.itemId = itemId;
        fishData.itemMetaData = itemMetaData;
        fishData.description = description;
        fishData.minFishTime = minFishTime;
        fishData.maxFishTime = maxFishTime;
        fishData.minReelAmount = minReelAmount;
        fishData.maxReelAmount = maxReelAmount;
        fishData.minErrorVariance = minErrorVariance;
        fishData.maxErrorVariance = maxErrorVariance;
        fishData.minWeight = minWeight;
        fishData.maxWeight = maxWeight;
        fishData.time = time;
        fishData.rainRequired = rainRequired;
        fishData.rarity = rarity;
        fishData.minDeepLevel = minDeepLevel;
        fishData.maxDeepLevel = maxDeepLevel;
        fishData.reproductionTime = reproductionTime;
        fishData.eatingFrequency = eatingFrequency;
        fishData.trackable = trackable;
        fishData.biomeBlacklist = biomeBlacklist;
        fishData.biomeList = new ArrayList<>();
        for (int biome : biomeList) fishData.biomeList.add(biome);
        fishData.timeOutsideOfWater = timeOutsideOfWater;
        fishData.resultingItem = resultingItem;
        fishData.resultingItemMetadata = resultingItemMetadata;
        fishData.resultQuantityUseWeight = resultQuantityUseWeight;
        fishData.baitItemMap = new HashMap<>(baitItemMap);

        return fishData;
    }
}
