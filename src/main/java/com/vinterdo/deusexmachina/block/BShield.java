package com.vinterdo.deusexmachina.block;

import com.vinterdo.deusexmachina.creativetab.CreativeTabDEM;
import com.vinterdo.deusexmachina.tileentity.TEShield;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BShield extends BTileEntityDEM
{
	public BShield()
	{
		super();
		this.setBlockName("shield");
		this.setHardness(5.0F);
		this.setCreativeTab(CreativeTabDEM.DEM_TAB);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_)
	{
		return new TEShield();
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
	
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
}