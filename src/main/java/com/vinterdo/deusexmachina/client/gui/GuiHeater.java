package com.vinterdo.deusexmachina.client.gui;

import com.vinterdo.deusexmachina.inventory.ContainerHeater;
import com.vinterdo.deusexmachina.tileentity.TEHeater;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiHeater extends GuiDEM
{
	TEHeater				te;
	public static final int	MAX_BURN_TIME	= 4000;
	
	public GuiHeater(InventoryPlayer playerInv, TileEntity te)
	{
		super(new ContainerHeater(playerInv, (TEHeater) te), "heater");
		this.te = (TEHeater) te;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mousex, int mousey)
	{
		super.drawGuiContainerBackgroundLayer(partialTick, mousex, mousey);
		
		if (te.burningTime > 0)
		{
			int i1 = (int) (((float) te.burningTime / (float) MAX_BURN_TIME) * 30f);
			if (i1 > 30)
				i1 = 30;
			this.drawTexturedModalRect(guiLeft + 113, guiTop + 35 + 30 - i1, 176, 30 - i1, 30, i1);
		}
	}
}
