package net.theawesomegem.fishingmadebetter.common.item.attachment.bobber;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.theawesomegem.fishingmadebetter.ModInfo;
import net.theawesomegem.fishingmadebetter.common.registry.FMBCreativeTab;

public abstract class ItemBobber extends Item {
	protected final boolean lavaBobber;
	protected final boolean voidBobber;
	protected final int varianceModifier;
	protected final int tensioningModifier;
	
	public ItemBobber(String name, int maxDamage, boolean lavaBobber, boolean voidBobber, int varianceModifier, int tensioningModifier) {
		this.lavaBobber = lavaBobber;
		this.voidBobber = voidBobber;
		this.varianceModifier = varianceModifier;
		this.tensioningModifier = tensioningModifier;
		
		this.setCreativeTab(FMBCreativeTab.instance);
		this.setNoRepair();
		this.setMaxStackSize(1);
		this.setMaxDamage(maxDamage);
		this.setRegistryName(name);
		this.setTranslationKey(ModInfo.MOD_ID + "." + name);
	}
	
	public boolean isLavaBobber() {
		return this.lavaBobber;
	}
	
	public boolean isVoidBobber() {
		return this.voidBobber;
	}
	
	public int getVarianceModifier() {
		return this.varianceModifier;
	}
	
	public int getTensioningModifier() {
		return this.tensioningModifier;
	}
	
	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}
	
	@Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if(this.lavaBobber) tooltip.add("Can fish in: Lava");
        if(this.voidBobber) tooltip.add("Can fish in: Void");
        if(!this.lavaBobber && !this.voidBobber) tooltip.add("Can fish in: Water"); 
        if(this.varianceModifier != 0) tooltip.add(String.format("Tension Bar Size Modifier: +%s", this.varianceModifier));
        if(this.tensioningModifier != 0) tooltip.add(String.format("Tension Bar Speed Modifier: +%s", this.tensioningModifier));
    }
}
