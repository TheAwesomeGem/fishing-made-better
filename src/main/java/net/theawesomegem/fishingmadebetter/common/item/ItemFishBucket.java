package net.theawesomegem.fishingmadebetter.common.item;

import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.theawesomegem.fishingmadebetter.ModInfo;
import net.theawesomegem.fishingmadebetter.common.capability.world.ChunkCapabilityProvider;
import net.theawesomegem.fishingmadebetter.common.capability.world.IChunkFishingData;
import net.theawesomegem.fishingmadebetter.common.capability.world.PopulationData;
import net.theawesomegem.fishingmadebetter.common.configuration.CustomConfigurationHandler;
import net.theawesomegem.fishingmadebetter.common.data.FishData;
import net.theawesomegem.fishingmadebetter.common.registry.FMBCreativeTab;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

public class ItemFishBucket extends Item {

    public ItemFishBucket() {
        super();

        this.maxStackSize = 1;
        this.setCreativeTab(FMBCreativeTab.instance);
        this.setRegistryName("fish_bucket");
        this.setTranslationKey(ModInfo.MOD_ID + ".fish_bucket");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        if(raytraceresult == null || raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) return new ActionResult<>(EnumActionResult.PASS, itemstack);

        if(worldIn.isRemote) return new ActionResult<>(EnumActionResult.FAIL, itemstack);
        
        String fishId = getFishId(itemstack);
        if(fishId == null) return new ActionResult<>(EnumActionResult.FAIL, itemstack);
        FishData fishData = CustomConfigurationHandler.fishDataMap.get(fishId);
        if(fishData == null) return new ActionResult<>(EnumActionResult.FAIL, itemstack);
        
        BlockPos blockpos = raytraceresult.getBlockPos();
        IChunkFishingData chunkFishingData = getChunkFishingData(worldIn.getChunk(blockpos));
        if(chunkFishingData == null) return new ActionResult<>(EnumActionResult.FAIL, itemstack);
        
        IBlockState blockState = worldIn.getBlockState(blockpos);
        Material material = blockState.getMaterial();
        if(material != MaterialLiquid.WATER) {
        	playerIn.sendMessage(new TextComponentTranslation("notif.fishingmadebetter.fish_bucket.only_water"));
        	return new ActionResult<>(EnumActionResult.FAIL, itemstack);
        }

        int waterCount = 0;
    	for(BlockPos pos : BlockPos.getAllInBox(blockpos.getX()-2, blockpos.getY()-3, blockpos.getZ()-2, blockpos.getX()+2, blockpos.getY(), blockpos.getZ()+2)) {
    		Material mat = worldIn.getBlockState(pos).getMaterial();
    		if(mat == MaterialLiquid.WATER) waterCount++;
    		if(waterCount >= 25) break;
    	}
    	if(waterCount < 25) {
    		playerIn.sendMessage(new TextComponentTranslation("notif.fishingmadebetter.fish_bucket.small_water"));
    		return new ActionResult<>(EnumActionResult.FAIL, itemstack);
    	}
        
        if(fishData.minYLevel > blockpos.getY() || fishData.maxYLevel < blockpos.getY()) {
        	playerIn.sendMessage(new TextComponentTranslation("notif.fishingmadebetter.fish_bucket.wrong_altitude"));
    		return new ActionResult<>(EnumActionResult.FAIL, itemstack);
        }

        Map<String, PopulationData> fishMap = chunkFishingData.getRawFishMap();
        PopulationData populationData;
        if(!fishMap.containsKey(fishId)) {
            populationData = new PopulationData(fishId, 1, 0, worldIn.getTotalWorldTime());
            fishMap.put(fishId, populationData);
        } 
        else {
            populationData = fishMap.get(fishId);
            populationData.setQuantity(populationData.getQuantity() + 1);
        }
        chunkFishingData.setPopulationData(fishId, populationData, true);

        worldIn.playSound(playerIn, blockpos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
        playerIn.setHeldItem(handIn, new ItemStack(Items.BUCKET));

        return super.onItemRightClick(worldIn, playerIn, handIn);
    }

    @SideOnly(Side.CLIENT)
    public void registerItemModel(Item item) {
        ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

    public static ItemStack getItemStack(String fishId) {
        ItemStack itemStack = new ItemStack(ItemManager.FISH_BUCKET, 1);

        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setString("FishId", fishId);
        itemStack.setTagCompound(tagCompound);
        
        return itemStack;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack itemStack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
    	String fishId = getFishId(itemStack);
        //String fishId = getFishDisplayName(itemStack);
    	if(fishId==null) return;

        String fishLangKey=String.format("%s%s:%d%s", "item.fmb.", getFishRegistry(itemStack), getFishMetadata(itemStack), ".name");
        tooltip.add(TextFormatting.BLUE + I18n.format("item.fishingmadebetter.fish_bucket.tooltip") + ": " + TextFormatting.BOLD + I18n.format(fishLangKey) + TextFormatting.RESET);
    }

    @Nullable
    private IChunkFishingData getChunkFishingData(Chunk chunk) {
        return chunk.getCapability(ChunkCapabilityProvider.CHUNK_FISHING_DATA_CAP, null);
    }

    @Nullable
    public static String getFishId(ItemStack itemStack) {
        if(!itemStack.hasTagCompound()) return null;

        NBTTagCompound tagCompound = itemStack.getTagCompound();

        return tagCompound.hasKey("FishId") ? tagCompound.getString("FishId") : null;
    }

    // TO THE SHADOW REALM! - KameiB
    public static void setFishId(ItemStack itemStack, String fishId){
        if(!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());
        itemStack.getTagCompound().setString("FishId", fishId);
    }

    public static String getFishRegistry(ItemStack itemStack){
        if(!itemStack.hasTagCompound()) return null;

        NBTTagCompound tagCompound = itemStack.getTagCompound();

        return tagCompound.hasKey("FishRegistry") ? tagCompound.getString("FishRegistry") : null;
    }

    public static void setFishRegistry(ItemStack itemStack, String fishRegistry){
        if(!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());
        itemStack.getTagCompound().setString("FishRegistry", fishRegistry);
    }

    public static int getFishMetadata(ItemStack itemStack){
        if(!itemStack.hasTagCompound()) return 0;

        NBTTagCompound tagCompound = itemStack.getTagCompound();

        return tagCompound.hasKey("FishMetaData") ? tagCompound.getInteger("FishMetaData") : 0;
    }

    public static void setFishMetadata(ItemStack itemStack, int meta){
        if(!itemStack.hasTagCompound())  itemStack.setTagCompound(new NBTTagCompound());
        itemStack.getTagCompound().setInteger("FishMetaData", meta);
    }

    public static String getFishDisplayName(ItemStack itemStack){
        if(!itemStack.hasTagCompound()) return null;

        NBTTagCompound tagCompound = itemStack.getTagCompound();

        return tagCompound.hasKey("FishDisplayName") ? tagCompound.getString("FishDisplayName") : getFishId(itemStack);
    }

    public static void setFishDisplayName(ItemStack itemStack, String displayName){
        if(!itemStack.hasTagCompound()) itemStack.setTagCompound(new NBTTagCompound());
        itemStack.getTagCompound().setString("FishDisplayName", displayName);
    }

    public static void removeFish(ItemStack itemStack){
        if(!itemStack.hasTagCompound()) return;

        NBTTagCompound tagCompound = itemStack.getTagCompound();

        if(tagCompound.hasKey("FishId")) itemStack.getTagCompound().removeTag("FishId");
        if(tagCompound.hasKey("FishRegistry")) itemStack.getTagCompound().removeTag("FishRegistry");
        if(tagCompound.hasKey("FishMetaData")) itemStack.getTagCompound().removeTag("FishMetaData");
        if(tagCompound.hasKey("FishDisplayName")) itemStack.getTagCompound().removeTag("FishDisplayName");
    }
}
