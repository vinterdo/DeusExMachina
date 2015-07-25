package com.vinterdo.deusexmachina.tileentity;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;

import com.vinterdo.deusexmachina.utility.LogHelper;

import cpw.mods.fml.common.network.ByteBufUtils;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TEMultiblock extends TEDEM
{
	protected TEMultiblockMaster master;
	
	public TEMultiblockMaster getMaster()
	{
		return master;
	}
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
	}
	
	public void setMaster(TEMultiblockMaster te)
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
			
			master = (TEMultiblockMaster) worldObj.getTileEntity(x, y, z);
		}
		else
		{
			master = null;
		}
		

		worldObj.markBlockRangeForRenderUpdate(xCoord - 1, yCoord, zCoord - 1, xCoord + 1, yCoord - 4, zCoord + 1);
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		
	}
	
}
