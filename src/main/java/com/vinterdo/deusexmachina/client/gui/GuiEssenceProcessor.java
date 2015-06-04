package com.vinterdo.deusexmachina.client.gui;

import com.vinterdo.deusexmachina.inventory.ContainerEssenceProcessor;
import com.vinterdo.deusexmachina.tileentity.TileEntityDEM;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiEssenceProcessor extends GuiDEM
{

	public GuiEssenceProcessor(InventoryPlayer playerInv, TileEntity te) 
	{
		super(new ContainerEssenceProcessor(playerInv, (TileEntityDEM)te), "essenceProcessor");
	}


}
