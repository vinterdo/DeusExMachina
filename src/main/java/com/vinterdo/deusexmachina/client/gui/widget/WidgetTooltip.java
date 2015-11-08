package com.vinterdo.deusexmachina.client.gui.widget;

import org.lwjgl.opengl.GL11;

import com.vinterdo.deusexmachina.reference.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;

public class WidgetTooltip
{
	private int		x;
	private int		y;
	private int		height;
	private int		width;
	private int		areaheight;
	private int		areawidth;
	private String	text;
					
	public int getX()
	{
		return x;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public void setHeight(int height)
	{
		this.height = height;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public void setWidth(int width)
	{
		this.width = width;
	}
	
	public int getAreaheight()
	{
		return areaheight;
	}
	
	public void setAreaheight(int areaheight)
	{
		this.areaheight = areaheight;
	}
	
	public int getAreawidth()
	{
		return areawidth;
	}
	
	public void setAreawidth(int areawidth)
	{
		this.areawidth = areawidth;
	}
	
	public String getText()
	{
		return text;
	}
	
	public void setText(String text)
	{
		this.text = text;
	}
	
	private static ResourceLocation tooltipBg = new ResourceLocation(Reference.MOD_ID + ":textures/gui/tooltipBg.png");
	
	public WidgetTooltip(String text, int x, int y, int height, int width, int areaheight, int areawidth)
	{
		this.text = text;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.areawidth = areawidth;
		this.areaheight = areaheight;
	}
	
	public WidgetTooltip(String text, int x, int y)
	{
		this.text = text;
		this.x = x;
		this.y = y;
		width = 80;
		height = 22;
		areawidth = 16;
		areaheight = 16;
	}
	
	public void render(int mouseX, int mouseY, float partialTick, FontRenderer fontRendererObj)
	{
		if (mouseX > x && mouseX < x + areawidth && mouseY > y && mouseY < y + areaheight)
		{
			
			GL11.glDisable(GL11.GL_LIGHTING);
			
			Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
			
			Minecraft.getMinecraft().getTextureManager().bindTexture(tooltipBg);
			GL11.glColor4d(1, 1, 1, 1);
			Gui.func_146110_a(mouseX, mouseY, 0, 0, width, height, width, height);
			fontRendererObj.drawString(text, mouseX + 6, mouseY + 6, 0xFFFFFFFF);
		}
	}
	
}