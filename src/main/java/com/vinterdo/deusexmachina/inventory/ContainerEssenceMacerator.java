package com.vinterdo.deusexmachina.inventory;

import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.tileentity.TEEssenceMacerator;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerEssenceMacerator extends ContainerDEM<TEEssenceMacerator>
{
	
	public ContainerEssenceMacerator(InventoryPlayer playerInv, TEEssenceMacerator te)
	{
		super(te);
		this.te = te;
		
		for (int i = 0; i < 3; i++)
		{
			this.addSlotToContainer(new Slot(te, i, 25, 14 + i * 21));
		}
		
		this.addSlotToContainer(new SlotItemOnly(te, 3, 45, 35, ModItems.essence));
		
		for (int i = 4; i < 7; i++)
		{
			this.addSlotToContainer(new SlotOutput(te, i, 134, 14 + (i - 4) * 21));
		}
		
		this.addSlotToContainer(new SlotOutput(te, 7, 113, 35));
		
		addPlayerSlots(playerInv, 8, 84);
	}
	
	@Override
	protected boolean mergeStackToContainer(ItemStack itemstack1)
	{
		if (itemstack1.getItem() == ModItems.essence)
		{
			if (!this.mergeItemStack(itemstack1, 3, 4, false))
				return true;
		}
		
		if (!this.mergeItemStack(itemstack1, 0, 3, false))
			return true;
			
		return false;
	}
	
}
