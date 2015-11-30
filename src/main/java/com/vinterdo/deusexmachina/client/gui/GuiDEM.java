package com.vinterdo.deusexmachina.client.gui;

import com.vinterdo.deusexmachina.client.gui.generic.Canvas;
import com.vinterdo.deusexmachina.reference.Reference;
import com.vinterdo.deusexmachina.tileentity.base.TEDEM;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public abstract class GuiDEM extends GuiContainer
{
	private ResourceLocation	guiTexture;
	public Canvas				canvas;
	private boolean				shouldRefresh	= false;
												
	public GuiDEM(Container container, String guiTextureName, TEDEM te)
	{
		super(container);
		canvas = new Canvas(this);
		guiTexture = new ResourceLocation(Reference.MOD_ID.toLowerCase() + ":textures/gui/" + guiTextureName + ".png");
		te.gui = this;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY)
	{
		if (shouldRefresh)
			refresh();
			
		mc.getTextureManager().bindTexture(guiTexture);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		canvas.update();
		canvas.render();
		
		canvas.mousex = mouseX;
		canvas.mousey = mouseY;
		canvas.partialTick = partialTick;
	}
	
	public FontRenderer getFontRenderer()
	{
		return this.fontRendererObj;
	}
	
	public void markToRefresh()
	{
		shouldRefresh = true;
	}
	
	public void refresh()
	{
	
	}
}
