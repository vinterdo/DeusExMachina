package com.vinterdo.deusexmachina.tileentity.base;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class TEIUniversalMultiblock extends TEMultiblock implements IInventory
{
	@Override
	public int getSizeInventory()
	{
		return getMaster() == null ? 0 : ((TEIMultiblockMaster) getMaster()).getSizeInventory();
	}
	
	@Override
	public ItemStack getStackInSlot(int slot)
	{
		return getMaster() == null ? null : ((TEIMultiblockMaster) getMaster()).getStackInSlot(slot);
	}
	
	@Override
	public ItemStack decrStackSize(int slot, int amount)
	{
		return getMaster() == null ? null : ((TEIMultiblockMaster) getMaster()).decrStackSize(slot, amount);
	}
	
	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
	{
		return getMaster() == null ? null : ((TEIMultiblockMaster) getMaster()).getStackInSlotOnClosing(slot);
	}
	
	@Override
	public void setInventorySlotContents(int slot, ItemStack stack)
	{
		if (getMaster() != null)
			((TEIMultiblockMaster) getMaster()).setInventorySlotContents(slot, stack);
	}
	
	@Override
	public String getInventoryName()
	{
		return getMaster() == null ? "" : ((TEIMultiblockMaster) getMaster()).getInventoryName();
	}
	
	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}
	
	@Override
	public int getInventoryStackLimit()
	{
		return getMaster() == null ? 0 : ((TEIMultiblockMaster) getMaster()).getInventoryStackLimit();
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return worldObj.getTileEntity(xCoord, yCoord, zCoord) != this ? false
				: player.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
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
		return getMaster() == null ? false : ((TEIMultiblockMaster) getMaster()).isItemValidForSlot(slot, item);
	}
}
