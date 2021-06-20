package net.theawesomegem.fishingmadebetter;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.theawesomegem.fishingmadebetter.common.configuration.CustomFishConfigurationHandler;
import net.theawesomegem.fishingmadebetter.common.data.FishData;
import net.theawesomegem.fishingmadebetter.util.ItemStackUtil;
import net.theawesomegem.fishingmadebetter.util.TimeUtil;

import java.util.List;

public class BetterFishUtil {
    public static String getFishId(ItemStack itemStack) {
        if(!itemStack.hasTagCompound()){
            return null;
        }

        if(!itemStack.getTagCompound().hasKey("FishId")){
            return null;
        }

        return itemStack.getTagCompound().getString("FishId");
    }

    public static int getFishWeight(ItemStack itemStack) {
        if(!itemStack.hasTagCompound()){
            return 1;
        }

        if(!itemStack.getTagCompound().hasKey("FishWeight")){
            return 1;
        }

        return itemStack.getTagCompound().getInteger("FishWeight");
    }

    public static long getFishCaughtTime(ItemStack itemStack) {
        if(!itemStack.hasTagCompound()){
            return 0;
        }

        if(!itemStack.getTagCompound().hasKey("FishCaughtTime")){
            return 0;
        }

        return itemStack.getTagCompound().getLong("FishCaughtTime");
    }

    public static void setFishCaughtTime(ItemStack itemStack, int time) {
        NBTTagCompound tagCompound = itemStack.getTagCompound();

        if(!itemStack.hasTagCompound()){
            tagCompound = new NBTTagCompound();
        }

        tagCompound.setLong("FishCaughtTime", time);

        itemStack.setTagCompound(tagCompound);
    }

    public static boolean doesFishHasScale(ItemStack itemStack) {
        if(!itemStack.hasTagCompound()){
            return false;
        }

        if(!itemStack.getTagCompound().hasKey("FishScale")){
            return false;
        }

        return itemStack.getTagCompound().getBoolean("FishScale");
    }

    public static void setFishHasScale(ItemStack itemStack, boolean value) {
        NBTTagCompound tagCompound = itemStack.getTagCompound();

        if(!itemStack.hasTagCompound()){
            tagCompound = new NBTTagCompound();
        }

        tagCompound.setBoolean("FishScale", value);

        itemStack.setTagCompound(tagCompound);

        List<String> loreList =  ItemStackUtil.getToolTip(itemStack);

        if(loreList.isEmpty()){
            return;
        }

        if(loreList.size() < 2){
            return;
        }

        loreList.set(1, String.format("Scale: %s", value ? "Attached" : "Detached"));

        ItemStackUtil.appendToolTip(itemStack, loreList);
    }

    public static boolean isBetterFish(ItemStack itemStack){
        String fishId = getFishId(itemStack);

        return fishId != null && fishId.length() > 0;
    }

    public static boolean isDead(ItemStack itemStack, long time) {
        String fishId = BetterFishUtil.getFishId(itemStack);
        FishData fishData = CustomFishConfigurationHandler.fishDataMap.get(fishId);

        long fishCaughtTime = BetterFishUtil.getFishCaughtTime(itemStack);

        if (fishCaughtTime == 0) {
            return false;
        }

        return time >= (fishCaughtTime + TimeUtil.secondsToMinecraftTicks(fishData.timeOutsideOfWater));
    }
}
