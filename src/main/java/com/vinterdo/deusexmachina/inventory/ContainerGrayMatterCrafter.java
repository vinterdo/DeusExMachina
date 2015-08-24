package com.vinterdo.deusexmachina.inventory;

import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.tileentity.TEGrayMatterCrafterMaster;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerGrayMatterCrafter extends ContainerDEM<TEGrayMatterCrafterMaster>
{
	
	public ContainerGrayMatterCrafter(InventoryPlayer playerInv, TEGrayMatterCrafterMaster _te)
	{
		super(_te);
		
		for (int y = 0; y < 4; y++)
		{
			for (int x = 0; x < 4; x++)
			{
				this.addSlotToContainer(new Slot(te, y * 4 + x, 8 + x * 18, 7 + y * 18));
			}
		}
		this.addSlotToContainer(new SlotOutput(te, 16, 111, 33));
		
		addPlayerSlots(playerInv, 8, 84);
	}
	
	@Override
	protected boolean mergeStackToContainer(ItemStack itemstack1)
	{
		if (itemstack1.getItem() == ModItems.steelIngot)
		{
			if (!this.mergeItemStack(itemstack1, 0, 1, false))
				return true;
		} else if (itemstack1.getItem() == ModItems.essence)
		{
			if (!this.mergeItemStack(itemstack1, 1, 2, false))
				return true;
		}
		
		return false;
	}
}
