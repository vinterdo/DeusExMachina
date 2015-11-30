package com.vinterdo.deusexmachina.client.gui;

import com.vinterdo.deusexmachina.inventory.ContainerEssenceProcessor;
import com.vinterdo.deusexmachina.tileentity.TEEssenceProcessor;
import com.vinterdo.deusexmachina.tileentity.base.TEDEM;

import net.minecraft.entity.player.InventoryPlayer;

public class GuiEssenceProcessor extends GuiDEM
{
	TEEssenceProcessor te;
	
	public GuiEssenceProcessor(InventoryPlayer playerInv, TEDEM te)
	{
		super(new ContainerEssenceProcessor(playerInv, (TEEssenceProcessor) te), "essenceProcessor", te);
		this.te = (TEEssenceProcessor) te;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mousex, int mousey)
	{
		super.drawGuiContainerForegroundLayer(mousex, mousey);
		//this.fontRendererObj.drawString("" + te.getProgress(), guiLeft + 10, guiTop + 10, 0xFF000000);
		
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mousex, int mousey)
	{
		super.drawGuiContainerBackgroundLayer(partialTick, mousex, mousey);
		
		if (this.te.progress > 0)
		{
			
			int i1 = (int) (this.te.progress / (100f / 45f));
			this.drawTexturedModalRect(guiLeft + 70, guiTop + 38, 176, 0, i1 + 1, 16);
		}
	}
}
