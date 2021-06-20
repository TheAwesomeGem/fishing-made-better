package net.theawesomegem.fishingmadebetter.client.registry;

import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.theawesomegem.fishingmadebetter.common.block.BlockManager;
import net.theawesomegem.fishingmadebetter.common.item.ItemManager;

/**
 * Created by TheAwesomeGem on 12/28/2017.
 */
public class ClientRegistryManager {
    @EventBusSubscriber(Side.CLIENT)
    public static class RegistryHandler {
        @SubscribeEvent
        public static void onRegisterModel(ModelRegistryEvent e) {
            BlockManager.registerModels();
            ItemManager.registerModels();
        }
    }
}
