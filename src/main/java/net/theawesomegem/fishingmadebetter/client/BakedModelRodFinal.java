package net.theawesomegem.fishingmadebetter.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.theawesomegem.fishingmadebetter.common.item.ItemManager;
import net.theawesomegem.fishingmadebetter.common.item.attachment.bobber.ItemBobber;
import net.theawesomegem.fishingmadebetter.common.item.attachment.hook.ItemHook;
import net.theawesomegem.fishingmadebetter.common.item.attachment.reel.ItemReel;
import net.theawesomegem.fishingmadebetter.common.item.fishingrod.ItemBetterFishingRod;

public class BakedModelRodFinal implements IBakedModel {

	private final IBakedModel modelMain;
	private final boolean cast;
	private final IBakedModel[][] attachmentModels;
	private ItemStack itemStack;
	
	public BakedModelRodFinal(IBakedModel modelMain, boolean cast, IBakedModel[][] attachmentModels) {
		this.modelMain = modelMain;
		this.cast = cast;
		this.attachmentModels = attachmentModels;
		this.itemStack = null;
	}
	
	public BakedModelRodFinal setCurrentItemStack(ItemStack itemStack) {
		this.itemStack = itemStack;
		return this;
	}
	
	@Override
    public ItemOverrideList getOverrides()
    {
        return this.modelMain.getOverrides();
    }
    
    @Override
    public TextureAtlasSprite getParticleTexture()
    {
        return this.modelMain.getParticleTexture();
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public ItemCameraTransforms getItemCameraTransforms() {
      return this.modelMain.getItemCameraTransforms();
    }
    
    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
        Matrix4f matrix4f = this.modelMain.handlePerspective(cameraTransformType).getRight();
        return Pair.of(this, matrix4f);
    }
    
    @Override
    public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand) {
    	ArrayList<BakedQuad> arrayQuad = new ArrayList<>();
    	
    	List<BakedQuad> listQuad1 = this.modelMain.getQuads(state, side, rand);
    	
    	IBakedModel modelReel;
    	IBakedModel modelBobber;
    	IBakedModel modelHook;
    	
    	if(listQuad1 != null && !listQuad1.isEmpty()) {
    		arrayQuad.addAll(listQuad1);
    	}
    	
    	if(this.itemStack!=null) {
	    	List<BakedQuad> listQuad2;
	    	
	    	ItemReel reel = ItemBetterFishingRod.getReelItem(itemStack);
	    	ItemBobber bobber = ItemBetterFishingRod.getBobberItem(itemStack);
	    	ItemHook hook = ItemBetterFishingRod.getHookItem(itemStack);
	    	modelReel = this.attachmentModels[0][Arrays.asList(ItemManager.reelAttachmentList()).indexOf(reel)];
	    	modelBobber = this.attachmentModels[1][Arrays.asList(ItemManager.bobberAttachmentList()).indexOf(bobber)];
	    	modelHook = this.attachmentModels[2][Arrays.asList(ItemManager.hookAttachmentList()).indexOf(hook)];
	    	
	    	listQuad2 = modelReel.getQuads(state, side, rand);
	    	if(listQuad2!=null) {
	    		arrayQuad.addAll(listQuad2);
	    	}
	    	
	    	listQuad2 = modelBobber.getQuads(state, side, rand);
	    	if(listQuad2!=null && !cast) {//Dont render bobber or hook if its cast
	    		arrayQuad.addAll(listQuad2);
	    	}
	    	
	    	listQuad2 = modelHook.getQuads(state, side, rand);
	    	if(listQuad2!=null && !cast) {
	    		arrayQuad.addAll(listQuad2);
	    	}
    	}
    	
    	return arrayQuad;
    }
    
    @Override
    public boolean isAmbientOcclusion()
    {
        return this.modelMain.isAmbientOcclusion();
    }
    
    @Override
    public boolean isBuiltInRenderer()
    {
        return this.modelMain.isBuiltInRenderer();
    }
    
    @Override
    public boolean isGui3d()
    {
        return this.modelMain.isGui3d();
    }
}
