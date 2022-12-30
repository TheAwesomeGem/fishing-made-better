package net.theawesomegem.fishingmadebetter.common.item.scalingknife;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.theawesomegem.fishingmadebetter.ModInfo;
import net.theawesomegem.fishingmadebetter.common.registry.FMBCreativeTab;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

/**
 * Created by TheAwesomeGem on 1/1/2018.
 */
public abstract class ItemScalingKnife extends ItemTool
{
    public ItemScalingKnife(String name, float attackDamage, float attackSpeed, ToolMaterial materialIn)
    {
        super(attackDamage, attackSpeed, materialIn, new HashSet<>());

        this.setCreativeTab(FMBCreativeTab.instance);
        this.setRegistryName(name);
        this.setTranslationKey(ModInfo.MOD_ID + "." + name);
    }

    @Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}
    
    @SideOnly(Side.CLIENT)
    public void registerItemModel(Item item)
    {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(ModInfo.MOD_ID + ":scaling_knife/" + getRegistryName().getPath(), "inventory"));
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
        tooltip.add("Use it to scale a fish.");
    }

    @Override
    public boolean hasContainerItem(ItemStack stack)
    {
        return true;
    }
}