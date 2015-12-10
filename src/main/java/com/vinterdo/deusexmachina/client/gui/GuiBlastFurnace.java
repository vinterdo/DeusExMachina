package com.vinterdo.deusexmachina.client.gui;

import com.vinterdo.deusexmachina.inventory.ContainerBlastFurnace;
import com.vinterdo.deusexmachina.tileentity.TEBlastFurnaceMaster;
import com.vinterdo.deusexmachina.tileentity.base.TEDEM;

import net.minecraft.entity.player.InventoryPlayer;

public class GuiBlastFurnace extends GuiDEM
{
	TEBlastFurnaceMaster te;
	
	public GuiBlastFurnace(InventoryPlayer playerInv, TEDEM te)
	{
		super(new ContainerBlastFurnace(playerInv, (TEBlastFurnaceMaster) te), "blastFurnace", te);
		this.te = (TEBlastFurnaceMaster) te;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mousex, int mousey)
	{
		super.drawGuiContainerBackgroundLayer(partialTick, mousex, mousey);
		
		if (this.te.progress > 0)
		{
			int i1 = (int) (((float) te.progress / te.progressTarget) * 45f);
			this.drawTexturedModalRect(guiLeft + 64, guiTop + 38, 176, 0, i1 + 1, 16);
		}
		
		if (this.te.burningTime > 0)
		{
			int i1 = (int) (((float) te.burningTime / te.MAX_BURNING_TIME) * 33f);
			this.drawTexturedModalRect(guiLeft + 74, guiTop + 5 + 33 - i1, 176, 16 + 33 - i1, 23, i1 + 1);
		}
	}
}
