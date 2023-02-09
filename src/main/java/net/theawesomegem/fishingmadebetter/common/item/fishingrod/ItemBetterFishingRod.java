package net.theawesomegem.fishingmadebetter.common.item.fishingrod;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.enchantment.EnchantmentDurability;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Enchantments;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.oredict.OreDictionary;
import net.theawesomegem.fishingmadebetter.ModInfo;
import net.theawesomegem.fishingmadebetter.common.configuration.CustomConfigurationHandler;
import net.theawesomegem.fishingmadebetter.common.data.FishData;
import net.theawesomegem.fishingmadebetter.common.entity.EntityFMBCustomFishHook;
import net.theawesomegem.fishingmadebetter.common.entity.EntityFMBLavaFishHook;
import net.theawesomegem.fishingmadebetter.common.entity.EntityFMBVoidFishHook;
import net.theawesomegem.fishingmadebetter.common.item.ItemManager;
import net.theawesomegem.fishingmadebetter.common.item.attachment.bobber.ItemBobber;
import net.theawesomegem.fishingmadebetter.common.item.attachment.hook.ItemHook;
import net.theawesomegem.fishingmadebetter.common.item.attachment.reel.ItemReel;
import net.theawesomegem.fishingmadebetter.common.registry.FMBCreativeTab;

import javax.annotation.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by TheAwesomeGem on 12/30/2017.
 */
public abstract class ItemBetterFishingRod extends ItemFishingRod {
	protected Item.ToolMaterial toolMaterial;
	
    public ItemBetterFishingRod(String name, ToolMaterial materialIn) {
        super();

        this.toolMaterial = materialIn;
        this.setCreativeTab(FMBCreativeTab.instance);
        this.setMaxDamage(materialIn.getMaxUses());
        this.setRegistryName(name);
        this.setTranslationKey(ModInfo.MOD_ID + "." + name);
    }
    
    public boolean getIsRepairable(ItemStack toRepair, ItemStack repair)
    {
        ItemStack mat = this.toolMaterial.getRepairItemStack();
        if (!mat.isEmpty() && OreDictionary.itemMatches(mat, repair, false)) return true;
        return super.getIsRepairable(toRepair, repair);
    }

    @SuppressWarnings("deprecation")
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
    	ItemStack itemstack = playerIn.getHeldItem(handIn);
    	
        if(playerIn.fishEntity != null) {
            int dmg = playerIn.fishEntity.handleHookRetraction();
            
            if(!playerIn.capabilities.isCreativeMode && itemstack.isItemStackDamageable()) {
            	Random rand = playerIn.getRNG();
            	
            	if(dmg > 0) {
                    int i = EnchantmentHelper.getEnchantmentLevel(Enchantments.UNBREAKING, itemstack);
                    int j = 0;

                    for(int k = 0; i > 0 && k < dmg; ++k) {
                        if(EnchantmentDurability.negateDamage(itemstack, i, rand)) ++j;
                    }

                    dmg -= j;

                    if(dmg > 0) {
                        //Handle attachment breaking
                        boolean reelAlive = false;
                        boolean bobberAlive = false;
                        boolean hookAlive = false;
                        
                        if(ItemBetterFishingRod.hasReelItem(itemstack)) {
                        	ItemBetterFishingRod.setReelDamage(itemstack, ItemBetterFishingRod.getReelDamage(itemstack) + dmg);
                        	
                        	if(ItemBetterFishingRod.getReelDamage(itemstack) > ItemBetterFishingRod.getReelItem(itemstack).getMaxDamage()) {
                            	ItemBetterFishingRod.removeReelItem(itemstack);
                            	playerIn.renderBrokenItemStack(itemstack);
                            }
                        	else reelAlive = true;
                        }
                        if(ItemBetterFishingRod.hasBobberItem(itemstack)) {
                        	ItemBetterFishingRod.setBobberDamage(itemstack, ItemBetterFishingRod.getBobberDamage(itemstack) + dmg);
                        	
                        	if(ItemBetterFishingRod.getBobberDamage(itemstack) > ItemBetterFishingRod.getBobberItem(itemstack).getMaxDamage()) {
                            	ItemBetterFishingRod.removeBobberItem(itemstack);
                            	playerIn.renderBrokenItemStack(itemstack);
                            }
                        	else bobberAlive = true;
                        }

                        if(ItemBetterFishingRod.hasHookItem(itemstack)) {
                        	ItemBetterFishingRod.setHookDamage(itemstack, ItemBetterFishingRod.getHookDamage(itemstack) + dmg);
                        	
                        	if(ItemBetterFishingRod.getHookDamage(itemstack) > ItemBetterFishingRod.getHookItem(itemstack).getMaxDamage()) {
                            	ItemBetterFishingRod.removeHookItem(itemstack);
                            	playerIn.renderBrokenItemStack(itemstack);
                            }
                        	else hookAlive = true;
                        }
                        
                        itemstack.setItemDamage(itemstack.getItemDamage() + dmg);
                        
                        if(itemstack.getItemDamage() > itemstack.getMaxDamage()) {
                        	playerIn.renderBrokenItemStack(itemstack);
                        	
                        	if(reelAlive) {
                        		ItemStack droppedReel = new ItemStack(ItemBetterFishingRod.getReelItem(itemstack));
                        		droppedReel.setItemDamage(ItemBetterFishingRod.getReelDamage(itemstack));
                        		playerIn.entityDropItem(droppedReel, 1.0f);
                        	}
                        	if(bobberAlive) {
                        		ItemStack droppedBobber = new ItemStack(ItemBetterFishingRod.getBobberItem(itemstack));
                        		droppedBobber.setItemDamage(ItemBetterFishingRod.getBobberDamage(itemstack));
                        		playerIn.entityDropItem(droppedBobber, 1.0f);
                        	}
                        	if(hookAlive) {
                        		ItemStack droppedHook = new ItemStack(ItemBetterFishingRod.getHookItem(itemstack));
                        		droppedHook.setItemDamage(ItemBetterFishingRod.getHookDamage(itemstack));
                        		playerIn.entityDropItem(droppedHook, 1.0f);
                        	}
                        	
                            itemstack.shrink(1);
                            itemstack.setItemDamage(0);
                        }
                    }
                }
            }
            
            playerIn.swingArm(handIn);
            worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_BOBBER_RETRIEVE, SoundCategory.NEUTRAL, 1.0F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
        }
        else if(playerIn.getHeldItem(EnumHand.MAIN_HAND) != null && playerIn.getHeldItem(EnumHand.OFF_HAND) != null && playerIn.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemFishingRod && playerIn.getHeldItem(EnumHand.OFF_HAND).getItem() instanceof ItemFishingRod) {
        	return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemstack);
        }
        else {
            worldIn.playSound((EntityPlayer)null, playerIn.posX, playerIn.posY, playerIn.posZ, SoundEvents.ENTITY_BOBBER_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));

            if(!worldIn.isRemote) {
            	if(getBobberItem(itemstack).isLavaBobber()) {
            		EntityFMBCustomFishHook entityFishHook = new EntityFMBLavaFishHook(worldIn, playerIn);
                    worldIn.spawnEntity(entityFishHook);
            	}
            	else if((getBobberItem(itemstack).isVoidBobber())) {
            		EntityFMBCustomFishHook entityFishHook = new EntityFMBVoidFishHook(worldIn, playerIn);
                    worldIn.spawnEntity(entityFishHook);
            	}
            	else {
            		EntityFishHook entityFishHook = new EntityFishHook(worldIn, playerIn) {
            			ItemStack parentRod;
            			
            			@Override
            			protected void entityInit() {
            				super.entityInit();
            				parentRod = itemstack;
            			}
            			
            			@Override
            			public void onUpdate() {
            				super.onUpdate();
            				
            				if(getAngler() != null && getAngler().fishEntity != null) {
            					if( (parentRod == null) ||
            						(getAngler().getHeldItemMainhand() == null || getAngler().getHeldItemOffhand() == null) ||
            						(getAngler().getHeldItemMainhand().getItem() instanceof ItemFishingRod == getAngler().getHeldItemOffhand().getItem() instanceof ItemFishingRod) ||
            						((getAngler().getHeldItemMainhand() == parentRod) == (getAngler().getHeldItemOffhand() == parentRod)) 
            						) super.setDead();
            				}
            			}
            		};
                    worldIn.spawnEntity(entityFishHook);
            	}
            }

            playerIn.swingArm(handIn);
            playerIn.addStat(StatList.getObjectUseStats(this));
        }

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemstack);
    }

    @SuppressWarnings("deprecation")
	@Override
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
    	ItemReel reel = getReelItem(stack);
    	ItemBobber bobber = getBobberItem(stack);
    	ItemHook hook = getHookItem(stack);
    	
    	if(GuiScreen.isShiftKeyDown()) {
    		tooltip.add(TextFormatting.BLUE + "" + TextFormatting.BOLD + I18n.format("tooltip.fishingmadebetter.fishing_rod.reel.name") + ": " + TextFormatting.RESET + "" + TextFormatting.GRAY + I18n.format("item.fishingmadebetter." + reel.getRegistryName().getPath() + ".name") + TextFormatting.RESET);
    		tooltip.add((reel.getMaxDamage() != 0) ? ("  " + I18n.format("tooltip.fishingmadebetter.fishing_rod.durability.title") + ": " + (reel.getMaxDamage()-getReelDamage(stack)) + "/" + reel.getMaxDamage()) : ("  " + I18n.format("tooltip.fishingmadebetter.fishing_rod.durability.title") + ": -/-"));
    		tooltip.add("  " + I18n.format("tooltip.fishingmadebetter.fishing_rod.reel.range") + ": " + reel.getReelRange() + "m");
    		tooltip.add("  " + I18n.format("tooltip.fishingmadebetter.fishing_rod.reel.speed") + ": " + reel.getReelSpeed() + "m/s");
    		tooltip.add(TextFormatting.BLUE + "" + TextFormatting.BOLD + I18n.format("tooltip.fishingmadebetter.fishing_rod.bobber") + ": " + TextFormatting.RESET + "" + TextFormatting.GRAY + I18n.format("item.fishingmadebetter." + bobber.getRegistryName().getPath() + ".name") + TextFormatting.RESET);
    		tooltip.add((bobber.getMaxDamage() != 0) ? ("  " + I18n.format("tooltip.fishingmadebetter.fishing_rod.durability.title") + ": " + (bobber.getMaxDamage()-getBobberDamage(stack)) + "/" + bobber.getMaxDamage()) : ("  " + I18n.format("tooltip.fishingmadebetter.fishing_rod.durability.title") + ": -/-"));
    		if(bobber.isLavaBobber()) tooltip.add("  " + I18n.format("tooltip.fishingmadebetter.fishing_rod.bobber.can_fish_in") + ": " + TextFormatting.RED + I18n.format("tooltip.fishingmadebetter.fishing_rod.bobber.can_fish_in.lava") + TextFormatting.RESET);
    		else if(bobber.isVoidBobber()) tooltip.add("  " + I18n.format("tooltip.fishingmadebetter.fishing_rod.bobber.can_fish_in") + ": " + TextFormatting.LIGHT_PURPLE + I18n.format("tooltip.fishingmadebetter.fishing_rod.bobber.can_fish_in.void") + TextFormatting.RESET);
    		else tooltip.add("  " + I18n.format("tooltip.fishingmadebetter.fishing_rod.bobber.can_fish_in") + ": " + TextFormatting.BLUE + I18n.format("tooltip.fishingmadebetter.fishing_rod.bobber.can_fish_in.water") + TextFormatting.RESET);
    		if(bobber.getVarianceModifier() != 0) tooltip.add("  " + I18n.format("tooltip.fishingmadebetter.fishing_rod.bobber.tension_size") + ": +" + bobber.getVarianceModifier());
    		if(bobber.getTensioningModifier() !=0) tooltip.add("  " + I18n.format("tooltip.fishingmadebetter.fishing_rod.bobber.tension_speed") + ": +" + bobber.getTensioningModifier());
    		tooltip.add(TextFormatting.BLUE + "" + TextFormatting.BOLD + I18n.format("tooltip.fishingmadebetter.fishing_rod.hook") + ": " + TextFormatting.RESET + "" + TextFormatting.GRAY + I18n.format("item.fishingmadebetter." + hook.getRegistryName().getPath() + ".name") + TextFormatting.RESET);
    		tooltip.add((hook.getMaxDamage() != 0) ? ("  " + I18n.format("tooltip.fishingmadebetter.fishing_rod.durability.title") + ": " + (hook.getMaxDamage()-getHookDamage(stack)) + "/" + hook.getMaxDamage()) : ("  " + I18n.format("tooltip.fishingmadebetter.fishing_rod.durability.title") + ": -/-"));
    		if(hook.getTuggingReduction() != 0) tooltip.add("  " + I18n.format("tooltip.fishingmadebetter.fishing_rod.hook.tugging") + ": -" + hook.getTuggingReduction());
    		if(hook.getTreasureModifier() != 0) tooltip.add("  " + I18n.format("tooltip.fishingmadebetter.fishing_rod.hook.treasure_chance") + ": +" + hook.getTreasureModifier());
    		if(hook.getBiteRateModifier() != 0) tooltip.add("  " + I18n.format("tooltip.fishingmadebetter.fishing_rod.hook.bite_rate") + ": +" + hook.getBiteRateModifier());
    		if(hook.getWeightModifier() != 0) tooltip.add("  " + I18n.format("tooltip.fishingmadebetter.fishing_rod.hook.weight") + ": +" + hook.getWeightModifier());
    		String baitDisplayName = getBaitDisplayName(stack);
            tooltip.add(TextFormatting.BLUE + "" + TextFormatting.BOLD + I18n.format("tooltip.fishingmadebetter.fishing_rod.bait") + ": " + TextFormatting.RESET + "" + TextFormatting.GRAY + ((baitDisplayName != null && baitDisplayName.length() > 0) ? baitDisplayName : I18n.format("tooltip.fishingmadebetter.fishing_rod.bait_none")) + TextFormatting.RESET);
            tooltip.add("");
    	}
    	else {
    		tooltip.add(TextFormatting.BLUE + "" + TextFormatting.BOLD + I18n.format("tooltip.fishingmadebetter.fishing_rod.reel.name") + ": " + TextFormatting.RESET + "" + TextFormatting.GRAY + I18n.format("item.fishingmadebetter." + reel.getRegistryName().getPath() + ".name") + TextFormatting.RESET);
    		tooltip.add((reel.getMaxDamage() != 0) ? ("  " + I18n.format("tooltip.fishingmadebetter.fishing_rod.durability.title") + ": " + (reel.getMaxDamage()-getReelDamage(stack)) + "/" + reel.getMaxDamage()) : ("  " + I18n.format("tooltip.fishingmadebetter.fishing_rod.durability.title") + ": -/-"));
    		tooltip.add(TextFormatting.BLUE + "" + TextFormatting.BOLD + I18n.format("tooltip.fishingmadebetter.fishing_rod.bobber") + ": " + TextFormatting.RESET + "" + TextFormatting.GRAY + I18n.format("item.fishingmadebetter." + bobber.getRegistryName().getPath() + ".name") + TextFormatting.RESET);
    		tooltip.add((bobber.getMaxDamage() != 0) ? ("  " +  I18n.format("tooltip.fishingmadebetter.fishing_rod.durability.title") + ": " + (bobber.getMaxDamage()-getBobberDamage(stack)) + "/" + bobber.getMaxDamage()) : ("  " + I18n.format("tooltip.fishingmadebetter.fishing_rod.durability.title") + ": -/-"));
    		tooltip.add(TextFormatting.BLUE + "" + TextFormatting.BOLD + I18n.format("tooltip.fishingmadebetter.fishing_rod.hook") + ": " + TextFormatting.RESET + "" + TextFormatting.GRAY + I18n.format("item.fishingmadebetter." + hook.getRegistryName().getPath() + ".name") + TextFormatting.RESET);
    		tooltip.add((hook.getMaxDamage() != 0) ? ("  " +  I18n.format("tooltip.fishingmadebetter.fishing_rod.durability.title") + ": " + (hook.getMaxDamage()-getHookDamage(stack)) + "/" + hook.getMaxDamage()) : ("  " + I18n.format("tooltip.fishingmadebetter.fishing_rod.durability.title") + ": -/-"));
    		String baitDisplayName = getBaitDisplayName(stack);
            tooltip.add(TextFormatting.BLUE + "" + TextFormatting.BOLD + I18n.format("tooltip.fishingmadebetter.fishing_rod.bait") + ": " + TextFormatting.RESET + "" + TextFormatting.GRAY + ((baitDisplayName != null && baitDisplayName.length() > 0) ? baitDisplayName : I18n.format("tooltip.fishingmadebetter.fishing_rod.bait_none")) + TextFormatting.RESET);
    		tooltip.add("");
            tooltip.add(I18n.format("tooltip.fishingmadebetter.fishing_rod.shift.1") + " " + TextFormatting.GOLD + "Shift" + TextFormatting.RESET + "" + TextFormatting.GRAY + " " + I18n.format("tooltip.fishingmadebetter.fishing_rod.shift.2") + TextFormatting.RESET);
    	}
    }
    
    //Bait//
    @Nullable
    public static String getBaitItem(ItemStack itemStack) {
        return(hasBait(itemStack) ? itemStack.getTagCompound().getString("BaitItem") : null);
    }

    public static void setBaitItem(ItemStack itemStack, String bait) {
        if(!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());

        itemStack.getTagCompound().setString("BaitItem", bait);
    }

    public static void removeBait(ItemStack itemStack) {
        if(!itemStack.hasTagCompound()) return;

        itemStack.getTagCompound().removeTag("BaitItem");
        itemStack.getTagCompound().removeTag("BaitMetadata");
        itemStack.getTagCompound().removeTag("BaitDisplayName");
    }

    public static int getBaitMetadata(ItemStack itemStack) {
        return (hasBait(itemStack) ? itemStack.getTagCompound().getInteger("BaitMetadata") : 0);
    }

    public static void setBaitMetadata(ItemStack itemStack, int metadata) {
        if(!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());

        itemStack.getTagCompound().setInteger("BaitMetadata", metadata);
    }

    @Nullable
    public static String getBaitDisplayName(ItemStack itemStack) {
        return (hasBait(itemStack) ? itemStack.getTagCompound().getString("BaitDisplayName") : null);
    }

    public static void setBaitDisplayName(ItemStack itemStack, String baitDisplayName) {
        if(!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());

        itemStack.getTagCompound().setString("BaitDisplayName", baitDisplayName);
    }

    public static boolean hasBait(ItemStack itemStack) {
        return itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("BaitItem");
    }
    
    public static boolean isBaitForFish(ItemStack itemStack, String fishId) {
        if(!hasBait(itemStack)) return false;

        FishData fishData = CustomConfigurationHandler.fishDataMap.get(fishId);
        if(fishData == null) return false;

        String baitItem = getBaitItem(itemStack);
        if(baitItem == null) return false;
        
        return fishData.baitItemMap.containsKey(baitItem) && Arrays.asList(fishData.baitItemMap.get(baitItem)).contains(getBaitMetadata(itemStack));
    }
    //****//
    
    //Reel//
    public static ItemReel getReelItem(ItemStack itemStack) {
    	return hasReelItem(itemStack) ? (ItemReel)Item.getByNameOrId(itemStack.getTagCompound().getString("ReelItem")) : ItemManager.REEL_BASIC;
    }
    
    public static void setReelItem(ItemStack itemStack, ItemReel itemReel) {
    	if(!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());

        itemStack.getTagCompound().setString("ReelItem", itemReel.getRegistryName().toString());
    }
    
    public static void removeReelItem(ItemStack itemStack) {
    	if(!itemStack.hasTagCompound()) return;

        itemStack.getTagCompound().removeTag("ReelItem");
        itemStack.getTagCompound().removeTag("ReelDamage");
    }
    
    public static int getReelDamage(ItemStack itemStack) {
    	return hasReelItem(itemStack) ? itemStack.getTagCompound().getInteger("ReelDamage") : 0;
    }
    
    public static void setReelDamage(ItemStack itemStack, int newDamage) {
    	if(!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());
    	
    	itemStack.getTagCompound().setInteger("ReelDamage", (hasReelItem(itemStack) ? newDamage : 0));
    }
    
    public static boolean hasReelItem(ItemStack itemStack) {
    	return itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("ReelItem");
    }
    //****//
    
    //Bobber//
    public static ItemBobber getBobberItem(ItemStack itemStack) {
    	return hasBobberItem(itemStack) ? (ItemBobber)Item.getByNameOrId(itemStack.getTagCompound().getString("BobberItem")) : ItemManager.BOBBER_BASIC;
    }
    
    public static void setBobberItem(ItemStack itemStack, ItemBobber itemBobber) {
    	if(!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());

        itemStack.getTagCompound().setString("BobberItem", itemBobber.getRegistryName().toString());
    }
    
    public static void removeBobberItem(ItemStack itemStack) {
    	if(!itemStack.hasTagCompound()) return;

        itemStack.getTagCompound().removeTag("BobberItem");
        itemStack.getTagCompound().removeTag("BobberDamage");
    }
    
    public static int getBobberDamage(ItemStack itemStack) {
    	return hasBobberItem(itemStack) ? itemStack.getTagCompound().getInteger("BobberDamage") : 0;
    }
    
    public static void setBobberDamage(ItemStack itemStack, int newDamage) {
    	if(!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());
    	
    	itemStack.getTagCompound().setInteger("BobberDamage", (hasBobberItem(itemStack) ? newDamage : 0));
    }
    
    public static boolean hasBobberItem(ItemStack itemStack) {
    	return itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("BobberItem");
    }
    //****//
    
    //Hook//
    public static ItemHook getHookItem(ItemStack itemStack) {
    	return hasHookItem(itemStack) ? (ItemHook)Item.getByNameOrId(itemStack.getTagCompound().getString("HookItem")) : ItemManager.HOOK_BASIC;
    }
    
    public static void setHookItem(ItemStack itemStack, ItemHook itemHook) {
    	if(!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());

        itemStack.getTagCompound().setString("HookItem", itemHook.getRegistryName().toString());
    }
    
    public static void removeHookItem(ItemStack itemStack) {
    	if(!itemStack.hasTagCompound()) return;

        itemStack.getTagCompound().removeTag("HookItem");
        itemStack.getTagCompound().removeTag("HookDamage");
    }
    
    public static int getHookDamage(ItemStack itemStack) {
    	return hasHookItem(itemStack) ? itemStack.getTagCompound().getInteger("HookDamage") : 0;
    }
    
    public static void setHookDamage(ItemStack itemStack, int newDamage) {
    	if(!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());
    	
    	itemStack.getTagCompound().setInteger("HookDamage", (hasHookItem(itemStack) ? newDamage : 0));
    }
    
    public static boolean hasHookItem(ItemStack itemStack) {
    	return itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("HookItem");
    }
    //****//
}
