package com.vinterdo.deusexmachina.tileentity;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;

import com.vinterdo.deusexmachina.utility.LogHelper;

import cpw.mods.fml.common.network.ByteBufUtils;

public class TileEntityMultiblockMaster extends TileEntityDEM
{
	protected ArrayList<TileEntityMultiblock> members;
	protected boolean formed = false;
	boolean shouldReform = false;
	
	public boolean isFormed()
	{
		return formed;
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		if(shouldReform)
		{
			shouldReform = false;
			tryForming();
		}
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
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
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

		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
	
	public void writeToPacket(ByteBuf buf)
	{
		super.writeToPacket(buf);
		ByteBufUtils.writeVarShort(buf, formed? 1:0);
	}
	
	public void readFromPacket(ByteBuf buf)
	{
		super.readFromPacket(buf);
		formed = ByteBufUtils.readVarShort(buf) == 0 ? false : true;
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox()
	{
		return AxisAlignedBB.getBoundingBox(-1 + xCoord, 1 + yCoord, -1 + zCoord, 1 + xCoord, -4 + yCoord, 1 + zCoord);
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		formed = tag.getBoolean("formed");
		
		if(formed)
		{
			shouldReform = true;
			/*int[] arrX = tag.getIntArray("childsX");
			int[] arrY = tag.getIntArray("childsY");
			int[] arrZ = tag.getIntArray("childsZ");
			
			members = new ArrayList<TileEntityMultiblock>(arrX.length);
			
			for(int i=0; i < arrX.length; i++)
			{
				members.set(i, (TileEntityMultiblock) worldObj.getTileEntity(arrX[i], arrY[i], arrZ[i]));
			}*/
		}
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setBoolean("formed", formed);
		
		if(formed)
		{
			/*int[] arr = new int[members.size()];
			for(int i=0; i<members.size(); i++)
			{
				arr[i] = members.get(i).xCoord;
			}
			tag.setIntArray("childsX", arr);
			
			for(int i=0; i<members.size(); i++)
			{
				arr[i] = members.get(i).yCoord;
			}
			tag.setIntArray("childsY", arr);
			
			for(int i=0; i<members.size(); i++)
			{
				arr[i] = members.get(i).zCoord;
			}
			tag.setIntArray("childsZ", arr);*/
		}
	}
	
}
