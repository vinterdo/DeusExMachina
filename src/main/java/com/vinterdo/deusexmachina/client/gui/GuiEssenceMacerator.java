package com.vinterdo.deusexmachina.client.gui;

import com.vinterdo.deusexmachina.inventory.ContainerEssenceMacerator;
import com.vinterdo.deusexmachina.tileentity.TEEssenceMacerator;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiEssenceMacerator extends GuiDEM
{
	TEEssenceMacerator te;
	
	public GuiEssenceMacerator(InventoryPlayer playerInv, TileEntity te)
	{
		super(new ContainerEssenceMacerator(playerInv, (TEEssenceMacerator) te), "essenceMacerator");
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
	}
}
