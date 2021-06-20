package net.theawesomegem.fishingmadebetter.common.networking.packet;


import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.theawesomegem.fishingmadebetter.Primary;
import net.theawesomegem.fishingmadebetter.common.capability.fishing.FishingCapabilityProvider;
import net.theawesomegem.fishingmadebetter.common.capability.fishing.IFishingData;
import net.theawesomegem.fishingmadebetter.common.registry.RegistryManager.RegistryHandler;
import net.theawesomegem.fishingmadebetter.util.MathUtil;

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

            if (player == null)
                return null;

            IThreadListener thread = Primary.proxy.getListener(ctx);

            thread.addScheduledTask(() -> // Game Thread
            {
                IFishingData fishingData = player.getCapability(FishingCapabilityProvider.FISHING_DATA_CAP, null);

                if (fishingData == null) {
                    return;
                }

                if (!fishingData.isFishing())
                    return;

                // getReelTarget() is a random number between 10 and 95 that the user is trying to guess.
                // getReelAmount is a number between 0 and infinite that the user has/is guessing so far based on keyboard input.

                if (bind.equals(Keybind.REEL_IN))
                    fishingData.setReelAmount(fishingData.getReelAmount() + 1);

                if (bind.equals(Keybind.REEL_OUT))
                    fishingData.setReelAmount(fishingData.getReelAmount() - 1);

                //Checking how far away they are from the target number. Aka hot and cold game.
                int reelDiff = fishingData.getReelTarget() - fishingData.getReelAmount();
                reelDiff = Math.abs(reelDiff);

                //The pitch of the audio changes based on how close they are. Limits to 0.0f to 2.0f;
                float pitch;

                if (MathUtil.inRange(reelDiff, 0, 1))
                    pitch = 2.0f;
                else if (MathUtil.inRange(reelDiff, 2, 4))
                    pitch = 1.75f;
                else if (MathUtil.inRange(reelDiff, 5, 8))
                    pitch = 1.55f;
                else if (MathUtil.inRange(reelDiff, 9, 13))
                    pitch = 1.35f;
                else if (MathUtil.inRange(reelDiff, 14, 19))
                    pitch = 1.15f;
                else if (MathUtil.inRange(reelDiff, 20, 30))
                    pitch = 1.0f;
                else if (MathUtil.inRange(reelDiff, 31, 43))
                    pitch = 0.65f;
                else if (MathUtil.inRange(reelDiff, 44, 58))
                    pitch = 0.35f;
                else if (MathUtil.inRange(reelDiff, 59, 72))
                    pitch = 0.15f;
                else
                    pitch = 0.0f;


                player.world.playSound(null, player.posX, player.posY, player.posZ, RegistryHandler.REELING_EVENT, SoundCategory.PLAYERS, 1.0f, pitch);
            });

            return null;
        }
    }
}
