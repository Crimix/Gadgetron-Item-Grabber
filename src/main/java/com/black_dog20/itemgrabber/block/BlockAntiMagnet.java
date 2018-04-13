package com.black_dog20.itemgrabber.block;

import com.black_dog20.itemgrabber.reference.AntiType;
import com.black_dog20.itemgrabber.tileentity.TileEntityAntiMagnet;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockAntiMagnet extends BlockBase{

	private AntiType type;
	private int range;
	
	public BlockAntiMagnet(String name, AntiType type, int range) {
		super(Material.WOOD, name);
		this.type = type;
		this.range = range;
	}
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityAntiMagnet(type, (range+1));
	}
	
	
}
