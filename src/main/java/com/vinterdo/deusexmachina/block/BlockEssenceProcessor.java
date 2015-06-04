package com.vinterdo.deusexmachina.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.vinterdo.deusexmachina.DeusExMachina;
import com.vinterdo.deusexmachina.creativetab.CreativeTabDEM;
import com.vinterdo.deusexmachina.handler.GuiHandler;
import com.vinterdo.deusexmachina.tileentity.TileEntityEssenceProcessor;

public class BlockEssenceProcessor extends BlockMultitexturedTileEntity
{
	public BlockEssenceProcessor()
	{
		super();
		this.setBlockName("essenceProcessor");
		this.setHardness(5.0F);
		this.setCreativeTab(CreativeTabDEM.DEM_TAB);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) 
	{
		return new TileEntityEssenceProcessor();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote)
		{
			player.openGui(DeusExMachina.instance, GuiHandler.GuiIDs.ESSENCE_PROCESSOR.ordinal(), world, x, y, z);
		}
		
		return true;
	}
}
