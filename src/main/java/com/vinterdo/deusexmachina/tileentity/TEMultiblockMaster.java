package com.vinterdo.deusexmachina.tileentity;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;

import com.vinterdo.deusexmachina.utility.LogHelper;

import cpw.mods.fml.common.network.ByteBufUtils;

public class TEMultiblockMaster extends TEDEM
{
	protected ArrayList<TEMultiblock> members;
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
		if(members == null) members = new ArrayList<TEMultiblock>();
		if(isProperMultiblock())
		{
			for(TEMultiblock te : members)
			{
				te.setMaster(this);
			}
			
			formed = true;
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}
	
	public boolean isProperMultiblock()
	{
		// in override add checking if is proper multiblock
		return true;
	}
	
	public void addMember(TEMultiblock te)
	{
		if(members == null)
			members = new ArrayList<TEMultiblock>();
		
		members.add(te);
	}
	
	public void removeMember(TEMultiblock te)
	{
		if(members != null)
			members.remove(te);
		
		destroyMultiblock();
	}
	
	public void destroyMultiblock()
	{
		if(members != null)
		{
			for(TEMultiblock te : members)
			{
				te.master = null;
				worldObj.markBlockForUpdate(te.xCoord, te.yCoord, te.zCoord);
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
		}
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setBoolean("formed", formed);
	}
	
}
