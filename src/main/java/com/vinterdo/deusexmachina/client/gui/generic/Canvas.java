package com.vinterdo.deusexmachina.client.gui.generic;

import java.util.LinkedList;

import com.vinterdo.deusexmachina.client.gui.GuiDEM;

import net.minecraft.client.gui.FontRenderer;

public class Canvas
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
		widgets = new LinkedList<Widget>();
		this.gui = gui;
	}
	
	public void render()
	{
		for (Widget w : widgets)
		{
			w.render();
		}
	}
	
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
}
