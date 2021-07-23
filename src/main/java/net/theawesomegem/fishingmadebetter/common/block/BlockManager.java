package net.theawesomegem.fishingmadebetter.common.block;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import net.theawesomegem.fishingmadebetter.ModInfo;
import net.theawesomegem.fishingmadebetter.common.block.tileentity.TileEntityBaitBox;

/**
 * Created by TheAwesomeGem on 1/2/2018.
 */
public class BlockManager {
	public static final BlockManager instance = new BlockManager();
	public static Block BLOCK_BAIT_BOX;
	public static ItemBlock ITEM_BLOCK_BAIT_BOX;
	
    public static void registerBlocks(IForgeRegistry<Block> registry) {
    	
    	BLOCK_BAIT_BOX = registerAsBlock("baitbox", new BlockBaitBox(), registry);
    	
    	GameRegistry.registerTileEntity(TileEntityBaitBox.class, new ResourceLocation(ModInfo.MOD_ID, "baitbox"));
    }
    
    public static void registerItemBlocks(IForgeRegistry<Item> registry) {
    	
    	ITEM_BLOCK_BAIT_BOX = registerAsItem(BLOCK_BAIT_BOX, registry);
    }

    private static Block registerAsBlock(String name, final Block newBlock, IForgeRegistry<Block> registry) {
    	registry.register(newBlock);
    	
    	return newBlock;
    }
    
    private static ItemBlock registerAsItem(Block block, IForgeRegistry<Item> registry) {
    	ItemBlock newBlock = new ItemBlock(block);
    	newBlock.setRegistryName(block.getRegistryName());
    	registry.register(newBlock);
    	
    	return newBlock;
    }
    
    @SideOnly(Side.CLIENT)
    public static void registerModels() {
    	instance.registerItemBlockModels(ITEM_BLOCK_BAIT_BOX);
    }
    
    private void registerItemBlockModels(Item item) {
    	ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "normal"));
    }
    
}
