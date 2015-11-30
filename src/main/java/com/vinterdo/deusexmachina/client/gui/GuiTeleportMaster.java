package com.vinterdo.deusexmachina.client.gui;

import java.util.ArrayList;

import com.vinterdo.deusexmachina.client.gui.widget.WidgetRF;
import com.vinterdo.deusexmachina.client.gui.widget.WidgetTeleportButton;
import com.vinterdo.deusexmachina.inventory.ContainerTeleportMaster;
import com.vinterdo.deusexmachina.tileentity.TETeleportGateMaster;
import com.vinterdo.deusexmachina.tileentity.TETeleportMasterMaster;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiTeleportMaster extends GuiDEM
{
	TETeleportMasterMaster					te;
	private WidgetRF						widgetEnergy;
	private ArrayList<WidgetTeleportButton>	teleports	= new ArrayList<WidgetTeleportButton>();
														
	public GuiTeleportMaster(InventoryPlayer playerInv, TileEntity te)
	{
		super(new ContainerTeleportMaster(playerInv, (TETeleportMasterMaster) te), "teleportMaster");
		this.te = (TETeleportMasterMaster) te;
		xSize = 256;
		ySize = 256;
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		widgetEnergy = new WidgetRF(this.te.energy, guiLeft + 134, guiTop + 19, 58, 16, canvas);
		
		int i = 0;
		for (TETeleportGateMaster teleport : te.teleports)
		{
			if (teleport != null)
			{
				int x = i % 2 == 0 ? 6 : 108;
				int y = 6 + i / 2 * 24;
				teleports.add(new WidgetTeleportButton(teleport, guiLeft + x, guiTop + y, canvas));
				i++;
				
			}
		}
		i = 0;
	}
}
