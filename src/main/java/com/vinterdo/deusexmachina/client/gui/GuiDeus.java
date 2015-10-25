package com.vinterdo.deusexmachina.client.gui;

import org.lwjgl.input.Mouse;

import com.vinterdo.deusexmachina.client.gui.widget.WidgetRF;
import com.vinterdo.deusexmachina.client.gui.widget.WidgetTank;
import com.vinterdo.deusexmachina.inventory.ContainerDeus;
import com.vinterdo.deusexmachina.network.MessageHandleGuiButtonResearch;
import com.vinterdo.deusexmachina.network.NetworkHandler;
import com.vinterdo.deusexmachina.research.ResearchTree;
import com.vinterdo.deusexmachina.tileentity.TEDeusMaster;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiDeus extends GuiDEM
{
	TEDeusMaster			te;
	private WidgetTank		widgetTank;
	private WidgetRF		widgetEnergy;
	private ResearchTree	research;
	
	protected float	offsetx	= guiLeft;
	protected float	offsety	= guiTop;
	
	private boolean	wasMouseDown	= false;
	private int		oldMouseX;
	private int		oldMouseY;
	
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
		research = new ResearchTree(guiLeft, guiTop);
		research.createTree(this);
		
		offsetx = guiLeft;
		offsety = guiTop;
	}
	
	@Override
	protected void actionPerformed(GuiButton button)
	{
		if (button.id == 0 && button instanceof GuiButtonResearch)
		{
			NetworkHandler.sendToServer(
					new MessageHandleGuiButtonResearch(this.te, 0, ((GuiButtonResearch) button).research.getName()));
			((GuiButtonResearch) button).research.setDiscovered(true);
		}
	}
	
	public void addButton(GuiButton button)
	{
		buttonList.add(button);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mousex, int mousey)
	{
		super.drawGuiContainerBackgroundLayer(partialTick, mousex, mousey);
		
		if (wasMouseDown)
		{
			offsetx += (-oldMouseX + Mouse.getX()) / 2f;
			offsety += (oldMouseY - Mouse.getY()) / 2f;
		}
		
		if (Mouse.isButtonDown(0))
		{
			wasMouseDown = true;
			oldMouseX = Mouse.getX();
			oldMouseY = Mouse.getY();
		} else
		{
			wasMouseDown = false;
		}
		widgetTank.render(mousex, mousey, partialTick);
		widgetEnergy.render(mousex, mousey, partialTick);
		research.renderTree(mousex, mousey, partialTick, (int) offsetx, (int) offsety);
	}
	
	public int getTop()
	{
		return guiTop;
	}
	
	public int getLeft()
	{
		return guiLeft;
	}
}
