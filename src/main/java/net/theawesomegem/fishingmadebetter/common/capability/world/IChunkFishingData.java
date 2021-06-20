package net.theawesomegem.fishingmadebetter.common.capability.world;

import net.minecraft.world.chunk.Chunk;

import java.util.Map;

/**
 * Created by TheAwesomeGem on 12/30/2017.
 */
public interface IChunkFishingData {
    void chunkLoad(Chunk chunk);
    void addFishes(Map<String, PopulationData> fishMap, boolean markDirty);
    Map<String, PopulationData> getFishes(long currentTime);
    Map<String, PopulationData> getRawFishMap();
    void reducePopulation(String fishId, int reduce, long currentTime, boolean markDirty);
    boolean hasFishes();
    long getTimeSinceLastRegen();
    long getTimeSinceLastReproduced();
    void setTimeSinceLastRegen(long time, boolean markDirty);
    void setTimeSinceLastReproduced(long time, boolean markDirty);
    void setPopulationData(String fishId, PopulationData populationData, boolean markDirty);
}
