package net.theawesomegem.fishingmadebetter.common.capability.fishing;

import net.minecraft.item.ItemStack;
import net.theawesomegem.fishingmadebetter.common.data.FishCaughtData;
import net.theawesomegem.fishingmadebetter.common.networking.packet.PacketKeybindS.Keybind;

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

    FishCaughtData getFishCaughtData();
    
    void setUsingVanillaRod(boolean value);
    
    boolean getUsingVanillaRod();

    void setTimeSinceTracking(long time);
    
    long getTimeSinceTracking();

    int getFishMomentum();
    
    void setFishMomentum(int momentum);
    
    int getFishTugging();
    
    void setFishTugging(int tugging);
    
    int getTensionMomentum();
    
    void setTensionMomentum(int momentum);
    
    Keybind getKeybind();
    
    void setKeybind(Keybind keybind);
    
    Integer[] getMinigameBackground();
    
    void setMinigameBackground(int a, int b, int c, int d, int e);
    
    void setMinigameBackground(Integer[] minigameBackground);
    
    int getLineBreak();
    
    void setLineBreak(int value);
    
    void startFishing(Random random, ItemStack fishingRod, Integer[] minigameBackground);

    void reset(boolean alsoResetFishSwimToHook);

    void reset();
}
