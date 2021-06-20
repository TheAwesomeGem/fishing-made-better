package net.theawesomegem.fishingmadebetter.proxy;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.theawesomegem.fishingmadebetter.client.KeybindManager;
import net.theawesomegem.fishingmadebetter.client.event.KeyBindingHandler;
import net.theawesomegem.fishingmadebetter.client.gui.GuiReelingOverlay;

/**
 * Created by TheAwesomeGem on 6/18/2017.
 */
public class ClientProxy extends CommonProxy {
    @Override
    public void onInit(FMLInitializationEvent e) {
        super.onInit(e);

        KeybindManager.load();
    }

    @Override
    protected void registerEvents() {
        super.registerEvents();

        MinecraftForge.EVENT_BUS.register(new KeyBindingHandler());
        MinecraftForge.EVENT_BUS.register(new GuiReelingOverlay(Minecraft.getMinecraft()));
    }

    @Override
    public IThreadListener getListener(MessageContext ctx) {
        return ctx.side == Side.CLIENT ? Minecraft.getMinecraft() : super.getListener(ctx);
    }

    @Override
    public EntityPlayer getPlayer(MessageContext ctx) {
        return ctx.side == Side.CLIENT ? Minecraft.getMinecraft().player : super.getPlayer(ctx);
    }
}
