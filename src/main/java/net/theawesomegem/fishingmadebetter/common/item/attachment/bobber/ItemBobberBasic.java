package net.theawesomegem.fishingmadebetter.common.item.attachment.bobber;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBobberBasic extends ItemBobber {

	public ItemBobberBasic() {
		super("bobber_basic", 0, false, false, 0, 0);
		this.setMaxStackSize(16);
	}

	@SideOnly(Side.CLIENT)
	@Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(TextFormatting.DARK_RED + I18n.format("tooltip.fishingmadebetter.bobber.basic") + TextFormatting.RESET);
    }
}
