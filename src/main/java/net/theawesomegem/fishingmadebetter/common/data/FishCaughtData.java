package net.theawesomegem.fishingmadebetter.common.data;

import net.theawesomegem.fishingmadebetter.util.RandomUtil;

import java.util.Random;

/**
 * Created by TheAwesomeGem on 12/30/2017.
 */
public class FishCaughtData {
    public final String fishId;
    public final String itemId;
    public final int itemMetaData;
    public final int fishTime;
    public final int reelAmount;
    public final int errorVariance;
    public final int weight;
    public final FishData.TimeToFish time;
    public final boolean rainRequired;
    public final int rarity;
    public final int deepLevel;

    public FishCaughtData(String fishId, String itemId, int itemMetaData, int fishTime, int reelAmount, int errorVariance, int weight, FishData.TimeToFish time, boolean rainRequired, int rarity, int deepLevel) {
        this.fishId = fishId;
        this.itemId = itemId;
        this.itemMetaData = itemMetaData;
        this.fishTime = fishTime;
        this.reelAmount = reelAmount;
        this.errorVariance = errorVariance;
        this.weight = weight;
        this.time = time;
        this.rainRequired = rainRequired;
        this.rarity = rarity;
        this.deepLevel = deepLevel;
    }

    public static FishCaughtData fromFishData(FishData fishData, Random random) {
        int fishTime = RandomUtil.getRandomInRange(random, fishData.minFishTime, fishData.maxFishTime);
        int reelAmount = RandomUtil.getRandomInRange(random, fishData.minReelAmount, fishData.maxReelAmount);
        int errorVariance = RandomUtil.getRandomInRange(random, fishData.minErrorVariance, fishData.maxErrorVariance);
        int weight = RandomUtil.getRandomInRange(random, fishData.minWeight, fishData.maxWeight);
        int deepLevel = RandomUtil.getRandomInRange(random, fishData.minDeepLevel, fishData.maxDeepLevel);

        return new FishCaughtData(fishData.fishId, fishData.itemId, fishData.itemMetaData, fishTime, reelAmount, errorVariance, weight, fishData.time, fishData.rainRequired, fishData.rarity, deepLevel);
    }
}
