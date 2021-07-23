package net.theawesomegem.fishingmadebetter.common.recipe;

import net.minecraft.init.Items;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.theawesomegem.fishingmadebetter.BetterFishUtil;
import net.theawesomegem.fishingmadebetter.common.configuration.CustomConfigurationHandler;
import net.theawesomegem.fishingmadebetter.common.data.FishData;
import net.theawesomegem.fishingmadebetter.common.item.ItemFishBucket;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

/**
 * Created by TheAwesomeGem on 1/1/2018.
 */
public class RecipeFishBucket extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
    private long worldTime;

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
    	worldTime = worldIn.getTotalWorldTime();
        return validInput(inv) != null;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
    	Integer[] slots = validInput(inv);
    	if(slots==null) return ItemStack.EMPTY;
    	
    	ItemStack itemFish = inv.getStackInSlot(slots[1]).copy();
    	
    	ItemStack fishBucket = ItemFishBucket.getItemStack(BetterFishUtil.getFishId(itemFish));
    	
        return fishBucket;
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
            else if(itemStack.getItem() == Items.WATER_BUCKET) bucketSlot = i;
            else if(BetterFishUtil.isBetterFish(itemStack) && !BetterFishUtil.isDead(itemStack, worldTime) && CustomConfigurationHandler.fishDataMap.get(BetterFishUtil.getFishId(itemStack)).liquid.equals(FishData.FishingLiquid.WATER)) fishSlot = i;
            else return null;
        }
        Integer[] slots = new Integer[2];
        slots[0] = bucketSlot;
        slots[1] = fishSlot;
        return (bucketSlot != -1 && fishSlot != -1) ? slots : null;
    }
}