package net.theawesomegem.fishingmadebetter.common.capability.fishing;

import net.theawesomegem.fishingmadebetter.common.data.FishCaughtData;

import java.util.Random;

/**
 * Created by TheAwesomeGem on 6/22/2017.
 */
public interface IFishingData {
    void setFishTime(int value);

    int getFishTime();

    void setFishing(boolean value);

    boolean isFishing();

    void setReelAmount(int value);

    int getReelAmount();

    void setReelTarget(int value);

    int getReelTarget();

    void setErrorVariance(int value);

    int getErrorVariance();

    void setTimeBeforeFishSwimToHook(int value);

    int getTimeBeforeFishSwimToHook();

    void setFishPopulation(FishPopulation fishPopulation);

    FishPopulation getFishPopulation();

    void setLastFailedFishing(int value);

    int getLastFailedFishing();

    void setLastFishTime(int value);

    int getLastFishTime();

    void setFishDistance(int value);

    int getFishDistance();

    void setFishDistanceTime(int value);

    int getFishDistanceTime();

    void setFishDeepLevel(int value);

    int getFishDeepLevel();

    void setFishWeight(int weight);

    int getFishWeight();

    void setFishCaughtData(FishCaughtData fishCaughtData);

    long getTimeSinceTracking();

    void setTimeSinceTracking(long time);

    FishCaughtData getFishCaughtData();

    void startFishing(Random random);

    void reset(boolean alsoResetFishSwimToHook);

    void reset();
}
