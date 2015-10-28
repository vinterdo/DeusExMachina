package com.vinterdo.deusexmachina.inventory;

import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.tileentity.TEDataBank;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;

public class ContainerDataBank extends ContainerDEM<TEDataBank>
{
	public ContainerDataBank(InventoryPlayer playerInv, TEDataBank te)
	{
		super(te);
		
		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				this.addSlotToContainer(
						new SlotItemOnly(te, j + i * 3, 7 + j * 19, 14 + i * 19, ModItems.researchCore));
			}
		}
		
		addPlayerSlots(playerInv, 8, 84);
	}
	
	@Override
	protected boolean mergeStackToContainer(ItemStack itemstack1)
	{
		if (itemstack1.getItem() == ModItems.researchCore)
		{
			if (!this.mergeItemStack(itemstack1, 0, 9, false))
				return true;
		}
		
		return false;
	}
	
}
