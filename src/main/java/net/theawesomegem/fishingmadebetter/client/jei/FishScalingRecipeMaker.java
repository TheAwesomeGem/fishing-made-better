package net.theawesomegem.fishingmadebetter.client.jei;

import java.util.ArrayList;
import java.util.List;

import net.theawesomegem.fishingmadebetter.common.configuration.CustomConfigurationHandler;
import net.theawesomegem.fishingmadebetter.common.data.FishData;

public final class FishScalingRecipeMaker {

	public static List<FishScalingRecipeWrapper> getFishScalingRecipes() {
		List<FishScalingRecipeWrapper> recipes = new ArrayList<>();
		for(FishData fishData : CustomConfigurationHandler.fishDataMap.values()) {
			if(!fishData.allowScaling) continue;
			
			recipes.add(new FishScalingRecipeWrapper(fishData));
		}
		return recipes;
	}
}
