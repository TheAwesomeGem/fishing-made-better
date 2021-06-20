package net.theawesomegem.fishingmadebetter.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.theawesomegem.fishingmadebetter.ModInfo;
import net.theawesomegem.fishingmadebetter.common.capability.fishing.FishingCapabilityProvider;
import net.theawesomegem.fishingmadebetter.common.capability.fishing.IFishingData;
import net.theawesomegem.fishingmadebetter.common.configuration.ConfigurationManager;

/**
 * Created by TheAwesomeGem on 12/29/2017.
 */
public class GuiReelingOverlay extends Gui {
    private static final ResourceLocation texture = new ResourceLocation(ModInfo.MOD_ID, "textures/gui/reeling_hud.png");

    private Minecraft mc;

    private final int barWidth = 105;
    private final int barHeight = 18;

    private final int meterHeight = barHeight - 3;

    private final int fontColor = 0xFFFFFF;
    private FontRenderer fontRenderer;

    public GuiReelingOverlay(Minecraft minecraft) {
        this.mc = minecraft;
        this.fontRenderer = minecraft.fontRenderer;
    }

    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent e) {
        if (e.isCancelable() || e.getType() != ElementType.EXPERIENCE)
            return;

        if (!ConfigurationManager.reelingHud)
            return;

        EntityPlayer player = this.mc.player;

        if (player == null || !player.isEntityAlive())
            return;

        IFishingData fishingData = player.getCapability(FishingCapabilityProvider.FISHING_DATA_CAP, null);

        if (fishingData == null || !fishingData.isFishing())
            return;

        int scaledWidth = e.getResolution().getScaledWidth();
        int scaledHeight = e.getResolution().getScaledHeight();

        int distance = fishingData.getFishDeepLevel() - fishingData.getFishDistance();

        if(distance < 0)
            distance = 0;

        fontRenderer.drawStringWithShadow(String.format("Distance: %d", distance), getBarPosX(scaledWidth) + (barWidth * 0.5f) - 30, getBarPosY(scaledHeight) + 30, fontColor);

        mc.renderEngine.bindTexture(texture);

        int posX = getBarPosX(scaledWidth);
        int posY = getBarPosY(scaledHeight);

        posX += ConfigurationManager.reelingMeterXOffset;
        posY += ConfigurationManager.reelingMeterYOffset;

        beginRenderingTransparency();
        {
            renderReelingBar(posX, posY);
            renderReelingPosition(fishingData, posX, posY);
        }
        endRenderingTransparency();
    }

    private void renderReelingBar(int posX, int posY) {
        // Here we draw the background bar which contains a transparent section; note the new size
        drawTexturedModalRect(posX, posY, 0, 0, barWidth, barHeight);
    }

    private void renderReelingPosition(IFishingData fishingData, int posX, int posY) {
        int meterStatus = getMeterStatus(fishingData);

        drawTexturedModalRect(posX + meterStatus + 2, posY + 3, 0, barHeight, 1, meterHeight - 4);
    }

    private int getMeterStatus(IFishingData fishingData) {
        int reelTarget = fishingData.getReelTarget();
        int reelAmount = fishingData.getReelAmount();
        int reelDiff = reelTarget - reelAmount;
        reelDiff = Math.abs(reelDiff);

        int meterStatus = 1;

        if (reelAmount == reelTarget) {
            meterStatus = 50;
        } else if (reelAmount > reelTarget) {
            meterStatus = reelDiff + 50;
        } else if (reelAmount < reelTarget) {
            meterStatus = 50 - reelDiff;
        }

        if (meterStatus <= 0)
            meterStatus = 1;

        if (meterStatus > 100)
            meterStatus = 100;

        return meterStatus;
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

        switch (ConfigurationManager.reelingHubPos) {
            case TOP_LEFT:
            case BOTTOM_LEFT:
                return 0;
            case TOP_RIGHT:
            case BOTTOM_RIGHT:
                return (scaledWidth - barWidth);
            case TOP_CENTER:
            case BOTTOM_CENTER:
                return ((scaledWidth / 2) - (barWidth / 2));
        }

        return 0;
    }

    private int getBarPosY(int scaledHeight) {
        switch (ConfigurationManager.reelingHubPos) {
            case TOP_LEFT:
            case TOP_RIGHT:
            case TOP_CENTER:
                return 0;
            case BOTTOM_LEFT:
            case BOTTOM_RIGHT:
            case BOTTOM_CENTER:
                return scaledHeight - barHeight;
        }

        return 0;
    }
}
