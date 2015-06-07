package com.vinterdo.deusexmachina.tileentity;

import java.util.ArrayList;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityMultiblock extends TileEntityDEM
{
	protected TileEntityMultiblockMaster master;
	
	public TileEntityMultiblockMaster getMaster()
	{
		return master;
	}
	
	public void setMaster(TileEntityMultiblockMaster te)
	{
		master = te;
	}
	
	public void onDestroy()
	{
		master.removeMember(this);
	}
	
	public boolean isFormed()
	{
		return master != null && master.isFormed();
	}
}
