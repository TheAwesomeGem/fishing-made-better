package net.theawesomegem.fishingmadebetter.common.item.attachment.hook;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemHookBasic extends ItemHook {

	public ItemHookBasic() {
		super("hook_basic", 0, 0, 0, 0, 0);
		this.setMaxStackSize(16);
	}
	
	@Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.DARK_RED + "Only used for crafting!" + TextFormatting.RESET);
    }
}
