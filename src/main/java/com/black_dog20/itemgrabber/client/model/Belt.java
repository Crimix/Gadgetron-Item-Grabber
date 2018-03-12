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
        this.textureWidth = 64;
        this.textureHeight = 64;
        this.right = new ModelRenderer(this, 20, 17);
        this.right.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.right.addBox(-10.0F, 0.0F, -5.0F, 1, 2, 8, 0.0F);
        this.front = new ModelRenderer(this, 3, 10);
        this.front.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.front.addBox(-10.0F, 0.0F, -6.0F, 18, 2, 1, 0.0F);
        this.back = new ModelRenderer(this, 5, 34);
        this.back.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.back.addBox(-10.0F, 0.0F, 3.0F, 18, 2, 1, 0.0F);
        this.left = new ModelRenderer(this, 1, 17);
        this.left.setRotationPoint(0.0F, 0.0F, 0.0F);
        this.left.addBox(7.0F, 0.0F, -5.0F, 1, 2, 8, 0.0F);
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) { 
        this.right.render(f5);
        this.front.render(f5);
        this.back.render(f5);
        this.left.render(f5);
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
