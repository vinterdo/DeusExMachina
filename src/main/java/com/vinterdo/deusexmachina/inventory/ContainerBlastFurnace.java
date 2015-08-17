package com.vinterdo.deusexmachina.inventory;

import com.vinterdo.deusexmachina.tileentity.TEBlastFurnaceMaster;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerBlastFurnace extends ContainerDEM<TEBlastFurnaceMaster>
{
	public ContainerBlastFurnace(InventoryPlayer playerInv, TEBlastFurnaceMaster te)
	{
		super(te);
		this.te = te;
		
		for (int i = 0; i < 3; i++)
		{
			this.addSlotToContainer(new Slot(te, i, 25, 14 + i * 21));
		}
		for (int i = 0; i < 3; i++)
		{
			this.addSlotToContainer(new SlotFuel(te, i + 3, 60 + i * 20, 56));
		}
		for (int i = 0; i < 3; i++)
		{
			this.addSlotToContainer(new SlotOutput(te, i + 6, 134, 14 + i * 21));
		}
		
		addPlayerSlots(playerInv, 8, 84);
	}
	
	@Override
	protected boolean mergeStackToContainer(ItemStack itemstack1)
	{
		if (TileEntityFurnace.isItemFuel(itemstack1))
		{
			if (!this.mergeItemStack(itemstack1, 3, 6, false))
				return true;
		} else
		{
			if (!this.mergeItemStack(itemstack1, 0, 3, false))
				return true;
		}
		
		return false;
	}
	
}
