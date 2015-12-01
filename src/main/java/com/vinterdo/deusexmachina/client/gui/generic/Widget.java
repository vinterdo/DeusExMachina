package com.vinterdo.deusexmachina.client.gui.generic;

public abstract class Widget
{
	protected Point		start;
	protected int		width;
	protected int		height;
	protected Canvas	canvas;
						
	public Widget(Point start, int width, int height, Canvas canvas)
	{
		super();
		this.start = start;
		this.width = width;
		this.height = height;
		this.canvas = canvas;
		
		canvas.addWidget(this);
	}
	
	public Widget(int x, int y, int width, int height, Canvas canvas)
	{
		super();
		this.start = new Point(x, y);
		this.width = width;
		this.height = height;
		this.canvas = canvas;
		if (canvas != null)
			canvas.addWidget(this);
	}
	
	public abstract void update();
	
	public abstract void render();
	
	public abstract void postRender();
	
	public Point getStart()
	{
		return start;
	}
	
	public void setStart(Point start)
	{
		this.start = start;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public void setWidth(int width)
	{
		this.width = width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public void setHeight(int height)
	{
		this.height = height;
	}
	
	public Canvas getCanvas()
	{
		return canvas;
	}
	
	public void setCanvas(Canvas canvas)
	{
		this.canvas = canvas;
	}
}
