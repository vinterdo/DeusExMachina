package com.vinterdo.deusexmachina.tileentity;

import io.netty.buffer.ByteBuf;

import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.utility.LogHelper;

import cpw.mods.fml.common.network.ByteBufUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityEssenceMacerator extends TileEntityDEM implements IInventory
{
	protected ItemStack[] stacks = new ItemStack[8];
	protected int progress;
	protected int power;
	
	public int getProgress()
	{
		return progress;
	}
	
	public void setProgress(int val)
	{
		progress = val;
	}
	
	public int getPower()
	{
		return power;
	}
	
	public void setPower(int val)
	{
		power = val;
	}
	
	@Override
	public void updateEntity()
	{
		// TODO: item processing
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
		return ModBlocks.essenceMacerator.getUnlocalizedName() + ".name";
	}

	@Override
	public boolean hasCustomInventoryName() 
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit() 
	{
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
		if(slot < 3) return true;
		if(slot == 3 && itemStack.getItem() == ModItems.essence) return true;
		return false;
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		
		NBTTagList stackTag = tag.getTagList("stacks", 10);
		stacks = new ItemStack[8];
		
		for(int i =0 ; i < stackTag.tagCount(); i++)
		{
			NBTTagCompound t = stackTag.getCompoundTagAt(i);
			int index = t.getByte("index");
			stacks[index] = ItemStack.loadItemStackFromNBT(t);
		}
		
		progress = tag.getInteger("progress");
		power = tag.getInteger("power");
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		
		NBTTagList stackTag = new NBTTagList();
		for(int i=0; i < 8; i++)
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
		
		tag.setInteger("progress", progress);
		tag.setInteger("power", power);
	}
	
	public void writeToPacket(ByteBuf buf)
	{
		for(ItemStack stack : stacks)
			ByteBufUtils.writeItemStack(buf, stack);
	}
	
	public void readFromPacket(ByteBuf buf)
	{
		for(int i=0 ; i < 8; i++)
			stacks[i] = ByteBufUtils.readItemStack(buf);
	}

}
