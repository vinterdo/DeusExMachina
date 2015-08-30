package com.vinterdo.deusexmachina.helpers;

import net.minecraft.item.ItemStack;

public class Helper
{
	public static boolean areItemStacksStackable(ItemStack a, ItemStack b)
	{
		return a == null & b == null ? true
				: a != null && b != null && a.getItem() == b.getItem() && a.getItemDamage() == b.getItemDamage();
	}
}
