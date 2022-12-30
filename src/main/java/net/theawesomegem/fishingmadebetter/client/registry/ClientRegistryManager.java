package net.theawesomegem.fishingmadebetter.client.registry;

import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.theawesomegem.fishingmadebetter.ModInfo;
import net.theawesomegem.fishingmadebetter.client.BakedModelRod;
import net.theawesomegem.fishingmadebetter.common.block.BlockManager;
import net.theawesomegem.fishingmadebetter.common.item.ItemManager;
import net.theawesomegem.fishingmadebetter.common.item.attachment.bobber.ItemBobber;
import net.theawesomegem.fishingmadebetter.common.item.attachment.hook.ItemHook;
import net.theawesomegem.fishingmadebetter.common.item.attachment.reel.ItemReel;
import net.theawesomegem.fishingmadebetter.common.item.fishingrod.ItemBetterFishingRod;

/**
 * Created by TheAwesomeGem on 12/28/2017.
 */
public class ClientRegistryManager {
    @EventBusSubscriber(Side.CLIENT)
    public static class RegistryHandler {
        @SubscribeEvent
        public static void onRegisterModel(ModelRegistryEvent e) {
            BlockManager.registerModels();
            ItemManager.registerModels();
            
            ItemReel reel;
        	ItemBobber bobber;
        	ItemHook hook;
        	
            ItemReel[] reelList = ItemManager.reelAttachmentList();
        	ItemBobber[] bobberList = ItemManager.bobberAttachmentList();
        	ItemHook[] hookList = ItemManager.hookAttachmentList();
        	
            for(ItemBetterFishingRod rod : ItemManager.rodList()) {
            	ModelLoader.setCustomModelResourceLocation(rod, 0, new ModelResourceLocation(ModInfo.MOD_ID + ":rod/" + rod.getRegistryName().getPath() + "/rod", "inventory"));
            	ModelLoader.setCustomModelResourceLocation(rod, 1, new ModelResourceLocation(ModInfo.MOD_ID + ":rod/" + rod.getRegistryName().getPath() + "/rod_cast", "inventory"));
            }
            for(int i=0;i<reelList.length;i++) {
        		reel = reelList[i];
        		
        		if(reel!=null) {//Use metadata 1 for mesh texture because variants dont seem to work properly for this
        			ModelLoader.setCustomModelResourceLocation(reel, 0, new ModelResourceLocation(ModInfo.MOD_ID + ":attachment/reel/" + reel.getRegistryName().getPath(), "inventory"));
        			ModelLoader.setCustomModelResourceLocation(reel, 1, new ModelResourceLocation(ModInfo.MOD_ID + ":mesh/reel/" + reel.getRegistryName().getPath(), "inventory"));
        		}
        	}
        	for(int i=0; i<bobberList.length; i++) {
        		bobber = bobberList[i];

        		if(bobber!=null) {
        			ModelLoader.setCustomModelResourceLocation(bobber, 0, new ModelResourceLocation(ModInfo.MOD_ID + ":attachment/bobber/" + bobber.getRegistryName().getPath(), "inventory"));
        			ModelLoader.setCustomModelResourceLocation(bobber, 1, new ModelResourceLocation(ModInfo.MOD_ID + ":mesh/bobber/" + bobber.getRegistryName().getPath(), "inventory"));
        		}
        	}

        	for(int i=0; i<hookList.length; i++) {
        		hook = hookList[i];
        		
        		if(hook!=null) {
        			ModelLoader.setCustomModelResourceLocation(hook, 0, new ModelResourceLocation(ModInfo.MOD_ID + ":attachment/hook/" + hook.getRegistryName().getPath(), "inventory"));
        			ModelLoader.setCustomModelResourceLocation(hook, 1, new ModelResourceLocation(ModInfo.MOD_ID + ":mesh/hook/" + hook.getRegistryName().getPath(), "inventory"));
        		}
        	}
        }
        
        @SubscribeEvent
        public static void onModelBake(ModelBakeEvent e) {
        	ModelResourceLocation mrl;
        	ModelResourceLocation mrlCast;
        	IBakedModel mainModel;
        	IBakedModel mainModelCast;
        	IBakedModel[][] models;
        	
        	ItemReel reel;
        	ItemBobber bobber;
        	ItemHook hook;
        	
        	ItemBetterFishingRod[] rodList = ItemManager.rodList();
        	ItemReel[] reelList = ItemManager.reelAttachmentList();
        	ItemBobber[] bobberList = ItemManager.bobberAttachmentList();
        	ItemHook[] hookList = ItemManager.hookAttachmentList();
        	
        	models = new IBakedModel[3][];
    		
    		models[0] = new IBakedModel[reelList.length];
    		models[1] = new IBakedModel[bobberList.length];
    		models[2] = new IBakedModel[hookList.length];
    		
        	for(int i=0; i<reelList.length; i++) {
        		reel = reelList[i];

        		if(reel != null) {
        			models[0][i] = e.getModelRegistry().getObject(new ModelResourceLocation(ModInfo.MOD_ID + ":mesh/reel/" + reel.getRegistryName().getPath(), "inventory"));
        		}
        	}
        	for(int i=0; i<bobberList.length; i++) {
        		bobber = bobberList[i];
        		
        		if(bobber != null) {
        			models[1][i] = e.getModelRegistry().getObject(new ModelResourceLocation(ModInfo.MOD_ID + ":mesh/bobber/" + bobber.getRegistryName().getPath(), "inventory"));
        		}
        	}
        	for(int i=0; i<hookList.length; i++) {
        		hook = hookList[i];
        		
        		if(hook != null) {
        			models[2][i] = e.getModelRegistry().getObject(new ModelResourceLocation(ModInfo.MOD_ID + ":mesh/hook/" + hook.getRegistryName().getPath(), "inventory"));
        		}
        	}
        	for(ItemBetterFishingRod rod : rodList) {
	        	mrl = new ModelResourceLocation(ModInfo.MOD_ID + ":rod/" + rod.getRegistryName().getPath() + "/rod", "inventory");
	        	mrlCast = new ModelResourceLocation(ModInfo.MOD_ID + ":rod/" + rod.getRegistryName().getPath() + "/rod_cast", "inventory");
	        	
	        	mainModel = e.getModelRegistry().getObject(mrl);
	        	mainModelCast = e.getModelRegistry().getObject(mrlCast);
	        	
	        	e.getModelRegistry().putObject(mrl, new BakedModelRod(mainModel, mainModelCast, models));
        	}	
        }
    }
}
