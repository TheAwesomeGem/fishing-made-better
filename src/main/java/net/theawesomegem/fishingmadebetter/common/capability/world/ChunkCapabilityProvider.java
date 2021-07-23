package net.theawesomegem.fishingmadebetter.common.capability.world;

import net.minecraft.nbt.NBTBase;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

import javax.annotation.Nullable;

/**
 * Created by TheAwesomeGem on 12/30/2017.
 */
public class ChunkCapabilityProvider implements ICapabilitySerializable<NBTBase> {
    @CapabilityInject(IChunkFishingData.class)
    public static final Capability<IChunkFishingData> CHUNK_FISHING_DATA_CAP = null;

    private final IChunkFishingData chunkFishingData;

    public ChunkCapabilityProvider() {
        chunkFishingData = new ChunkFishingData();
    }

    @Override
    public NBTBase serializeNBT() {
        return CHUNK_FISHING_DATA_CAP.getStorage().writeNBT(CHUNK_FISHING_DATA_CAP, chunkFishingData, null);
    }

    @Override
    public void deserializeNBT(NBTBase nbt) {
        CHUNK_FISHING_DATA_CAP.getStorage().readNBT(CHUNK_FISHING_DATA_CAP, chunkFishingData, null, nbt);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing) {
        return capability == CHUNK_FISHING_DATA_CAP;
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing) {
        return capability == CHUNK_FISHING_DATA_CAP ? CHUNK_FISHING_DATA_CAP.cast(chunkFishingData) : null;
    }
}

