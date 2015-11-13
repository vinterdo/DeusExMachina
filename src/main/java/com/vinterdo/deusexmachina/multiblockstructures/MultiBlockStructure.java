package com.vinterdo.deusexmachina.multiblockstructures;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import com.vinterdo.deusexmachina.tileentity.base.TEDEM;
import com.vinterdo.deusexmachina.tileentity.base.TEMultiblock;
import com.vinterdo.deusexmachina.tileentity.base.TEMultiblockMaster;

public abstract class MultiBlockStructure
{
	private HashMap<Coord, List<Class<?>>> validBlocks = new HashMap<Coord, List<Class<?>>>();
	
	public <T extends TEMultiblock> void addBlock(Class<T> te, int x, int y, int z) // Add block relative to master, master is always on coord 0,0,0
	{
		Coord coord = new Coord(x, y, z);
		if(!validBlocks.containsKey(coord))
		{
			validBlocks.put(coord, new LinkedList());
		}
		validBlocks.get(coord).add(te);
	}
	
	public boolean isValidMultiblock(TEMultiblockMaster tem)
	{
		return isValidMultiblockPP(tem) || isValidMultiblockPM(tem) || isValidMultiblockMM(tem) || isValidMultiblockMP(tem);
	}
	
	public boolean isValidMultiblockPP(TEMultiblockMaster tem)
	{
		for(Coord coord : validBlocks.keySet())
		{
			List<Class<?>> blocks = validBlocks.get(coord);
			TileEntity block = tem.getWorldObj().getTileEntity(tem.xCoord + coord.x, tem.yCoord + coord.y, tem.zCoord + coord.z);
			if(block == null)
				return false;
			if(!blocks.contains(block.getClass()))
			{
				return false;
			}
		}
		
		return true;
	}
	
	public boolean isValidMultiblockPM(TEMultiblockMaster tem)
	{
		for(Coord coord : validBlocks.keySet())
		{
			List<Class<?>> blocks = validBlocks.get(coord);
			TileEntity block = tem.getWorldObj().getTileEntity(tem.xCoord + coord.x, tem.yCoord + coord.y, tem.zCoord - coord.z);
			if(block == null)
				return false;
			if(!blocks.contains(block.getClass()))
			{
				return false;
			}
		}
		
		return true;
	}
	
	public boolean isValidMultiblockMM(TEMultiblockMaster tem)
	{
		for(Coord coord : validBlocks.keySet())
		{
			List<Class<?>> blocks = validBlocks.get(coord);
			TileEntity block = tem.getWorldObj().getTileEntity(tem.xCoord - coord.z, tem.yCoord + coord.y, tem.zCoord + coord.x);
			if(block == null)
				return false;
			if(!blocks.contains(block.getClass()))
			{
				return false;
			}
		}
		
		return true;
	}
	
	public boolean isValidMultiblockMP(TEMultiblockMaster tem)
	{
		for(Coord coord : validBlocks.keySet())
		{
			List<Class<?>> blocks = validBlocks.get(coord);
			TileEntity block = tem.getWorldObj().getTileEntity(tem.xCoord + coord.z, tem.yCoord + coord.y, tem.zCoord + coord.x);
			if(block == null)
				return false;
			if(!blocks.contains(block.getClass()))
			{
				return false;
			}
		}
		
		return true;
	}
	
	public void getMembers(TEMultiblockMaster tem, List<TEMultiblock> members) // use this only after checking if multiblock is valid
	{
		if(isValidMultiblockPP(tem))
		{
			getMembersPP(tem, members);
		}
		else if(isValidMultiblockPM(tem))
		{
			getMembersPM(tem, members);
		}
		else if(isValidMultiblockMM(tem))
		{
			getMembersMM(tem, members);
		}
		else if(isValidMultiblockMP(tem))
		{
			getMembersMP(tem, members);
		}
	}
	
	public void getMembersPP(TEMultiblockMaster tem, List<TEMultiblock> members) // use this only after checking if multiblock is valid
	{
		for(Coord coord : validBlocks.keySet())
		{
			TEMultiblock block = (TEMultiblock)tem.getWorldObj().getTileEntity(tem.xCoord + coord.x, tem.yCoord + coord.y, tem.zCoord + coord.z);
			members.add(block);
			tem.rotation = ForgeDirection.NORTH; 
		}
	}
	
	public void getMembersPM(TEMultiblockMaster tem, List<TEMultiblock> members) // use this only after checking if multiblock is valid
	{
		for(Coord coord : validBlocks.keySet())
		{
			TEMultiblock block = (TEMultiblock)tem.getWorldObj().getTileEntity(tem.xCoord + coord.x, tem.yCoord + coord.y, tem.zCoord - coord.z);
			members.add(block);
			tem.rotation = ForgeDirection.SOUTH; 
		}
	}
	
	public void getMembersMM(TEMultiblockMaster tem, List<TEMultiblock> members) // use this only after checking if multiblock is valid
	{
		for(Coord coord : validBlocks.keySet())
		{
			TEMultiblock block = (TEMultiblock)tem.getWorldObj().getTileEntity(tem.xCoord - coord.z, tem.yCoord + coord.y, tem.zCoord + coord.x);
			members.add(block);
			tem.rotation = ForgeDirection.WEST; 
		}
	}
	
	public void getMembersMP(TEMultiblockMaster tem, List<TEMultiblock> members) // use this only after checking if multiblock is valid
	{
		for(Coord coord : validBlocks.keySet())
		{
			TEMultiblock block = (TEMultiblock)tem.getWorldObj().getTileEntity(tem.xCoord + coord.z, tem.yCoord + coord.y, tem.zCoord + coord.x);
			members.add(block);
			tem.rotation = ForgeDirection.EAST; 
		}
	}
	
	private class Coord
	{
		public Coord(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}
		public int x;
		public int y;
		public int z;
		@Override
		public String toString() {
			return "Coord [x=" + x + ", y=" + y + ", z=" + z + "]";
		}
	}
}
