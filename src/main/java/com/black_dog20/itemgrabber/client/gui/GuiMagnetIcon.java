package com.black_dog20.itemgrabber.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.black_dog20.itemgrabber.capability.IMagnetHandler;
import com.black_dog20.itemgrabber.capability.MagnetHandler;
import com.black_dog20.itemgrabber.config.ModConfig;
import com.black_dog20.itemgrabber.reference.Reference;
import com.black_dog20.itemgrabber.utility.MagnetHelper;

public class GuiMagnetIcon extends Gui{
	private Minecraft mc;
	
	public GuiMagnetIcon() {
		super();
		mc = Minecraft.getMinecraft();
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRender(RenderGameOverlayEvent.Post event) {
		if (event.isCanceled() || event.getType() != ElementType.ALL || ModConfig.client.iconHUDPos.hide || mc.currentScreen instanceof GuiChat) return;
		int width = event.getResolution().getScaledWidth();
        int height = event.getResolution().getScaledHeight();
        int xPos = 0;
		int yPos = 0;
        int offsetX = ModConfig.client.iconHUDPos.x;
        int offsetY = ModConfig.client.iconHUDPos.y;
        switch (ModConfig.client.iconHUDPos.pos) {
		case Center_Left:
			xPos = 0+offsetX;
			yPos = (height/2)-20+offsetY;
			break;
		case Top_Left:
			xPos = 0+offsetX;
			yPos = 0+offsetX;
			break;
		case Bottom_Left:
			xPos = 0+offsetX;
			yPos = height-20-offsetY;
			break;
		case Center_Right:
			xPos = width-20-offsetX;
			yPos = (height/2)-20+offsetY;
			break;
		case Top_Right:
			xPos = width-20-offsetX;
			yPos = 0+offsetY;
			break;
		case Bottom_Right:
			xPos = width-20-offsetX;
			yPos = height-20-offsetY;
			break;
		case Center_Middle:
			xPos = (width/2)-25+offsetX;
			yPos = (height/2)-10+offsetY;
			break;
		case Top_Middle:
			xPos = (width/2)-20+offsetX;
			yPos = 0+offsetY;
			break;
		case Bottom_Middle:
			xPos = (width/2)-20+offsetX;
			yPos = height-20-offsetY;
			break;

		default:
			xPos = 0;
			yPos = 0;
			break;
		}
        
		GlStateManager.pushMatrix();
		GlStateManager.color(1F, 1F, 1F);
		GlStateManager.disableLighting();
		GlStateManager.enableAlpha();
		IMagnetHandler mh = MagnetHandler.instanceFor(mc.player);
		if(mh != null){
			if(mh.getHasMagnetOn() && mh.getSneakDeactivate() && mc.player.isSneaking() && (MagnetHelper.hasMagnetInInventory(mc.player) || mh.getHasBelt())){
				mc.renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/magnetsneak.png"));
			} else if(mh.getHasMagnetOn() && (MagnetHelper.hasMagnetInInventory(mc.player) || mh.getHasBelt())){
				mc.renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/magneton.png"));
			} else if((MagnetHelper.hasMagnetInInventory(mc.player) || mh.getHasBelt())){
				mc.renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/magnetoff.png"));
			} else {
				GlStateManager.popMatrix();
				return;
			}
		}
		drawNonStandardTexturedRect(xPos,yPos,0,0,18,18,18,18);
		GlStateManager.popMatrix();
	}
	
	
	protected void drawNonStandardTexturedRect(int x, int y, int u, int v, int width, int height, int textureWidth, int textureHeight) {
		double f = 1F / (double) textureWidth;
		double f1 = 1F / (double) textureHeight;
		VertexBuffer wr = Tessellator.getInstance().getBuffer();
		wr.begin(7, DefaultVertexFormats.POSITION_TEX);
		wr.pos(x,y + height, 0).tex( u * f, (v + height) * f1).endVertex();
		wr.pos(x + width, y + height, 0).tex((u + width) * f, (v + height) * f1).endVertex();
		wr.pos(x + width, y, 0).tex((u + width) * f, v * f1).endVertex();
		wr.pos(x, y, 0).tex(u * f, v * f1).endVertex();
		Tessellator.getInstance().draw();
	}
}
