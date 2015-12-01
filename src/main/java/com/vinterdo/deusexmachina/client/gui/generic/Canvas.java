package com.vinterdo.deusexmachina.client.gui.generic;

import java.util.LinkedList;

import org.lwjgl.opengl.GL11;

import com.vinterdo.deusexmachina.client.gui.GuiDEM;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;

public class Canvas extends Widget
{
	private LinkedList<Widget>	widgets;
	public Point				start;
	public int					width;
	public int					height;
	public int					mousex;
	public int					mousey;
	public float				partialTick;
	private GuiDEM				gui;
								
	public Canvas(GuiDEM gui)
	{
		super(gui.getLeft(), gui.getTop(), gui.width, gui.height, null);
		widgets = new LinkedList<Widget>();
		start = new Point(0, 0);
		this.gui = gui;
	}
	
	@Override
	public void render()
	{
		for (Widget w : widgets)
		{
			w.render();
		}
	}
	
	@Override
	public void update()
	{
		for (Widget w : widgets)
		{
			w.update();
		}
	}
	
	public void addWidget(Widget w)
	{
		if (w.getCanvas() != null)
		{
			w.getCanvas().removeWidget(w);
		}
		w.setCanvas(this);
		widgets.add(w);
	}
	
	public void removeWidget(Widget w)
	{
		this.widgets.remove(w);
		
	}
	
	public FontRenderer getFontRenderer()
	{
		return gui.getFontRenderer();
	}
	
	public void drawTex(ResourceLocation tex, int x, int y, int width, int height)
	{
		drawTex(tex, x, y, width, height, width, height);
	}
	
	public void drawTex(ResourceLocation tex, int x, int y, int width, int height, int sourceWidth, int sourceHeight)
	{
		GL11.glDisable(GL11.GL_LIGHTING);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(tex);
		GL11.glColor4d(1, 1, 1, 1);
		Gui.func_146110_a(start.x + x, start.y + y, 0, 0, width, height, sourceWidth, sourceHeight);
	}
	
	public void drawString(String text, int x, int y, int maxWidth, int color)
	{
		if (text.length() > maxWidth / 5)
			text = text.substring(0, maxWidth / 5);
		getFontRenderer().drawString(text, this.start.x + x, this.start.y + y, color);
	}
	
	@Override
	public void postRender()
	{
		for (Widget w : widgets)
		{
			w.postRender();
		}
	}
}
