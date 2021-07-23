package net.theawesomegem.fishingmadebetter.common.networking.packet;


import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.theawesomegem.fishingmadebetter.Primary;
import net.theawesomegem.fishingmadebetter.common.capability.fishing.FishingCapabilityProvider;
import net.theawesomegem.fishingmadebetter.common.capability.fishing.IFishingData;

/**
 * Created by TheAwesomeGem on 6/22/2017.
 */
public class PacketKeybindS implements IMessage {
    public enum Keybind {
        REEL_IN,
        REEL_OUT,
        NONE
    }

    private Keybind bind;

    public Keybind getKeyBind() {
        return bind;
    }

    public PacketKeybindS() {
        this.bind = Keybind.NONE;
    }

    public PacketKeybindS(Keybind keybind) {
        this.bind = keybind;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.bind = Keybind.values()[buf.readShort()];
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeShort(bind.ordinal());
    }

    public static class KeybindMessageHandler implements IMessageHandler<PacketKeybindS, IMessage> {
        @Override
        public IMessage onMessage(PacketKeybindS message, MessageContext ctx) {
            final EntityPlayer player = Primary.proxy.getPlayer(ctx);
            final Keybind bind = message.getKeyBind();

            if(player == null) return null;

            IThreadListener thread = Primary.proxy.getListener(ctx);

            thread.addScheduledTask(() -> // Game Thread
            {
                IFishingData fishingData = player.getCapability(FishingCapabilityProvider.FISHING_DATA_CAP, null);

                if(fishingData == null) return;
                if(!fishingData.isFishing()) return;

                fishingData.setKeybind(bind);
            });

            return null;
        }
    }
}
