package com.vinterdo.deusexmachina.client.gui.generic;

public abstract class Widget
{
	protected Point		start;
	public int			width;
	public int			height;
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
	
	public int getStartX()
	{
		if (canvas != null)
		{
			return canvas.getStartX() + this.start.x;
		}
		return start.x;
	}
	
	public int getStartY()
	{
		if (canvas != null)
		{
			return canvas.getStartY() + this.start.y;
		}
		return start.y;
	}
	
	public void setX(int x)
	{
		this.start.setX(x);
		
	}
	
	public void setY(int y)
	{
		this.start.setY(y);
		
	}
}
