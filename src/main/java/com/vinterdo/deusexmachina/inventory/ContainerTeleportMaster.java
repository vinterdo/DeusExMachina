package com.vinterdo.deusexmachina.inventory;

import com.vinterdo.deusexmachina.tileentity.TETeleportMasterMaster;

import net.minecraft.entity.player.InventoryPlayer;

public class ContainerTeleportMaster extends ContainerDEM<TETeleportMasterMaster>
{
	
	public ContainerTeleportMaster(InventoryPlayer playerInv, TETeleportMasterMaster _te)
	{
		super(_te);
		
		this.addSlotToContainer(new SlotOutput(te, 0, 111, 33));
		
		addPlayerSlots(playerInv, 8, 84);
	}
}
