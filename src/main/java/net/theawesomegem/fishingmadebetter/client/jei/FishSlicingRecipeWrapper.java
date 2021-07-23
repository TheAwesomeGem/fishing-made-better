package net.theawesomegem.fishingmadebetter.client.jei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.theawesomegem.fishingmadebetter.common.data.FishData;
import net.theawesomegem.fishingmadebetter.common.item.ItemManager;

public class FishSlicingRecipeWrapper implements ICraftingRecipeWrapper {
	private final List<List<ItemStack>> inputs;
	private final ItemStack output;

	public FishSlicingRecipeWrapper(List<FishData> fishDataList, boolean useDefault) {
		ItemStack knifeWood = new ItemStack(ItemManager.FILLET_KNIFE_WOOD);
		ItemStack knifeIron = new ItemStack(ItemManager.FILLET_KNIFE_IRON);
		ItemStack knifeDiamond = new ItemStack(ItemManager.FILLET_KNIFE_DIAMOND);
		
		List<ItemStack> fishStackList = new ArrayList<ItemStack>();
		
		ItemStack sliceStack = new ItemStack(ItemManager.FISH_SLICE_RAW);
		
		for(FishData fishData : fishDataList) {
			ItemStack fishStack = new ItemStack(Item.getByNameOrId(fishData.itemId), 1, fishData.itemMetaData);
			fishStackList.add(fishStack);
			
			if(!useDefault) sliceStack = new ItemStack(Item.getByNameOrId(fishData.filletItem), 1, fishData.filletItemMetadata);
		}
	
		
		this.inputs = Arrays.asList(Arrays.asList(knifeWood, knifeIron, knifeDiamond), fishStackList);
		this.output = sliceStack;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(VanillaTypes.ITEM, this.inputs);
		ingredients.setOutput(VanillaTypes.ITEM, this.output);
	}
}
