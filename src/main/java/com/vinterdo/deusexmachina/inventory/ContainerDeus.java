package com.vinterdo.deusexmachina.inventory;

import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.tileentity.TEDeusMaster;

import net.minecraft.entity.player.InventoryPlayer;

public class ContainerDeus extends ContainerDEM<TEDeusMaster>
{
	
	public ContainerDeus(InventoryPlayer playerInv, TEDeusMaster _te)
	{
		super(_te);
		this.addSlotToContainer(new SlotItemOnly(te, 0, 172, 194, ModItems.researchCore));
		this.addSlotToContainer(new SlotOutput(te, 1, 172, 234));
		this.addSlotToContainer(new SlotItemOnly(te, 2, 172, 175, ModItems.deusDataMatrix));
		addPlayerSlots(playerInv, 6, 176);
	}
	
}
