package com.vinterdo.deusexmachina.tileentity;

import com.vinterdo.deusexmachina.network.DescriptionHandler;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;

public abstract class TEDEM extends TileEntity
{
	@Override
	public Packet getDescriptionPacket()
	{
		ByteBuf buf = Unpooled.buffer();
		buf.writeInt(xCoord);
		buf.writeInt(yCoord);
		buf.writeInt(zCoord);
		writeToPacket(buf);
		return new FMLProxyPacket(buf, DescriptionHandler.CHANNEL);
	}
	
	public void writeToPacket(ByteBuf buf)
	{
		
	}
	
	public void readFromPacket(ByteBuf buf)
	{
		
	}
	
}
