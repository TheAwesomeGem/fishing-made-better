package net.theawesomegem.fishingmadebetter.common.capability.fishing;

import net.minecraft.item.ItemStack;
import net.theawesomegem.fishingmadebetter.common.configuration.CustomConfigurationHandler;
import net.theawesomegem.fishingmadebetter.common.data.FishCaughtData;
import net.theawesomegem.fishingmadebetter.common.data.FishData;
import net.theawesomegem.fishingmadebetter.common.item.fishingrod.ItemBetterFishingRod;
import net.theawesomegem.fishingmadebetter.common.networking.packet.PacketKeybindS.Keybind;
import net.theawesomegem.fishingmadebetter.util.RandomUtil;

import java.util.Random;

/**
 * Created by TheAwesomeGem on 6/22/2017.
 */
public class FishingData implements IFishingData {
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
    private boolean usingVanillaRod = false;
    private int fishMomentum;
    private int fishTugging;
    private int tensionMomentum;
    private Keybind keybind;
    private Integer[] minigameBackground = new Integer[5];
    private int lineBreak;
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
    	value = Math.min(1100-this.errorVariance, Math.max(this.errorVariance, value));//Clamp target within minigame boundaries, 0-110 but account for size based on variance
    	
        this.reelAmount = value;
    }

    @Override
    public int getReelAmount() {
        return reelAmount;
    }

    @Override
    public void setReelTarget(int value) {
        value = Math.min(1070, Math.max(30, value));//Clamp target within minigame boundaries, 0-110 but fish is 6 wide

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
        this.fishDistance = Math.max(0, value);
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
    public boolean getUsingVanillaRod() {
    	return this.usingVanillaRod;
    }
    
    @Override
    public void setUsingVanillaRod(boolean value) {
    	this.usingVanillaRod = value;
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
    public int getFishMomentum() {
    	return fishMomentum;
    }
    
    @Override
    public void setFishMomentum(int momentum) {
    	this.fishMomentum = momentum;
    }
    
    @Override
    public int getFishTugging() {
    	return fishTugging;
    }
    
    @Override
    public void setFishTugging(int tugging) {
    	this.fishTugging = tugging;
    }
    
    @Override
    public int getTensionMomentum() {
    	return tensionMomentum;
    }
    
    @Override
    public void setTensionMomentum(int momentum) {
    	this.tensionMomentum = momentum;
    }
    
    @Override
    public Keybind getKeybind() {
    	return keybind;
    }
    
    @Override
    public void setKeybind(Keybind keybind) {
    	this.keybind = keybind;
    }
    
    @Override
    public Integer[] getMinigameBackground() {
    	return this.minigameBackground;
    }
    
    @Override
    public void setMinigameBackground(int a, int b, int c, int d, int e) {
    	this.minigameBackground[0] = a;
    	this.minigameBackground[1] = b;
    	this.minigameBackground[2] = c;
    	this.minigameBackground[3] = d;
    	this.minigameBackground[4] = e;
    }
    
    @Override
    public int getLineBreak() {
    	return this.lineBreak;
    }
    
    @Override
    public void setLineBreak(int value) {
    	this.lineBreak = Math.min(60, Math.max(0, value));
    }
    
    @Override
    public void setMinigameBackground(Integer[] minigameBackground) {
    	this.minigameBackground = minigameBackground;
    }

    @Override
    public void startFishing(Random random, ItemStack fishingRod, Integer[] minigameBackground) {
        if(isFishing()) return;

        if(fishPopulation == null) return;
        
        String fishId = fishPopulation.fishId;

        reset();

        FishData fishData = CustomConfigurationHandler.fishDataMap.get(fishId);
        FishCaughtData fishCaughtData = FishCaughtData.fromFishData(fishData, random);
        setFishCaughtData(fishCaughtData);
        		
        setFishTime(fishCaughtData.fishTime);
        setFishing(true);
        setErrorVariance((2 + fishCaughtData.errorVariance + ItemBetterFishingRod.getBobberItem(fishingRod).getVarianceModifier())*10);
        setReelAmount(RandomUtil.getRandomInRange(random, 15, 95)*10);
        setReelTarget(RandomUtil.getRandomInRange(random, 30, 80)*10);
        setFishDistance((ItemBetterFishingRod.getReelItem(fishingRod).getReelRange()-fishCaughtData.deepLevel)*10);
        setFishDistanceTime(0);
        setFishDeepLevel(ItemBetterFishingRod.getReelItem(fishingRod).getReelRange()*10);//Multiply by 10 for smoother reeling, divide when called
        setFishWeight(fishCaughtData.weight);
        setFishMomentum(0);
        setFishTugging(0);
        setTensionMomentum(0);
        setKeybind(Keybind.NONE);
        setMinigameBackground(minigameBackground);
        setLineBreak(0);
        
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
        setErrorVariance(0);
        setReelAmount(0);
        setReelTarget(0);
        setFishDistance(0);
        setFishDistanceTime(0);
        setFishDeepLevel(0);
        setFishWeight(0);
        setFishMomentum(0);
        setFishTugging(0);
        setTensionMomentum(0);
        setKeybind(Keybind.NONE);
        setMinigameBackground(new Integer[] {0, 0, 0, 0 ,0});
        setLineBreak(0);
    }

    @Override
    public void reset() {
        reset(true);
    }
}
