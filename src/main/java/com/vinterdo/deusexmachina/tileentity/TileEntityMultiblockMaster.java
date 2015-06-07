package com.vinterdo.deusexmachina.tileentity;

import java.util.ArrayList;

import com.vinterdo.deusexmachina.utility.LogHelper;

public class TileEntityMultiblockMaster extends TileEntityDEM
{
	protected ArrayList<TileEntityMultiblock> members;
	protected boolean formed = false;
	
	public boolean isFormed()
	{
		return formed;
	}
	
	public void tryForming()
	{
		// in override add every member of proper multiblock
		if(members == null) members = new ArrayList<TileEntityMultiblock>();
		if(isProperMultiblock())
		{
			for(TileEntityMultiblock te : members)
			{
				te.setMaster(this);
			}
			
			formed = true;
			LogHelper.info("formed");
		}
		else
		{
			LogHelper.info("failed forming");
		}
		
		
	}
	
	public boolean isProperMultiblock()
	{
		// in override add checking if is proper multiblock
		return true;
	}
	
	public void addMember(TileEntityMultiblock te)
	{
		if(members == null)
			members = new ArrayList<TileEntityMultiblock>();
		
		members.add(te);
	}
	
	public void removeMember(TileEntityMultiblock te)
	{
		if(members != null)
			members.remove(te);
		
		destroyMultiblock();
	}
	
	public void destroyMultiblock()
	{
		if(members != null)
		{
			for(TileEntityMultiblock te : members)
			{
				te.master = null;
			}
		}
		formed = false;
	}
}
