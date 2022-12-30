package net.theawesomegem.fishingmadebetter.client.jei;

import net.theawesomegem.fishingmadebetter.common.item.ItemManager;

import java.util.ArrayList;
import java.util.List;

public final class BaitBucketRecipeMaker {

    public static List<BaitBucketRecipeWrapper> getBaitBucketRecipes() {
        List<BaitBucketRecipeWrapper> recipes = new ArrayList<>();

        recipes.add(new BaitBucketRecipeWrapper(ItemManager.BAIT_BUCKET));

        return recipes;
    }
}