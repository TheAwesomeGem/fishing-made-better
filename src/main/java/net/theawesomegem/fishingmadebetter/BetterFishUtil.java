package net.theawesomegem.fishingmadebetter;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
//import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TextComponentTranslation;

//import net.minecraft.util.ResourceLocation;

import net.theawesomegem.fishingmadebetter.common.configuration.CustomConfigurationHandler;
import net.theawesomegem.fishingmadebetter.common.data.FishData;
import net.theawesomegem.fishingmadebetter.common.item.ItemFishBucket;
import net.theawesomegem.fishingmadebetter.common.item.ItemFishLavaBucket;
import net.theawesomegem.fishingmadebetter.common.item.ItemFishVoidBucket;
import net.theawesomegem.fishingmadebetter.util.ItemStackUtil;
import net.theawesomegem.fishingmadebetter.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class BetterFishUtil {
	
	@Nullable
    public static String getFishId(ItemStack itemStack) {
        return (!itemStack.hasTagCompound() || !itemStack.getTagCompound().hasKey("FishId")) ? null : itemStack.getTagCompound().getString("FishId");
    }

    public static int getFishWeight(ItemStack itemStack) {
        return (!itemStack.hasTagCompound() || !itemStack.getTagCompound().hasKey("FishWeight")) ? 1 : itemStack.getTagCompound().getInteger("FishWeight");
    }

    public static long getFishCaughtTime(ItemStack itemStack) {
        return (!itemStack.hasTagCompound() || !itemStack.getTagCompound().hasKey("FishCaughtTime")) ? 0 : itemStack.getTagCompound().getLong("FishCaughtTime");
    }

    public static void setFishCaughtTime(ItemStack itemStack, int time) {
        NBTTagCompound tagCompound = itemStack.getTagCompound();

        if(tagCompound == null) tagCompound = new NBTTagCompound();

        tagCompound.setLong("FishCaughtTime", time);

        itemStack.setTagCompound(tagCompound);
    }

    public static boolean doesFishHasScale(ItemStack itemStack) {
        return (!itemStack.hasTagCompound() || !itemStack.getTagCompound().hasKey("FishScale")) ? false : itemStack.getTagCompound().getBoolean("FishScale");
    }

    public static void setFishHasScale(ItemStack itemStack, boolean value) {
        NBTTagCompound tagCompound = itemStack.getTagCompound();

        if(tagCompound == null) tagCompound = new NBTTagCompound();
        
        tagCompound.setBoolean("FishScale", value);

        itemStack.setTagCompound(tagCompound);
        
        if(!value) {
        	List<String> tooltipList = new ArrayList<>();
            tooltipList.add(String.format("%s: %d", new TextComponentTranslation("tooltip.fishingmadebetter.fish.weight").getUnformattedComponentText(), BetterFishUtil.getFishWeight(itemStack)));
            tooltipList.add(String.format("%s: %s", new TextComponentTranslation("tooltip.fishingmadebetter.fish.scale").getUnformattedComponentText(), new TextComponentTranslation("tooltip.fishingmadebetter.fish.scale_detached").getUnformattedComponentText()));
            tooltipList.add( new TextComponentTranslation("tooltip.fishingmadebetter.fish.dead").getUnformattedComponentText() );
            itemStack = ItemStackUtil.appendToolTip(itemStack, tooltipList);

            BetterFishUtil.setFishCaughtTime(itemStack, 0);
        }
    }

    public static boolean isBetterFish(ItemStack itemStack) {
        String fishId = BetterFishUtil.getFishId(itemStack);

        if(itemStack.getItem() instanceof ItemFishBucket || itemStack.getItem() instanceof ItemFishLavaBucket || itemStack.getItem() instanceof ItemFishVoidBucket) return false;//No, a bucket of fish is not a fish itself
        return fishId != null && fishId.length() > 0;
    }
    
    public static boolean isValidBait(ItemStack itemStack) {//Hopefully works lel
    	String baitString = Item.REGISTRY.getNameForObject(itemStack.getItem()).toString();
    	if(baitString == null) return false;
    	
    	Integer baitMeta = itemStack.getMetadata();
    	return CustomConfigurationHandler.possibleBaitMap.containsKey(baitString) && CustomConfigurationHandler.possibleBaitMap.get(baitString).contains(baitMeta);
    }

    public static boolean isDead(ItemStack itemStack, long time) {
        String fishId = BetterFishUtil.getFishId(itemStack);
        if(fishId == null) return true;//Dodging NPE's?
        
        FishData fishData = CustomConfigurationHandler.fishDataMap.get(fishId);
        if(fishData == null) return true;//Or am I missing something?
        
        long fishCaughtTime = BetterFishUtil.getFishCaughtTime(itemStack);
        if(fishCaughtTime == 0) return true;//Was false, why treat broken fish as alive?
        
        if(fishData.allowScaling && !BetterFishUtil.doesFishHasScale(itemStack)) return true;//If its scaled, its dead, otherwise you can dupe

        return time >= (fishCaughtTime + TimeUtil.secondsToMinecraftTicks(fishData.timeOutsideOfWater));
    }

    public static String getFishUnlocalizedName(ItemStack itemStack)
    {
        if(!itemStack.hasTagCompound() || !itemStack.getTagCompound().hasKey("FishId"))  return null;

        //return String.format("%s:%d", itemStack.getItem().getRegistryName().toString(), itemStack.getItemDamage());
        return String.format("%s:%d", itemStack.getItem().getRegistryName().toString(), itemStack.getMetadata());
    }

    public static String getFishCustomLangKey(ItemStack itemStack){
        if(!itemStack.hasTagCompound() || !itemStack.getTagCompound().hasKey("FishId"))  return null;

        return String.format("%s%s:%d%s", "item.fmb.", itemStack.getItem().getRegistryName().toString(), itemStack.getMetadata(), ".name");
    }
}
