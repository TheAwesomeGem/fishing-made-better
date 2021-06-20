package net.theawesomegem.fishingmadebetter.common.item.fishingrod;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.theawesomegem.fishingmadebetter.ModInfo;
import net.theawesomegem.fishingmadebetter.common.configuration.CustomFishConfigurationHandler;
import net.theawesomegem.fishingmadebetter.common.data.FishData;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

/**
 * Created by TheAwesomeGem on 12/30/2017.
 */
public abstract class ItemBetterFishingRod extends ItemFishingRod {
    public enum ReelLength {
        Normal(1),
        Long(2);

        private final int length;

        ReelLength(int length) {
            this.length = length;
        }

        public int getLength() {
            return length;
        }

        public static ReelLength fromLength(int length) {
            switch (length) {
                case 1:
                    return ReelLength.Normal;
                case 2:
                    return ReelLength.Long;
            }

            return null;
        }
    }

    protected final int reelRange;
    protected final int tuggingAmount;
    protected final int dragSpeed;

    public ItemBetterFishingRod(String name, int reelRange, int tuggingAmount, int dragSpeed) {
        super();

        this.setRegistryName(name);
        this.setUnlocalizedName(ModInfo.MOD_ID + "." + name);

        this.reelRange = reelRange;
        this.tuggingAmount = tuggingAmount;
        this.dragSpeed = dragSpeed;
    }

    public int getReelRange() {
        return reelRange;
    }

    public int getTuggingAmount() {
        return tuggingAmount;
    }

    public int getDragSpeed(){
        return dragSpeed;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        ActionResult<ItemStack> result = super.onItemRightClick(worldIn, playerIn, handIn);

        if (playerIn.fishEntity != null) {
            calculateVelocity(playerIn.fishEntity, reelRange, 1.0f);
        }

        return result;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add("Reel Length: " + ReelLength.fromLength(reelRange));
        tooltip.add("Tugging Amount: " + tuggingAmount);
        tooltip.add("Drag Speed: " + dragSpeed);

        String baitDisplayName = getBaitDisplayName(stack);

        tooltip.add("Bait: " +  ((baitDisplayName != null && baitDisplayName.length() > 0) ? baitDisplayName : "None"));
    }

    @SideOnly(Side.CLIENT)
    public void registerItemModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    private void calculateVelocity(EntityFishHook hook, float velocity, float modifier) {
        double xMotion = hook.motionX;
        double yMotion = hook.motionY;
        double zMotion = hook.motionZ;
        Random rand = hook.world.rand;

        float f2 = MathHelper.sqrt(xMotion * xMotion + yMotion * yMotion + zMotion * zMotion);

        xMotion /= f2;
        yMotion /= f2;
        zMotion /= f2;
        xMotion += rand.nextGaussian() * 0.007499999832361937D * modifier;
        yMotion += rand.nextGaussian() * 0.007499999832361937D * modifier;
        zMotion += rand.nextGaussian() * 0.007499999832361937D * modifier;
        xMotion *= velocity;
        yMotion *= velocity;
        zMotion *= velocity;

        hook.motionX = xMotion;
        hook.motionY = yMotion;
        hook.motionZ = zMotion;

        float f3 = MathHelper.sqrt(xMotion * xMotion + zMotion * zMotion);
        hook.prevRotationYaw = hook.rotationYaw = (float) (Math.atan2(xMotion, zMotion) * 180.0D / Math.PI);
        hook.prevRotationPitch = hook.rotationPitch = (float) (Math.atan2(yMotion, f3) * 180.0D / Math.PI);
    }

    public static String getBaitItem(ItemStack itemStack) {
        return (hasBait(itemStack) ? itemStack.getTagCompound().getString("BaitItem") : null);
    }

    public static void setBaitItem(ItemStack itemStack, String bait) {
        if (!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound());
        }

        itemStack.getTagCompound().setString("BaitItem", bait);
    }

    public static void removeBait(ItemStack itemStack){
        if(!itemStack.hasTagCompound()){
            return;
        }

        itemStack.getTagCompound().removeTag("BaitItem");
        itemStack.getTagCompound().removeTag("BaitMetadata");
        itemStack.getTagCompound().removeTag("BaitDisplayName");
    }

    public static int getBaitMetadata(ItemStack itemStack) {
        return (hasBait(itemStack) ? itemStack.getTagCompound().getInteger("BaitMetadata") : 0);
    }

    public static void setBaitMetadata(ItemStack itemStack, int metadata) {
        if (!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound());
        }

        itemStack.getTagCompound().setInteger("BaitMetadata", metadata);
    }

    public static String getBaitDisplayName(ItemStack itemStack) {
        return (hasBait(itemStack) ? itemStack.getTagCompound().getString("BaitDisplayName") : null);
    }

    public static void setBaitDisplayName(ItemStack itemStack, String baitDisplayName) {
        if (!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound());
        }

        itemStack.getTagCompound().setString("BaitDisplayName", baitDisplayName);
    }

    public static boolean hasBait(ItemStack itemStack) {
        return itemStack.hasTagCompound() && itemStack.getTagCompound().hasKey("BaitItem");
    }
    public static boolean isBaitForFish(ItemStack itemStack, String fishId) {
        if(!hasBait(itemStack)){
            return false;
        }

        FishData fishData = CustomFishConfigurationHandler.fishDataMap.get(fishId);

        if(fishData == null){
            return false;
        }

        String baitItem = getBaitItem(itemStack);

        return fishData.baitItemMap.containsKey(baitItem) && fishData.baitItemMap.get(baitItem) == getBaitMetadata(itemStack);
    }
}
