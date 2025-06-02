package net.theawesomegem.fishingmadebetter.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.theawesomegem.fishingmadebetter.ModInfo;
import net.theawesomegem.fishingmadebetter.Primary;

import java.util.function.Function;

@Mod.EventBusSubscriber
public class EntityManager {

	public static final String EntityFMBLavaFishHookName = "FMBLavaFishHook";
	public static final String EntityFMBVoidFishHookName = "FMBVoidFishHook";
	public static final ResourceLocation EntityFMBLavaFishHookResource = new ResourceLocation(ModInfo.MOD_ID + ":fmblavafishhook");
	public static final ResourceLocation EntityFMBVoidFishHookResource = new ResourceLocation(ModInfo.MOD_ID + ":fmbvoidfishhook");
	
	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityEntry> event) {
		Function<World, EntityFMBLavaFishHook> lavaFunc = new Function<World,EntityFMBLavaFishHook>() {
			@Override
			public EntityFMBLavaFishHook apply(World world) {
				EntityPlayer player = Primary.proxy.getClientPlayer();
				return player == null ? null : new EntityFMBLavaFishHook(world, player);
			}
		};
		registerEntity(EntityEntryBuilder.<EntityFMBLavaFishHook>create(), event, EntityFMBLavaFishHook.class, EntityFMBLavaFishHookName, EntityFMBLavaFishHookResource, 0, 100, lavaFunc);
		Function<World, EntityFMBVoidFishHook> voidFunc = new Function<World,EntityFMBVoidFishHook>() {
			@Override
			public EntityFMBVoidFishHook apply(World world) {
				EntityPlayer player = Primary.proxy.getClientPlayer();
				return player == null ? null : new EntityFMBVoidFishHook(world, player);
			}
		};
		registerEntity(EntityEntryBuilder.<EntityFMBVoidFishHook>create(), event, EntityFMBVoidFishHook.class, EntityFMBVoidFishHookName, EntityFMBVoidFishHookResource, 1, 100, voidFunc);
	}
	
	public static void registerEntity(EntityEntryBuilder builder, RegistryEvent.Register<EntityEntry> event, Class<? extends Entity> entityClass, String name, ResourceLocation entityid, int id, int range, Function<World,? extends Entity> function) {
		builder.entity(entityClass);
		builder.id(entityid, id);
		builder.name(name);
		builder.tracker(range, 1, true);
		builder.factory(function);
		event.getRegistry().register(builder.build());
	}
}