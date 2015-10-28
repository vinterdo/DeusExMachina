package com.vinterdo.deusexmachina.network;

import com.vinterdo.deusexmachina.tileentity.TEDeusMaster;
import com.vinterdo.deusexmachina.tileentity.base.TEDEM;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.Constants;

public class MessageHandleGuiButtonResearch extends MessageBase<MessageHandleGuiButtonResearch>
{
	private int		x, y, z, id;
	private String	name;
					
	public MessageHandleGuiButtonResearch()
	{
	}
	
	public MessageHandleGuiButtonResearch(TEDEM te, int id, String name)
	{
		x = te.xCoord;
		y = te.yCoord;
		z = te.zCoord;
		this.id = id;
		this.name = name;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		id = buf.readInt();
		name = ByteBufUtils.readUTF8String(buf);
		
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(x);
		buf.writeInt(y);
		buf.writeInt(z);
		buf.writeInt(id);
		ByteBufUtils.writeUTF8String(buf, name);
	}
	
	@Override
	public void handleClientSide(MessageHandleGuiButtonResearch message, EntityPlayer player)
	{
	
	}
	
	@Override
	public void handleServerSide(MessageHandleGuiButtonResearch message, EntityPlayer player)
	{
		TileEntity te = player.worldObj.getTileEntity(message.x, message.y, message.z);
		if (te instanceof TEDeusMaster)
		{
			((TEDeusMaster) te).onButtonPressed(message.id, message.name);
			NBTTagList list = ((TEDeusMaster) te).getStackInSlot(2).stackTagCompound.getTagList("tree",
					Constants.NBT.TAG_COMPOUND);
			for (int i = 0; i < list.tagCount(); i++)
			{
				NBTTagCompound tag = list.getCompoundTagAt(i);
				if (tag.getString("recipe").equals(message.name))
				{
					tag.setBoolean("discovered", true);
					break;
				}
			}
		}
	}
	
}
