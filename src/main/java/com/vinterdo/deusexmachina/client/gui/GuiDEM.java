package com.vinterdo.deusexmachina.client.gui;

import com.vinterdo.deusexmachina.client.gui.generic.Canvas;
import com.vinterdo.deusexmachina.reference.Reference;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public abstract class GuiDEM extends GuiContainer
{
	private ResourceLocation	guiTexture;
	public Canvas				canvas;
								
	public GuiDEM(Container container, String guiTextureName)
	{
		super(container);
		canvas = new Canvas(this);
		guiTexture = new ResourceLocation(Reference.MOD_ID.toLowerCase() + ":textures/gui/" + guiTextureName + ".png");
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY)
	{
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
}
