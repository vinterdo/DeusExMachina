package com.vinterdo.deusexmachina.client.gui.widget;

import org.lwjgl.opengl.GL11;

import com.vinterdo.deusexmachina.client.gui.generic.Canvas;
import com.vinterdo.deusexmachina.reference.Reference;

import cofh.api.energy.EnergyStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;

public class WidgetRF extends WidgetTooltip
{
	private EnergyStorage			energy;
									
	private static ResourceLocation	rfProgressbar	= new ResourceLocation(
			Reference.MOD_ID + ":textures/gui/rfProgressbar.png");
			
	public WidgetRF(EnergyStorage energy, int x, int y, int width, int height, Canvas canvas)
	{
		super("", x, y, 120, 20, width, height, canvas);
		this.energy = energy;
	}
	
	public WidgetRF(EnergyStorage energy, int x, int y, Canvas canvas)
	{
		super("", x, y, 120, 20, 22, 120, canvas);
		this.energy = energy;
	}
	
	@Override
	public void render()
	{
		super.setText("RF: " + energy.getEnergyStored() + "/" + energy.getMaxEnergyStored());
		GL11.glDisable(GL11.GL_LIGHTING);
		
		int amt = energy.getEnergyStored();
		int capacity = energy.getMaxEnergyStored();
		
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		
		float percentage = (float) amt / (float) capacity;
		float pheight = areaheight * percentage;
		Minecraft.getMinecraft().getTextureManager().bindTexture(rfProgressbar);
		GL11.glColor4d(1, 1, 1, 1);
		Gui.func_146110_a(getStartX(), getStartY() + areaheight - (int) pheight, 0, 0, areawidth, (int) pheight, 16,
				16);
				
		super.render();
	}
	
}