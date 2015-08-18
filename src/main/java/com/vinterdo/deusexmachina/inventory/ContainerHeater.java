package com.vinterdo.deusexmachina.inventory;

import com.vinterdo.deusexmachina.tileentity.TEHeater;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerHeater extends ContainerDEM<TEHeater>
{
	public ContainerHeater(InventoryPlayer playerInv, TEHeater te)
	{
		super(te);
		
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				this.addSlotToContainer(new SlotFuel(te, j + i * 3, 7 + j * 19, 14 + i * 19));
			}
		}
		
		addPlayerSlots(playerInv, 8, 84);
	}
	
	@Override
	protected boolean mergeStackToContainer(ItemStack itemstack1)
	{
		if (TileEntityFurnace.isItemFuel(itemstack1))
		{
			if (!this.mergeItemStack(itemstack1, 0, 9, false))
				return true;
		}
		
		return false;
	}
	
}
