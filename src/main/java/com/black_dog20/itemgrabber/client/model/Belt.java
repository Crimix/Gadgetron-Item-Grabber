package com.black_dog20.itemgrabber.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**
 * Belt - black_dog20
 * Created using Tabula 7.0.0
 */
public class Belt extends ModelBase {
    public ModelRenderer back;
    public ModelRenderer right;
    public ModelRenderer front;
    public ModelRenderer left;

    public Belt() {
        this.textureWidth = 32;
        this.textureHeight = 32;
        this.back = new ModelRenderer(this, 5, 25);
        this.back.setRotationPoint(-5.0F, 9.0F, 2.0F);
        this.back.addBox(0.0F, 0.0F, 0.0F, 10, 1, 1, 0.0F);
        this.right = new ModelRenderer(this, 20, 17);
        this.right.setRotationPoint(-5.0F, 9.0F, -2.0F);
        this.right.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.left = new ModelRenderer(this, 1, 17);
        this.left.setRotationPoint(4.0F, 9.0F, -2.0F);
        this.left.addBox(0.0F, 0.0F, 0.0F, 1, 1, 4, 0.0F);
        this.front = new ModelRenderer(this, 3, 10);
        this.front.setRotationPoint(-5.0F, 9.0F, -3.0F);
        this.front.addBox(0.0F, 0.0F, 0.0F, 10, 1, 1, 0F);

    }

    @Override
    public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) { 
        this.left.render(scale);
        this.right.render(scale);
        this.back.render(scale);
        this.front.render(scale);
    }

    /**
     * This is a helper function from Tabula to set the rotation of model parts
     */
    public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
        modelRenderer.rotateAngleX = x;
        modelRenderer.rotateAngleY = y;
        modelRenderer.rotateAngleZ = z;
    }
}
