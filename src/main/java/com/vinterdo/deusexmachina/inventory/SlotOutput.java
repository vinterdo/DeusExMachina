package com.vinterdo.deusexmachina.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class SlotOutput extends SlotDEM
{
	
	public SlotOutput(IInventory inventory, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_)
	{
		super(inventory, p_i1824_2_, p_i1824_3_, p_i1824_4_);
	}
	
	@Override
	public boolean isItemValid(ItemStack item)
	{
		return false;
	}
	
}
