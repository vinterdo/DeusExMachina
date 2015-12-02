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
	public int					mousex;
	public int					mousey;
	public float				partialTick;
	private GuiDEM				gui;
								
	public Canvas(GuiDEM gui)
	{
		super(gui.getLeft(), gui.getTop(), gui.width, gui.height, null);
		widgets = new LinkedList<Widget>();
		this.gui = gui;
	}
	
	public Canvas(GuiDEM gui, Canvas parent)
	{
		super(gui.getLeft(), gui.getTop(), gui.width, gui.height, null);
		widgets = new LinkedList<Widget>();
		this.gui = gui;
		parent.addWidget(this);
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
		if (canvas != null)
		{
			
			mousex = canvas.mousex;
			mousey = canvas.mousey;
			partialTick = canvas.partialTick;
		}
		
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
		int drawx = clamp(x, start.x, start.x + this.width);
		int drawy = clamp(y, start.y, start.y + this.height);
		int drawwidth = Math.min(width, Math.min(x + width - start.x, -(x - (start.x + this.width))));
		int drawheight = Math.min(height, Math.min(y + height - start.y, -(y - (start.y + this.height))));
		int drawsourcew = sourceWidth;
		int drawsourceh = sourceHeight;
		Gui.func_146110_a(drawx, drawy, 0, 0, drawwidth, drawheight, drawsourcew, drawsourceh);
	}
	
	public void drawString(String text, int x, int y, int maxWidth, int color)
	{
		if (text.length() > maxWidth / 5)
			text = text.substring(0, maxWidth / 5);
		getFontRenderer().drawString(text, x, y, color);
	}
	
	@Override
	public void postRender()
	{
		for (Widget w : widgets)
		{
			w.postRender();
		}
	}
	
	private static int clamp(int val, int min, int max)
	{
		return Math.max(min, Math.min(max, val));
	}
}
