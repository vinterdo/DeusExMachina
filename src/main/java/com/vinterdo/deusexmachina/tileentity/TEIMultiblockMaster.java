package com.vinterdo.deusexmachina.tileentity;

import java.util.ArrayList;

import com.vinterdo.deusexmachina.helpers.Helper;
import com.vinterdo.deusexmachina.init.ModBlocks;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class TEIMultiblockMaster extends TEMultiblockMaster implements IInventory 
{
	protected ArrayList<ItemStack> stacks;
	
	public void setNumOfStacks(int num)
	{
		if(stacks == null)
		{
			stacks = new ArrayList<ItemStack>(num);
		}
		else if(stacks.size() > num)
		{
			stacks = (ArrayList<ItemStack>) stacks.subList(0, num);
		}
		else
		{
			while(stacks.size() <= num)
				stacks.add(null);
		}
	}
	
	@Override
	public int getSizeInventory() 
	{
		return stacks.size();
	}

	@Override
	public ItemStack getStackInSlot(int slot) 
	{
		return stacks.get(slot);
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount)
    {
        if (this.stacks.get(slot) != null)
        {
            ItemStack itemstack;

            if (stacks.get(slot).stackSize <= amount)
            {
                itemstack = stacks.get(slot);
                setInventorySlotContents(slot, null);
                return itemstack;
            }
            else
            {
                itemstack = stacks.get(slot).splitStack(amount);

                if (stacks.get(slot).stackSize == 0)
                {
                	setInventorySlotContents(slot, null);
                }

                markDirty();
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
        ItemStack itemstack = stacks.get(slot);
        stacks.set(slot, null);
        return itemstack;
    }

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack)
    {
        stacks.set(slot, stack);

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
    }

	@Override
	public String getInventoryName() 
	{
		return "";
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
		return slot < stacks.size();
	}
	
}
