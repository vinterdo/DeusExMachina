package com.vinterdo.deusexmachina.tileentity;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;

import com.vinterdo.deusexmachina.init.ModBlocks;

import cpw.mods.fml.common.network.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

public class TileEntityBlastFurnaceMaster extends TileEntityMultiblockMaster implements IInventory
{

	protected ItemStack[] stacks = new ItemStack[9]; // 0..2 - input, 3..5 - output 6..8 - fuel
	protected int burningTime;
	protected int progress;
	protected int progressTarget;
	
	
	@Override
	public void tryForming()
	{
		members = new ArrayList<TileEntityMultiblock>();
		for(int x =0; x < 3; x++)
		{
			for(int y =0; y < 2; y++)
			{
				for(int z =0; z < 3; z++)
				{
					TileEntity te = worldObj.getTileEntity(xCoord + x - 1, yCoord + y - 1, zCoord + z - 1);
					
					if(te instanceof TileEntityBlastFurnace)
					{
						members.add((TileEntityMultiblock)te);
					}
				}
			}
		}
		super.tryForming();
	}
	
	@Override
	public boolean isProperMultiblock()
	{
		for(int x =0; x < 3; x++)
		{
			for(int y =0; y < 2; y++)
			{
				for(int z =0; z < 3; z++)
				{
					TileEntity te = worldObj.getTileEntity(xCoord + x - 1, yCoord + y - 1, zCoord + z - 1);
					if(te == null)
					{
						return false;
					}
					else if(te instanceof TileEntityBlastFurnace)
					{
						TileEntityMultiblock tem = (TileEntityMultiblock)te;
						if(tem.getMaster() != null) 
							return false;
					}
					else if(te != this)
					{
						return false;
					}
					
				}
			}
		}
		
		return true;
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount)
    {
        if (this.stacks[slot] != null)
        {
            ItemStack itemstack;

            if (this.stacks[slot].stackSize <= amount)
            {
                itemstack = this.stacks[slot];
                setInventorySlotContents(slot, null);
                return itemstack;
            }
            else
            {
                itemstack = this.stacks[slot].splitStack(amount);

                if (this.stacks[slot].stackSize == 0)
                {
                	setInventorySlotContents(slot, null);
                }

                this.markDirty();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }
	
	@Override
	public int getSizeInventory() 
	{
		return stacks.length;
	}
	
	@Override
	public ItemStack getStackInSlot(int slot) 
	{
		return this.stacks[slot];
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
    {
        if (this.stacks[slot] != null)
        {
            ItemStack itemstack = this.stacks[slot];
            this.stacks[slot] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack)
    {
        this.stacks[slot] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
    }

	@Override
	public String getInventoryName() 
	{
		return ModBlocks.blastFurnaceMaster.getUnlocalizedName() + ".name";
	}

	@Override
	public boolean hasCustomInventoryName() 
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit() 
	{
		// TODO Auto-generated method stub
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

	@Override
	public void openInventory() 
	{
	}

	@Override
	public void closeInventory() 
	{		
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack) 
	{
		if(slot <= 2)
			return true;
		if(slot > 2 && slot <= 5)
			return TileEntityFurnace.getItemBurnTime(itemStack) > 0;
		
		return false;
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);

        this.burningTime = tag.getShort("burningTimes");
        this.progress = tag.getShort("progress");
        this.progressTarget = tag.getShort("progressTarget");
		
		NBTTagList stackTag = tag.getTagList("stacks", 10);
		stacks = new ItemStack[9];
		
		for(int i =0 ; i < stackTag.tagCount(); i++)
		{
			NBTTagCompound t = stackTag.getCompoundTagAt(i);
			int index = t.getByte("index");
			stacks[index] = ItemStack.loadItemStackFromNBT(t);
		}
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		
		tag.setShort("burningTime", (short)this.burningTime);
		tag.setShort("progress", (short)this.progress);
		tag.setShort("progressTarget", (short)this.progressTarget);
		
		NBTTagList stackTag = new NBTTagList();
		for(int i=0; i < 9; i++)
		{
			if(stacks[i] != null)
			{
				NBTTagCompound t = new NBTTagCompound();
				stacks[i].writeToNBT(t);
				t.setByte("index", (byte)i);
				stackTag.appendTag(t);
			}
		}
		
		tag.setTag("stacks", stackTag);
	}
	
	public void writeToPacket(ByteBuf buf)
	{
		for(ItemStack stack : stacks)
			ByteBufUtils.writeItemStack(buf, stack);
	}
	
	public void readFromPacket(ByteBuf buf)
	{
		for(int i=0 ; i < 6; i++)
			stacks[i] = ByteBufUtils.readItemStack(buf);
	}
}
