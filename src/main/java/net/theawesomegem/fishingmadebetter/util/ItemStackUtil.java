package net.theawesomegem.fishingmadebetter.util;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class ItemStackUtil {
    public static ItemStack appendToolTip(ItemStack stack, Iterable<String> tooltip) {
        NBTTagCompound tag = stack.hasTagCompound() ? stack.getTagCompound() : new NBTTagCompound();
        boolean needAppend = false;
        NBTTagCompound tag1;
        if (tag.hasKey("display", Constants.NBT.TAG_COMPOUND)) {
            tag1 = tag.getCompoundTag("display");
            if (tag.hasKey("Lore", Constants.NBT.TAG_LIST)) {
                needAppend = true;
            }
        } else {
            tag.setTag("display", new NBTTagCompound());
            tag1 = tag.getCompoundTag("display");
        }
        if (!needAppend) {
            tag1.setTag("Lore", new NBTTagList());
        }
        for (String s : tooltip) {
            tag1.getTagList("Lore", Constants.NBT.TAG_STRING).appendTag(new NBTTagString(TextFormatting.RESET + s));
        }
        tag.setTag("display", tag1);
        ItemStack output = stack.copy();
        output.setTagCompound(tag);
        return output;
    }

    public static List<String> getToolTip(ItemStack stack){
        List<String> loreList = new ArrayList<>();

        if(!stack.hasTagCompound()){
            return loreList;
        }

        NBTTagCompound mainTag = stack.getTagCompound();

        if(!mainTag.hasKey("display", Constants.NBT.TAG_COMPOUND)){
            return loreList;
        }

        NBTTagCompound displayTag = mainTag.getCompoundTag("display");

        if(!displayTag.hasKey("Lore", Constants.NBT.TAG_LIST)){
            return loreList;
        }

        NBTTagList loreTagList = displayTag.getTagList("Lore", Constants.NBT.TAG_STRING);

        for(int i = 0; i < loreTagList.tagCount(); i++){
            loreList.add(loreTagList.getStringTagAt(i));
        }

        return loreList;
    }
}
