package com.vinterdo.deusexmachina.block;

import com.vinterdo.deusexmachina.creativetab.CreativeTabDEM;
import com.vinterdo.deusexmachina.tileentity.TEShieldPylonBase;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BShieldPylonBase extends BTileEntityDEM
{
	public BShieldPylonBase()
	{
		super();
		this.setBlockName("shieldPylonBase");
		this.setHardness(5.0F);
		this.setCreativeTab(CreativeTabDEM.DEM_TAB);
	}
	
	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
	{
		((TEShieldPylonBase) world.getTileEntity(x, y, z)).hit(player.getLookVec());
	}
	
	
	
	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_)
	{
		return new TEShieldPylonBase();
	}
	
	/*	@Override
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
	}*/
}