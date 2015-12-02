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
		canvas.height = 10000;
		canvas.width = 10000;
		guiTexture = new ResourceLocation(Reference.MOD_ID.toLowerCase() + ":textures/gui/" + guiTextureName + ".png");
		te.gui = this;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY)
	{
		canvas.mousex = mouseX;
		canvas.mousey = mouseY;
		canvas.partialTick = partialTick;
		
		if (shouldRefresh)
			refresh();
			
		mc.getTextureManager().bindTexture(guiTexture);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		canvas.update();
		canvas.render();
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		//canvas.setStart(new Point(guiLeft, guiTop));
	}
	
	@Override
	public void drawScreen(int p_73863_1_, int p_73863_2_, float p_73863_3_)
	{
		super.drawScreen(p_73863_1_, p_73863_2_, p_73863_3_);
		canvas.postRender();
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
	
	public int getLeft()
	{
		return guiLeft;
	}
	
	public int getTop()
	{
		return guiTop;
	}
}
