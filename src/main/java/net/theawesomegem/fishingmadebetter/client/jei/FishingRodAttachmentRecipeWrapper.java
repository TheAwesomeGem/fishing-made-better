package net.theawesomegem.fishingmadebetter.client.jei;

import java.util.Arrays;
import java.util.List;

import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.ingredients.VanillaTypes;
import mezz.jei.api.recipe.wrapper.ICraftingRecipeWrapper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.theawesomegem.fishingmadebetter.common.item.ItemManager;

public class FishingRodAttachmentRecipeWrapper implements ICraftingRecipeWrapper {
	private final List<List<ItemStack>> inputs;
	private final ItemStack output;

	public FishingRodAttachmentRecipeWrapper(Item rod) {
		ItemStack rodStack = new ItemStack(rod);
		
		this.inputs = Arrays.asList(Arrays.asList(rodStack), Arrays.asList(
				new ItemStack(ItemManager.REEL_FAST),
				new ItemStack(ItemManager.REEL_LONG)
				), Arrays.asList(
						new ItemStack(ItemManager.BOBBER_OBSIDIAN),
						new ItemStack(ItemManager.BOBBER_VOID),
						new ItemStack(ItemManager.BOBBER_HEAVY),
						new ItemStack(ItemManager.BOBBER_LIGHTWEIGHT)
						), Arrays.asList(
								new ItemStack(ItemManager.HOOK_BARBED),
								new ItemStack(ItemManager.HOOK_FATTY),
								new ItemStack(ItemManager.HOOK_SHINY),
								new ItemStack(ItemManager.HOOK_MAGNETIC)
								));
		this.output = rodStack;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(VanillaTypes.ITEM, this.inputs);
		ingredients.setOutput(VanillaTypes.ITEM, this.output);
	}
}
