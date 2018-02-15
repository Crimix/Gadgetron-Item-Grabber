package com.black_dog20.itemgrabber.item;

import java.util.List;









import net.minecraft.client.renderer.block.model.ModelResourceLocation;
//import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import com.black_dog20.itemgrabber.client.render.IItemModelRegister;
import com.black_dog20.itemgrabber.creativetab.CreativeTabMOT;
import com.black_dog20.itemgrabber.reference.Reference;

import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;
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
		this.setCreativeTab(CreativeTabMOT.MOT_TAB);

	}
	
	
    @SideOnly(Side.CLIENT)
    @Override
    public void initModel() {
        ModelLoader.setCustomModelResourceLocation(this, 0, new ModelResourceLocation(getRegistryName(), "inventory"));
    }

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack item, EntityPlayer player, List<String> list, boolean bool, String text) {
		super.addInformation(item, player, list, bool);
		list.add(text);
	}

}
