package com.vinterdo.deusexmachina.inventory;


import com.vinterdo.deusexmachina.tileentity.TEShieldPylonBase;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.item.ItemMachineCard;

public class ContainerShieldBase extends ContainerDEM<TEShieldPylonBase>
{
	public ContainerShieldBase(InventoryPlayer playerInv, TEShieldPylonBase te)
	{
		super(te);
		
		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < 2; j++)
			{
				this.addSlotToContainer(new SlotItemOnly(te, j + i * 2, 7 + j * 19, 14 + i * 19, ModItems.machineCard ));
			}
		}
		
		addPlayerSlots(playerInv, 8, 84);
	}
	
	@Override
	protected boolean mergeStackToContainer(ItemStack itemstack1)
	{
		if (TileEntityFurnace.isItemFuel(itemstack1))
		{
			if (!this.mergeItemStack(itemstack1, 0, 4, false))
				return true;
		}
		
		return false;
	}
	
}
