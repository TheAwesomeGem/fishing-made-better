package net.theawesomegem.fishingmadebetter.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.theawesomegem.fishingmadebetter.ModInfo;

/**
 * Created by TheAwesomeGem on 12/21/2017.
 */
public abstract class BlockBase extends Block {
    public BlockBase(final Material material, final MapColor mapColor, final String name) {
        super(material, mapColor);

        this.setRegistryName(name);
        this.setUnlocalizedName(ModInfo.MOD_ID + "." + name);
    }

    public BlockBase(final Material material, final String blockName) {
        this(material, material.getMaterialMapColor(), blockName);
    }

    public Item createItemBlock() {
        return new ItemBlock(this).setRegistryName(getRegistryName());
    }

    @SideOnly(Side.CLIENT)
    public void registerItemModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }
}
