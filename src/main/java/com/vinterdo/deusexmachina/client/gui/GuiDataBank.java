package com.vinterdo.deusexmachina.client.gui;

import com.vinterdo.deusexmachina.inventory.ContainerDataBank;
import com.vinterdo.deusexmachina.tileentity.TEDataBank;
import com.vinterdo.deusexmachina.tileentity.base.TEDEM;

import net.minecraft.entity.player.InventoryPlayer;

public class GuiDataBank extends GuiDEM
{
	public GuiDataBank(InventoryPlayer playerInv, TEDEM te)
	{
		super(new ContainerDataBank(playerInv, (TEDataBank) te), "dataBank", te);
		
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
