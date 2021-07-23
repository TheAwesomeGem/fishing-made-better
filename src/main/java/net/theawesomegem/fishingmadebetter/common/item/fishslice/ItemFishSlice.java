package net.theawesomegem.fishingmadebetter.common.item.fishslice;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.theawesomegem.fishingmadebetter.ModInfo;
import net.theawesomegem.fishingmadebetter.common.registry.FMBCreativeTab;

/**
 * Created by TheAwesomeGem on 1/1/2018.
 */
public abstract class ItemFishSlice extends ItemFood {//TODO: maybe something with healing/saturation/texture per fish type
    public ItemFishSlice(String name, int healAmount, float saturation) {
        super(healAmount, saturation, false);

        this.setCreativeTab(FMBCreativeTab.instance);
        this.setRegistryName(name);
        this.setUnlocalizedName(ModInfo.MOD_ID + "." + name);
    }
    
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
    	String fishDisplayName = getFishDisplayName(stack);

        if(fishDisplayName != null && fishDisplayName.length() > 0) tooltip.add(String.format("Sliced %s", fishDisplayName));
    }
    
    @SideOnly(Side.CLIENT)
    public void registerItemModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(ModInfo.MOD_ID + ":fish_slice/" + getRegistryName().getResourcePath(), "inventory"));
    }

    public ItemStack getItemStack(String itemId, String displayName, int amount) {
        ItemStack itemStack = new ItemStack(this, amount);

        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("FishItemId", itemId);
        if(displayName != null) nbt.setString("FishDisplayName", displayName);
        itemStack.setTagCompound(nbt);

        return itemStack;
    }

    @Nullable
    private String getFishDisplayName(ItemStack itemStack) {
        if(!itemStack.hasTagCompound()) return null;

        if(!itemStack.getTagCompound().hasKey("FishDisplayName")) return null;

        return itemStack.getTagCompound().getString("FishDisplayName");
    }

    @Nullable
    private String getFishItemId(ItemStack itemStack) {
        if(!itemStack.hasTagCompound()) return null;

        if(!itemStack.getTagCompound().hasKey("FishItemId")) return null;

        return itemStack.getTagCompound().getString("FishItemId");
    }
}

