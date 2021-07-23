package net.theawesomegem.fishingmadebetter.common.registry;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.theawesomegem.fishingmadebetter.common.item.ItemManager;

public class FMBCreativeTab extends CreativeTabs {

	public static final FMBCreativeTab instance = new FMBCreativeTab(CreativeTabs.getNextID(), "tabFishingMadeBetter");
	public FMBCreativeTab(int index, String label) {
		super(index, label);
	}
	
	@Override
	public ItemStack getTabIconItem() {
		return new ItemStack(ItemManager.FISHING_ROD_DIAMOND);
	}
}
