package net.theawesomegem.fishingmadebetter.common.capability.world;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.theawesomegem.fishingmadebetter.common.configuration.ConfigurationManager;
import net.theawesomegem.fishingmadebetter.common.configuration.CustomFishConfigurationHandler;
import net.theawesomegem.fishingmadebetter.common.data.FishData;
import net.theawesomegem.fishingmadebetter.util.RandomUtil;
import net.theawesomegem.fishingmadebetter.util.TimeUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by TheAwesomeGem on 12/30/2017.
 */
public class ChunkFishingData implements IChunkFishingData {
    private final Map<String, PopulationData> fishPopulationMap;

    private Chunk chunk;
    private World world;

    private long timeSinceRegenerated;
    private long timeSinceReproduction;

    public ChunkFishingData() {
        this.fishPopulationMap = new HashMap<>();
    }

    @Override
    public void chunkLoad(Chunk chunk) {
        this.world = chunk.getWorld();
        this.chunk = chunk;
        this.timeSinceReproduction = world.getTotalWorldTime();
        checkForRegeneration(world.getTotalWorldTime());
    }

    @Override
    public void addFishes(Map<String, PopulationData> fishMap, boolean markDirty) {
        this.fishPopulationMap.clear();
        this.fishPopulationMap.putAll(fishMap);

        if (markDirty && chunk != null)
            chunk.markDirty();
    }

    @Override
    public Map<String, PopulationData> getFishes(long currentTime) {
        checkForRegeneration(currentTime);
        checkForReproduction(currentTime);

        return fishPopulationMap;
    }

    @Override
    public Map<String, PopulationData> getRawFishMap() {
        return fishPopulationMap;
    }

    @Override
    public void reducePopulation(String fishId, int reduce, long currentTime, boolean markDirty) {
        if (!fishPopulationMap.containsKey(fishId))
            return;

        PopulationData populationData = fishPopulationMap.get(fishId);

        int quantity = populationData.getQuantity();
        int newPopulation = quantity - reduce;

        if (newPopulation <= 0) {
            removeFish(fishId, currentTime, markDirty);
        } else {
            populationData.setQuantity(newPopulation);
        }

        if (markDirty && chunk != null)
            chunk.markDirty();
    }

    @Override
    public boolean hasFishes() {
        return fishPopulationMap.size() > 0;
    }

    @Override
    public long getTimeSinceLastRegen() {
        return timeSinceRegenerated;
    }

    @Override
    public long getTimeSinceLastReproduced() {
        return timeSinceReproduction;
    }

    @Override
    public void setTimeSinceLastRegen(long time, boolean markDirty) {
        this.timeSinceRegenerated = time;

        if (markDirty) {
            chunk.markDirty();
        }
    }

    @Override
    public void setTimeSinceLastReproduced(long time, boolean markDirty) {
        this.timeSinceReproduction = time;

        if (markDirty && chunk != null) {
            chunk.markDirty();
        }
    }

    @Override
    public void setPopulationData(String fishId, PopulationData populationData, boolean markDirty) {
        if(!fishPopulationMap.containsKey(fishId)){
            return;
        }

        fishPopulationMap.put(fishId, populationData);

        if(markDirty && chunk != null){
            chunk.markDirty();
        }
    }

    private void checkForRegeneration(long currentTime) {
        if (hasFishes()) {
            return;
        }

        if (currentTime >= timeSinceRegenerated) {
            regenerateInitialFishes();
        }

    }

    private void checkForReproduction(long currentTime) {
        boolean updated = false;

        if (!hasFishes()) {
            return;
        }

        long timeDiff = currentTime - timeSinceReproduction;
        int steps = (int) (timeDiff / getReproductionTime());

        if (steps < 1) {
            return;
        }

        for (int i = 0; i < steps; i++) {
            for (PopulationData populationData : fishPopulationMap.values()) {
                FishData fishData = CustomFishConfigurationHandler.fishDataMap.get(populationData.getFishType());

                if (fishData == null) {
                    continue;
                }

                if (populationData.getQuantity() < 2) {
                    continue;
                }

                if (populationData.isHungry(currentTime)) {
                    continue;
                }

                updated = true;
                this.timeSinceReproduction = currentTime;

                populationData.setReproductionTick(populationData.getReproductionTick() + 1);

                if (populationData.getReproductionTick() >= fishData.reproductionTime) {
                    populationData.setReproductionTick(0);
                    populationData.setQuantity(populationData.getQuantity() + 1);
                }
            }
        }

        if (updated && chunk != null) {
            chunk.markDirty();
        }
    }

    private void regenerateInitialFishes() {
        Map<String, PopulationData> fishPopulationMap = new HashMap<>();
        Random random = chunk.getWorld().rand;

        for (FishData fishData : CustomFishConfigurationHandler.getAllFishData()) {
            if(fishData.biomeBlacklist == isBiomeListInChunk(fishData.biomeList))
                continue;

            int popCount = getInitialPopulationCount(random, fishData);

            if (popCount <= 0)
                continue;

            PopulationData populationData = new PopulationData(fishData.fishId, popCount, 0, world.getTotalWorldTime());

            fishPopulationMap.put(fishData.fishId, populationData);
        }

        addFishes(fishPopulationMap, true);
    }

    private void removeFish(String fishId, long currentTime, boolean markDirty) {
        if (!fishPopulationMap.containsKey(fishId))
            return;

        fishPopulationMap.remove(fishId);

        if (!hasFishes()) {
            this.timeSinceRegenerated = currentTime + getRegenTime();
        }

        if (markDirty && chunk != null)
            chunk.markDirty();
    }

    private long getRegenTime() {
        return TimeUtil.minutesToMinecraftTicks(ConfigurationManager.fishRegenerationTime);
    }

    private long getReproductionTime() {
        return TimeUtil.minutesToMinecraftTicks(ConfigurationManager.fishReproductionTime);
    }

    private int getInitialPopulationCount(Random random, FishData fishData) {
        int popCount = (int) ((float) fishData.rarity * 0.4f);
        popCount += RandomUtil.getRandomInRange(random, -ConfigurationManager.randomPopulationFactor, ConfigurationManager.randomPopulationFactor);

        if (popCount < 0)
            popCount = 0;

        return popCount;
    }

    private boolean isBiomeListInChunk(List<Integer> biomeList) {
        if(chunk == null){
            return false;
        }

        if(biomeList.isEmpty()){
            return false;
        }

        byte[] biomeIds = chunk.getBiomeArray();

        for (byte biomeId : biomeIds) {
            if (biomeList.contains((int) biomeId)) {
                return true;
            }
        }

        return false;
    }
}
    