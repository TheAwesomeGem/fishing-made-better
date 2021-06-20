package net.theawesomegem.fishingmadebetter.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.theawesomegem.fishingmadebetter.ModInfo;
import net.theawesomegem.fishingmadebetter.common.block.BlockManager;
import net.theawesomegem.fishingmadebetter.common.capability.CapabilityHandler;
import net.theawesomegem.fishingmadebetter.common.capability.fishing.FishingData;
import net.theawesomegem.fishingmadebetter.common.capability.fishing.IFishingData;
import net.theawesomegem.fishingmadebetter.common.capability.world.ChunkFishingData;
import net.theawesomegem.fishingmadebetter.common.capability.world.ChunkFishingStorage;
import net.theawesomegem.fishingmadebetter.common.capability.world.IChunkFishingData;
import net.theawesomegem.fishingmadebetter.common.command.FishingCommand;
import net.theawesomegem.fishingmadebetter.common.configuration.ConfigurationManager;
import net.theawesomegem.fishingmadebetter.common.configuration.CustomFishConfigurationHandler;
import net.theawesomegem.fishingmadebetter.common.event.FishingEventHandler;
import net.theawesomegem.fishingmadebetter.common.item.ItemManager;
import net.theawesomegem.fishingmadebetter.common.networking.PrimaryPacketHandler;
import net.theawesomegem.fishingmadebetter.common.recipe.RecipeBaitedRod;
import net.theawesomegem.fishingmadebetter.common.recipe.RecipeFishBucket;
import net.theawesomegem.fishingmadebetter.common.recipe.RecipeFishScale;
import net.theawesomegem.fishingmadebetter.common.recipe.RecipeFishSlice;
import net.theawesomegem.fishingmadebetter.util.RebornCraftingHelper;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

/**
 * Created by TheAwesomeGem on 6/18/2017.
 */

public class CommonProxy {
    public static Logger Logger;

    public void onPreInit(FMLPreInitializationEvent e) {
        Logger = e.getModLog();

        CustomFishConfigurationHandler.preInit(e.getModConfigurationDirectory());
        PrimaryPacketHandler.registerPackets();
    }

    public void onInit(FMLInitializationEvent e) {
        registerCapabilities();
        registerEvents();
        registerRecipes();
    }

    public void onPostInit(FMLPostInitializationEvent e) {
        CustomFishConfigurationHandler.postInit();
    }

    public void onServerStarting(FMLServerStartingEvent e) {
        e.registerServerCommand(new FishingCommand());
    }

    private void registerCapabilities() {
        CapabilityManager.INSTANCE.register(IChunkFishingData.class, new ChunkFishingStorage(), ChunkFishingData.class);

        CapabilityManager.INSTANCE.register(IFishingData.class, new IStorage<IFishingData>() {
            @Nullable
            @Override
            public NBTBase writeNBT(Capability<IFishingData> capability, IFishingData instance, EnumFacing side) {
                return new NBTTagCompound();
            }

            @Override
            public void readNBT(Capability<IFishingData> capability, IFishingData instance, EnumFacing side, NBTBase nbt) {

            }
        }, FishingData::new);
    }

    protected void registerEvents() {
        MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
        MinecraftForge.EVENT_BUS.register(new FishingEventHandler());
    }

    protected void registerRecipes() {
        GameRegistry.findRegistry(IRecipe.class).register(new RecipeFishSlice().setRegistryName(new ResourceLocation(ModInfo.MOD_ID, "fish_slice")));
        GameRegistry.findRegistry(IRecipe.class).register(new RecipeFishScale().setRegistryName(new ResourceLocation(ModInfo.MOD_ID, "fish_scale")));
        GameRegistry.findRegistry(IRecipe.class).register(new RecipeBaitedRod().setRegistryName(new ResourceLocation(ModInfo.MOD_ID, "baitedrod")));

        if(ConfigurationManager.enableFishBucket) {
            GameRegistry.findRegistry(IRecipe.class).register(new RecipeFishBucket().setRegistryName(new ResourceLocation(ModInfo.MOD_ID, "fish_bucket")));
        }

        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.FISHING_ROD_BASIC, 1), "  S", " SI", "S W", 'S', "stickWood", 'I', "ingotIron", 'W', Items.STRING);
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.FISHING_ROD_IRON, 1), "  S", " SI", "S W", 'S', "ingotIron", 'I', "ingotIron", 'W', Items.STRING);
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.FISHING_ROD_DIAMOND, 1), "  S", " SI", "S W", 'S', "gemDiamond", 'I', "ingotIron", 'W', Items.STRING);
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.KNIFE, 1), "  I", " I ", "S  ", 'I', "ingotIron", 'S', "stickWood");
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.SCALE_REMOVER, 1), " II", "ID ", "I  ", 'I', "ingotIron", 'D', "gemDiamond");

        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.FISH_TRACKER_IRON, 1), " I ", "IFI", " I ", 'I', "ingotIron", 'F', Items.FISH);
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.FISH_TRACKER_GOLD, 1), " I ", "IFI", " I ", 'I', "ingotGold", 'F', Items.FISH);
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.FISH_TRACKER_DIAMOND, 1), " I ", "IFI", " I ", 'I', "gemDiamond", 'F', Items.FISH);

        RebornCraftingHelper.addShapedRecipe(new ItemStack(BlockManager.ITEM_BLOCK_BAIT_BOX, 1), "LLL", "LGL", "LLL", 'L', new ItemStack(Items.DYE, 1, 4), 'G', new ItemStack(Blocks.GLASS));
    }

    public IThreadListener getListener(MessageContext ctx) {
        return (WorldServer) ctx.getServerHandler().player.world;
    }

    public EntityPlayer getPlayer(MessageContext ctx) {
        return ctx.getServerHandler().player;
    }

    public boolean hasPermission(EntityPlayer player)
    {
        return player.canUseCommand(2, "gamemode");
    }
}
