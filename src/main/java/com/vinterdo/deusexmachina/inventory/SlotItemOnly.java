package com.vinterdo.deusexmachina.inventory;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotItemOnly extends SlotDEM
{
	private Item item;
	
	public SlotItemOnly(IInventory inventory, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_, Item _item)
	{
		super(inventory, p_i1824_2_, p_i1824_3_, p_i1824_4_);
		item = _item;
	}
	
	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return item == stack.getItem();
	}
	
}
