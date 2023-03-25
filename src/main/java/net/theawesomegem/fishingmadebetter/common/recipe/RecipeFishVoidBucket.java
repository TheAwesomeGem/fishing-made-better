package net.theawesomegem.fishingmadebetter.common.recipe;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.theawesomegem.fishingmadebetter.BetterFishUtil;
import net.theawesomegem.fishingmadebetter.common.configuration.CustomConfigurationHandler;
import net.theawesomegem.fishingmadebetter.common.data.FishData;
import net.theawesomegem.fishingmadebetter.common.item.ItemFishVoidBucket;
import net.theawesomegem.fishingmadebetter.common.item.ItemManager;
import net.theawesomegem.fishingmadebetter.common.item.ItemVoidBucket;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by KameiB on 03/24/2023.
 */
public class RecipeFishVoidBucket extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
    private long worldTime;

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
    	if(worldIn == null) return false;
    	
    	worldTime = worldIn.getTotalWorldTime();
        return validInput(inv) != null;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
    	Integer[] slots = validInput(inv);
    	if(slots==null) return ItemStack.EMPTY;

        ItemStack itemFish = inv.getStackInSlot(slots[1]).copy();
        ItemStack fishVoidBucket = ItemFishVoidBucket.getItemStack(BetterFishUtil.getFishId(itemFish));

        ItemFishVoidBucket.setFishRegistry(fishVoidBucket, Item.REGISTRY.getNameForObject(itemFish.getItem()).toString());
        ItemFishVoidBucket.setFishMetadata(fishVoidBucket, itemFish.getMetadata());
        ItemFishVoidBucket.setFishDisplayName(fishVoidBucket, itemFish.getDisplayName());

        return fishVoidBucket;
    }

    @Override
    public boolean canFit(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }
    
    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
        return NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);
    }
    
    @Nullable
    private Integer[] validInput(InventoryCrafting inv) {
    	int numStacks = 0;
        int bucketSlot = -1;
        int fishSlot = -1;
        List<Integer> occupiedSlots = new ArrayList<>();
        
        for(int i = 0; i < inv.getSizeInventory(); i++) {
            if (!inv.getStackInSlot(i).isEmpty()) {
                numStacks++;
                occupiedSlots.add(i);
            }
        }
        if(numStacks != 2) return null;

        for(int i : occupiedSlots) {
            ItemStack itemStack = inv.getStackInSlot(i);
            
            if(itemStack.isEmpty()) return null;
            //else if(itemStack.getItem() == Items.BUCKET) bucketSlot = i;
            else if(itemStack.getItem() instanceof ItemVoidBucket) bucketSlot = i;
            else if(BetterFishUtil.isBetterFish(itemStack) && !BetterFishUtil.isDead(itemStack, worldTime) && CustomConfigurationHandler.fishDataMap.get(BetterFishUtil.getFishId(itemStack)).liquid.equals(FishData.FishingLiquid.VOID)) fishSlot = i;
            else return null;
        }
        Integer[] slots = new Integer[2];
        slots[0] = bucketSlot;
        slots[1] = fishSlot;
        return (bucketSlot != -1 && fishSlot != -1) ? slots : null;
    }
}