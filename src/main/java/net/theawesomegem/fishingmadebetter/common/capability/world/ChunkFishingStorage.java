package net.theawesomegem.fishingmadebetter.common.capability.world;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by TheAwesomeGem on 12/30/2017.
 */
public class ChunkFishingStorage implements Capability.IStorage<IChunkFishingData> {
    @Nullable
    @Override
    public NBTBase writeNBT(Capability<IChunkFishingData> capability, IChunkFishingData instance, EnumFacing side) {
        NBTTagCompound nbt = new NBTTagCompound();

        for (PopulationData fishEntry : instance.getRawFishMap().values()) {
            NBTTagCompound fishDataTag = new NBTTagCompound();
            fishDataTag.setInteger("Quantity", fishEntry.getQuantity());
            fishDataTag.setInteger("ReproductionTick", fishEntry.getReproductionTick());
            fishDataTag.setLong("LastEatenTime", fishEntry.getLastEatenTime());
            nbt.setTag(fishEntry.getFishType(), fishDataTag);
        }

        nbt.setLong("TimeSinceRegenerated", instance.getTimeSinceLastRegen());
        nbt.setLong("TimeSinceReproduction", instance.getTimeSinceLastReproduced());

        return nbt;
    }

    @Override
    public void readNBT(Capability<IChunkFishingData> capability, IChunkFishingData instance, EnumFacing side, NBTBase nbtBase) {
        if (nbtBase instanceof NBTTagCompound) {
            NBTTagCompound nbt = (NBTTagCompound) nbtBase;
            Map<String, PopulationData> fishPopulationMap = new HashMap<>();

            for (String fishType : nbt.getKeySet()) {
                NBTTagCompound fishDataTag = nbt.getCompoundTag(fishType);

                if(fishDataTag.isEmpty()){
                    continue;
                }

                PopulationData populationData = new PopulationData(fishType,
                        fishDataTag.getInteger("Quantity"),
                        fishDataTag.getInteger("ReproductionTick"),
                        fishDataTag.getLong("LastEatenTime"));

                fishPopulationMap.put(fishType, populationData);
            }

            instance.setTimeSinceLastRegen(nbt.getLong("TimeSinceRegenerated"), false);
            instance.setTimeSinceLastReproduced(nbt.getLong("TimeSinceReproduction"), false);

            instance.addFishes(fishPopulationMap, false);
        }
    }
}
