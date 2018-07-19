package com.black_dog20.itemgrabber.block;

import java.util.List;

import com.black_dog20.itemgrabber.reference.AntiType;
import com.black_dog20.itemgrabber.tileentity.TileEntityAntiMagnet;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockAntiMagnet extends BlockBase {

    public static final AxisAlignedBB BOUNDS = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.0625D, 1.0D);

	private AntiType type;
    
    public BlockAntiMagnet (String name, AntiType type) {

        super(Material.ROCK,name);
        this.setHardness(3.0F);
        this.setResistance(10f);
        this.setHarvestLevel("pickaxe", 1);
        this.setLightOpacity(0);
        this.type = type;
    }
	
	@Override
	public boolean hasTileEntity(IBlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(World world, IBlockState state) {
		return new TileEntityAntiMagnet(type);
	}
	
	@Override
	public void addInformation(ItemStack stack, World player, List<String> tooltip, ITooltipFlag advanced) {
		super.addInformation(stack, player, tooltip, advanced);
		TextComponentTranslation range = new TextComponentTranslation("tooltip.gadgetronig:antiRange");
		TextComponentTranslation change = new TextComponentTranslation("tooltip.gadgetronig:antiRangeChange");
		tooltip.add(range.getFormattedText());
		tooltip.add(change.getFormattedText());
	}
	
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if(!worldIn.isRemote) {
			TileEntity te = worldIn.getTileEntity(pos);
			if(te instanceof TileEntityAntiMagnet) {
				TileEntityAntiMagnet t = (TileEntityAntiMagnet)te;
				if(playerIn.isSneaking()) {
					t.incrementMarker();
					TextComponentTranslation changed = new TextComponentTranslation("tooltip.gadgetronig:antiRangeChanged");
					playerIn.sendMessage(new TextComponentString(changed.getFormattedText() + " " + t.getRangeString()));
				}
				else {
					TextComponentTranslation current = new TextComponentTranslation("tooltip.gadgetronig:antiRangeCurrent");
					playerIn.sendMessage(new TextComponentString(current.getFormattedText() + " " + t.getRangeString()));
				}
			}
		}
		return true;
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

        return BlockRenderLayer.TRANSLUCENT;
    }
}