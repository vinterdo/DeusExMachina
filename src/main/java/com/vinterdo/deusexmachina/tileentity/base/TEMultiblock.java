package com.vinterdo.deusexmachina.tileentity.base;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.nbt.NBTTagCompound;

public class TEMultiblock extends TEDEM
{
	protected int	masterXCoord	= Integer.MAX_VALUE;
	protected int	masterYCoord	= Integer.MAX_VALUE;
	protected int	masterZCoord	= Integer.MAX_VALUE;
	
	public TEMultiblockMaster getMaster()
	{
		return (TEMultiblockMaster) worldObj.getTileEntity(masterXCoord, masterYCoord, masterZCoord);
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
	}
	
	public void setMaster(TEMultiblockMaster te)
	{
		if (te != null)
		{
			masterXCoord = te.xCoord;
			masterYCoord = te.yCoord;
			masterZCoord = te.zCoord;
		} else
		{
			masterXCoord = Integer.MAX_VALUE;
			masterYCoord = Integer.MAX_VALUE;
			masterZCoord = Integer.MAX_VALUE;
		}
		
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
	
	public boolean isFormed()
	{
		return getMaster() != null && getMaster().isFormed();
	}
	
	@Override
	public void writeToPacket(ByteBuf buf)
	{
		super.writeToPacket(buf);
		ByteBufUtils.writeVarShort(buf, getMaster() != null ? 1 : 0);
		if (getMaster() != null)
		{
			ByteBufUtils.writeVarInt(buf, getMaster().xCoord, 5);
			ByteBufUtils.writeVarInt(buf, getMaster().yCoord, 5);
			ByteBufUtils.writeVarInt(buf, getMaster().zCoord, 5);
		}
	}
	
	@Override
	public void readFromPacket(ByteBuf buf)
	{
		super.readFromPacket(buf);
		if (ByteBufUtils.readVarShort(buf) != 0)
		{
			masterXCoord = ByteBufUtils.readVarInt(buf, 5);
			masterYCoord = ByteBufUtils.readVarInt(buf, 5);
			masterZCoord = ByteBufUtils.readVarInt(buf, 5);
		} else
		{
			masterXCoord = Integer.MAX_VALUE;
			masterYCoord = Integer.MAX_VALUE;
			masterZCoord = Integer.MAX_VALUE;
		}
		
		worldObj.markBlockRangeForRenderUpdate(xCoord - 1, yCoord - 1, zCoord - 1, xCoord + 1, yCoord + 1, zCoord + 1);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		
	}
	
}
