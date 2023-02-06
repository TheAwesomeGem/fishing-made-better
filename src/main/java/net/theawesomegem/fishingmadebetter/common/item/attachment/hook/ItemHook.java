package net.theawesomegem.fishingmadebetter.common.item.attachment.hook;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.theawesomegem.fishingmadebetter.ModInfo;
import net.theawesomegem.fishingmadebetter.common.registry.FMBCreativeTab;

public abstract class ItemHook extends Item {
	protected final int tuggingReduction;
	protected final int treasureModifier;
	protected final int biteRateModifier;
	protected final int weightModifier;
	
	public ItemHook(String name, int maxDamage, int tuggingReduction, int treasureModifier, int biteRateModifier, int weightModifier) {
		this.tuggingReduction = tuggingReduction;
		this.treasureModifier = treasureModifier;
		this.biteRateModifier = biteRateModifier;
		this.weightModifier = weightModifier;
		
		this.setCreativeTab(FMBCreativeTab.instance);
		this.setNoRepair();
		this.setMaxStackSize(1);
		this.setMaxDamage(maxDamage);
		this.setRegistryName(name);
		this.setTranslationKey(ModInfo.MOD_ID + "." + name);
	}
	
	public int getTuggingReduction() {
		return this.tuggingReduction;
	}
	
	public int getTreasureModifier() {
		return this.treasureModifier;
	}
	
	public int getBiteRateModifier() {
		return this.biteRateModifier;
	}
	
	public int getWeightModifier() {
		return this.weightModifier;
	}
	
	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}
	
	@Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(this.tuggingReduction != 0) tooltip.add(String.format(I18n.format("item.fishingmadebetter.hook_barbed.tooltip")+" %s", this.tuggingReduction));
        if(this.treasureModifier != 0) tooltip.add(String.format(I18n.format("item.fishingmadebetter.hook_magnetic.tooltip") +" +%s%%", this.treasureModifier));
        if(this.biteRateModifier != 0) tooltip.add(String.format(I18n.format("item.fishingmadebetter.hook_shiny.tooltip") +" +%s%%", this.biteRateModifier));
        if(this.weightModifier != 0) tooltip.add(String.format(I18n.format("item.fishingmadebetter.hook_fatty.tooltip") + " +%s%%", this.weightModifier));
    }
}
