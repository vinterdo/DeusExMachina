package com.vinterdo.deusexmachina.client.gui.widget;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;

public abstract class WidgetButton
{
	public int			x;
	public int			y;
	public int			w;
	public int			h;
	ResourceLocation	icon;
	
	public WidgetButton(int x, int y, int w, int h, ResourceLocation icon)
	{
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.icon = icon;
	}
	
	public abstract void onClick();
	
	public void render(int mousex, int mousey, float partialTick, int offsetx, int offsety)
	{
		GL11.glDisable(GL11.GL_LIGHTING);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(icon);
		GL11.glColor4d(1, 1, 1, 1);
		Gui.func_146110_a(x + offsetx, y + offsety, 0, 0, w, h, w, h);
	}
	
}
