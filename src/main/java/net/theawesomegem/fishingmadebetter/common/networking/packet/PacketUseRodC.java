package net.theawesomegem.fishingmadebetter.common.networking.packet;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.theawesomegem.fishingmadebetter.Primary;

public class PacketUseRodC implements IMessage {

	private boolean useRod;
	
	public PacketUseRodC() {
		this.useRod = false;
	}
	
	public PacketUseRodC(boolean useRod) {
		this.useRod = useRod;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		this.useRod = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(this.useRod);
	}
	
	public static class UseRodMessageHandler implements IMessageHandler<PacketUseRodC, IMessage> {
		@Override
		public IMessage onMessage(PacketUseRodC message, MessageContext ctx) {
			final EntityPlayer player = Primary.proxy.getPlayer(ctx);
			if(player==null) return null;
			final boolean useRod = message.useRod;
			
			IThreadListener thread = Primary.proxy.getListener(ctx);

            thread.addScheduledTask(() ->
            {
            	if(useRod) KeyBinding.onTick(Minecraft.getMinecraft().gameSettings.keyBindUseItem.getKeyCode());
            });
            
            return null;
		}
	}
}
