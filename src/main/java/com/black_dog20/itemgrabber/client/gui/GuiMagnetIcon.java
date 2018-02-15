package com.black_dog20.itemgrabber.client.gui;

import org.lwjgl.opengl.GL11;

import com.black_dog20.itemgrabber.reference.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GuiMagnetIcon extends Gui{
	private Minecraft mc;
	
	public GuiMagnetIcon() {
		super();
		mc = Minecraft.getMinecraft();
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void onRender(RenderGameOverlayEvent.Post event) {
		if (event.isCanceled()) return;
		int width = event.getResolution().getScaledWidth();
        int height = event.getResolution().getScaledHeight();
        int xPos = width-20;
		int yPos = height-20;
        int side = 3;
        int offsetX = 5;
        int offsetY = 5;
        switch (side) {
		case 0:
			xPos = 0+offsetX;
			yPos = 0+offsetX;
			break;
		case 1:
			xPos = 0+offsetX;
			yPos = height-20-offsetY;
			break;
		case 2:
			xPos = width-20-offsetX;
			yPos = 0+offsetY;
			break;
		case 3:
			xPos = width-20-offsetX;
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
		mc.renderEngine.bindTexture(new ResourceLocation(Reference.MOD_ID, "textures/gui/magnetOff.png"));
		drawNonStandardTexturedRect(xPos,yPos,0,0,18,18,18,18);
		GlStateManager.popMatrix();
	}
	
	/**
	 * Draws textured rectangles of sizes other than 256x256
	 * @param x The x value of the top-left corner point on the screen where drawing to starts 
	 * @param y The y value of the top-left corner point on the screen where drawing to starts
	 * @param u The u (x) value of top-left corner point of the texture to start drawing from
	 * @param v The v (y) value of top-left corner point of the texture to start drawing from
	 * @param width The width of the rectangle to draw on screen
	 * @param height The height of the rectangle to draw on screen
	 * @param textureWidth The width of the whole texture
	 * @param textureHeight The height of the whole texture
	 */
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
