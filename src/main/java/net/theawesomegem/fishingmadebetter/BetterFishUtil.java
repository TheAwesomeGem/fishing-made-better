package net.theawesomegem.fishingmadebetter;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.theawesomegem.fishingmadebetter.common.configuration.CustomConfigurationHandler;
import net.theawesomegem.fishingmadebetter.common.data.FishData;
import net.theawesomegem.fishingmadebetter.common.item.ItemFishBucket;
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
            //List<String> tooltipList = new ArrayList<>();
            //tooltipList.add(String.format("Weight: %d", BetterFishUtil.getFishWeight(itemStack)));
            //tooltipList.add(String.format("Scale: %s", "Detached"));
            //tooltipList.add(TextFormatting.BLUE + "" + TextFormatting.BOLD + "Dead" + TextFormatting.RESET);
            //tooltipList.add("Dead"); // Removed text formatting because it's handled by onItemTooltip
            //itemStack = ItemStackUtil.appendToolTip(itemStack, tooltipList);

            BetterFishUtil.setFishCaughtTime(itemStack, 0);
        }
    }

    public static boolean isBetterFish(ItemStack itemStack) {
        String fishId = BetterFishUtil.getFishId(itemStack);

        if(itemStack.getItem() instanceof ItemFishBucket) return false;//No, a bucket of fish is not a fish itself
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

    // If itemStack is a BetterFish, then get its custom lang key
    public static String getFishCustomLangKey(ItemStack itemStack){
        if(!itemStack.hasTagCompound() || !itemStack.getTagCompound().hasKey("FishId"))  return null;

        return String.format("%s%s:%d%s", "item.fmb.", itemStack.getItem().getRegistryName().toString(), itemStack.getMetadata(), ".name");
    }

    // It looks for a fishId in the fishDataMap. If it exists, return its custom lang key
    public static String fishIdToCustomLangKey(String fishId){
        if(fishId == null) return null;
        if(fishId.isEmpty()) return fishId;

        String unformattedFishId; // For fishes that have their name as TextFormatting.RESET + fishId. Take away the formatting part.
        if(fishId.startsWith(TextFormatting.RESET.toString())) unformattedFishId=fishId.substring(2);
        else unformattedFishId=fishId;

        // Look up in the fishDataMap
        FishData fishData = CustomConfigurationHandler.fishDataMap.get(unformattedFishId);
        if(fishData == null) return unformattedFishId; // If it's not found return the pure fishId, without formatting.

        return String.format("%s%s:%d%s", "item.fmb.", fishData.itemId, fishData.itemMetaData, ".name");
    }

    // For bait fishes
    public static boolean isFish(String baitName){
        if(baitName == null) return false;
        return baitName.equals("aquaculture:fish") || baitName.equals("advanced-fishing:fish") || baitName.equals("minecraft:fish");
    }
    public static boolean isFish(ResourceLocation baitResource){
        if(baitResource == null) return false;
        return isFish(baitResource.toString());
    }

    // Just for readability on the rest of the code (Bait Bucket, Bait Box, Baited Fishing Rod)
    public static String getBaitLangKey(String baitId, int baitMetaData){
        if(baitId == null || baitId.isEmpty()) return null;
        Item item = Item.getByNameOrId(baitId);
        if(item == null) return null;

        if(isFish(baitId)) // If bait is a fish, get its custom lang key
            return String.format("%s%s:%d%s", "item.fmb.", baitId, baitMetaData, ".name");
        else { // Get its lang key, because server always sends its English display name
            ItemStack itemStack = new ItemStack(item, 1, baitMetaData);
            if(itemStack.isEmpty()) return null;
            return itemStack.getItem().getUnlocalizedNameInefficiently(itemStack) + ".name";
        }
    }

    public static TextFormatting getLiquidColor(FishData.FishingLiquid liquid){
        switch(liquid){
            case WATER: return TextFormatting.BLUE;
            case LAVA: return TextFormatting.RED;
            case VOID: return TextFormatting.LIGHT_PURPLE;
            default: return TextFormatting.WHITE;
        }
    }

    public static TextFormatting getPopulationColor(int population){
        if(population > 20) return TextFormatting.GREEN;
        if(population > 3) return TextFormatting.YELLOW;
        if(population > 1) return TextFormatting.GOLD;
        return TextFormatting.RED;
    }
}