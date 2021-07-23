package net.theawesomegem.fishingmadebetter.client.jei;

import java.util.Arrays;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.theawesomegem.fishingmadebetter.common.data.FishData;
import net.theawesomegem.fishingmadebetter.common.item.ItemManager;

public class FishScalingRecipeWrapper implements ICraftingRecipeWrapper {
	private final List<List<ItemStack>> inputs;
	private final ItemStack output;

	public FishScalingRecipeWrapper(FishData fishData) {
		ItemStack knifeWood = new ItemStack(ItemManager.SCALING_KNIFE_WOOD);
		ItemStack knifeIron = new ItemStack(ItemManager.SCALING_KNIFE_IRON);
		ItemStack knifeDiamond = new ItemStack(ItemManager.SCALING_KNIFE_DIAMOND);
		
		ItemStack fishStack = new ItemStack(Item.getByNameOrId(fishData.itemId), 1, fishData.itemMetaData);
		ItemStack scaleStack = new ItemStack(Item.getByNameOrId(fishData.scalingItem), 1, fishData.scalingItemMetadata);
	
		this.inputs = Arrays.asList(Arrays.asList(knifeWood, knifeIron, knifeDiamond), Arrays.asList(fishStack));
		this.output = scaleStack;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(VanillaTypes.ITEM, this.inputs);
		ingredients.setOutput(VanillaTypes.ITEM, this.output);
	}
}
