package com.vinterdo.deusexmachina.client.gui;

import com.vinterdo.deusexmachina.inventory.ContainerEssenceProcessor;
import com.vinterdo.deusexmachina.inventory.ContainerHeater;
import com.vinterdo.deusexmachina.tileentity.TEDEM;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiHeater extends GuiDEM
{

	public GuiHeater(InventoryPlayer playerInv, TileEntity te) 
	{
		super(new ContainerHeater(playerInv, (TEDEM)te), "heater");
	}


}
