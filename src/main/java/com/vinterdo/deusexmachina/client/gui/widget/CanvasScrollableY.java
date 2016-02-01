package com.vinterdo.deusexmachina.client.gui.widget;

import org.lwjgl.input.Mouse;

import com.vinterdo.deusexmachina.client.gui.GuiDEM;
import com.vinterdo.deusexmachina.client.gui.generic.Canvas;

public class CanvasScrollableY extends Canvas
{
	protected final int	SCROLL_DIVIDER	= 30;
	protected int		offsetY;
						
	public CanvasScrollableY(GuiDEM gui, Canvas parent)
	{
		super(gui, parent);
	}
	
	@Override
	public int getStartY()
	{
		if (canvas != null)
		{
			return canvas.getStartY() + this.start.y + offsetY;
		}
		return start.y + offsetY;
	}
	
	@Override
	public void update()
	{
		int wheel = Mouse.getDWheel() / SCROLL_DIVIDER;
		if (wheel != 0)
		{
			offsetY += wheel;
		}
		
		super.update();
		
	}
	
	public int getOffsetY()
	{
		return offsetY;
	}
	
	public void setOffsetY(int offsetY)
	{
		this.offsetY = offsetY;
	}
}
