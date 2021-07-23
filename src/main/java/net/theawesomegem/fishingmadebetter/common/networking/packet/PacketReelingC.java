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
    private int errorVariance;
    
    private int minigameBackground0;
    private int minigameBackground1;
    private int minigameBackground2;
    private int minigameBackground3;
    private int minigameBackground4;
    
    private int lineBreak;

    public PacketReelingC() {
        this.reelingAmount = 0;
        this.reelingTarget = 0;
        this.fishDistance = 0;
        this.fishDeepLevel = 0;
        this.errorVariance = 0;
        this.fishing = false;
        
        this.minigameBackground0 = 0;
        this.minigameBackground1 = 0;
        this.minigameBackground2 = 0;
        this.minigameBackground3 = 0;
        this.minigameBackground4 = 0;
        
        this.lineBreak = 0;
    }

    public PacketReelingC(int reelingAmount, int reelingTarget, int fishDistance, int fishDeepLevel, int errorVariance, boolean fishing, int minigameBackground0, int minigameBackground1, int minigameBackground2, int minigameBackground3, int minigameBackground4, int lineBreak) {
        this.reelingAmount = reelingAmount;
        this.reelingTarget = reelingTarget;
        this.fishDistance = fishDistance;
        this.fishDeepLevel = fishDeepLevel;
        this.errorVariance = errorVariance;
        this.fishing = fishing;
        
        this.minigameBackground0 = minigameBackground0;
        this.minigameBackground1 = minigameBackground1;
        this.minigameBackground2 = minigameBackground2;
        this.minigameBackground3 = minigameBackground3;
        this.minigameBackground4 = minigameBackground4;
        
        this.lineBreak = lineBreak;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.reelingAmount = buf.readInt();
        this.reelingTarget = buf.readInt();
        this.fishDistance = buf.readInt();
        this.fishDeepLevel = buf.readInt();
        this.errorVariance = buf.readInt();
        this.fishing = buf.readBoolean();
        
        this.minigameBackground0 = buf.readInt();
        this.minigameBackground1 = buf.readInt();
        this.minigameBackground2 = buf.readInt();
        this.minigameBackground3 = buf.readInt();
        this.minigameBackground4 = buf.readInt();
        
        this.lineBreak = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(this.reelingAmount);
        buf.writeInt(this.reelingTarget);
        buf.writeInt(this.fishDistance);
        buf.writeInt(this.fishDeepLevel);
        buf.writeInt(this.errorVariance);
        buf.writeBoolean(this.fishing);
        
        buf.writeInt(this.minigameBackground0);
        buf.writeInt(this.minigameBackground1);
        buf.writeInt(this.minigameBackground2);
        buf.writeInt(this.minigameBackground3);
        buf.writeInt(this.minigameBackground4);
        
        buf.writeInt(this.lineBreak);
    }

    public static class ReelingMessageHandler implements IMessageHandler<PacketReelingC, IMessage> {
        @Override
        public IMessage onMessage(PacketReelingC message, MessageContext ctx) {
            final EntityPlayer player = Primary.proxy.getPlayer(ctx);
            final int newReelingAmount = message.reelingAmount;
            final int newReelingTarget = message.reelingTarget;
            final int newFishDistance = message.fishDistance;
            final int newFishDeepLevel = message.fishDeepLevel;
            final int newErrorVariance = message.errorVariance;
            final boolean fishing = message.fishing;
            
            final int minigameBackground0 = message.minigameBackground0;
            final int minigameBackground1 = message.minigameBackground1;
            final int minigameBackground2 = message.minigameBackground2;
            final int minigameBackground3 = message.minigameBackground3;
            final int minigameBackground4 = message.minigameBackground4;
            
            final int lineBreak = message.lineBreak;

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
                fishingData.setErrorVariance(newErrorVariance);
                
                fishingData.setMinigameBackground(minigameBackground0, minigameBackground1, minigameBackground2, minigameBackground3, minigameBackground4);
                
                fishingData.setLineBreak(lineBreak);
            });

            return null;
        }
    }
}
