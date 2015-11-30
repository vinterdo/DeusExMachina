package com.vinterdo.deusexmachina.client.gui;

import com.vinterdo.deusexmachina.client.gui.widget.WidgetRF;
import com.vinterdo.deusexmachina.inventory.ContainerTeleportGate;
import com.vinterdo.deusexmachina.tileentity.TETeleportGateMaster;
import com.vinterdo.deusexmachina.tileentity.base.TEDEM;

import net.minecraft.entity.player.InventoryPlayer;

public class GuiTeleportGate extends GuiDEM
{
	TETeleportGateMaster	te;
	private WidgetRF		widgetEnergy;
							
	public GuiTeleportGate(InventoryPlayer playerInv, TEDEM te)
	{
		super(new ContainerTeleportGate(playerInv, (TETeleportGateMaster) te), "teleportGate", te);
		this.te = (TETeleportGateMaster) te;
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		widgetEnergy = new WidgetRF(this.te.energy, guiLeft + 134, guiTop + 19, 58, 16, canvas);
	}
}
