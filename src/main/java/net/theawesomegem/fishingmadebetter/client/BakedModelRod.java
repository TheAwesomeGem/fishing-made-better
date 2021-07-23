package net.theawesomegem.fishingmadebetter.client;

import java.util.Collections;
import java.util.List;

import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class BakedModelRod implements IBakedModel {
	
	private IBakedModel modelMain;
	private IBakedModel modelMainCast;
	private IBakedModel[][] attachmentModels;
	
	private ItemOverrideListRod overridesList;
	
	public BakedModelRod(IBakedModel modelMain, IBakedModel modelMainCast, IBakedModel[][] attachmentModels) {
		this.modelMain = modelMain;
		this.modelMainCast = modelMainCast;
		this.attachmentModels = attachmentModels;
		this.overridesList = new ItemOverrideListRod(this);
	}
	
	public IBakedModel getCastVariant() {
		return this.modelMainCast;
	}
	
	@Override
	public ItemOverrideList getOverrides() {
		return this.overridesList;
	}
	
    @Override
    public TextureAtlasSprite getParticleTexture()
    {
        return this.modelMain.getParticleTexture();
    }
    
    @Override
    public List<BakedQuad> getQuads(IBlockState state, EnumFacing side, long rand)
    {
        return this.modelMain.getQuads(state, side, rand);
    }
    
    @Override
    public boolean isAmbientOcclusion()
    {
        return this.modelMain.isAmbientOcclusion();
    }
    
    @Override
    public boolean isBuiltInRenderer()
    {
    	return false;
    }
    
    @Override
    public boolean isGui3d()
    {
        return this.modelMain.isGui3d();
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
    
	private static class ItemOverrideListRod extends ItemOverrideList {
		private BakedModelRod modelRod;
		
		public ItemOverrideListRod(BakedModelRod modelRod) {
			super(Collections.emptyList());
			this.modelRod = modelRod;
		}
		
		@Override
		public IBakedModel handleItemState(IBakedModel model, ItemStack stack, World world, EntityLivingBase entity) {
			if(stack!=null && entity!= null && entity instanceof EntityPlayer) {
				boolean flag = entity.getHeldItemMainhand() == stack;
                boolean flag1 = entity.getHeldItemOffhand() == stack;

                if (entity.getHeldItemMainhand().getItem() instanceof ItemFishingRod)
                {
                    flag1 = false;
                }

                if((flag || flag1) && entity instanceof EntityPlayer && ((EntityPlayer)entity).fishEntity != null) {
					return new BakedModelRodFinal(this.modelRod.getCastVariant(), true, modelRod.attachmentModels).setCurrentItemStack(stack);
				}
			}
			return new BakedModelRodFinal(model, false, modelRod.attachmentModels).setCurrentItemStack(stack);
		}
	}
}
