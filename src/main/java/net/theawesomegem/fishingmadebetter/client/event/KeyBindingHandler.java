package net.theawesomegem.fishingmadebetter.client.event;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.Phase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.theawesomegem.fishingmadebetter.client.KeybindManager;
import net.theawesomegem.fishingmadebetter.common.networking.PrimaryPacketHandler;
import net.theawesomegem.fishingmadebetter.common.networking.packet.PacketKeybindS;

/**
 * Created by TheAwesomeGem on 12/27/2017.
 */
public class KeyBindingHandler {
	private boolean lastKeyPress;
	
	@SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onTick(ClientTickEvent event){
    	if(event.phase.equals(Phase.START)) return;
    	
        if(KeybindManager.reelIn.isKeyDown()) {
        	lastKeyPress=true;
        	PrimaryPacketHandler.INSTANCE.sendToServer(new PacketKeybindS(PacketKeybindS.Keybind.REEL_IN));
        }
        else if(KeybindManager.reelOut.isKeyDown()) {
        	lastKeyPress=true;
        	PrimaryPacketHandler.INSTANCE.sendToServer(new PacketKeybindS(PacketKeybindS.Keybind.REEL_OUT));
        }
        else {
        	if(lastKeyPress==true) PrimaryPacketHandler.INSTANCE.sendToServer(new PacketKeybindS(PacketKeybindS.Keybind.NONE));
        	lastKeyPress=false;
        }
	}
}
