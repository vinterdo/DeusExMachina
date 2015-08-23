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
		
		this.addSlotToContainer(new Slot(te, 0, 25, 14 + 0 * 21));
		this.addSlotToContainer(new Slot(te, 1, 60 + 0 * 20, 56));
		this.addSlotToContainer(new SlotOutput(te, 2, 134, 14 + 0 * 21));
		
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
