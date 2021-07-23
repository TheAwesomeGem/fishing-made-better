package net.theawesomegem.fishingmadebetter.client.jei;

import java.util.ArrayList;
import java.util.List;

import net.theawesomegem.fishingmadebetter.common.configuration.CustomConfigurationHandler;
import net.theawesomegem.fishingmadebetter.common.data.FishData;
import net.theawesomegem.fishingmadebetter.common.data.FishData.FishingLiquid;

public final class FishBucketRecipeMaker {

	public static List<FishBucketRecipeWrapper> getFishBucketRecipes() {
		List<FishBucketRecipeWrapper> recipes = new ArrayList<>();
		
		recipes.add(new FishBucketRecipeWrapper());
		
		return recipes;
	}
}
