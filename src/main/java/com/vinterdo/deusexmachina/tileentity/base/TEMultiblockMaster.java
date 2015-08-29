package com.vinterdo.deusexmachina.tileentity.base;

import java.util.ArrayList;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;

public abstract class TEMultiblockMaster extends TEDEM
{
	protected ArrayList<TEMultiblock>	members;
	protected boolean					formed			= false;
	boolean								shouldReform	= false;
	
	public boolean isFormed()
	{
		return formed;
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		if (shouldReform)
		{
			shouldReform = false;
			tryForming();
		}
	}
	
	public void tryForming()
	{
		// in override add every member of proper multiblock
		if (members == null)
			members = new ArrayList<TEMultiblock>();
		if (isProperMultiblock())
		{
			for (TEMultiblock te : members)
			{
				if (te.getMaster() == null)
				{
					te.setMaster(this);
				} else
				{
					formed = false;
					return;
				}
			}
			
			formed = true;
			worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		}
	}
	
	public abstract boolean isProperMultiblock();
	
	public void addMember(TEMultiblock te)
	{
		if (members == null)
			members = new ArrayList<TEMultiblock>();
			
		members.add(te);
	}
	
	public void removeMember(TEMultiblock te)
	{
		if (members != null)
			members.remove(te);
			
		destroyMultiblock();
	}
	
	public void destroyMultiblock()
	{
		if (members != null && formed)
		{
			for (TEMultiblock te : members)
			{
				te.setMaster(null);
				worldObj.markBlockForUpdate(te.xCoord, te.yCoord, te.zCoord);
			}
		}
		formed = false;
		
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
	
	@Override
	public void writeToPacket(ByteBuf buf)
	{
		super.writeToPacket(buf);
		ByteBufUtils.writeVarShort(buf, formed ? 1 : 0);
	}
	
	@Override
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
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		formed = tag.getBoolean("formed");
		
		if (formed)
		{
			shouldReform = true;
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		tag.setBoolean("formed", formed);
	}
	
}
