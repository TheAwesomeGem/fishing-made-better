package net.theawesomegem.fishingmadebetter.common.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.theawesomegem.fishingmadebetter.BetterFishUtil;
import net.theawesomegem.fishingmadebetter.common.configuration.CustomFishConfigurationHandler;
import net.theawesomegem.fishingmadebetter.common.data.FishData;
import net.theawesomegem.fishingmadebetter.common.item.ItemScaleRemover;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheAwesomeGem on 1/1/2018.
 */
public class RecipeFishScale extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
    private int scaleSlot = -1;
    private int fishSlot = -1;
    private String fishId = null;
    private int weight = -1;

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

        ItemStack itemScaler = ItemStack.EMPTY;

        for (int i : occupiedSlots) {
            ItemStack itemStack = inv.getStackInSlot(i);

            if (itemStack.getItem() instanceof ItemScaleRemover && (itemStack.getMaxDamage() - itemStack.getItemDamage()) >= 8 && itemScaler.isEmpty()) {
                itemScaler = itemStack;

                this.scaleSlot = i;
            } else if (BetterFishUtil.isBetterFish(itemStack) && BetterFishUtil.doesFishHasScale(itemStack)) {
                this.weight = BetterFishUtil.getFishWeight(itemStack);
                this.fishId = BetterFishUtil.getFishId(itemStack);
                this.fishSlot = i;
            } else {
                return false;
            }
        }

        return fishId != null && scaleSlot != -1 && fishSlot != -1 && weight != -1;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        if (fishId == null || scaleSlot == -1 || fishSlot == -1 || weight < 1)
            return ItemStack.EMPTY;

        ItemStack itemScaler = inv.getStackInSlot(scaleSlot);

        if (!(itemScaler.getItem() instanceof ItemScaleRemover))
            return ItemStack.EMPTY;

        FishData fishData = CustomFishConfigurationHandler.fishDataMap.get(fishId);

        if (fishData == null) {
            return ItemStack.EMPTY;
        }

        Item item = Item.getByNameOrId(fishData.resultingItem);

        if (item == null) {
            return ItemStack.EMPTY;
        }

        int quantity = 1;

        if (fishData.resultQuantityUseWeight) {
            quantity = weight / 2;

            if (quantity < 1)
                quantity = 1;

            if (quantity > item.getItemStackLimit())
                quantity = item.getItemStackLimit();
        }

        return new ItemStack(item, quantity, fishData.resultingItemMetadata);
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
        ItemStack itemScaler = ItemStack.EMPTY;
        ItemStack itemFish = ItemStack.EMPTY;

        if (scaleSlot != -1) {
            ItemStack itemStack = inv.getStackInSlot(scaleSlot);

            if (!itemStack.isEmpty() && itemStack.getItem() instanceof ItemScaleRemover) {
                itemScaler = itemStack.copy();
            }
        }

        if(fishSlot != -1){
            ItemStack itemStack = inv.getStackInSlot(fishSlot);

            if (!itemStack.isEmpty()) {
                itemFish = itemStack.copy();
                BetterFishUtil.setFishHasScale(itemFish, false);
            }
        }

        if (!itemScaler.isEmpty()) {
            itemScaler.setItemDamage(itemScaler.getItemDamage() + 8);
        }

        NonNullList<ItemStack> ret = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

        for (int i = 0; i < ret.size(); i++) {
            ItemStack itemStack = inv.getStackInSlot(i);

            if (!itemStack.isEmpty() && itemStack.getItem() instanceof ItemScaleRemover) {
                ret.set(i, itemScaler);
            } else if(!itemStack.isEmpty() && !itemFish.isEmpty()){
                ret.set(i, itemFish);
            }
        }

        return ret;
    }
}
