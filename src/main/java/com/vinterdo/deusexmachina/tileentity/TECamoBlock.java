package com.vinterdo.deusexmachina.tileentity;

import com.vinterdo.deusexmachina.tileentity.base.TEDEM;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;

public class TECamoBlock extends TEDEM
{
	private ItemStack[] camoStacks = new ItemStack[6];
	
	public void setCamo(ItemStack stack, int side)
	{
		camoStacks[side] = stack;
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
	}
	
	public ItemStack getCamo(int side)
	{
		return camoStacks[side];
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		
		NBTTagList camoStackTag = tag.getTagList("camoStacks", 10);
		camoStacks = new ItemStack[6];
		
		for (int i = 0; i < camoStackTag.tagCount(); i++)
		{
			NBTTagCompound t = camoStackTag.getCompoundTagAt(i);
			int index = t.getByte("index");
			camoStacks[index] = ItemStack.loadItemStackFromNBT(t);
		}
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		
		NBTTagList camoStackTag = new NBTTagList();
		for (int i = 0; i < 6; i++)
		{
			if (camoStacks[i] != null)
			{
				NBTTagCompound t = new NBTTagCompound();
				camoStacks[i].writeToNBT(t);
				t.setByte("index", (byte) i);
				camoStackTag.appendTag(t);
			}
		}
		
		tag.setTag("camoStacks", camoStackTag);
	}
	
	public void writeToPacket(ByteBuf buf)
	{
		for (ItemStack stack : camoStacks)
			ByteBufUtils.writeItemStack(buf, stack);
	}
	
	public void readFromPacket(ByteBuf buf)
	{
		for (int i = 0; i < 6; i++)
			camoStacks[i] = ByteBufUtils.readItemStack(buf);
		worldObj.markBlockRangeForRenderUpdate(xCoord, yCoord, zCoord, xCoord, yCoord, zCoord);
	}
}
