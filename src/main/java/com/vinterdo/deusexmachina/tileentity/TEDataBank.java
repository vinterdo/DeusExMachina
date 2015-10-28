package com.vinterdo.deusexmachina.tileentity;

import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.tileentity.base.TEI;

import net.minecraft.item.ItemStack;

public class TEDataBank extends TEI
{
	public TEDataBank()
	{
		super();
		this.setNumOfStacks(9);
	}
	
	@Override
	public void updateEntity()
	{
	
	}
	
	@Override
	public String getInventoryName()
	{
		return ModBlocks.dataBank.getUnlocalizedName() + ".name";
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack)
	{
		return itemStack.getItem() == ModItems.researchCore;
	}
}
