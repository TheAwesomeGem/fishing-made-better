package net.theawesomegem.fishingmadebetter.common.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.theawesomegem.fishingmadebetter.common.item.attachment.bobber.ItemBobber;
import net.theawesomegem.fishingmadebetter.common.item.attachment.bobber.ItemBobberBasic;
import net.theawesomegem.fishingmadebetter.common.item.attachment.hook.ItemHook;
import net.theawesomegem.fishingmadebetter.common.item.attachment.hook.ItemHookBasic;
import net.theawesomegem.fishingmadebetter.common.item.attachment.reel.ItemReel;
import net.theawesomegem.fishingmadebetter.common.item.attachment.reel.ItemReelBasic;
import net.theawesomegem.fishingmadebetter.common.item.fishingrod.ItemBetterFishingRod;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

/**
 * Created by TheAwesomeGem on 1/1/2018.
 */
public class RecipeRodAttachment extends net.minecraftforge.registries.IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
    @Override
    public boolean matches(InventoryCrafting inv, World worldIn) {
        return validInput(inv) != null;
    }

    @Override
    public ItemStack getCraftingResult(InventoryCrafting inv) {
    	Integer[] slots = validInput(inv);
    	if(slots==null) return ItemStack.EMPTY;
    	
        ItemStack itemFishingRod = inv.getStackInSlot(slots[0]).copy();
        ItemStack itemReel = slots[1]!=-1 ? inv.getStackInSlot(slots[1]).copy() : null;
        ItemStack itemBobber = slots[2]!=-1 ? inv.getStackInSlot(slots[2]).copy() : null;
        ItemStack itemHook = slots[3]!=-1 ? inv.getStackInSlot(slots[3]).copy() : null;
        
        if(itemReel!=null) {
        	ItemBetterFishingRod.setReelItem(itemFishingRod, (ItemReel)itemReel.getItem());
        	ItemBetterFishingRod.setReelDamage(itemFishingRod, itemReel.getItemDamage());
        }
        if(itemBobber!=null) {
        	ItemBetterFishingRod.setBobberItem(itemFishingRod, (ItemBobber)itemBobber.getItem());
        	ItemBetterFishingRod.setBobberDamage(itemFishingRod, itemBobber.getItemDamage());
        }
        if(itemHook!=null) {
        	ItemBetterFishingRod.setHookItem(itemFishingRod, (ItemHook)itemHook.getItem());
        	ItemBetterFishingRod.setHookDamage(itemFishingRod, itemHook.getItemDamage());
        }
        if(itemReel==null && itemBobber==null && itemHook==null) {
        	ItemBetterFishingRod.removeReelItem(itemFishingRod);
        	ItemBetterFishingRod.removeBobberItem(itemFishingRod);
        	ItemBetterFishingRod.removeHookItem(itemFishingRod);
        }
        
        return itemFishingRod;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
    	Integer[] slots = validInput(inv);
    	
    	ItemStack itemFishingRod = inv.getStackInSlot(slots[0]).copy();
        ItemStack itemReel = slots[1]!=-1 ? inv.getStackInSlot(slots[1]).copy() : null;
        ItemStack itemBobber = slots[2]!=-1 ? inv.getStackInSlot(slots[2]).copy() : null;
        ItemStack itemHook = slots[3]!=-1 ? inv.getStackInSlot(slots[3]).copy() : null;
    	
        ItemStack oldReel = ItemBetterFishingRod.hasReelItem(itemFishingRod) ? new ItemStack(ItemBetterFishingRod.getReelItem(itemFishingRod)) : null;
        if(oldReel!=null) oldReel.setItemDamage(ItemBetterFishingRod.getReelDamage(itemFishingRod));
        ItemStack oldBobber = ItemBetterFishingRod.hasBobberItem(itemFishingRod) ? new ItemStack(ItemBetterFishingRod.getBobberItem(itemFishingRod)) : null;
        if(oldBobber!=null) oldBobber.setItemDamage(ItemBetterFishingRod.getBobberDamage(itemFishingRod));
        ItemStack oldHook = ItemBetterFishingRod.hasHookItem(itemFishingRod) ? new ItemStack(ItemBetterFishingRod.getHookItem(itemFishingRod)) : null;
        if(oldHook!=null) oldHook.setItemDamage(ItemBetterFishingRod.getHookDamage(itemFishingRod));
        
        if(itemReel!=null || itemBobber!=null || itemHook!=null) {
        	if(itemReel==null && oldReel!=null) oldReel = null;
        	if(itemBobber==null && oldBobber!=null) oldBobber = null;
        	if(itemHook==null && oldHook!=null) oldHook = null;
        }
        
        NonNullList<ItemStack> ret = NonNullList.withSize(inv.getSizeInventory(), ItemStack.EMPTY);

        for(int i = 0; i < ret.size(); i++) {
            if(oldReel!=null) {
            	ret.set(i, oldReel);
            	oldReel = null;
            }
            else if(oldBobber!=null) {
            	ret.set(i, oldBobber);
            	oldBobber = null;
            }
            else if(oldHook!=null) {
            	ret.set(i, oldHook);
            	oldHook = null;
            }
            else break;
        }
        
        return ret;
    }
    
    @Override
    public boolean canFit(int width, int height) {
        return width * height >= 4;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return ItemStack.EMPTY;
    }
    
    @Nullable
    private Integer[] validInput(InventoryCrafting inv) {
    	int numRod = 0;
    	int numReel = 0;
    	int numBobber = 0;
    	int numHook = 0;
    	
        int slotRod = -1;
        int slotReel = -1;
        int slotBobber = -1;
        int slotHook = -1;
        
        List<Integer> occupiedSlots = new ArrayList<>();
        
        for(int i = 0; i < inv.getSizeInventory(); i++) {
            if(!inv.getStackInSlot(i).isEmpty()) {
                occupiedSlots.add(i);
            }
        }

        for(int i : occupiedSlots) {
            ItemStack itemStack = inv.getStackInSlot(i);
            
            if(itemStack.isEmpty()) return null;
            else if(itemStack.getItem() instanceof ItemBetterFishingRod) {
            	numRod++;
            	slotRod = i;
            }
            else if(itemStack.getItem() instanceof ItemReel && !(itemStack.getItem() instanceof ItemReelBasic)) {
            	numReel++;
            	slotReel = i;
            }
            else if(itemStack.getItem() instanceof ItemBobber && !(itemStack.getItem() instanceof ItemBobberBasic)) {
            	numBobber++;
            	slotBobber = i;
            }
            else if(itemStack.getItem() instanceof ItemHook && !(itemStack.getItem() instanceof ItemHookBasic)) {
            	numHook++;
            	slotHook = i;
            }
            else return null;
        }
        
        Integer[] slots = new Integer[4];
        slots[0] = slotRod;
        slots[1] = slotReel;
        slots[2] = slotBobber;
        slots[3] = slotHook;
        
        return (numRod==1 && (numReel<2 && numBobber<2 && numHook<2)) ? slots : null;
    }
}