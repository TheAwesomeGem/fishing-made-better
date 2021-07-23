package net.theawesomegem.fishingmadebetter.client.jei;

import java.util.ArrayList;
import java.util.List;

import net.theawesomegem.fishingmadebetter.common.item.ItemManager;

public final class FishingRodAttachmentRecipeMaker {

	public static List<FishingRodAttachmentRecipeWrapper> getFishingRodAttachmentRecipes() {
		List<FishingRodAttachmentRecipeWrapper> recipes = new ArrayList<>();
		
		recipes.add(new FishingRodAttachmentRecipeWrapper(ItemManager.FISHING_ROD_WOOD));
		recipes.add(new FishingRodAttachmentRecipeWrapper(ItemManager.FISHING_ROD_IRON));
		recipes.add(new FishingRodAttachmentRecipeWrapper(ItemManager.FISHING_ROD_DIAMOND));
		
		return recipes;
	}
}
