package com.vinterdo.deusexmachina.client.gui;

import com.vinterdo.deusexmachina.client.gui.widget.WidgetRF;
import com.vinterdo.deusexmachina.client.gui.widget.WidgetTank;
import com.vinterdo.deusexmachina.inventory.ContainerGrayMatterCrafter;
import com.vinterdo.deusexmachina.tileentity.TEGrayMatterCrafterMaster;
import com.vinterdo.deusexmachina.tileentity.base.TEDEM;

import net.minecraft.entity.player.InventoryPlayer;

public class GuiGrayMatterCrafter extends GuiDEM
{
	TEGrayMatterCrafterMaster	te;
	private WidgetTank			widgetTank;
	private WidgetRF			widgetEnergy;
								
	public GuiGrayMatterCrafter(InventoryPlayer playerInv, TEDEM te)
	{
		super(new ContainerGrayMatterCrafter(playerInv, (TEGrayMatterCrafterMaster) te), "grayMatterCrafter", te);
		this.te = (TEGrayMatterCrafterMaster) te;
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		widgetTank = new WidgetTank(this.te.tank, guiLeft + 152, guiTop + 19, 16, 58, canvas);
		widgetEnergy = new WidgetRF(this.te.energy, guiLeft + 134, guiTop + 19, 16, 58, canvas);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mousex, int mousey)
	{
		super.drawGuiContainerBackgroundLayer(partialTick, mousex, mousey);
		
		if (te.progress > 0)
		{
			int i1 = (int) (((float) te.progress / (float) te.progressTarget) * 24f);
			this.drawTexturedModalRect(guiLeft + 82, guiTop + 38, 176, 0, i1 + 1, 16);
		}
	}
}
