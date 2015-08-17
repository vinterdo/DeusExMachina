package com.vinterdo.deusexmachina.inventory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

import com.vinterdo.deusexmachina.network.Synchronized;
import com.vinterdo.deusexmachina.utility.LogHelper;

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
import net.minecraft.tileentity.TileEntityFurnace;

public abstract class ContainerDEM<T extends TileEntity> extends Container
{
    protected T                       te;
    protected final HashMap<Field, Integer> synced = new HashMap<Field, Integer>();
    private int playerSlotRangeStart;

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

    public void detectAndSendChanges()
    {
        for (Field f : te.getClass().getFields())
        {
            if (f.isAnnotationPresent(Synchronized.class))
            {
            	if(!synced.containsKey(f))
            	{
            		synced.put(f, -1);
            	}
                try
                {
                    if ((int)(Integer)f.get(te) != (int)synced.get(f))
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
                if(mergeStackToContainer(itemstack1)) return null;
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
            	try {
					f.set(te, (Integer)value);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
            }
        }
    }

	protected boolean mergeStackToContainer(ItemStack itemstack1) 
	{
		if (!this.mergeItemStack(itemstack1, 0, getPlayerSlotStart(), false))
			return true;
		return false;
	}

}
