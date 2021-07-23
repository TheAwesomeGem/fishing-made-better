package net.theawesomegem.fishingmadebetter.client.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.recipe.VanillaRecipeCategoryUid;
import net.theawesomegem.fishingmadebetter.common.configuration.ConfigurationManager;

@JEIPlugin
public class JeiPlugin implements IModPlugin {

	@Override
	public void register(IModRegistry registry) {
		
		registry.addRecipes(FishScalingRecipeMaker.getFishScalingRecipes(), VanillaRecipeCategoryUid.CRAFTING);
		registry.addRecipes(FishSlicingRecipeMaker.getFishSlicingRecipes(), VanillaRecipeCategoryUid.CRAFTING);
		registry.addRecipes(FishingRodBaitRecipeMaker.getFishingRodBaitRecipes(), VanillaRecipeCategoryUid.CRAFTING);
		registry.addRecipes(FishingRodAttachmentRecipeMaker.getFishingRodAttachmentRecipes(), VanillaRecipeCategoryUid.CRAFTING);
		
		if(ConfigurationManager.server.enableFishBucket) registry.addRecipes(FishBucketRecipeMaker.getFishBucketRecipes(), VanillaRecipeCategoryUid.CRAFTING);
	}
	
}
