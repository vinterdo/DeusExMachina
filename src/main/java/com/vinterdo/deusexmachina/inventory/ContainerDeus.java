package com.vinterdo.deusexmachina.inventory;

import com.vinterdo.deusexmachina.tileentity.TEDeusMaster;

import net.minecraft.entity.player.InventoryPlayer;

public class ContainerDeus extends ContainerDEM<TEDeusMaster>
{
	
	public ContainerDeus(InventoryPlayer playerInv, TEDeusMaster _te)
	{
		super(_te);
		
		addPlayerSlots(playerInv, 6, 176);
	}
	
}
