package net.theawesomegem.fishingmadebetter.client.jei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.theawesomegem.fishingmadebetter.common.configuration.CustomConfigurationHandler;
import net.theawesomegem.fishingmadebetter.common.data.FishData;
import net.theawesomegem.fishingmadebetter.common.data.FishData.FishingLiquid;
import net.theawesomegem.fishingmadebetter.common.item.ItemManager;

public class FishBucketRecipeWrapper implements ICraftingRecipeWrapper {
	private final List<List<ItemStack>> inputs;
	private final ItemStack output;

	public FishBucketRecipeWrapper() {
		ItemStack waterBucket = new ItemStack(Items.WATER_BUCKET);
		ItemStack fishBucket = new ItemStack(ItemManager.FISH_BUCKET);
		List<ItemStack> fishStackList = new ArrayList<ItemStack>();
		
		for(FishData fishData : CustomConfigurationHandler.fishDataMap.values()) {
			if(!fishData.liquid.equals(FishingLiquid.WATER)) continue;
			
			ItemStack fishStack = new ItemStack(Item.getByNameOrId(fishData.itemId), 1, fishData.itemMetaData);
			fishStackList.add(fishStack);
		}
		
	
		this.inputs = Arrays.asList(Arrays.asList(waterBucket), fishStackList);
		this.output = fishBucket;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(VanillaTypes.ITEM, this.inputs);
		ingredients.setOutput(VanillaTypes.ITEM, this.output);
	}
}
