package com.vinterdo.deusexmachina.client.gui.widget;

import org.lwjgl.opengl.GL11;

import com.vinterdo.deusexmachina.reference.Reference;

import cofh.api.energy.EnergyStorage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;

public class WidgetRF
{
	private EnergyStorage	energy;
	private int				x;
	private int				y;
	private int				height;
	private int				width;
	
	private static ResourceLocation rfProgressbar = new ResourceLocation(
			Reference.MOD_ID + ":textures/gui/rfProgressbar.png");
			
	public WidgetRF(EnergyStorage energy, int x, int y, int height, int width)
	{
		this.energy = energy;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public WidgetRF(EnergyStorage energy, int x, int y)
	{
		this.energy = energy;
		this.x = x;
		this.y = y;
		width = 16;
		height = 64;
	}
	
	public void render(int mouseX, int mouseY, float partialTick)
	{
		GL11.glDisable(GL11.GL_LIGHTING);
		
		int amt = energy.getEnergyStored();
		int capacity = energy.getMaxEnergyStored();
		
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		
		float percentage = (float) amt / (float) capacity;
		float pheight = height * percentage;
		Minecraft.getMinecraft().getTextureManager().bindTexture(rfProgressbar);
		GL11.glColor4d(1, 1, 1, 1);
		Gui.func_146110_a(x, y + height - (int) pheight, 0, 0, 16, (int) pheight, 16, 16);
	}
	
}