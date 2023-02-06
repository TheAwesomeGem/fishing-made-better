package net.theawesomegem.fishingmadebetter.common.item.attachment.reel;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.theawesomegem.fishingmadebetter.ModInfo;
import net.theawesomegem.fishingmadebetter.common.registry.FMBCreativeTab;

public abstract class ItemReel extends Item {
	protected final int reelRange;
	protected final int reelSpeed;
	
	public ItemReel(String name, int maxDamage, int reelRange, int reelSpeed) {
		this.reelRange = reelRange;
		this.reelSpeed = reelSpeed;
		
		this.setCreativeTab(FMBCreativeTab.instance);
		this.setNoRepair();
		this.setMaxStackSize(1);
		this.setMaxDamage(maxDamage);
		this.setRegistryName(name);
		this.setTranslationKey(ModInfo.MOD_ID + "." + name);
	}
	
	public int getReelRange() {
		return this.reelRange;
	}
	
	public int getReelSpeed() {
		return this.reelSpeed;
	}
	
	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}
	
	@Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(String.format(I18n.format("tooltip.fishingmadebetter.reel.range") + " %sm", this.reelRange));
        tooltip.add(String.format(I18n.format("tooltip.fishingmadebetter.reel.speed") + " %sm/s", this.reelSpeed));
    }
}
