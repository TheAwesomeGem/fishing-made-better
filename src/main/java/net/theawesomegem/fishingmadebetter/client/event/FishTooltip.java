package net.theawesomegem.fishingmadebetter.client.event;

import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.theawesomegem.fishingmadebetter.BetterFishUtil;
import net.theawesomegem.fishingmadebetter.common.configuration.CustomConfigurationHandler;

import java.util.List;

public class FishTooltip {
    // For custom names, instead of using the "Name" NBT tag, use vanilla's "LocName". This allows us to rename an item with a translatable name.
    // About the fish details, this update no longer adds the "Lore" NBTTag.
    // Instead, it takes the raw information from the NBT tags, uses lang keys and adds that info into the tooltip.
    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onItemTooltip(ItemTooltipEvent event) {
        if(event.isCanceled() || !BetterFishUtil.isBetterFish(event.getItemStack())) return ;

        ItemStack itemStack = event.getItemStack();
        NBTTagCompound tagCompound = itemStack.getTagCompound();
        NBTTagCompound tagDisplay = tagCompound.hasKey("display", Constants.NBT.TAG_COMPOUND) ? tagCompound.getCompoundTag("display") : null;
        if(tagDisplay == null) return ;
        boolean hasLore = tagDisplay.hasKey("Lore", Constants.NBT.TAG_LIST); // Previous version fish

        short tooltipLine = 1;
        List<String> tooltip = event.getToolTip();
        if(tagCompound.hasKey("FishWeight")){
            if (hasLore)
                tooltip.set(tooltipLine, TextFormatting.GRAY + String.format("%s: %d", I18n.format("tooltip.fishingmadebetter.fish.weight"), BetterFishUtil.getFishWeight(itemStack)) + TextFormatting.RESET);
            else
                tooltip.add(tooltipLine, TextFormatting.GRAY + String.format("%s: %d", I18n.format("tooltip.fishingmadebetter.fish.weight"), BetterFishUtil.getFishWeight(itemStack)) + TextFormatting.RESET);
            tooltipLine++;
        }
        if (CustomConfigurationHandler.fishDataMap.get(BetterFishUtil.getFishId(itemStack)).allowScaling) {
            if (tagCompound.hasKey("FishScale")){
                if (hasLore)
                    tooltip.set(tooltipLine,TextFormatting.GRAY + String.format("%s: %s", I18n.format("tooltip.fishingmadebetter.fish.scale"), BetterFishUtil.doesFishHasScale(itemStack) ? (TextFormatting.BOLD + I18n.format("tooltip.fishingmadebetter.fish.scale_attached")) : I18n.format("tooltip.fishingmadebetter.fish.scale_detached")) + TextFormatting.RESET);
                else
                    tooltip.add(tooltipLine,TextFormatting.GRAY + String.format("%s: %s", I18n.format("tooltip.fishingmadebetter.fish.scale"), BetterFishUtil.doesFishHasScale(itemStack) ? (TextFormatting.BOLD + I18n.format("tooltip.fishingmadebetter.fish.scale_attached")) : I18n.format("tooltip.fishingmadebetter.fish.scale_detached")) + TextFormatting.RESET);
                tooltipLine++;
            }
        }
        if (tagCompound.hasKey("FishCaughtTime")){
            if (hasLore)
                tooltip.set(tooltipLine,TextFormatting.BLUE + "" + TextFormatting.BOLD + (BetterFishUtil.getFishCaughtTime(itemStack) == 0 ? I18n.format("tooltip.fishingmadebetter.fish.dead") : I18n.format("tooltip.fishingmadebetter.fish.alive")) + TextFormatting.RESET);
            else
                tooltip.add(tooltipLine,TextFormatting.BLUE + "" + TextFormatting.BOLD + (BetterFishUtil.getFishCaughtTime(itemStack) == 0 ? I18n.format("tooltip.fishingmadebetter.fish.dead") : I18n.format("tooltip.fishingmadebetter.fish.alive")) + TextFormatting.RESET);
        }
    }
}
