package net.theawesomegem.fishingmadebetter.common.capability;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.theawesomegem.fishingmadebetter.ModInfo;
import net.theawesomegem.fishingmadebetter.common.capability.fishing.FishingCapabilityProvider;
import net.theawesomegem.fishingmadebetter.common.capability.world.ChunkCapabilityProvider;

/**
 * Created by TheAwesomeGem on 6/22/2017.
 */
public class CapabilityHandler {
    public static final ResourceLocation FISHING_DATA_CAP_RESOURCE = new ResourceLocation(ModInfo.MOD_ID, "fishingdata");
    public static final ResourceLocation CHUNK_FISHING_DATA_CAP_RESOURCE = new ResourceLocation(ModInfo.MOD_ID, "chunkfishingdata");

    @SubscribeEvent
    public void onAttachCapabilityEntity(AttachCapabilitiesEvent<Entity> e) {
        if(!(e.getObject() instanceof EntityPlayer)) return;

        e.addCapability(FISHING_DATA_CAP_RESOURCE, new FishingCapabilityProvider());
    }

    @SubscribeEvent
    public void onAttachCapabilityChunk(AttachCapabilitiesEvent<Chunk> e) {
        e.addCapability(CHUNK_FISHING_DATA_CAP_RESOURCE, new ChunkCapabilityProvider());
    }
}
