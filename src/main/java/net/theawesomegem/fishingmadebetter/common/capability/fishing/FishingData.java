package net.theawesomegem.fishingmadebetter.common.capability.fishing;


import net.theawesomegem.fishingmadebetter.common.configuration.CustomFishConfigurationHandler;
import net.theawesomegem.fishingmadebetter.common.data.FishCaughtData;
import net.theawesomegem.fishingmadebetter.common.data.FishData;

import java.util.Random;

/**
 * Created by TheAwesomeGem on 6/22/2017.
 */
public class FishingData implements IFishingData {
    //Temp fishing data
    private int fishTime;
    private boolean fishing;
    private int reelAmount;
    private int reelTarget;
    private int errorVariance;
    private int timeBeforeFishSwimToHook;
    private int fishDistance;
    private int fishDeepLevel;
    private int fishDistanceTime;
    private FishCaughtData fishCaughtData;
    private FishPopulation fishPopulation;
    //========================

    private int lastFailedFishing;
    private int lastFishTime;
    private int fishWeight;
    private long timeSinceTracking;

    public FishingData() {
        reset();
    }

    @Override
    public void setFishTime(int value) {
        this.fishTime = value;
    }

    @Override
    public int getFishTime() {
        return this.fishTime;
    }

    @Override
    public void setFishing(boolean value) {
        this.fishing = value;
    }

    @Override
    public boolean isFishing() {
        return this.fishing;
    }

    @Override
    public void setReelAmount(int value) {
        this.reelAmount = value;
    }

    @Override
    public int getReelAmount() {
        return reelAmount;
    }

    @Override
    public void setReelTarget(int value) {
        if (value < 0)
            value = 0;

        this.reelTarget = value;
    }

    @Override
    public int getReelTarget() {
        return reelTarget;
    }

    @Override
    public void setErrorVariance(int value) {
        if (value < 0)
            value = 0;

        this.errorVariance = value;
    }

    @Override
    public int getErrorVariance() {
        return errorVariance;
    }

    @Override
    public void setTimeBeforeFishSwimToHook(int value) {
        this.timeBeforeFishSwimToHook = value;
    }

    @Override
    public int getTimeBeforeFishSwimToHook() {
        return timeBeforeFishSwimToHook;
    }

    @Override
    public void setFishPopulation(FishPopulation fishPopulation) {
        this.fishPopulation = fishPopulation;
    }

    @Override
    public FishPopulation getFishPopulation() {
        return this.fishPopulation;
    }

    @Override
    public void setLastFailedFishing(int value) {
        this.lastFailedFishing = value;
    }

    @Override
    public int getLastFailedFishing() {
        return lastFailedFishing;
    }

    @Override
    public void setLastFishTime(int value) {
        this.lastFishTime = value;
    }

    @Override
    public int getLastFishTime() {
        return lastFishTime;
    }

    @Override
    public void setFishDistance(int value) {
        if(value < 0){
            value = 0;
        }

        this.fishDistance = value;
    }

    @Override
    public int getFishDistance() {
        return fishDistance;
    }

    @Override
    public void setFishDistanceTime(int value) {
        this.fishDistanceTime = value;
    }

    @Override
    public int getFishDistanceTime() {
        return fishDistanceTime;
    }

    @Override
    public void setFishDeepLevel(int value) {
        this.fishDeepLevel = value;
    }

    @Override
    public int getFishDeepLevel() {
        return fishDeepLevel;
    }
    @Override
    public void setFishWeight(int value) {
        this.fishWeight = value;
    }

    @Override
    public int getFishWeight() {
        return fishWeight;
    }

    @Override
    public void setFishCaughtData(FishCaughtData fishCaughtData) {
        this.fishCaughtData = fishCaughtData;
    }

    @Override
    public FishCaughtData getFishCaughtData() {
        return fishCaughtData;
    }

    @Override
    public long getTimeSinceTracking() {
        return timeSinceTracking;
    }

    @Override
    public void setTimeSinceTracking(long time) {
        this.timeSinceTracking = time;
    }

    @Override
    public void startFishing(Random random) {
        if (isFishing())
            return;

        if (fishPopulation == null)
            return;

        String fishId = fishPopulation.fishId;

        reset();

        FishData fishData = CustomFishConfigurationHandler.fishDataMap.get(fishId);
        FishCaughtData fishCaughtData = FishCaughtData.fromFishData(fishData, random);
        setFishCaughtData(fishCaughtData);

        setFishTime(fishCaughtData.fishTime);
        setFishing(true);
        setReelAmount(0);
        setReelTarget(fishCaughtData.reelAmount);
        setFishDistance(0);
        setFishDistanceTime(0);
        setFishDeepLevel(fishCaughtData.deepLevel);
        setFishWeight(fishCaughtData.weight);
        setErrorVariance(fishCaughtData.errorVariance);

        setLastFishTime(getFishTime());
    }

    @Override
    public void reset(boolean alsoResetFishSwimToHook) {
        if (alsoResetFishSwimToHook) {
            setTimeBeforeFishSwimToHook(-1);
            fishPopulation = null;
        }

        fishCaughtData = null;
        setFishTime(0);
        setFishing(false);
        setReelAmount(0);
        setReelTarget(0);
        setFishDistance(0);
        setFishDistanceTime(0);
        setFishDeepLevel(0);
        setFishWeight(0);
    }

    @Override
    public void reset() {
        reset(true);
    }
}
