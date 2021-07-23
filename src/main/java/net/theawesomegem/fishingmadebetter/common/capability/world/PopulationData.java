package net.theawesomegem.fishingmadebetter.common.capability.world;

import net.theawesomegem.fishingmadebetter.common.configuration.CustomConfigurationHandler;
import net.theawesomegem.fishingmadebetter.common.data.FishData;
import net.theawesomegem.fishingmadebetter.util.TimeUtil;

public class PopulationData {
    private final String fishType;

    private int quantity;
    private int reproductionTick;
    private long lastEatenTime;

    public PopulationData(String fishType, int quantity, int reproductionTick, long time){
        this.fishType = fishType;

        this.quantity = quantity;
        this.reproductionTick = reproductionTick;
        this.lastEatenTime = time;
    }

    public String getFishType(){
        return fishType;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getReproductionTick() {
        return reproductionTick;
    }

    public void setReproductionTick(int reproductionTick) {
        this.reproductionTick = reproductionTick;
    }

    public long getLastEatenTime() {
        return lastEatenTime;
    }

    public void setLastEatenTime(long lastEatenTime) {
        this.lastEatenTime = lastEatenTime;
    }

    public boolean isHungry(long currentTime){
        FishData fishData = CustomConfigurationHandler.fishDataMap.get(fishType);

        if(fishData == null) return false;

        long eatingFrequency = TimeUtil.minutesToMinecraftTicks(fishData.eatingFrequency);
        long timeDiff = currentTime - lastEatenTime;

        return (timeDiff > eatingFrequency);
    }
}
