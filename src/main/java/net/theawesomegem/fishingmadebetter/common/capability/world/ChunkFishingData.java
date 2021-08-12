package net.theawesomegem.fishingmadebetter.common.capability.world;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.theawesomegem.fishingmadebetter.common.configuration.ConfigurationManager;
import net.theawesomegem.fishingmadebetter.common.configuration.CustomConfigurationHandler;
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
    private final Map<String, Type> byName = new HashMap<String, Type>();

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
        if(ConfigurationManager.server.regenerateEmptyChunks) checkForRegeneration(currentTime);
        checkForReproduction(currentTime);

        return fishPopulationMap;
    }

    @Override
    public Map<String, PopulationData> getRawFishMap() {
        return fishPopulationMap;
    }

    @Override
    public void reducePopulation(String fishId, int reduce, long currentTime, boolean markDirty) {
        if(!fishPopulationMap.containsKey(fishId)) return;

        PopulationData populationData = fishPopulationMap.get(fishId);

        int quantity = populationData.getQuantity();
        int newPopulation = quantity - reduce;

        if(newPopulation <= 0) removeFish(fishId, currentTime, markDirty);
        else populationData.setQuantity(newPopulation);

        if(markDirty && chunk != null) chunk.markDirty();
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
        if(hasFishes()) return;

        if(currentTime >= timeSinceRegenerated) regenerateInitialFishes();
    }

    private void checkForReproduction(long currentTime) {
        boolean updated = false;

        if(!hasFishes()) return;

        long timeDiff = currentTime - timeSinceReproduction;
        int steps = (int)(timeDiff/getReproductionTime());

        if(steps < 1) return;

        for(int i = 0; i < steps; i++) {
            for(PopulationData populationData : fishPopulationMap.values()) {
                FishData fishData = CustomConfigurationHandler.fishDataMap.get(populationData.getFishType());

                if(fishData == null) continue;

                if(populationData.getQuantity() < 2) continue;

                if(populationData.isHungry(currentTime)) continue;

                updated = true;
                this.timeSinceReproduction = currentTime;

                populationData.setReproductionTick(populationData.getReproductionTick() + 1);

                if(populationData.getReproductionTick() >= fishData.reproductionTime) {
                    populationData.setReproductionTick(0);
                    populationData.setQuantity(populationData.getQuantity() + 1);
                }
            }
        }

        if(updated && chunk != null) chunk.markDirty();
    }

    private void regenerateInitialFishes() {
        Map<String, PopulationData> fishPopulationMap = new HashMap<>();
        Random random = chunk.getWorld().rand;

        for(FishData fishData : CustomConfigurationHandler.getAllFishData()) {//TODO: reenable after testing
        	if(fishData.dimensionBlacklist == isChunkInDimension(fishData.dimensionList)) continue;
            if(fishData.biomeBlacklist == isChunkBiomeInBiomeList(fishData.biomeTagList)) continue;

            int popCount = getInitialPopulationCount(random, fishData);
            if(popCount <= 0) continue;

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
        return TimeUtil.minutesToMinecraftTicks(ConfigurationManager.server.fishRegenerationTime);
    }

    private long getReproductionTime() {
        return TimeUtil.minutesToMinecraftTicks(ConfigurationManager.server.fishReproductionTime);
    }

    private int getInitialPopulationCount(Random random, FishData fishData) {
        float popRarity = (float)fishData.rarity * 0.5f;
        int popFactor = ConfigurationManager.server.randomPopulationFactor;
        
        return Math.max(0, (int)((popRarity * (1D + ((float)RandomUtil.getRandomInRange(random, -popFactor, popFactor)/100D))) + 0.5f));
    }

    private boolean isChunkBiomeInBiomeList(List<String> biomeTagList) {
        if(chunk == null) return false;
        
        if(biomeTagList.isEmpty()) return false;
        
        if(byName.isEmpty()) initByName();
        
        //JEID breaks this method, may as well use the other one
        /*
        byte[] biomeIds = chunk.getBiomeArray();

        for(String biomeTag : biomeTagList) {//No need to check every single block, just check chunk corners and middle side
        	if(byName.get(biomeTag) != null && (
        			(biomeIds[0] >= 0 ? BiomeDictionary.hasType(Biome.getBiome(biomeIds[0]), byName.get(biomeTag)) : false) ||//Why was biome array randomly returning id -123 sometimes?
        			(biomeIds[9] >= 0 ? BiomeDictionary.hasType(Biome.getBiome(biomeIds[9]), byName.get(biomeTag)) : false) ||
        			(biomeIds[15] >= 0 ? BiomeDictionary.hasType(Biome.getBiome(biomeIds[15]), byName.get(biomeTag)) : false) ||
        			(biomeIds[112] >= 0 ? BiomeDictionary.hasType(Biome.getBiome(biomeIds[112]), byName.get(biomeTag)): false) ||
        			(biomeIds[127] >= 0 ? BiomeDictionary.hasType(Biome.getBiome(biomeIds[127]), byName.get(biomeTag)) : false) ||
        			(biomeIds[240] >= 0 ? BiomeDictionary.hasType(Biome.getBiome(biomeIds[240]), byName.get(biomeTag)) : false) ||
        			(biomeIds[249] >= 0 ? BiomeDictionary.hasType(Biome.getBiome(biomeIds[249]), byName.get(biomeTag)) : false) ||
        			(biomeIds[255] >= 0 ? BiomeDictionary.hasType(Biome.getBiome(biomeIds[255]), byName.get(biomeTag)) : false) )
        	) return true;
        }
        */
        
        ChunkPos chunkPos = chunk.getPos();
        BiomeProvider biomeProv = world.getBiomeProvider();
        
        for(String biomeTag : biomeTagList) {//No need to check every single block, just check chunk corners and middle side
        	if(byName.get(biomeTag) != null && (
        			BiomeDictionary.hasType(chunk.getBiome(chunkPos.getBlock(0, 60, 0), biomeProv), byName.get(biomeTag)) ||
        			BiomeDictionary.hasType(chunk.getBiome(chunkPos.getBlock(8, 60, 0), biomeProv), byName.get(biomeTag)) ||
        			BiomeDictionary.hasType(chunk.getBiome(chunkPos.getBlock(15, 60, 0), biomeProv), byName.get(biomeTag)) ||
        			BiomeDictionary.hasType(chunk.getBiome(chunkPos.getBlock(0, 60, 0), biomeProv), byName.get(biomeTag)) ||
        			BiomeDictionary.hasType(chunk.getBiome(chunkPos.getBlock(0, 60, 8), biomeProv), byName.get(biomeTag)) ||
        			BiomeDictionary.hasType(chunk.getBiome(chunkPos.getBlock(0, 60, 15), biomeProv), byName.get(biomeTag)) ||
        			BiomeDictionary.hasType(chunk.getBiome(chunkPos.getBlock(8, 60, 15), biomeProv), byName.get(biomeTag)) ||
        			BiomeDictionary.hasType(chunk.getBiome(chunkPos.getBlock(15, 60, 8), biomeProv), byName.get(biomeTag)) )
        	) return true;
        }
        
        return false;
    }
    
    private boolean isChunkInDimension(List<Integer> dimensionList) {
    	if(chunk == null) return false;
    	if(dimensionList.isEmpty()) return false;
    	if(dimensionList.contains(chunk.getWorld().provider.getDimension())) return true;
    	
    	return false;
    }
    
    private void initByName() {
    	for(Type type : Type.getAll()) {
    		byName.put(type.getName(), type);
    	}
    }
}
    