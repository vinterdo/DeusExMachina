package com.vinterdo.deusexmachina.client.gui;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

import com.vinterdo.deusexmachina.client.gui.widget.WidgetRF;
import com.vinterdo.deusexmachina.inventory.ContainerTeleportGate;
import com.vinterdo.deusexmachina.tileentity.TETeleportGateMaster;

public class GuiTeleportGate extends GuiDEM
{
	TETeleportGateMaster	te;
	private WidgetRF		widgetEnergy;
	
	public GuiTeleportGate(InventoryPlayer playerInv, TileEntity te)
	{
		super(new ContainerTeleportGate(playerInv, (TETeleportGateMaster) te), "teleportGate");
		this.te = (TETeleportGateMaster) te;
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		widgetEnergy = new WidgetRF(this.te.energy, guiLeft + 134, guiTop + 19, 58, 16, canvas);
	}
}
