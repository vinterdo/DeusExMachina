package com.vinterdo.deusexmachina.tileentity;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;

import cpw.mods.fml.common.network.ByteBufUtils;
import net.minecraft.nbt.NBTTagCompound;
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
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
	
	public boolean isFormed()
	{
		return master != null && master.isFormed();
	}
	
	public void writeToPacket(ByteBuf buf)
	{
		super.writeToPacket(buf);
		ByteBufUtils.writeVarShort(buf, master != null ? 1 : 0);
		if(master != null)
		{
			ByteBufUtils.writeVarInt(buf, master.xCoord, 5);
			ByteBufUtils.writeVarInt(buf, master.yCoord, 5);
			ByteBufUtils.writeVarInt(buf, master.zCoord, 5);
		}
	}
	
	public void readFromPacket(ByteBuf buf)
	{
		super.readFromPacket(buf);
		if(ByteBufUtils.readVarShort(buf) == 0 ? false : true)
		{
			int x = ByteBufUtils.readVarInt(buf, 5);
			int y = ByteBufUtils.readVarInt(buf, 5);
			int z = ByteBufUtils.readVarInt(buf, 5);
			
			master = (TileEntityMultiblockMaster) worldObj.getTileEntity(x, y, z);
		}
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		
		/*if(tag.hasKey("masterX"))
		{
			int x = tag.getInteger("masterX");
			int y = tag.getInteger("masterY");
			int z = tag.getInteger("masterZ");
			
			master = (TileEntityMultiblockMaster) worldObj.getTileEntity(x, y, z);
		}*/
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		
		/*if(master != null)
		{
			tag.setInteger("masterX", master.xCoord);
			tag.setInteger("masterY", master.yCoord);
			tag.setInteger("masterZ", master.zCoord);
		}*/
	}
	
}
