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
		
		for (int i = 0; i < 9; i++)
		{
			for (int j = 0; j < 9; j++)
			{
				this.addSlotToContainer(
						new SlotItemOnly(te, j + i * 9, 8 + j * 18, 12 + i * 18, ModItems.researchCore));
			}
		}
		
		addPlayerSlots(playerInv, 8, 174);
	}
	
	@Override
	protected boolean mergeStackToContainer(ItemStack itemstack1)
	{
		if (itemstack1.getItem() == ModItems.researchCore)
		{
			if (!this.mergeItemStack(itemstack1, 0, 81, false))
				return true;
		}
		
		return false;
	}
	
}
