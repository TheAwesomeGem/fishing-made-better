package net.theawesomegem.fishingmadebetter.common.item;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.theawesomegem.fishingmadebetter.ModInfo;
import net.theawesomegem.fishingmadebetter.util.TextUtils;

/**
 * Created by TheAwesomeGem on 1/1/2018.
 */
public class ItemFishSlice extends ItemFood
{
    public ItemFishSlice()
    {
        super(0, 0, false);

        this.setRegistryName("fish_slice");
        this.setUnlocalizedName(ModInfo.MOD_ID + ".fish_slice");
    }

    @Override
    public String getUnlocalizedName(ItemStack stack)
    {
        String fishItemId = getFishItemId(stack);

        if(fishItemId == null || fishItemId.length() < 1)
            return super.getUnlocalizedName(stack);

        return ModInfo.MOD_ID + ".fish_slice." + fishItemId.toLowerCase();
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {
        String fishDisplayName = getFishDisplayName(stack);

        if(fishDisplayName != null && fishDisplayName.length() > 0) {
            return String.format("Raw %s Slice", fishDisplayName);
        }

        return super.getItemStackDisplayName(stack);
    }

    @SideOnly(Side.CLIENT)
    public void registerItemModel(Item item)
    {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    public ItemStack getItemStack(String itemId, String displayName, int amount)
    {
        ItemStack itemStack = new ItemStack(this, amount);

        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setString("FishItemId", itemId);
        if(displayName != null) {
            nbt.setString("FishDisplayName", displayName);
        }
        itemStack.setTagCompound(nbt);

        return itemStack;
    }

    private String getFishDisplayName(ItemStack itemStack)
    {
        if(!itemStack.hasTagCompound())
            return null;

        if(!itemStack.getTagCompound().hasKey("FishDisplayName"))
            return null;

        return itemStack.getTagCompound().getString("FishDisplayName");
    }

    private String getFishItemId(ItemStack itemStack)
    {
        if(!itemStack.hasTagCompound())
            return null;

        if(!itemStack.getTagCompound().hasKey("FishItemId"))
            return null;

        return itemStack.getTagCompound().getString("FishItemId");
    }

    @Override
    public int getHealAmount(ItemStack stack) {
        String fishId = getFishItemId(stack);

        if(TextUtils.isEmpty(fishId)){
            return super.getHealAmount(stack);
        }

        Item item = getByNameOrId(fishId);

        if(item instanceof ItemFood){
            return ((ItemFood)item).getHealAmount(ItemStack.EMPTY);
        }

        return super.getHealAmount(stack);
    }

    @Override
    public float getSaturationModifier(ItemStack stack) {
        String fishId = getFishItemId(stack);

        if(TextUtils.isEmpty(fishId)){
            return super.getSaturationModifier(stack);
        }


        Item item = getByNameOrId(fishId);

        if(item instanceof ItemFood){
            return ((ItemFood)item).getSaturationModifier(ItemStack.EMPTY);
        }

        return super.getSaturationModifier(stack);
    }
}

