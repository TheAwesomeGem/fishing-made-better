package net.theawesomegem.fishingmadebetter.client.jei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.theawesomegem.fishingmadebetter.common.configuration.CustomConfigurationHandler;
import net.theawesomegem.fishingmadebetter.common.data.FishData;

public final class FishSlicingRecipeMaker {

	public static List<FishSlicingRecipeWrapper> getFishSlicingRecipes() {
		List<FishSlicingRecipeWrapper> recipes = new ArrayList<>();
		List<FishData> defaultSlicing = new ArrayList<>();
		List<FishData> customSlicing = new ArrayList<>();
		
		for(FishData fishData : CustomConfigurationHandler.fishDataMap.values()) {
			if(!fishData.allowFillet) continue;
			
			if(fishData.defaultFillet) defaultSlicing.add(fishData);
			else customSlicing.add(fishData);
		}
		recipes.add(new FishSlicingRecipeWrapper(defaultSlicing, true));
		for(FishData fishData : customSlicing) {
			recipes.add(new FishSlicingRecipeWrapper(Arrays.asList(fishData), false));
		}
		return recipes;
	}
}
