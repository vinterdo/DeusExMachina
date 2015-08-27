package com.vinterdo.deusexmachina.inventory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

import com.vinterdo.deusexmachina.network.Synchronized;
import com.vinterdo.deusexmachina.utility.LogHelper;

import cofh.api.energy.EnergyStorage;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public abstract class ContainerDEM<T extends TileEntity> extends Container
{
	protected T								te;
	protected final HashMap<Field, Integer>	synced	= new HashMap<Field, Integer>();
	private int								playerSlotRangeStart;
	
	protected ContainerDEM(T _te)
	{
		te = _te;
	}
	
	protected void addPlayerSlots(InventoryPlayer playerInv, int x, int y)
	{
		addPlayerSlots(playerInv, x, y, x, y);
	}
	
	protected void addPlayerSlots(InventoryPlayer playerInv, int x, int y, int hx, int hy)
	{
		playerSlotRangeStart = this.inventorySlots.size();
		int i, j;
		for (i = 0; i < 3; ++i)
		{
			for (j = 0; j < 9; ++j)
			{
				this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, x + j * 18, y + i * 18));
			}
		}
		
		for (i = 0; i < 9; ++i)
		{
			this.addSlotToContainer(new Slot(playerInv, i, hx + i * 18, hy + 58));
		}
	}
	
	public int getPlayerSlotStart()
	{
		return playerSlotRangeStart;
	}
	
	public int getPlayerSlotEnd()
	{
		return playerSlotRangeStart + 36;
	}
	
	@Override
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		for (Field f : te.getClass().getFields())
		{
			if (f.isAnnotationPresent(Synchronized.class))
			{
				if (f.getType() == int.class || f.getType() == Integer.class)
					processInteger(f);
				else if (f.getType() == FluidTank.class)
					processTank(f);
				else if (f.getType() == EnergyStorage.class)
					processEnergy(f);
			}
		}
		
	}
	
	private void processEnergy(Field f)
	{
		if (!synced.containsKey(f))
		{
			synced.put(f, -1);
		}
		try
		{
			int energyAmount = ((EnergyStorage) f.get(te)).getEnergyStored();
			if (energyAmount != synced.get(f))
			{
				for (ICrafting crafter : (List<ICrafting>) crafters)
				{
					crafter.sendProgressBarUpdate(this, f.getAnnotation(Synchronized.class).id(), energyAmount);
				}
				synced.put(f, energyAmount);
			}
		} catch (Exception e)
		{
			LogHelper.error("Illegal access while syncing fields to " + f.getName());
		}
	}
	
	private void processTank(Field f)
	{
		if (!synced.containsKey(f))
		{
			synced.put(f, -1);
		}
		try
		{
			int fluidAmount = ((FluidTank) f.get(te)).getFluidAmount();
			if (fluidAmount != synced.get(f))
			{
				for (ICrafting crafter : (List<ICrafting>) crafters)
				{
					crafter.sendProgressBarUpdate(this, f.getAnnotation(Synchronized.class).id(), fluidAmount);
				}
				synced.put(f, fluidAmount);
				//LogHelper.info("sent packet to " + f.toString() + " with value " + f.get(te).toString());
			}
		} catch (Exception e)
		{
			LogHelper.error("Illegal access while syncing fields to " + f.getName());
		}
	}
	
	private void processInteger(Field f)
	{
		if (!synced.containsKey(f))
		{
			synced.put(f, -1);
		}
		try
		{
			if ((Integer) f.get(te) != synced.get(f))
			{
				for (ICrafting crafter : (List<ICrafting>) crafters)
				{
					crafter.sendProgressBarUpdate(this, f.getAnnotation(Synchronized.class).id(), (Integer) f.get(te));
				}
				synced.put(f, (Integer) f.get(te));
				//LogHelper.info("sent packet to " + f.toString() + " with value " + f.get(te).toString());
			}
		} catch (Exception e)
		{
			LogHelper.error("Illegal access while syncing fields to " + f.getName());
		}
	}
	
	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int target)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot) this.inventorySlots.get(target);
		
		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			
			if (target < getPlayerSlotStart())
			{
				if (!this.mergeItemStack(itemstack1, getPlayerSlotStart(), getPlayerSlotEnd(), true))
				{
					return null;
				}
			} else
			{
				if (mergeStackToContainer(itemstack1))
					return null;
			}
			
			if (itemstack1.stackSize == 0)
			{
				slot.putStack((ItemStack) null);
			} else
			{
				slot.onSlotChanged();
			}
			
			if (itemstack1.stackSize == itemstack.stackSize)
			{
				return null;
			}
			
			slot.onPickupFromSlot(player, itemstack1);
		}
		
		return itemstack;
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player)
	{
		return ((IInventory) te).isUseableByPlayer(player);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int value)
	{
		super.updateProgressBar(id, value);
		for (Field f : te.getClass().getFields())
		{
			if (f.isAnnotationPresent(Synchronized.class) && f.getAnnotation(Synchronized.class).id() == id)
			{
				if (f.getType() == Integer.class || f.getType() == int.class)
					setInteger(value, f);
				else if (f.getType() == FluidTank.class)
					setTank(value, f);
				else if (f.getType() == EnergyStorage.class)
					setEnergy(value, f);
			}
		}
	}
	
	private void setEnergy(int value, Field f)
	{
		try
		{
			((EnergyStorage) f.get(te)).setEnergyStored(value);
		} catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}
	
	private void setTank(int value, Field f)
	{
		try
		{
			((FluidTank) f.get(te)).setFluid(new FluidStack(((FluidTank) f.get(te)).getFluid(), value));
		} catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}
	
	private void setInteger(int value, Field f)
	{
		try
		{
			f.set(te, value);
		} catch (IllegalArgumentException e)
		{
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}
	
	protected boolean mergeStackToContainer(ItemStack itemstack1)
	{
		if (!this.mergeItemStack(itemstack1, 0, getPlayerSlotStart(), false))
			return true;
		return false;
	}
	
}
