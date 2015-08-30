package com.vinterdo.deusexmachina.client.gui;

import com.vinterdo.deusexmachina.reference.Reference;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public abstract class GuiDEM extends GuiContainer
{
	private ResourceLocation guiTexture;
	
	public GuiDEM(Container container, String guiTextureName)
	{
		super(container);
		guiTexture = new ResourceLocation(Reference.MOD_ID.toLowerCase() + ":textures/gui/" + guiTextureName + ".png");
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mouseX, int mouseY)
	{
		mc.getTextureManager().bindTexture(guiTexture);
		this.drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
}
