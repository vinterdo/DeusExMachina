package com.vinterdo.deusexmachina.client.gui;

import com.vinterdo.deusexmachina.inventory.ContainerDeus;
import com.vinterdo.deusexmachina.tileentity.TEDeusMaster;
import com.vinterdo.deusexmachina.tileentity.base.TEDEM;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiDeus extends GuiDEM
{
	TEDeusMaster te;
	
	public GuiDeus(InventoryPlayer playerInv, TileEntity te)
	{
		super(new ContainerDeus(playerInv, (TEDEM) te), "deus");
		this.te = (TEDeusMaster) te;
		xSize = 256;
		ySize = 256;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mousex, int mousey)
	{
		super.drawGuiContainerBackgroundLayer(partialTick, mousex, mousey);
	}
}
