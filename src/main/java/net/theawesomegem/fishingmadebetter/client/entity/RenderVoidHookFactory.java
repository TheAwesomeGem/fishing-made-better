package net.theawesomegem.fishingmadebetter.client.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class RenderVoidHookFactory implements IRenderFactory<EntityFishHook> {

    public static final RenderVoidHookFactory INSTANCE = new RenderVoidHookFactory();

    @Override
    public Render<? super EntityFishHook> createRenderFor(RenderManager manager) {
        return new RenderVoidHook(manager);
    }

}