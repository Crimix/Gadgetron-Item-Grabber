package com.black_dog20.itemgrabber.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

import com.black_dog20.itemgrabber.capability.IMagnetHandler;
import com.black_dog20.itemgrabber.capability.MagnetHandler;
import com.black_dog20.itemgrabber.client.model.Belt;

public class BeltRender implements LayerRenderer<EntityPlayer>{

	@Override
	public void doRenderLayer(EntityPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		IMagnetHandler mh = player.getCapability(MagnetHandler.CAP, null);
		if(mh != null && mh.getHasBelt()) {
			Belt model = new Belt();
			float Myscale = 1F / 2.2F;
			GlStateManager.pushMatrix();
			int tier = mh.getTier();
			if(mh.getHasMagnetOn())
				Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("gadgetronig:textures/models/beltt"+ tier + ".png"));
			else
				Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("gadgetronig:textures/models/beltt"+ tier + "_off.png"));
			if(player.isSneaking()){
				GlStateManager.translate(0.03F, 0.6F, 0.25F);
				GlStateManager.rotate(90F / (float) Math.PI, 1.0F, 0.0F, 0.0F);
			}else {
				GlStateManager.translate(0.03F, 0.5F, 0.032F);
			}
			GlStateManager.scale(Myscale, Myscale, Myscale);
			model.render(player, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
			GlStateManager.popMatrix();
		}
		
	}

	@Override
	public boolean shouldCombineTextures() {
		// TODO Auto-generated method stub
		return false;
	}

}
