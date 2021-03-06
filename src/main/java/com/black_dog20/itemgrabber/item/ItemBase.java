package com.black_dog20.itemgrabber.item;

import com.black_dog20.itemgrabber.client.render.IItemModelRegister;
import com.black_dog20.itemgrabber.creativetab.CreativeTabGTIG;
import com.black_dog20.itemgrabber.reference.Reference;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemBase extends Item implements IItemModelRegister{

	public ItemBase(String name) {

		this();
		this.setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
		this.setUnlocalizedName(this.getRegistryName().toString());
	}

	public ItemBase() {

		super();
		this.setCreativeTab(CreativeTabGTIG.TAB);

	}
	
	
    @SideOnly(Side.CLIENT)
    @Override
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

}
