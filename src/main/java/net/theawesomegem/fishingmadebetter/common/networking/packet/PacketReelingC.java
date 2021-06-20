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
 * Created by TheAwesomeGem on 12/29/2017.
 */
public class PacketReelingC implements IMessage {
    private boolean fishing;
    private int reelingAmount;
    private int reelingTarget;
    private int fishDistance;
    private int fishDeepLevel;

    public PacketReelingC() {
        this.reelingAmount = 0;
        this.reelingTarget = 0;
        this.fishDistance = 0;
        this.fishDeepLevel = 0;
        this.fishing = false;
    }

    public PacketReelingC(int reelingAmount, int reelingTarget, int fishDistance, int fishDeepLevel, boolean fishing) {
        this.reelingAmount = reelingAmount;
        this.reelingTarget = reelingTarget;
        this.fishDistance = fishDistance;
        this.fishDeepLevel = fishDeepLevel;
        this.fishing = fishing;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.reelingAmount = buf.readInt();
        this.reelingTarget = buf.readInt();
        this.fishDistance = buf.readInt();
        this.fishDeepLevel = buf.readInt();
        this.fishing = buf.readBoolean();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.reelingAmount);
        buf.writeInt(this.reelingTarget);
        buf.writeInt(this.fishDistance);
        buf.writeInt(this.fishDeepLevel);
        buf.writeBoolean(this.fishing);
    }

    public static class ReelingMessageHandler implements IMessageHandler<PacketReelingC, IMessage> {
        @Override
        public IMessage onMessage(PacketReelingC message, MessageContext ctx) {
            final EntityPlayer player = Primary.proxy.getPlayer(ctx);
            final int newReelingAmount = message.reelingAmount;
            final int newReelingTarget = message.reelingTarget;
            final int newFishDistance = message.fishDistance;
            final int newFishDeepLevel = message.fishDeepLevel;
            final boolean fishing = message.fishing;

            if (player == null)
                return null;

            IThreadListener thread = Primary.proxy.getListener(ctx);

            thread.addScheduledTask(() -> // Game Thread
            {
                IFishingData fishingData = player.getCapability(FishingCapabilityProvider.FISHING_DATA_CAP, null);

                if (fishingData == null)
                    return;

                fishingData.setFishing(fishing);
                fishingData.setReelAmount(newReelingAmount);
                fishingData.setReelTarget(newReelingTarget);
                fishingData.setFishDistance(newFishDistance);
                fishingData.setFishDeepLevel(newFishDeepLevel);
            });

            return null;
        }
    }
}
