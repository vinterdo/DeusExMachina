package com.vinterdo.deusexmachina.client.gui;

import com.vinterdo.deusexmachina.client.gui.widget.WidgetRF;
import com.vinterdo.deusexmachina.inventory.ContainerTeleportMaster;
import com.vinterdo.deusexmachina.tileentity.TETeleportMasterMaster;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiTeleportMaster extends GuiDEM
{
	TETeleportMasterMaster	te;
	private WidgetRF		widgetEnergy;
							
	public GuiTeleportMaster(InventoryPlayer playerInv, TileEntity te)
	{
		super(new ContainerTeleportMaster(playerInv, (TETeleportMasterMaster) te), "teleportMaster");
		this.te = (TETeleportMasterMaster) te;
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		widgetEnergy = new WidgetRF(this.te.energy, guiLeft + 134, guiTop + 19, 58, 16);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mousex, int mousey)
	{
		super.drawGuiContainerBackgroundLayer(partialTick, mousex, mousey);
		
		widgetEnergy.render(mousex, mousey, partialTick, this.fontRendererObj);
	}
}
