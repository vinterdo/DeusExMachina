package com.vinterdo.deusexmachina.client.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

import com.vinterdo.deusexmachina.client.gui.generic.Canvas;
import com.vinterdo.deusexmachina.reference.Reference;

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
	}
	
	public FontRenderer getFontRenderer()
	{
		return this.fontRendererObj;
	}
}
