package net.theawesomegem.fishingmadebetter.common.networking;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import net.theawesomegem.fishingmadebetter.ModInfo;
import net.theawesomegem.fishingmadebetter.common.networking.packet.PacketKeybindS;
import net.theawesomegem.fishingmadebetter.common.networking.packet.PacketReelingC;
import net.theawesomegem.fishingmadebetter.common.networking.packet.PacketUseRodC;

/**
 * Created by TheAwesomeGem on 6/22/2017.
 */
public class PrimaryPacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(ModInfo.MOD_ID);

    public static void registerPackets() {
        INSTANCE.registerMessage(PacketKeybindS.KeybindMessageHandler.class, PacketKeybindS.class, 1, Side.SERVER);

        INSTANCE.registerMessage(PacketReelingC.ReelingMessageHandler.class, PacketReelingC.class, 2, Side.CLIENT);
        
        INSTANCE.registerMessage(PacketUseRodC.UseRodMessageHandler.class, PacketUseRodC.class, 3, Side.CLIENT);
    }
}
