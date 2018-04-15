package com.black_dog20.itemgrabber.block;

import com.black_dog20.itemgrabber.reference.AntiType;
import com.black_dog20.itemgrabber.tileentity.TileEntityAntiMagnet;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAntiMagnet extends BlockBase {

    public static final AxisAlignedBB BOUNDS = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);


	private AntiType type;
	private int range;
    
    public BlockAntiMagnet (String name, AntiType type, int range) {

        super(Material.ROCK,name);
        this.setHardness(3.0F);
        this.setResistance(10f);
        this.setHarvestLevel("pickaxe", 1);
        this.setLightOpacity(0);
        this.type = type;
        this.range = range;
    }
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityAntiMagnet(type, range);
	}

    private boolean checkForIfItShouldDrop (World world, BlockPos pos, IBlockState state) {

        if (!this.canBlockStay(world, pos)) {

            this.dropBlockAsItem(world, pos, state, 0);
            world.setBlockToAir(pos);
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public AxisAlignedBB getBoundingBox (IBlockState state, IBlockAccess source, BlockPos pos) {

        return BOUNDS;
    }

    private boolean canBlockStay (World world, BlockPos pos) {

        return !(world.isAirBlock(pos.down()) || !world.isSideSolid(pos.down(), EnumFacing.UP));
    }

    @Override
    public boolean canPlaceBlockAt (World world, BlockPos pos) {

        return super.canPlaceBlockAt(world, pos) && this.canBlockStay(world, pos);
    }

    @Override
    public void neighborChanged (IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {

        this.checkForIfItShouldDrop(worldIn, pos, state);
    }

    @Override
    public AxisAlignedBB getCollisionBoundingBox (IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {

        return NULL_AABB;
    }

    @Override
    public boolean isFullCube (IBlockState state) {

        return false;
    }

    @Override
    public boolean isOpaqueCube (IBlockState state) {

        return false;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getBlockLayer () {

        return BlockRenderLayer.CUTOUT;
    }
}
