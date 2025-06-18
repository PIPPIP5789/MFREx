package com.pippip5789.mfrex.core.content.tailor;

import minefantasy.mfr.block.BlockTileEntity;
import minefantasy.mfr.init.MineFantasyTabs;
import minefantasy.mfr.mechanics.knowledge.ResearchLogic;
import net.minecraft.block.BlockHorizontal;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTailorBench extends BlockTileEntity<TileEntityTailorBench> {

    public static final PropertyDirection FACING;

    public BlockTailorBench(String registryName) {
        super(Material.WOOD);
        this.setRegistryName(registryName);
        this.setTranslationKey(registryName);
        this.setSoundType(SoundType.WOOD);
        this.setHardness(5.0F);
        this.setResistance(2.0F);
        this.setLightOpacity(0);
        this.setCreativeTab(MineFantasyTabs.tabUtil);
    }

    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[]{FACING});
    }

    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityTailorBench();
    }

    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public IBlockState getStateFromMeta(int meta) {
        EnumFacing enumfacing = EnumFacing.byIndex(meta);
        if (enumfacing.getAxis() == Axis.Y) {
            enumfacing = EnumFacing.NORTH;
        }

        return this.getDefaultState().withProperty(FACING, enumfacing);
    }

    public int getMetaFromState(IBlockState state) {
        return ((EnumFacing)state.getValue(FACING)).getIndex();
    }

    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
    }

    public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        TileEntityTailorBench tile = (TileEntityTailorBench)getTile(world, pos);
        if (tile != null && (world.isAirBlock(pos.add(0, 1, 0)) || !world.isSideSolid(pos.add(0, 1, 0), EnumFacing.DOWN)) && (facing != EnumFacing.UP || !tile.tryCraft(player) && !world.isRemote)) {
            tile.openGUI(world, player);
        }

        if (!world.isRemote) {
            ResearchLogic.syncData(player);
        }

        return true;
    }

    public void onBlockClicked(World world, BlockPos pos, EntityPlayer user) {
        TileEntityTailorBench tile = (TileEntityTailorBench)getTile(world, pos);
        if (tile != null) {
            tile.tryCraft(user);
        }

    }

    static {
        FACING = BlockHorizontal.FACING;
    }
}
