package com.vinterdo.deusexmachina.client.gui;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.input.Mouse;

import com.vinterdo.deusexmachina.client.gui.widget.WidgetRF;
import com.vinterdo.deusexmachina.client.gui.widget.WidgetTank;
import com.vinterdo.deusexmachina.client.gui.widget.WidgetTooltip;
import com.vinterdo.deusexmachina.inventory.ContainerDeus;
import com.vinterdo.deusexmachina.network.MessageHandleGuiButtonResearch;
import com.vinterdo.deusexmachina.network.NetworkHandler;
import com.vinterdo.deusexmachina.research.ResearchTree;
import com.vinterdo.deusexmachina.tileentity.TEDeusMaster;

public class GuiDeus extends GuiDEM
{
	public TEDeusMaster			te;
	private WidgetTank			widgetTank;
	private WidgetRF			widgetEnergy;
	private ResearchTree		research;
	private List<WidgetTooltip>	tooltips;
	
	protected float				offsetx			= guiLeft;
	protected float				offsety			= guiTop;
	
	private boolean				wasMouseDown	= false;
	private int					oldMouseX;
	private int					oldMouseY;
	
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
		tooltips = new LinkedList<WidgetTooltip>();
		widgetTank = new WidgetTank(this.te.tank, guiLeft + 233, guiTop + 192, 58, 16, canvas);
		widgetEnergy = new WidgetRF(this.te.energy, guiLeft + 215, guiTop + 192, 58, 16, canvas);
		research = te.getStackInSlot(2) == null ? new ResearchTree()
				: te.getStackInSlot(2).stackTagCompound == null ? new ResearchTree() : ResearchTree.fromNBT(te
						.getStackInSlot(2).stackTagCompound);
		research.setRender(this);
		
		offsetx = guiLeft;
		offsety = guiTop;
		
	}
	
	public void addTooltip(WidgetTooltip tip)
	{
		tooltips.add(tip);
	}
	
	public void removeTooltip(WidgetTooltip tip)
	{
		tooltips.remove(tip);
	}
	
	@Override
	public void onGuiClosed()
	{
		super.onGuiClosed();
		//if (research.getRoot() != null && te.getStackInSlot(2) != null)
		//	te.getStackInSlot(2).stackTagCompound = research.toNBT();
		
	}
	
	@Override
	protected void actionPerformed(GuiButton button)
	{
		if (button.id == 0 && button instanceof GuiButtonResearch)
		{
			NetworkHandler.sendToServer(new MessageHandleGuiButtonResearch(this.te, 0,
					((GuiButtonResearch) button).research.getName()));
			
		}
	}
	
	public void addButton(GuiButton button)
	{
		buttonList.add(button);
	}
	
	private static int clamp(int val, int min, int max)
	{
		return Math.max(min, Math.min(max, val));
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mousex, int mousey)
	{
		super.drawGuiContainerBackgroundLayer(partialTick, mousex, mousey);
		
		int maxX = guiLeft + 229;
		int maxY = guiTop + 171;
		
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
		}
		else
		{
			wasMouseDown = false;
		}
		
		Gui.drawRect(clamp(te.highlightX + (int) offsetx - 2, guiLeft + 5, maxX),
				clamp(te.highlightY + (int) offsety - 2, guiTop + 5, maxY),
				clamp(te.highlightX + 18 + (int) offsetx, guiLeft + 5, maxX),
				clamp(te.highlightY + 18 + (int) offsety, guiTop + 5, maxY), 0xFF00FFFF);
		
		research.renderTree(mousex, mousey, partialTick, (int) offsetx, (int) offsety);
		
		if (te.coreChanged == 1)
		{
			research = te.getStackInSlot(2) == null ? new ResearchTree()
					: te.getStackInSlot(2).stackTagCompound == null ? new ResearchTree() : ResearchTree.fromNBT(te
							.getStackInSlot(2).stackTagCompound);
			research.setRender(this);
		}
		
		Gui.drawRect(guiLeft + 197, guiTop + 192, guiLeft + 201, (int) (guiTop + 192 + (te.progress * 1f
				/ te.progressTarget * 1f) * 58), 0xFF00FFFF);
		
		Gui.drawRect(guiLeft + 203, guiTop + 192, guiLeft + 207, (int) (guiTop + 192 + (te.gmConsumed * 1f
				/ te.gmTarget * 1f) * 58), 0xFF888888);
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mousex, int mousey)
	{
		super.drawGuiContainerForegroundLayer(mousex, mousey);
		
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
