package net.theawesomegem.fishingmadebetter.common.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.theawesomegem.fishingmadebetter.BetterFishUtil;
import net.theawesomegem.fishingmadebetter.common.configuration.CustomConfigurationHandler;
import net.theawesomegem.fishingmadebetter.common.data.FishData;
import net.theawesomegem.fishingmadebetter.common.item.scalingknife.ItemScalingKnife;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

/**
 * Created by TheAwesomeGem on 1/1/2018.
 */
public class RecipeFishScale extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        return validInput(inv) != null;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
    	Integer[] slots = validInput(inv);
        if(slots==null) return ItemStack.EMPTY;
        
        ItemStack itemStackFish = inv.getStackInSlot(slots[1]);

        FishData fishData = CustomConfigurationHandler.fishDataMap.get(BetterFishUtil.getFishId(itemStackFish));
        if(fishData == null) return ItemStack.EMPTY;

        Item itemResult = Item.getByNameOrId(fishData.scalingItem);
        if(itemResult == null) return ItemStack.EMPTY;

        int quantity = 1;
        int fishWeight = BetterFishUtil.getFishWeight(itemStackFish);

        if(fishData.scalingUseWeight) quantity = getScaleAmount(fishWeight);

        return new ItemStack(itemResult, quantity, fishData.scalingItemMetadata);
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
    	Integer[] slots = validInput(inv);
    	ItemStack itemStackKnife = inv.getStackInSlot(slots[0]).copy();
    	ItemStack itemStackFish = inv.getStackInSlot(slots[1]).copy();

    	itemStackFish = itemStackFish.splitStack(1);
    	
    	FishData fishData = CustomConfigurationHandler.fishDataMap.get(BetterFishUtil.getFishId(itemStackFish));
    	int fishWeight = BetterFishUtil.getFishWeight(itemStackFish);

    	BetterFishUtil.setFishHasScale(itemStackFish, false);
    	
        itemStackKnife.setItemDamage(itemStackKnife.getItemDamage() + (fishData.scalingUseWeight ? getScaleAmount(fishWeight) : 1));
        if(itemStackKnife.getItemDamage() >= itemStackKnife.getMaxDamage()) itemStackKnife = ItemStack.EMPTY;
        
        NonNullList<ItemStack> ret = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

        for(int i = 0; i < ret.size(); i++) {
            ItemStack itemStack = inv.getStackInSlot(i);

            if(!itemStack.isEmpty() && itemStack.getItem() instanceof ItemScalingKnife) ret.set(i, itemStackKnife);
            else if(!itemStack.isEmpty()) ret.set(i, itemStackFish);
        }
        return ret;
    }

    private int getScaleAmount(int weight) {
    	return Math.max(1, (int)Math.cbrt(Math.cbrt(Math.pow(weight, 4))));
    }
    
    @Nullable
    private Integer[] validInput(InventoryCrafting inv) {
    	int numStacks = 0;
        int knifeSlot = -1;
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
            else if(itemStack.getItem() instanceof ItemScalingKnife && itemStack.getMaxDamage() > itemStack.getItemDamage()) knifeSlot = i;
            else if(BetterFishUtil.isBetterFish(itemStack) && BetterFishUtil.doesFishHasScale(itemStack)) fishSlot = i;
            else return null;
        }
        Integer[] slots = new Integer[2];
        slots[0] = knifeSlot;
        slots[1] = fishSlot;
        return (knifeSlot != -1 && fishSlot != -1) ? slots : null;
    }
}
