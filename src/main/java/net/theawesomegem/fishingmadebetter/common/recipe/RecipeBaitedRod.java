package net.theawesomegem.fishingmadebetter.common.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.world.World;
import net.theawesomegem.fishingmadebetter.BetterFishUtil;
import net.theawesomegem.fishingmadebetter.common.item.fishingrod.ItemBetterFishingRod;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

/**
 * Created by TheAwesomeGem on 1/1/2018.
 */
public class RecipeBaitedRod extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        return validInput(inv) != null;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        Integer[] slots = validInput(inv);
        if(slots==null) return ItemStack.EMPTY;

        ItemStack itemFishingRod = inv.getStackInSlot(slots[0]).copy();
        ItemStack itemBait = inv.getStackInSlot(slots[1]).copy();

        ItemBetterFishingRod.setBaitItem(itemFishingRod, Item.REGISTRY.getNameForObject(itemBait.getItem()).toString());
        ItemBetterFishingRod.setBaitMetadata(itemFishingRod, itemBait.getMetadata());
        ItemBetterFishingRod.setBaitDisplayName(itemFishingRod, itemBait.getDisplayName());

        return itemFishingRod;
    }

    @Override
    public boolean canFit(int width, int height) {
        return width * height >= 2;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }

    @Nullable
    private Integer[] validInput(InventoryCrafting inv) {
        int numStacks = 0;
        int rodSlot = -1;
        int baitSlot = -1;
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
            else if(itemStack.getItem() instanceof ItemBetterFishingRod) rodSlot = i;
            else if(BetterFishUtil.isValidBait(itemStack)) baitSlot = i;
            else return null;
        }
        Integer[] slots = new Integer[2];
        slots[0] = rodSlot;
        slots[1] = baitSlot;
        return (rodSlot != -1 && baitSlot != -1) ? slots : null;
    }
}