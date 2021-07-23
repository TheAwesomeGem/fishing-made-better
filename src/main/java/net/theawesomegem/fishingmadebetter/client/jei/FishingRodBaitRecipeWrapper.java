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
import net.minecraftforge.fml.common.Loader;

public class FishingRodBaitRecipeWrapper implements ICraftingRecipeWrapper {
	private final List<List<ItemStack>> inputs;
	private final ItemStack output;

	public FishingRodBaitRecipeWrapper(Item rod) {
		ItemStack rodStack = new ItemStack(rod);
		
		ArrayList<ItemStack> baitList = new ArrayList<ItemStack>();
		
		baitList.addAll(Arrays.asList(
			new ItemStack(Items.APPLE),
			new ItemStack(Items.MELON),
			
			new ItemStack(Items.WHEAT_SEEDS),
			new ItemStack(Items.PUMPKIN_SEEDS),
			new ItemStack(Items.MELON_SEEDS),
			new ItemStack(Items.BEETROOT_SEEDS),
			
			new ItemStack(Items.CARROT),
			new ItemStack(Items.POTATO),
			new ItemStack(Items.BEETROOT),
			
			new ItemStack(Items.FISH, 1, 2),
			new ItemStack(Items.SPIDER_EYE),
			new ItemStack(Items.ROTTEN_FLESH),
			
			new ItemStack(Items.PORKCHOP),
			new ItemStack(Items.BEEF),
			new ItemStack(Items.CHICKEN),
			new ItemStack(Items.RABBIT),
			new ItemStack(Items.MUTTON)
		));
		
		if(Loader.isModLoaded("aquaculture")) baitList.addAll(Arrays.asList(
				new ItemStack(Item.getByNameOrId("aquaculture:fish"), 1, 19),
				new ItemStack(Item.getByNameOrId("aquaculture:fish"), 1, 37),
				
				new ItemStack(Item.getByNameOrId("aquaculture:fish"), 1, 0),
				new ItemStack(Item.getByNameOrId("aquaculture:fish"), 1, 1),
				new ItemStack(Item.getByNameOrId("aquaculture:fish"), 1, 17),
				
				new ItemStack(Item.getByNameOrId("aquaculture:fish"), 1, 9),
				new ItemStack(Item.getByNameOrId("aquaculture:fish"), 1, 10),
				new ItemStack(Item.getByNameOrId("aquaculture:fish"), 1, 15),
				new ItemStack(Item.getByNameOrId("aquaculture:fish"), 1, 16),
				new ItemStack(Item.getByNameOrId("aquaculture:fish"), 1, 26),
				
				new ItemStack(Item.getByNameOrId("aquaculture:food"), 1, 0),
				new ItemStack(Item.getByNameOrId("aquaculture:food"), 1, 1)
				));
		
		this.inputs = Arrays.asList(Arrays.asList(rodStack), baitList);
		
		this.output = rodStack;
	}

	@Override
	public void getIngredients(IIngredients ingredients) {
		ingredients.setInputLists(VanillaTypes.ITEM, this.inputs);
		ingredients.setOutput(VanillaTypes.ITEM, this.output);
	}
}
