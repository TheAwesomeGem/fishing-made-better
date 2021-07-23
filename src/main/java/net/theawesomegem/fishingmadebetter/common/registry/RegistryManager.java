package net.theawesomegem.fishingmadebetter.common.registry;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.theawesomegem.fishingmadebetter.ModInfo;
import net.theawesomegem.fishingmadebetter.common.block.BlockManager;
import net.theawesomegem.fishingmadebetter.common.item.ItemManager;

/**
 * Created by TheAwesomeGem on 12/23/2017.
 */

public class RegistryManager {
    @EventBusSubscriber
    public static class RegistryHandler {
        public static SoundEvent FISH_SPLASHING_EVENT;
        public static SoundEvent REELING_EVENT;

        @SubscribeEvent
        public static void onRegisterBlock(RegistryEvent.Register<Block> e) {
            BlockManager.registerBlocks(e.getRegistry());
        }

        @SubscribeEvent
        public static void onRegisterItem(RegistryEvent.Register<Item> e) {
            BlockManager.registerItemBlocks(e.getRegistry());
            ItemManager.register(e.getRegistry());
        }

        @SubscribeEvent
        public static void onRegisterSound(RegistryEvent.Register<SoundEvent> e) {
            FISH_SPLASHING_EVENT = new SoundEvent(new ResourceLocation(ModInfo.MOD_ID, "fishsplashing")).setRegistryName("soundfishsplashing");
            REELING_EVENT = new SoundEvent(new ResourceLocation(ModInfo.MOD_ID, "reeling")).setRegistryName("soundreeling");

            e.getRegistry().registerAll(FISH_SPLASHING_EVENT, REELING_EVENT);
        }
    }
}
