package com.vinterdo.deusexmachina.inventory;

import com.vinterdo.deusexmachina.tileentity.TETeleportGateMaster;
import com.vinterdo.deusexmachina.tileentity.TETeleportMasterMaster;

import net.minecraft.entity.player.InventoryPlayer;

public class ContainerTeleportGate extends ContainerDEM<TETeleportGateMaster>
{
	
	public ContainerTeleportGate(InventoryPlayer playerInv, TETeleportGateMaster _te)
	{
		super(_te); 
		
		this.addSlotToContainer(new SlotOutput(te, 0, 111, 33));
		
		//addPlayerSlots(playerInv, 8, 84);
	}
}
