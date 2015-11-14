package com.vinterdo.deusexmachina.inventory;

import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.tileentity.TETeleportMasterMaster;

import net.minecraft.entity.player.InventoryPlayer;

public class ContainerTeleportMaster extends ContainerDEM<TETeleportMasterMaster>
{
	
	public ContainerTeleportMaster(InventoryPlayer playerInv, TETeleportMasterMaster _te)
	{
		super(_te);
		
		for(int x = 0; x < 4; x++)
		{
			for(int y = 0; y < 13; y++)
			{

				this.addSlotToContainer(new SlotItemOnly(te, x + y * 4, 181 + x * 18, 6 + y * 18, ModItems.machineCard));
			}
		}
		
		addPlayerSlots(playerInv, 8, 174);
	}
}
