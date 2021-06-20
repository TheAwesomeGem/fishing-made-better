package net.theawesomegem.fishingmadebetter.common.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.theawesomegem.fishingmadebetter.BetterFishUtil;
import net.theawesomegem.fishingmadebetter.common.item.ItemFishBucket;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheAwesomeGem on 1/1/2018.
 */
public class RecipeFishBucket extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
    private int bucketSlot = -1;
    private String fishId = null;

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

        for (int i : occupiedSlots) {
            ItemStack itemStack = inv.getStackInSlot(i);

            if (itemStack.getItem() instanceof ItemBucket && itemStack.getItem().getUnlocalizedName().contains("bucketWater")) {
                this.bucketSlot = i;
            } else if (!itemStack.isEmpty() && BetterFishUtil.isBetterFish(itemStack) && !BetterFishUtil.isDead(itemStack, worldIn.getTotalWorldTime())) {
                fishId = BetterFishUtil.getFishId(itemStack);
            } else {
                return false;
            }
        }

        return fishId != null && bucketSlot != -1;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        if (fishId == null || bucketSlot == -1) {
            return ItemStack.EMPTY;
        }

        return ItemFishBucket.getItemStack(fishId);
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
}