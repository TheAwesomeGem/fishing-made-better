package net.theawesomegem.fishingmadebetter.common.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.theawesomegem.fishingmadebetter.BetterFishUtil;
import net.theawesomegem.fishingmadebetter.common.item.ItemBaitBucket;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class RecipeBaitBucket extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {

    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        return validInput(inv) != null;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
        Integer[] slots = validInput(inv);
        if(slots==null) return ItemStack.EMPTY;

        ItemStack itemBaitBucket = inv.getStackInSlot(slots[0]).copy();
        ItemStack itemBait = slots[1]!=-1 ? inv.getStackInSlot(slots[1]).copy() : null;

        if(itemBait == null) {
            ItemBaitBucket.removeBait(itemBaitBucket);
        }
        else {
            int count = slots.length-1;
            ItemBaitBucket.setBaitId(itemBaitBucket, Item.REGISTRY.getNameForObject(itemBait.getItem()).toString());
            ItemBaitBucket.setBaitMetadata(itemBaitBucket, itemBait.getMetadata());
            ItemBaitBucket.setBaitDisplayName(itemBaitBucket, itemBait.getDisplayName());
            ItemBaitBucket.setBaitCount(itemBaitBucket, ItemBaitBucket.getBaitCount(itemBaitBucket) + count);
        }

        return itemBaitBucket;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
        NonNullList<ItemStack> ret = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);
        Integer[] slots = validInput(inv);
        if(slots==null) return ret;

        ItemStack itemBaitBucket = inv.getStackInSlot(slots[0]).copy();
        ItemStack itemBait = slots[1]!=-1 ? inv.getStackInSlot(slots[1]).copy() : null;

        if(itemBait!=null || Item.getByNameOrId(ItemBaitBucket.getBaitId(itemBaitBucket))==null || ItemBaitBucket.getBaitCount(itemBaitBucket)<=0) return ret;

        ItemStack oldBait = new ItemStack(Item.getByNameOrId(ItemBaitBucket.getBaitId(itemBaitBucket)), ItemBaitBucket.getBaitCount(itemBaitBucket), ItemBaitBucket.getBaitMetadata(itemBaitBucket));
        ret.set(0, oldBait);
        return ret;
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
        int bucketSlot = -1;
        List<Integer> baitSlots = new ArrayList<>();
        ItemStack bait = null;
        List<Integer> occupiedSlots = new ArrayList<>();

        for(int i = 0; i < inv.getSizeInventory(); i++) {
            if (!inv.getStackInSlot(i).isEmpty()) {
                numStacks++;
                occupiedSlots.add(i);
            }
        }
        if(numStacks <= 0) return null;

        for(int i : occupiedSlots) {
            ItemStack itemStack = inv.getStackInSlot(i);

            if(itemStack.isEmpty()) return null;
            else if(itemStack.getItem() instanceof ItemBaitBucket && bucketSlot == -1) bucketSlot = i;
            else if(BetterFishUtil.isValidBait(itemStack) && (bait == null || itemStack.getItem() == bait.getItem())) {
                baitSlots.add(i);
                bait = itemStack;
            }
            else return null;
        }

        if(bucketSlot == -1) return null;

        if(numStacks>1 && !ItemBaitBucket.getBaitId(inv.getStackInSlot(bucketSlot)).isEmpty()) {
            if(!ItemBaitBucket.getBaitId(inv.getStackInSlot(bucketSlot)).equals(Item.REGISTRY.getNameForObject(inv.getStackInSlot(baitSlots.get(0)).getItem()).toString())) return null;
            if(ItemBaitBucket.getBaitMetadata(inv.getStackInSlot(bucketSlot)) != inv.getStackInSlot(baitSlots.get(0)).getMetadata()) return null;
            if(ItemBaitBucket.getBaitCount(inv.getStackInSlot(bucketSlot)) + numStacks - 1 > 64) return null;
        }
        else if(numStacks==1) {
            if(ItemBaitBucket.getBaitId(inv.getStackInSlot(bucketSlot)).isEmpty() || ItemBaitBucket.getBaitCount(inv.getStackInSlot(bucketSlot))<=0) return null;
        }

        Integer[] slots = new Integer[Math.max(2, numStacks)];
        slots[0] = bucketSlot;
        slots[1] = -1;
        for(int i = 0; i < baitSlots.size(); i++) {
            slots[i+1] = baitSlots.get(i);
        }
        return slots;
    }
}