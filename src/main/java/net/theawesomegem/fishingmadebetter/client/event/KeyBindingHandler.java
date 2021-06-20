package net.theawesomegem.fishingmadebetter.client.event;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.theawesomegem.fishingmadebetter.client.KeybindManager;
import net.theawesomegem.fishingmadebetter.common.configuration.ConfigurationManager;
import net.theawesomegem.fishingmadebetter.common.networking.PrimaryPacketHandler;
import net.theawesomegem.fishingmadebetter.common.networking.packet.PacketKeybindS;

/**
 * Created by TheAwesomeGem on 12/27/2017.
 */
public class KeyBindingHandler {
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onInput(KeyInputEvent event) {
        if(!ConfigurationManager.reelingHold) {
            if (KeybindManager.isReelInPressed()) {
                PrimaryPacketHandler.INSTANCE.sendToServer(new PacketKeybindS(PacketKeybindS.Keybind.REEL_IN));
            }

            if (KeybindManager.isReelOutPressed()) {
                PrimaryPacketHandler.INSTANCE.sendToServer(new PacketKeybindS(PacketKeybindS.Keybind.REEL_OUT));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onTick(TickEvent.PlayerTickEvent event){
        if(ConfigurationManager.reelingHold) {
            if (KeybindManager.isReelInPressed()) {
                PrimaryPacketHandler.INSTANCE.sendToServer(new PacketKeybindS(PacketKeybindS.Keybind.REEL_IN));
            }

            if (KeybindManager.isReelOutPressed()) {
                PrimaryPacketHandler.INSTANCE.sendToServer(new PacketKeybindS(PacketKeybindS.Keybind.REEL_OUT));
            }
        }
    }
}
