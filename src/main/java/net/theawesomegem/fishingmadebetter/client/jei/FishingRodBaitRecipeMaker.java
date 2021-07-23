package net.theawesomegem.fishingmadebetter.client.jei;

import java.util.ArrayList;
import java.util.List;

import net.theawesomegem.fishingmadebetter.common.item.ItemManager;

public final class FishingRodBaitRecipeMaker {

	public static List<FishingRodBaitRecipeWrapper> getFishingRodBaitRecipes() {
		List<FishingRodBaitRecipeWrapper> recipes = new ArrayList<>();
		
		recipes.add(new FishingRodBaitRecipeWrapper(ItemManager.FISHING_ROD_WOOD));
		recipes.add(new FishingRodBaitRecipeWrapper(ItemManager.FISHING_ROD_IRON));
		recipes.add(new FishingRodBaitRecipeWrapper(ItemManager.FISHING_ROD_DIAMOND));
		
		return recipes;
	}
}
