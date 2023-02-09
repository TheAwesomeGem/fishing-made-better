package net.theawesomegem.fishingmadebetter.common.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.theawesomegem.fishingmadebetter.BetterFishUtil;
import net.theawesomegem.fishingmadebetter.common.configuration.CustomConfigurationHandler;
import net.theawesomegem.fishingmadebetter.common.data.FishData;
import net.theawesomegem.fishingmadebetter.common.item.ItemManager;
import net.theawesomegem.fishingmadebetter.common.item.filletknife.ItemFilletKnife;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

/**
 * Created by TheAwesomeGem on 1/1/2018.
 */
public class RecipeFishSliceRaw extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
    	return validInput(inv) != null;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        Integer[] slots = validInput(inv);
        if(slots==null) return ItemStack.EMPTY;

        ItemStack itemStackFish = inv.getStackInSlot(slots[1]);
        
        if(!BetterFishUtil.isBetterFish(itemStackFish)) {
    		return new ItemStack(ItemManager.FISH_SLICE_RAW, 1);
    	}
        
        String fishId = BetterFishUtil.getFishId(itemStackFish);
        //String fishDisplayName = fishId;
        String fishDisplayName =  new TextComponentTranslation(BetterFishUtil.getFishCustomLangKey(itemStackFish)).getUnformattedComponentText();

        int fishWeight = BetterFishUtil.getFishWeight(itemStackFish);

        FishData fishData = CustomConfigurationHandler.fishDataMap.get(fishId);
        
        if(fishData.defaultFillet) {
        	return ItemManager.FISH_SLICE_RAW.getItemStack(fishId, fishDisplayName, (fishData.filletUseWeight ? Math.min(getSliceAmount(fishWeight), 64) : 1));
        }
        else {
        	Item ret = Item.getByNameOrId(fishData.filletItem);
        	return new ItemStack(ret, (fishData.filletUseWeight ? Math.min(getSliceAmount(fishWeight), 64) : 1), fishData.filletItemMetadata);
        }
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
    	ItemStack itemStackFish = inv.getStackInSlot(slots[1]);
    	Integer remain = 0;

    	if(!BetterFishUtil.isBetterFish(itemStackFish)) {
    		itemStackKnife.setItemDamage(itemStackKnife.getItemDamage() + 1);
    	}
    	else {
    		itemStackKnife.setItemDamage(itemStackKnife.getItemDamage() + (CustomConfigurationHandler.fishDataMap.get(BetterFishUtil.getFishId(itemStackFish)).filletUseWeight ? getSliceAmount(BetterFishUtil.getFishWeight(itemStackFish)) : 1));
    	}
    	
        if(itemStackKnife.getItemDamage() >= itemStackKnife.getMaxDamage()) itemStackKnife = ItemStack.EMPTY;
        
        NonNullList<ItemStack> ret = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

        if(BetterFishUtil.isBetterFish(itemStackFish) && CustomConfigurationHandler.fishDataMap.get(BetterFishUtil.getFishId(itemStackFish)).filletUseWeight) {
        	remain = getSliceAmount(BetterFishUtil.getFishWeight(itemStackFish)) - 64;
        }
        
        for(int i = 0; i < ret.size(); i++) {
            ItemStack itemStack = inv.getStackInSlot(i);
            if(!itemStack.isEmpty() && itemStack.getItem() instanceof ItemFilletKnife) ret.set(i, itemStackKnife);
            else if(itemStack.isEmpty() && remain > 0) {
            	String fishId = BetterFishUtil.getFishId(itemStackFish);
            	FishData fishData = CustomConfigurationHandler.fishDataMap.get(fishId);
            	
            	if(remain >= 64) {
                	ret.set(i, fishData.defaultFillet ? ItemManager.FISH_SLICE_RAW.getItemStack(fishId, fishId, 64) : new ItemStack(Item.getByNameOrId(fishData.filletItem), 64, fishData.filletItemMetadata));
            	}
            	else {
                	ret.set(i, fishData.defaultFillet ? ItemManager.FISH_SLICE_RAW.getItemStack(fishId, fishId, remain) : new ItemStack(Item.getByNameOrId(fishData.filletItem), remain, fishData.filletItemMetadata));
            	}
            	remain -= 64;
            }
        }
        return ret;
    }

    private int getSliceAmount(int weight) {
    	return Math.max(1, (int)Math.cbrt(Math.pow(weight, 2)));
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
            else if(itemStack.getItem() instanceof ItemFilletKnife && itemStack.getMaxDamage() > itemStack.getItemDamage()) knifeSlot = i;
            else if(BetterFishUtil.isBetterFish(itemStack) && CustomConfigurationHandler.fishDataMap.get(BetterFishUtil.getFishId(itemStack)).allowFillet) fishSlot = i;
            else if(itemStack.getItem().getRegistryName().equals(new ResourceLocation("minecraft:fish"))) fishSlot = i;
            else return null;
        }
        Integer[] slots = new Integer[2];
        slots[0] = knifeSlot;
        slots[1] = fishSlot;
        return (knifeSlot != -1 && fishSlot != -1) ? slots : null;
    }
}