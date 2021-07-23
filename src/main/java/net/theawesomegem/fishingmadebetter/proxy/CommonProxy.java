package net.theawesomegem.fishingmadebetter.proxy;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
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
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryModifiable;
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
import net.theawesomegem.fishingmadebetter.common.configuration.CustomConfigurationHandler;
import net.theawesomegem.fishingmadebetter.common.entity.EntityManager;
import net.theawesomegem.fishingmadebetter.common.event.FishingEventHandler;
import net.theawesomegem.fishingmadebetter.common.item.ItemManager;
import net.theawesomegem.fishingmadebetter.common.loottable.LootHandler;
import net.theawesomegem.fishingmadebetter.common.networking.PrimaryPacketHandler;
import net.theawesomegem.fishingmadebetter.common.recipe.RecipeBaitedRod;
import net.theawesomegem.fishingmadebetter.common.recipe.RecipeFishBucket;
import net.theawesomegem.fishingmadebetter.common.recipe.RecipeFishScale;
import net.theawesomegem.fishingmadebetter.common.recipe.RecipeFishSliceRaw;
import net.theawesomegem.fishingmadebetter.common.recipe.RecipeRodAttachment;
import net.theawesomegem.fishingmadebetter.util.RebornCraftingHelper;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

import javax.annotation.Nullable;

/**
 * Created by TheAwesomeGem on 6/18/2017.
 */

public class CommonProxy {
    public static Logger Logger;

    public void onPreInit(FMLPreInitializationEvent e) {
        Logger = e.getModLog();

        CustomConfigurationHandler.preInit(e.getModConfigurationDirectory());
        PrimaryPacketHandler.registerPackets();
        EntityManager.register();
    }

    public void onInit(FMLInitializationEvent e) {
        registerCapabilities();
        registerEvents();
        registerRecipes();
    }

    public void onPostInit(FMLPostInitializationEvent e) {
        CustomConfigurationHandler.postInit();
        LootHandler.registerLootTable();
    }

    public void onServerStarting(FMLServerStartingEvent e) {
        e.registerServerCommand(new FishingCommand());
    }

    private void registerCapabilities() {
        CapabilityManager.INSTANCE.register(IChunkFishingData.class, new ChunkFishingStorage(), ChunkFishingData::new);

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
        MinecraftForge.EVENT_BUS.register(new LootHandler());
    }

    protected void registerRecipes() {
    	IForgeRegistry<IRecipe> recipeRegistry = GameRegistry.findRegistry(IRecipe.class);
    	
    	recipeRegistry.register(new RecipeFishSliceRaw().setRegistryName(new ResourceLocation(ModInfo.MOD_ID, "fish_slice_raw")));
    	recipeRegistry.register(new RecipeFishScale().setRegistryName(new ResourceLocation(ModInfo.MOD_ID, "fish_scale")));
    	recipeRegistry.register(new RecipeBaitedRod().setRegistryName(new ResourceLocation(ModInfo.MOD_ID, "baited_rod")));
    	recipeRegistry.register(new RecipeRodAttachment().setRegistryName(new ResourceLocation(ModInfo.MOD_ID, "rod_attachment")));

        if(ConfigurationManager.server.enableFishBucket) {
        	recipeRegistry.register(new RecipeFishBucket().setRegistryName(new ResourceLocation(ModInfo.MOD_ID, "fish_bucket")));
            RebornCraftingHelper.addShapelessRecipe(new ItemStack(Items.BUCKET), ItemManager.FISH_BUCKET);
        }
        
        RebornCraftingHelper.addSmelting(new ItemStack(ItemManager.FISH_SLICE_RAW), new ItemStack(ItemManager.FISH_SLICE_COOKED));

        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.REEL_BASIC, 1), "SIS", "IWI", "SIS", 'S', "string", 'I', "ingotIron", 'W', "stickWood");
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.REEL_FAST, 1), "SIS", "IWI", "SIS", 'S', "dustRedstone", 'I', "ingotGold", 'W', ItemManager.REEL_BASIC);
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.REEL_LONG, 1), "SSS", "SWS", "SSS", 'S', "string", 'W', ItemManager.REEL_BASIC);
        
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.BOBBER_BASIC, 1), "WSW", "RRR", "WSW", 'S', "string", 'W', new ItemStack(Blocks.WOOL, 1, 0), 'R', new ItemStack(Blocks.WOOL, 1, 14));
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.BOBBER_HEAVY, 1), " I ", "IBI", " I ", 'I', "ingotIron", 'B', ItemManager.BOBBER_BASIC);
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.BOBBER_LIGHTWEIGHT, 1), " S ", "IWI", " S ", 'S', "string", 'I', "nuggetIron", 'W', new ItemStack(Blocks.WOOL, 1, 14));
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.BOBBER_OBSIDIAN, 1), "OSO", "OBO", "OSO", 'S', "string", 'O', "obsidian", 'B', ItemManager.BOBBER_BASIC);
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.BOBBER_VOID, 1), "OSO", "OBO", "OSO", 'S', "string", 'O', "enderpearl", 'B', ItemManager.BOBBER_BASIC);
        
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.HOOK_BASIC, 1), "  S", "S S", " S ", 'S', "nuggetIron");
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.HOOK_BARBED, 1), " S ", " S ", "HIH", 'S', "nuggetIron", 'I', "ingotIron", 'H', ItemManager.HOOK_BASIC);
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.HOOK_FATTY, 1), " S ", "IHI", " I ", 'S', "nuggetIron", 'I', Items.COOKED_BEEF, 'H', ItemManager.HOOK_BASIC);
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.HOOK_MAGNETIC, 1), "BSR", "IHI", "   ", 'S', "nuggetIron", 'I', "ingotIron", 'H', ItemManager.HOOK_BARBED, 'B', new ItemStack(Items.DYE, 1, 4), 'R', new ItemStack(Items.DYE, 1, 1));
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.HOOK_SHINY, 1), " S ", "SHS", " S ", 'S', "gemDiamond", 'H', ItemManager.HOOK_BASIC);

        RebornCraftingHelper.addShapelessRecipe(new ItemStack(ItemManager.FISHING_ROD_WOOD, 1), Items.FISHING_ROD, ItemManager.BOBBER_BASIC, ItemManager.REEL_BASIC, ItemManager.HOOK_BASIC);
        
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.FISHING_ROD_WOOD, 1), "  S", " SB", "SRH", 'S', "stickWood", 'B', ItemManager.BOBBER_BASIC, 'R', ItemManager.REEL_BASIC, 'H', ItemManager.HOOK_BASIC);
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.FISHING_ROD_IRON, 1), "  S", " SB", "SRH", 'S', "ingotIron", 'B', ItemManager.BOBBER_BASIC, 'R', ItemManager.REEL_BASIC, 'H', ItemManager.HOOK_BASIC);
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.FISHING_ROD_DIAMOND, 1), "  S", " SB", "SRH", 'S', "gemDiamond", 'B', ItemManager.BOBBER_BASIC, 'R', ItemManager.REEL_BASIC, 'H', ItemManager.HOOK_BASIC);
        
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.FILLET_KNIFE_WOOD, 1), "  I", " IF", "S  ", 'I', "plankWood", 'S', "stickWood", 'F', Items.FLINT);
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.FILLET_KNIFE_IRON, 1), "  I", " IF", "S  ", 'I', "ingotIron", 'S', "stickWood", 'F', Items.FLINT);
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.FILLET_KNIFE_DIAMOND, 1), "  I", " IF", "S  ", 'I', "gemDiamond", 'S', "stickWood", 'F', Items.FLINT);
        
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.SCALING_KNIFE_WOOD, 1), " FF", "SII", "   ", 'I', "plankWood", 'S', "stickWood", 'F', Items.FLINT);
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.SCALING_KNIFE_IRON, 1), " FF", "SII", "   ", 'I', "ingotIron", 'S', "stickWood", 'F', Items.FLINT);
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.SCALING_KNIFE_DIAMOND, 1), " FF", "SII", "   ", 'I', "gemDiamond", 'S', "stickWood", 'F', Items.FLINT);
        
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.FISH_TRACKER_IRON, 1), "IGI", "GFG", "IGI", 'I', "ingotIron", 'G', "paneGlass", 'F', Items.FISH);
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.FISH_TRACKER_GOLD, 1), "IGI", "GFG", "IGI", 'I', "ingotGold", 'G', "paneGlass", 'F', Items.FISH);
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.FISH_TRACKER_DIAMOND, 1), "IGI", "GFG", "IGI", 'I', "gemDiamond", 'G', "paneGlass", 'F', Items.FISH);
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.FISH_TRACKER_OBSIDIAN, 1), "IGI", "GFG", "IGI", 'I', Blocks.OBSIDIAN, 'G', "paneGlass", 'F', Items.FISH);
        RebornCraftingHelper.addShapedOreRecipe(new ItemStack(ItemManager.FISH_TRACKER_VOID, 1), "IGI", "GFG", "IGI", 'I', Items.ENDER_EYE, 'G', "paneGlass", 'F', Items.FISH);

        RebornCraftingHelper.addShapedRecipe(new ItemStack(BlockManager.ITEM_BLOCK_BAIT_BOX, 1), "WIW", "I I", "WWW", 'W', "plankWood", 'I', Blocks.IRON_BARS);
        
        if(Loader.isModLoaded("aquaculture") && ConfigurationManager.server.aquacultureRecipeOverride) {
        	IForgeRegistryModifiable<IRecipe> modRegistry = (IForgeRegistryModifiable<IRecipe>)recipeRegistry;
        	
        	modRegistry.remove(new ResourceLocation("aquaculture:wooden_rod_from_vanilla_rod"));
        	modRegistry.remove(new ResourceLocation("aquaculture:vanilla_rod_from_wooden_rod"));
        	modRegistry.remove(new ResourceLocation("aquaculture:iron_fishing_rod"));
        	modRegistry.remove(new ResourceLocation("aquaculture:golden_fishing_rod"));
        	modRegistry.remove(new ResourceLocation("aquaculture:diamond_fishing_rod"));
        	modRegistry.remove(new ResourceLocation("aquaculture:ink_from_squid"));
        	modRegistry.remove(new ResourceLocation("aquaculture:red_mushroom_from_red_shrooma"));
        	modRegistry.remove(new ResourceLocation("aquaculture:brown_mushroom_from_fish"));
        	modRegistry.remove(new ResourceLocation("aquaculture:gold_nugget_from_gold_fish"));
        	int aqcRemoved = 9;
        	ArrayList<IRecipe> recipeRemoveQueue = new ArrayList<IRecipe>();
        	for(IRecipe recipe : modRegistry.getValuesCollection()) {
        		if(recipe.getRecipeOutput().getItem() == Item.getByNameOrId("aquaculture:food")) {
        			recipeRemoveQueue.add(recipe);
        		}
        	}
        	for(IRecipe recipe : recipeRemoveQueue) {
        		modRegistry.remove(recipe.getRegistryName());
        		aqcRemoved++;
        	}
        	CommonProxy.Logger.log(Level.INFO, "Removed " + aqcRemoved + " Aquaculture recipes");

            RebornCraftingHelper.addSmelting(new ItemStack(Item.getByNameOrId("aquaculture:fish"), 1, 18), new ItemStack(Item.getByNameOrId("aquaculture:food"), 1, 9));
        	RebornCraftingHelper.addShapelessRecipe(new ItemStack(Item.getByNameOrId("aquaculture:food"), 1, 6), Items.BREAD, Items.BREAD, new ItemStack(Item.getByNameOrId("aquaculture:food"), 1, 5));
        	RebornCraftingHelper.addShapelessRecipe(new ItemStack(Item.getByNameOrId("aquaculture:food"), 1, 10), ItemManager.FISH_SLICE_RAW, new ItemStack(Item.getByNameOrId("aquaculture:food"), 1, 0));
        }
    }

    public IThreadListener getListener(MessageContext ctx) {
        return (WorldServer) ctx.getServerHandler().player.world;
    }

    public EntityPlayer getPlayer(MessageContext ctx) {
        return ctx.getServerHandler().player;
    }
}
