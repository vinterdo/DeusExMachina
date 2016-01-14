package com.vinterdo.deusexmachina.block;

import com.vinterdo.deusexmachina.creativetab.CreativeTabDEM;
import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.tileentity.TEShieldPylonEnding;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BShieldPylonEnding extends BTileEntityDEM
{
	public BShieldPylonEnding()
	{
		super();
		this.setBlockName("shieldPylonEnding");
		this.setHardness(5.0F);
		this.setCreativeTab(CreativeTabDEM.DEM_TAB);
	}
	
	@Override
	public void onBlockClicked(World world, int x, int y, int z, EntityPlayer player)
	{
		((TEShieldPylonEnding) world.getTileEntity(x, y, z)).hit(player.getLookVec());
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
			float hitY, float hitZ)
	{
		
		for(int i=1; i<10; i++)
		{
			for (int j=0; j<5; j++)
			{
				if(world.getBlock(x+i, y+j, z).isAir(world, x+i, y+j, z)) 
					world.setBlock(x+i,  y+j, z, ModBlocks.shield);
				
			}
		
		}
		return true;
	}
	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_)
	{
		return new TEShieldPylonEnding();
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