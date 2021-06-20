package net.theawesomegem.fishingmadebetter.common.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.theawesomegem.fishingmadebetter.common.item.fishingrod.ItemBetterFishingRod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheAwesomeGem on 1/1/2018.
 */
public class RecipeBaitedRod extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
    private int fishingRodSlot = -1;
    private String baitItem = null;
    private String baitDisplayName = null;
    private int baitMetadata = 0;

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        int numStacks = 0;

        List<Integer> occupiedSlots = new ArrayList<>();

        for (int i = 0; i < inv.getSizeInventory(); i++) {
            if (!inv.getStackInSlot(i).isEmpty()) {
                numStacks++;
                occupiedSlots.add(i);
            }
        }

        if (numStacks != 2) {
            return false;
        }

        ItemStack itemFishingRod = ItemStack.EMPTY;

        for (int i : occupiedSlots) {
            ItemStack itemStack = inv.getStackInSlot(i);

            if (itemStack.getItem() instanceof ItemBetterFishingRod && itemFishingRod.isEmpty()) {
                itemFishingRod = itemStack;

                this.fishingRodSlot = i;
            } else if (!itemStack.isEmpty()) {
                baitItem = Item.REGISTRY.getNameForObject(itemStack.getItem()).toString();
                baitMetadata = itemStack.getMetadata();
                baitDisplayName = itemStack.getDisplayName();
            } else {
                return false;
            }
        }

        return baitItem != null && fishingRodSlot != -1;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        if (baitItem == null || fishingRodSlot == -1)
            return ItemStack.EMPTY;

        ItemStack itemFishingRod = inv.getStackInSlot(fishingRodSlot).copy();

        if (!(itemFishingRod.getItem() instanceof ItemBetterFishingRod))
            return ItemStack.EMPTY;

        ItemBetterFishingRod.setBaitItem(itemFishingRod, baitItem);
        ItemBetterFishingRod.setBaitMetadata(itemFishingRod, baitMetadata);
        ItemBetterFishingRod.setBaitDisplayName(itemFishingRod, baitDisplayName);

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

    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
        return net.minecraftforge.common.ForgeHooks.defaultRecipeGetRemainingItems(inv);
    }
}