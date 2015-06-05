package com.vinterdo.deusexmachina.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.vinterdo.deusexmachina.DeusExMachina;
import com.vinterdo.deusexmachina.creativetab.CreativeTabDEM;
import com.vinterdo.deusexmachina.handler.GuiHandler;
import com.vinterdo.deusexmachina.tileentity.TileEntityEssenceMacerator;
import com.vinterdo.deusexmachina.tileentity.TileEntityEssenceProcessor;

public class BlockEssenceMacerator extends BlockMultitexturedTileEntity
{
	public BlockEssenceMacerator()
	{
		super();
		this.setBlockName("essenceMacerator");
		this.setHardness(5.0F);
		this.setCreativeTab(CreativeTabDEM.DEM_TAB);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) 
	{
		return new TileEntityEssenceMacerator();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote)
		{
			player.openGui(DeusExMachina.instance, GuiHandler.GuiIDs.ESSENCE_MACERATOR.ordinal(), world, x, y, z);
		}
		
		return true;
	}
}
