package com.black_dog20.itemgrabber.client.render;

import org.lwjgl.opengl.GL11;

import com.black_dog20.itemgrabber.client.model.Belt;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class BeltRender implements LayerRenderer<EntityPlayer>{

	@Override
	public void doRenderLayer(EntityPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		Belt model = new Belt();
		float Myscale = 1F;
		Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("mot:textures/models/belt.png"));
		if(player.isSneaking()){
			GlStateManager.translate(0F, 0.2F, 0F);
			GlStateManager.rotate(90F / (float) Math.PI, 1.0F, 0.0F, 0.0F);
		}
		GlStateManager.scale(Myscale, Myscale, Myscale);
		model.render(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		//player.inventory.hasItemStack(itemStackIn)
		
	}

	@Override
	public boolean shouldCombineTextures() {
		// TODO Auto-generated method stub
		return false;
	}

}
