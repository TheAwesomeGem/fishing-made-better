package net.theawesomegem.fishingmadebetter.common.item;

import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
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
import net.theawesomegem.fishingmadebetter.common.configuration.CustomFishConfigurationHandler;
import net.theawesomegem.fishingmadebetter.common.data.FishData;
import net.theawesomegem.fishingmadebetter.util.ItemStackUtil;

import java.util.Collections;
import java.util.Map;

public class ItemFishBucket extends Item {
    //TODO: Add fish quantity in the future.

    public ItemFishBucket() {
        super();

        this.setRegistryName("fish_bucket");
        this.setUnlocalizedName(ModInfo.MOD_ID + ".fish_bucket");
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);
        ItemStack itemstack = playerIn.getHeldItem(handIn);

        if (raytraceresult == null) {
            return new ActionResult<>(EnumActionResult.PASS, itemstack);
        } else if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK) {
            return new ActionResult<>(EnumActionResult.PASS, itemstack);
        }

        BlockPos blockpos = raytraceresult.getBlockPos();
        IBlockState blockState = worldIn.getBlockState(blockpos);
        Material material = blockState.getMaterial();

        if(!(material instanceof MaterialLiquid)){
            return new ActionResult<>(EnumActionResult.FAIL, itemstack);
        }

        if (worldIn.isRemote) {
            return new ActionResult<>(EnumActionResult.FAIL, itemstack);
        }

        int waterVolume = 0;
        int startX = blockpos.getX() - 8;
        int startY = blockpos.getY() - 8;
        int startZ = blockpos.getZ() - 8;
        int endX = blockpos.getX() + 8;
        int endY = blockpos.getY() + 8;
        int endZ = blockpos.getZ() + 8;

        for(int x = startX; x <= endX; x++){
            for(int y = startY; y <= endY; y++){
                for(int z = startZ; z <= endZ; z++){
                    IBlockState otherBlockState = worldIn.getBlockState(new BlockPos(x, y, z));

                    if(otherBlockState.getMaterial() instanceof MaterialLiquid){
                        waterVolume++;
                    }
                }
            }
        }

        String fishId = getFishId(itemstack);

        if(fishId == null){
            return new ActionResult<>(EnumActionResult.FAIL, itemstack);
        }

        FishData fishData = CustomFishConfigurationHandler.fishDataMap.get(fishId);
        int fishSize = fishData.maxWeight * 2;

        if(waterVolume < fishSize){
            playerIn.sendMessage(new TextComponentString("The fish is too big for this water."));
            return new ActionResult<>(EnumActionResult.FAIL, itemstack);
        }

        IChunkFishingData chunkFishingData = getChunkFishingData(worldIn.getChunkFromBlockCoords(blockpos));

        if(chunkFishingData == null){
            return new ActionResult<>(EnumActionResult.FAIL, itemstack);
        }

        Map<String, PopulationData> fishMap = chunkFishingData.getRawFishMap();
        PopulationData populationData;

        if(!fishMap.containsKey(fishId)) {
            populationData = new PopulationData(fishId, 1, 0, worldIn.getTotalWorldTime());
            fishMap.put(fishId, populationData);
        } else {
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

        itemStack = ItemStackUtil.appendToolTip(itemStack, Collections.singleton(TextFormatting.BLUE + "" + TextFormatting.BOLD + fishId + TextFormatting.RESET));

        return itemStack;
    }

    public static String getFishId(ItemStack itemStack){
        if(!itemStack.hasTagCompound()){
            return null;
        }

        NBTTagCompound tagCompound = itemStack.getTagCompound();

        if(!tagCompound.hasKey("FishId")){
            return null;
        }

        return tagCompound.getString("FishId");
    }

    private IChunkFishingData getChunkFishingData(Chunk chunk) {
        return chunk.getCapability(ChunkCapabilityProvider.CHUNK_FISHING_DATA_CAP, null);
    }
}
