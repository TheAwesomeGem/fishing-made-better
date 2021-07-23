package net.theawesomegem.fishingmadebetter.common.entity;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.theawesomegem.fishingmadebetter.ModInfo;

public class EntityManager {

	public static final String EntityFMBLavaFishHookName = "FMBLavaFishHook";
	public static final String EntityFMBVoidFishHookName = "FMBVoidFishHook";
	public static final ResourceLocation EntityFMBLavaFishHookResource = new ResourceLocation(ModInfo.MOD_ID + ":fmblavafishhook");
	public static final ResourceLocation EntityFMBVoidFishHookResource = new ResourceLocation(ModInfo.MOD_ID + ":fmbvoidfishhook");
	
	private EntityManager() {}
	
	public static void register() {
		EntityRegistry.registerModEntity(EntityFMBLavaFishHookResource, EntityFMBLavaFishHook.class, EntityFMBLavaFishHookName, 0, ModInfo.MOD_ID, 100, 1, true);
		EntityRegistry.registerModEntity(EntityFMBVoidFishHookResource, EntityFMBVoidFishHook.class, EntityFMBVoidFishHookName, 1, ModInfo.MOD_ID, 100, 1, true);
	}
}
