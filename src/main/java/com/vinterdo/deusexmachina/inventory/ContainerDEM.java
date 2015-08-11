package com.vinterdo.deusexmachina.inventory;

import com.vinterdo.deusexmachina.tileentity.TEDEM;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;


public abstract class ContainerDEM extends Container
{
	protected final TEDEM te;
	
	protected ContainerDEM(TEDEM _te)
	{
		te = _te;
	}
	
	protected void addPlayerSlots(InventoryPlayer playerInv, int x, int y)
	{
		addPlayerSlots(playerInv, x, y, x, y);
	}
	
	protected void addPlayerSlots(InventoryPlayer playerInv, int x, int y, int hx, int hy)
	{
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
            this.addSlotToContainer(new Slot(playerInv, i, hx + i * 18,  hy + 58));
        }
	}
	

}
