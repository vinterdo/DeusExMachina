package com.vinterdo.deusexmachina.inventory;

import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.tileentity.TEEssenceProcessor;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerEssenceProcessor extends ContainerDEM<TEEssenceProcessor>
{
	
	public ContainerEssenceProcessor(InventoryPlayer playerInv, TEEssenceProcessor te)
	{
		super(te);
		this.te = te;
		
		for (int i = 0; i < 3; i++)
		{
			this.addSlotToContainer(new SlotItemOnly(te, i, 25, 14 + i * 21, ModItems.essenceContainer));
		}
		for (int i = 3; i < 6; i++)
		{
			this.addSlotToContainer(
					new SlotItemOnly(te, i, 45, 14 + (i - 3) * 21, Item.getItemFromBlock(ModBlocks.essenceOre)));
		}
		for (int i = 6; i < 9; i++)
		{
			this.addSlotToContainer(new SlotOutput(te, i, 134, 14 + (i - 6) * 21));
		}
		
		addPlayerSlots(playerInv, 8, 84);
	}
	
	@Override
	protected boolean mergeStackToContainer(ItemStack itemstack1)
	{
		if (itemstack1.getItem() == ModItems.essenceContainer)
		{
			if (!this.mergeItemStack(itemstack1, 0, 3, false))
				return true;
		}
		
		if (itemstack1.getItem() == Item.getItemFromBlock(ModBlocks.essenceOre))
		{
			if (!this.mergeItemStack(itemstack1, 3, 6, false))
				return true;
		}
		
		return false;
	}
	
}
