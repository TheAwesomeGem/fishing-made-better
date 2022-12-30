package net.theawesomegem.fishingmadebetter.common.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.theawesomegem.fishingmadebetter.ModInfo;
import net.theawesomegem.fishingmadebetter.common.registry.FMBCreativeTab;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBaitBucket extends Item {

    public ItemBaitBucket() {
        super();

        this.maxStackSize = 1;
        this.setCreativeTab(FMBCreativeTab.instance);
        this.setRegistryName("bait_bucket");
        this.setTranslationKey(ModInfo.MOD_ID + ".bait_bucket");
    }

    @SideOnly(Side.CLIENT)
    public void registerItemModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    @Override
    public void addInformation(ItemStack itemStack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Automatically rebaits the fishing rod with contained bait when held in offhand after a bite.");

        String baitId = getBaitId(itemStack);
        if(baitId.isEmpty()) {
            tooltip.add(TextFormatting.BLUE + "Contains: " + TextFormatting.BOLD + "None" + TextFormatting.RESET);
        }
        else {
            tooltip.add(TextFormatting.BLUE + "Contains: " + TextFormatting.BOLD + getBaitCount(itemStack) + " " + getBaitDisplayName(itemStack) + TextFormatting.RESET);
        }
    }

    public static String getBaitId(ItemStack itemStack) {
        if(!itemStack.hasTagCompound()) return "";

        NBTTagCompound tagCompound = itemStack.getTagCompound();

        return tagCompound.getString("BaitId");
    }

    public static void setBaitId(ItemStack itemStack, String baitId) {
        if(!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());
        itemStack.getTagCompound().setString("BaitId", baitId);
    }

    public static int getBaitMetadata(ItemStack itemStack) {
        if(!itemStack.hasTagCompound()) return 0;

        NBTTagCompound tagCompound = itemStack.getTagCompound();
        return tagCompound.getInteger("BaitMetadata");
    }

    public static void setBaitMetadata(ItemStack itemStack, int meta) {
        if(!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());
        itemStack.getTagCompound().setInteger("BaitMetadata", meta);
    }

    public static String getBaitDisplayName(ItemStack itemStack) {
        if(!itemStack.hasTagCompound()) return "";

        NBTTagCompound tagCompound = itemStack.getTagCompound();

        return tagCompound.getString("BaitDisplayName");
    }

    public static void setBaitDisplayName(ItemStack itemStack, String displayName) {
        if(!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());
        itemStack.getTagCompound().setString("BaitDisplayName", displayName);
    }

    public static int getBaitCount(ItemStack itemStack) {
        if(!itemStack.hasTagCompound()) return 0;

        NBTTagCompound tagCompound = itemStack.getTagCompound();
        return tagCompound.getInteger("BaitCount");
    }

    public static void setBaitCount(ItemStack itemStack, int count) {
        if(!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());
        itemStack.getTagCompound().setInteger("BaitCount", count);
    }

    public static void removeBait(ItemStack itemStack) {
        if(!itemStack.hasTagCompound()) return;

        itemStack.getTagCompound().removeTag("BaitId");
        itemStack.getTagCompound().removeTag("BaitMetadata");
        itemStack.getTagCompound().removeTag("BaitDisplayName");
        itemStack.getTagCompound().removeTag("BaitCount");
    }
}