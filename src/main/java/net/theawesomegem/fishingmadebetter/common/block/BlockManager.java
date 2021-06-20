package net.theawesomegem.fishingmadebetter.common.block;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;

/**
 * Created by TheAwesomeGem on 1/2/2018.
 */
public class BlockManager {
    public static final BlockBaitBox BLOCK_BAIT_BOX = new BlockBaitBox();

    public static Item ITEM_BLOCK_BAIT_BOX;

    public static void register(IForgeRegistry<Block> registry) {
        registry.registerAll(BLOCK_BAIT_BOX);
    }

    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
        registry.registerAll(ITEM_BLOCK_BAIT_BOX = BLOCK_BAIT_BOX.createItemBlock());

        GameRegistry.registerTileEntity(BLOCK_BAIT_BOX.getTileEntityClass(), BLOCK_BAIT_BOX.getRegistryName().toString());
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels() {
        BLOCK_BAIT_BOX.registerItemModel(Item.getItemFromBlock(BLOCK_BAIT_BOX));
    }
}
