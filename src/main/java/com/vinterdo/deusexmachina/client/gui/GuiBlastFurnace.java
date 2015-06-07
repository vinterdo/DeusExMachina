package com.vinterdo.deusexmachina.client.gui;

import com.vinterdo.deusexmachina.inventory.ContainerBlastFurnace;
import com.vinterdo.deusexmachina.inventory.ContainerEssenceProcessor;
import com.vinterdo.deusexmachina.inventory.ContainerHeater;
import com.vinterdo.deusexmachina.tileentity.TileEntityDEM;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiBlastFurnace extends GuiDEM
{

	public GuiBlastFurnace(InventoryPlayer playerInv, TileEntity te) 
	{
		super(new ContainerBlastFurnace(playerInv, (TileEntityDEM)te), "blastFurnace");
	}


}
