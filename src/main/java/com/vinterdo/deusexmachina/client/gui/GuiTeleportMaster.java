package com.vinterdo.deusexmachina.client.gui;

import java.util.ArrayList;

import com.vinterdo.deusexmachina.client.gui.generic.Point;
import com.vinterdo.deusexmachina.client.gui.widget.CanvasScrollableY;
//import com.vinterdo.deusexmachina.client.gui.widget.CanvasScrollableY;
import com.vinterdo.deusexmachina.client.gui.widget.WidgetRF;
import com.vinterdo.deusexmachina.client.gui.widget.WidgetTeleportButton;
import com.vinterdo.deusexmachina.inventory.ContainerTeleportMaster;
import com.vinterdo.deusexmachina.tileentity.TETeleportGateMaster;
import com.vinterdo.deusexmachina.tileentity.TETeleportMasterMaster;
import com.vinterdo.deusexmachina.tileentity.base.TEDEM;

import net.minecraft.entity.player.InventoryPlayer;

public class GuiTeleportMaster extends GuiDEM
{
	TETeleportMasterMaster					te;
	private WidgetRF						widgetEnergy;
											
	private ArrayList<WidgetTeleportButton>	teleports	= new ArrayList<WidgetTeleportButton>();
														
	private CanvasScrollableY				buttonsCanvas;
											
	public GuiTeleportMaster(InventoryPlayer playerInv, TEDEM te)
	{
		super(new ContainerTeleportMaster(playerInv, (TETeleportMasterMaster) te), "teleportMaster", te);
		this.te = (TETeleportMasterMaster) te;
		xSize = 256;
		ySize = 256;
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		//widgetEnergy = new WidgetRF(this.te.energy, guiLeft + 134, guiTop + 19, 58, 16, canvas);
		resetTeleports();
	}
	
	private void resetTeleports()
	{
		int offsetY = buttonsCanvas != null ? buttonsCanvas.getOffsetY() : 0;
		canvas.removeWidget(buttonsCanvas);
		for (WidgetTeleportButton widgetTeleport : teleports)
		{
			buttonsCanvas.removeWidget(widgetTeleport);
		}
		
		buttonsCanvas = new CanvasScrollableY(this, canvas);
		buttonsCanvas.setStart(new Point(6 + guiLeft, 5 + guiTop));
		buttonsCanvas.setOffsetY(offsetY);
		buttonsCanvas.width = 166;
		buttonsCanvas.height = 166;
		int i = 0;
		for (TETeleportGateMaster teleport : te.teleports)
		{
			if (teleport != null)
			{
				int x = 6;
				int y = i * 24;
				teleports.add(new WidgetTeleportButton(teleport, x, y, buttonsCanvas));
				i++;
			}
		}
		i = 0;
	}
	
	@Override
	public void refresh()
	{
		resetTeleports();
	}
}
