package net.theawesomegem.fishingmadebetter.client.jei;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FishLavaBucketRecipeWrapper implements ICraftingRecipeWrapper {
	private final List<List<ItemStack>> inputs;
	private final ItemStack output;

	public FishLavaBucketRecipeWrapper() {
		ItemStack lavaBucket = new ItemStack(Items.LAVA_BUCKET);
		ItemStack fishLavaBucket = new ItemStack(ItemManager.FISH_LAVA_BUCKET);
		List<ItemStack> fishStackList = new ArrayList<ItemStack>();
		
		for(FishData fishData : CustomConfigurationHandler.fishDataMap.values()) {
			if(!fishData.liquid.equals(FishingLiquid.LAVA)) continue;
			
			ItemStack fishStack = new ItemStack(Item.getByNameOrId(fishData.itemId), 1, fishData.itemMetaData);
			fishStackList.add(fishStack);
		}
		
	
		this.inputs = Arrays.asList(Arrays.asList(lavaBucket), fishStackList);
		this.output = fishLavaBucket;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(VanillaTypes.ITEM, this.inputs);
		ingredients.setOutput(VanillaTypes.ITEM, this.output);
	}
}
