package com.vinterdo.deusexmachina.client.gui;

import com.vinterdo.deusexmachina.client.gui.widget.WidgetRF;
import com.vinterdo.deusexmachina.client.gui.widget.WidgetTank;
import com.vinterdo.deusexmachina.inventory.ContainerDeus;
import com.vinterdo.deusexmachina.research.ResearchTree;
import com.vinterdo.deusexmachina.tileentity.TEDeusMaster;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiDeus extends GuiDEM
{
	TEDeusMaster			te;
	private WidgetTank		widgetTank;
	private WidgetRF		widgetEnergy;
	private ResearchTree	research;
	
	protected int	offsetx	= guiLeft;
	protected int	offsety	= guiTop;
	
	public GuiDeus(InventoryPlayer playerInv, TileEntity te)
	{
		super(new ContainerDeus(playerInv, (TEDeusMaster) te), "deus");
		this.te = (TEDeusMaster) te;
		xSize = 256;
		ySize = 256;
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		widgetTank = new WidgetTank(this.te.tank, guiLeft + 152, guiTop + 19, 58, 16);
		widgetEnergy = new WidgetRF(this.te.energy, guiLeft + 215, guiTop + 192, 58, 16);
		research = new ResearchTree();
		research.createTree();
		
		offsetx = guiLeft;
		offsety = guiTop;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mousex, int mousey)
	{
		super.drawGuiContainerBackgroundLayer(partialTick, mousex, mousey);
		
		widgetTank.render(mousex, mousey, partialTick);
		widgetEnergy.render(mousex, mousey, partialTick);
		research.renderTree(mousex, mousey, partialTick, offsetx, offsety);
	}
}
