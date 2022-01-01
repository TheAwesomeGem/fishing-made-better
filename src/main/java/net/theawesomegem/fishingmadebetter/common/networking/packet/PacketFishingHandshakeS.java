package net.theawesomegem.fishingmadebetter.common.networking.packet;


import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.theawesomegem.fishingmadebetter.Primary;
import net.theawesomegem.fishingmadebetter.common.capability.fishing.FishingCapabilityProvider;
import net.theawesomegem.fishingmadebetter.common.capability.fishing.IFishingData;
import net.theawesomegem.fishingmadebetter.common.networking.PrimaryPacketHandler;

/**
 * Created by TheAwesomeGem on 6/22/2017.
 */
public class PacketFishingHandshakeS implements IMessage {
    
    private boolean isFishing;
    
    public boolean getIsFishing() {
    	return isFishing;
    }
    
    public PacketFishingHandshakeS() {
        this.isFishing = false;
    }

    public PacketFishingHandshakeS(boolean isFishing) {
        this.isFishing = isFishing;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.isFishing = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeBoolean(this.isFishing);
    }

    public static class HandshakeMessageHandler implements IMessageHandler<PacketFishingHandshakeS, IMessage> {
        @Override
        public IMessage onMessage(PacketFishingHandshakeS message, MessageContext ctx) {
            final EntityPlayer player = Primary.proxy.getPlayer(ctx);
            if(player==null) return null;
            final boolean isFishing = message.getIsFishing();

            IThreadListener thread = Primary.proxy.getListener(ctx);

            thread.addScheduledTask(() -> // Game Thread
            {
                IFishingData fishingData = player.getCapability(FishingCapabilityProvider.FISHING_DATA_CAP, null);

                if(fishingData==null) return;
                if(!fishingData.isFishing() && isFishing) {//server isnt fishing, but client still is, so reset
                	PrimaryPacketHandler.INSTANCE.sendTo(new PacketReelingC(fishingData.getReelAmount(), fishingData.getReelTarget(), fishingData.getFishDistance(), fishingData.getFishDeepLevel(), fishingData.getErrorVariance(), fishingData.isFishing(), fishingData.getMinigameBackground()[0], fishingData.getMinigameBackground()[1], fishingData.getMinigameBackground()[2], fishingData.getMinigameBackground()[3], fishingData.getMinigameBackground()[4], fishingData.getLineBreak()), (EntityPlayerMP) player);
                }
            });

            return null;
        }
    }
}
