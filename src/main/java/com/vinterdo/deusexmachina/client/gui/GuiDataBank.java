package com.vinterdo.deusexmachina.client.gui;

import com.vinterdo.deusexmachina.inventory.ContainerDataBank;
import com.vinterdo.deusexmachina.tileentity.TEDataBank;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiDataBank extends GuiDEM
{
	public GuiDataBank(InventoryPlayer playerInv, TileEntity te)
	{
		super(new ContainerDataBank(playerInv, (TEDataBank) te), "dataBank");
		
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
