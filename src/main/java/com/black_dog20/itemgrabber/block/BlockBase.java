package com.black_dog20.itemgrabber.block;

import com.black_dog20.itemgrabber.client.render.IItemModelRegister;
import com.black_dog20.itemgrabber.client.render.ModelHandler;
import com.black_dog20.itemgrabber.creativetab.CreativeTabGTIG;
import com.black_dog20.itemgrabber.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class BlockBase extends Block implements IItemModelRegister{

	public BlockBase(Material materialIn, String name) {
		super(materialIn);
		setDefaultState(pickDefaultState());
		setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
		setUnlocalizedName(getRegistryName().toString());
		setCreativeTab(CreativeTabGTIG.TAB);
		setHardness(1.0F);
	}

	protected IBlockState pickDefaultState() {
		return blockState.getBaseState();
	}
	
	
	@Override
	public void initModel() {
		if(Item.getItemFromBlock(this) != Items.AIR){
			ModelHandler.registerBlockToState(this, 0, new ModelResourceLocation(this.getRegistryName(), "inventory"));
		}
	}

}
