package com.vinterdo.deusexmachina.client.gui;

import com.vinterdo.deusexmachina.inventory.ContainerEssenceMacerator;
import com.vinterdo.deusexmachina.tileentity.TEEssenceMacerator;
import com.vinterdo.deusexmachina.tileentity.base.TEDEM;

import net.minecraft.entity.player.InventoryPlayer;

public class GuiEssenceMacerator extends GuiDEM
{
	TEEssenceMacerator te;
	
	public GuiEssenceMacerator(InventoryPlayer playerInv, TEDEM te)
	{
		super(new ContainerEssenceMacerator(playerInv, (TEEssenceMacerator) te), "essenceMacerator", te);
		this.te = (TEEssenceMacerator) te;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mousex, int mousey)
	{
		super.drawGuiContainerBackgroundLayer(partialTick, mousex, mousey);
		
		if (this.te.progress > 0)
		{
			int i1 = (int) (this.te.getProgressPercent() * 45f);
			this.drawTexturedModalRect(guiLeft + 64, guiTop + 38, 176, 0, i1 + 1, 16);
		}
		
		if (this.te.power > 0)
		{
			int i1 = (int) (((float) te.power / te.ESSENCE_POWER) * 25f);
			this.drawTexturedModalRect(guiLeft + 80, guiTop + 55 + 25 - i1, 176, 22 + 25 - i1, 18, i1 + 1);
		}
	}
}
