package net.theawesomegem.fishingmadebetter.common.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.theawesomegem.fishingmadebetter.BetterFishUtil;
import net.theawesomegem.fishingmadebetter.common.configuration.ConfigurationManager;
import net.theawesomegem.fishingmadebetter.common.item.ItemKnife;
import net.theawesomegem.fishingmadebetter.common.item.ItemManager;
import net.theawesomegem.fishingmadebetter.util.MathUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheAwesomeGem on 1/1/2018.
 */
public class RecipeFishSlice extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
    private int knifeSlot = -1;
    private String fishId = null;
    private String fishDisplayName = null;
    private int weight = -1;
    private Item fishItem;
    private int fishMetadata;

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

        ItemStack itemKnife = ItemStack.EMPTY;

        for (int i : occupiedSlots) {
            ItemStack itemStack = inv.getStackInSlot(i);

            if (itemStack.getItem() instanceof ItemKnife && (itemStack.getMaxDamage() - itemStack.getItemDamage()) >= 8 && itemKnife.isEmpty()) {
                itemKnife = itemStack;

                this.knifeSlot = i;
            } else if (BetterFishUtil.isBetterFish(itemStack)) {
                this.weight = BetterFishUtil.getFishWeight(itemStack);
                this.fishId = BetterFishUtil.getFishId(itemStack);
                this.fishDisplayName = itemStack.getDisplayName();
                this.fishItem = itemStack.getItem();
                this.fishMetadata = itemStack.getMetadata();
            } else {
                return false;
            }
        }

        return fishId != null && knifeSlot != -1 && weight != -1;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        if (fishId == null || knifeSlot == -1 || weight < 1)
            return ItemStack.EMPTY;

        ItemStack itemKnife = inv.getStackInSlot(knifeSlot);

        if (!(itemKnife.getItem() instanceof ItemKnife))
            return ItemStack.EMPTY;

        if(ConfigurationManager.customFishSlicing) {
            return ItemManager.FISH_SLICE.getItemStack(fishId, fishDisplayName, getSliceAmount(weight));
        }

        if(fishItem == null){
            return ItemStack.EMPTY;
        }

        return new ItemStack(fishItem, weight, fishMetadata);
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
        ItemStack itemKnife = ItemStack.EMPTY;

        if (knifeSlot != -1) {
            ItemStack itemStack = inv.getStackInSlot(knifeSlot);

            if (!itemStack.isEmpty() && itemStack.getItem() instanceof ItemKnife) {
                itemKnife = itemStack.copy();
            }
        }

        if (!itemKnife.isEmpty()) {
            itemKnife.setItemDamage(itemKnife.getItemDamage() + 8);
        }

        NonNullList<ItemStack> ret = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

        for (int i = 0; i < ret.size(); i++) {
            ItemStack itemStack = inv.getStackInSlot(i);

            if (!itemStack.isEmpty() && itemStack.getItem() instanceof ItemKnife)
                ret.set(i, itemKnife);
        }

        return ret;
    }

    private int getSliceAmount(int weight) {
        if (MathUtil.inRange(weight, 1, 5))
            return 1;
        else if (MathUtil.inRange(weight, 6, 12))
            return 2;
        else if (MathUtil.inRange(weight, 13, 20))
            return 4;
        else if (MathUtil.inRange(weight, 21, 31))
            return 6;
        else if (MathUtil.inRange(weight, 32, 46))
            return 8;
        else if (MathUtil.inRange(weight, 47, 61))
            return 14;
        else if (MathUtil.inRange(weight, 62, 73))
            return 18;
        else if (MathUtil.inRange(weight, 74, 90))
            return 22;
        else if (MathUtil.inRange(weight, 91, 110))
            return 26;
        else if (MathUtil.inRange(weight, 111, 130))
            return 32;
        else if (MathUtil.inRange(weight, 131, 180))
            return 38;
        else if (MathUtil.inRange(weight, 181, 230))
            return 46;
        else if (MathUtil.inRange(weight, 231, 319))
            return 54;
        else if (MathUtil.inRange(weight, 320, 500000))
            return 64;
        else
            return 1;
    }
}
