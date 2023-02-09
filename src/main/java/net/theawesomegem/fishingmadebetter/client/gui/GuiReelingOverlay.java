package net.theawesomegem.fishingmadebetter.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.theawesomegem.fishingmadebetter.ModInfo;
import net.theawesomegem.fishingmadebetter.client.KeybindManager;
import net.theawesomegem.fishingmadebetter.common.capability.fishing.FishingCapabilityProvider;
import net.theawesomegem.fishingmadebetter.common.capability.fishing.IFishingData;
import net.theawesomegem.fishingmadebetter.common.configuration.ConfigurationManager;

/**
 * Created by TheAwesomeGem on 12/29/2017.
 */
public class GuiReelingOverlay extends Gui {
    private static final ResourceLocation textureOutline = new ResourceLocation(ModInfo.MOD_ID, "textures/gui/reeling_hud_outline.png");
    private static final ResourceLocation textureTarget = new ResourceLocation(ModInfo.MOD_ID, "textures/gui/reeling_hud_target.png");
    private static final ResourceLocation texturePosition = new ResourceLocation(ModInfo.MOD_ID, "textures/gui/reeling_hud_position.png");
    
    private static final ResourceLocation textureUnderOverlay = new ResourceLocation(ModInfo.MOD_ID, "textures/gui/reeling_hud_underoverlay.png");
    private static final ResourceLocation textureFullsize = new ResourceLocation(ModInfo.MOD_ID, "textures/gui/reeling_hud_fullsize.png");
    private static final ResourceLocation textureBiome = new ResourceLocation(ModInfo.MOD_ID, "textures/gui/reeling_hud_biome.png");

    private Minecraft mc;

    /*
    private final int barWidth = 105;
    private final int barHeight = 18;
    */
    private final int backgroundBarWidth = 128;
    private final int backgroundBarHeight = 24;
    private final int outlineBarWidth = backgroundBarWidth + 6;
    private final int outlineBarHeight = backgroundBarHeight + 6;

    private int fontColor = 0xFFFFFF;
    private FontRenderer fontRenderer;

    public GuiReelingOverlay(Minecraft minecraft) {
        this.mc = minecraft;
        this.fontRenderer = minecraft.fontRenderer;
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent.Post e) {
        if(e.isCanceled() || e.getType() != ElementType.ALL || !ConfigurationManager.client.reelingHud) return;
        
        EntityPlayer player = this.mc.player;

        if(player == null || !player.isEntityAlive()) return;
        
        IFishingData fishingData = player.getCapability(FishingCapabilityProvider.FISHING_DATA_CAP, null);

        if(fishingData == null || !fishingData.isFishing()) return;
        
        int scaledWidth = e.getResolution().getScaledWidth();
        int scaledHeight = e.getResolution().getScaledHeight();

        double distance = (double)(fishingData.getFishDeepLevel() - fishingData.getFishDistance())/10;
        
        fontColor = getIntFromColor(fishingData.getLineBreak());
        //fontRenderer.drawStringWithShadow(I18n.format("fishingmadebetter.reelingoverlay.distance") + String.format(": %sm", distance), getBarPosX(scaledWidth) + (outlineBarWidth * 0.5f) - 30, getBarPosY(scaledHeight) + outlineBarHeight + 2, fontColor);
        String txtDistance = I18n.format("fishingmadebetter.reelingoverlay.distance") + String.format(": %sm", distance);
        fontRenderer.drawStringWithShadow( txtDistance, getBarPosX(scaledWidth) + (outlineBarWidth * 0.5f) - (fontRenderer.getStringWidth(txtDistance) * 0.5f), getBarPosY(scaledHeight) + outlineBarHeight + 2, fontColor);

        // Trying to emulate the situation when a player doesn't know how to play, so they just stare at the fish swimming further in the distance.
        // (Show "Press [LEFT] / [RIGHT]" when fish is out of reel rectangle and the reel is on its break point, so experienced players don't see this message too often)
        if((Math.abs(fishingData.getReelTarget() - fishingData.getReelAmount()) > (fishingData.getErrorVariance() + 10)) && fishingData.getLineBreak() > 0 ) {
            String txtInstructions = I18n.format("fishingmadebetter.reelingoverlay.move_with", KeybindManager.reelIn.getDisplayName(), KeybindManager.reelOut.getDisplayName());
            fontRenderer.drawStringWithShadow(txtInstructions, getBarPosX(scaledWidth) + (outlineBarWidth * 0.5f) - (fontRenderer.getStringWidth(txtInstructions) * 0.5f), getBarPosY(scaledHeight) + outlineBarHeight + 12, fontColor);
        }
        
        int posX = getBarPosX(scaledWidth);
        int posY = getBarPosY(scaledHeight);

        posX += ConfigurationManager.client.reelingMeterXOffset;
        posY += ConfigurationManager.client.reelingMeterYOffset;
        
        Integer[] minigameBackground = fishingData.getMinigameBackground();
        
        beginRenderingTransparency();
        {
        	mc.renderEngine.bindTexture(textureOutline);
            renderOutlineBar(posX, posY);
            
            if(minigameBackground[0] == 1) {
            	mc.renderEngine.bindTexture(textureUnderOverlay);
                renderBackgroundBar(posX, posY, 0, (minigameBackground[3] == 0 ? 7 : minigameBackground[3]));//liquid
                
                mc.renderEngine.bindTexture(textureFullsize);//Only end
                renderBackgroundBar(posX, posY, 0, 0);
            }
            else if(minigameBackground[0] == -1) {
            	mc.renderEngine.bindTexture(textureUnderOverlay);
                renderBackgroundBar(posX, posY, 0, minigameBackground[3]);//liquid
                
                mc.renderEngine.bindTexture(textureFullsize);//Only nether
                renderBackgroundBar(posX, posY, 0, 1);
            }
            else if(minigameBackground[1] == 1) {
            	mc.renderEngine.bindTexture(textureUnderOverlay);
                renderBackgroundBar(posX, posY, 0, (minigameBackground[3] == 0 ? 9 : minigameBackground[3]));//liquid
                
                mc.renderEngine.bindTexture(textureFullsize);//Only underground over liquid
                renderBackgroundBar(posX, posY, 0, 2);
            }
            else {
            	if(minigameBackground[3] == 0) {
            		mc.renderEngine.bindTexture(textureUnderOverlay);
                    renderBackgroundBar(posX, posY, 1, minigameBackground[4]);//Liquid, offset for biome
            	}
            	else {
                	mc.renderEngine.bindTexture(textureUnderOverlay);
                    renderBackgroundBar(posX, posY, 0, minigameBackground[3]);//Liquid, offset for biome
            	}
                
                mc.renderEngine.bindTexture(textureUnderOverlay);
                renderBackgroundBar(posX, posY, 0, minigameBackground[2]+3);//Offset for day/night underlay

                mc.renderEngine.bindTexture(textureBiome);//Biome
                renderBackgroundBar(posX, posY, 0, minigameBackground[4]);
                    
                mc.renderEngine.bindTexture(textureUnderOverlay);//Offset for day/night overlay
                renderBackgroundBar(posX, posY, 0, minigameBackground[2]+5);
            }
            
            mc.renderEngine.bindTexture(textureTarget);
            renderReelingTarget(fishingData, posX, posY);
            mc.renderEngine.bindTexture(texturePosition);
            renderReelingPosition(fishingData, posX, posY);
        }
        endRenderingTransparency();
    }
    
    public int getIntFromColor(int input){
        int R = (int)Math.min(255, (double)input*8.5);
        int G = (int)Math.min(255, 510-(double)input*8.5);
        int B = 0;

        R = (R << 16) & 0x00FF0000;
        G = (G << 8) & 0x0000FF00;
        B = B & 0x000000FF;

        return 0xFF000000 | R | G | B;
    }

    private void renderOutlineBar(int posX, int posY) {
    	drawTexturedModalRect(posX-3, posY-3, 0, 0, outlineBarWidth, outlineBarHeight);
    }
    
    private void renderBackgroundBar(int posX, int posY, int indexX, int indexY) {
    	drawTexturedModalRect(posX, posY, backgroundBarWidth*indexX, backgroundBarHeight*indexY, backgroundBarWidth, backgroundBarHeight);
    }
    
    private void renderReelingPosition(IFishingData fishingData, int posX, int posY) {
        int reelAmount = fishingData.getReelAmount()/10;
        int variance = fishingData.getErrorVariance()/10;
        int width = variance*2;
        
        drawModalRectWithCustomSizedTexture(posX+16+reelAmount-variance, posY+13, 0, 0, 2, 8, 8, 8);
        if(width-4>0) drawScaledCustomSizeModalRect(posX+16+reelAmount-variance+2, posY+13, 2, 0, 4, 8, width-4, 8, 8, 8);
        drawModalRectWithCustomSizedTexture(posX+16+reelAmount+variance-2, posY+13, 6, 0, 2, 8, 8, 8);
    }
    
    private void renderReelingTarget(IFishingData fishingData, int posX, int posY) {
    	int reelTarget = fishingData.getReelTarget()/10;
    	
    	drawModalRectWithCustomSizedTexture(posX+16+reelTarget-3, posY+14, 0, 0, 6, 6, 6, 6);
    }
    
    private void beginRenderingTransparency() {
        GlStateManager.pushMatrix();

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableLighting();

        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
    }

    private void endRenderingTransparency() {
        GlStateManager.popMatrix();
    }

    private int getBarPosX(int scaledWidth) {
        //Bottom Right
        //int posX = scaledWidth - barWidth;
        //int posY = scaledHeight - barHeight;

        switch (ConfigurationManager.client.reelingHubPos) {
            case TOP_LEFT:
            case BOTTOM_LEFT:
                return 0;
            case TOP_RIGHT:
            case BOTTOM_RIGHT:
                return (scaledWidth - backgroundBarWidth);
            case TOP_CENTER:
            case BOTTOM_CENTER:
                return ((scaledWidth / 2) - (backgroundBarWidth / 2));
        }

        return 0;
    }

    private int getBarPosY(int scaledHeight) {
        switch (ConfigurationManager.client.reelingHubPos) {
            case TOP_LEFT:
            case TOP_RIGHT:
            case TOP_CENTER:
                return 0;
            case BOTTOM_LEFT:
            case BOTTOM_RIGHT:
            case BOTTOM_CENTER:
                return scaledHeight - backgroundBarHeight;
        }

        return 0;
    }
}
