package com.vinterdo.deusexmachina.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class TEDeus extends TEMultiblock implements IInventory
{

	@Override
	public int getSizeInventory() 
	{
		return master == null ? 0 : ((TEDeusMaster)master).getSizeInventory();
	}

	@Override
	public ItemStack getStackInSlot(int slot) 
	{
		return master == null ? null : ((TEDeusMaster)master).getStackInSlot(slot);
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) 
	{
		return master == null ? null : ((TEDeusMaster)master).decrStackSize(slot, amount);
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) 
	{
		return master == null ? null : ((TEDeusMaster)master).getStackInSlotOnClosing(slot);
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) 
	{
		if(master != null)
			((TEDeusMaster)master).setInventorySlotContents(slot, stack);
	}

	@Override
	public String getInventoryName() 
	{
		return master == null ? "" : ((TEDeusMaster)master).getInventoryName();
	}

	@Override
	public boolean hasCustomInventoryName() 
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return master == null ? 0 : ((TEDeusMaster)master).getInventoryStackLimit();
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
	public boolean isItemValidForSlot(int slot, ItemStack item) 
	{
		return master == null ? false : ((TEDeusMaster)master).isItemValidForSlot(slot, item);
	}

}
