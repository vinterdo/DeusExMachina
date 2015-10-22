package com.vinterdo.deusexmachina.client.gui;

import com.vinterdo.deusexmachina.inventory.ContainerTerminal;
import com.vinterdo.deusexmachina.tileentity.TETerminal;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiTerminal extends GuiDEM
{
	public GuiTerminal(InventoryPlayer playerInv, TileEntity te)
	{
		super(new ContainerTerminal(playerInv, (TETerminal) te), "terminal");
		
		xSize = 256;
		ySize = 256;
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mousex, int mousey)
	{
		super.drawGuiContainerBackgroundLayer(partialTick, mousex, mousey);
		
	}
}
