package com.vinterdo.deusexmachina.client.gui.widget;

import com.vinterdo.deusexmachina.client.gui.generic.Canvas;
import com.vinterdo.deusexmachina.client.gui.generic.Widget;
import com.vinterdo.deusexmachina.reference.Reference;

import net.minecraft.util.ResourceLocation;

public class WidgetTooltip extends Widget
{
	
	protected static ResourceLocation	tooltipBg	= new ResourceLocation(
			Reference.MOD_ID + ":textures/gui/tooltipBg.png");
			
	protected int						areaheight;
	protected int						areawidth;
	protected String					text;
										
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
	
	public WidgetTooltip(String text, int x, int y, int width, int height, int areawidth, int areaheight, Canvas canvas)
	{
		super(x, y, width, height, canvas);
		this.text = text;
		this.areawidth = areawidth;
		this.areaheight = areaheight;
	}
	
	public WidgetTooltip(String text, int x, int y, Canvas canvas)
	{
		super(x, y, 16, 16, canvas);
		this.text = text;
		areawidth = 16;
		areaheight = 16;
	}
	
	@Override
	public void render()
	{
	}
	
	@Override
	public void postRender()
	{
		if (canvas.mousex > getStartX() && canvas.mousex < getStartX() + areawidth && canvas.mousey > getStartY()
				&& canvas.mousey < getStartY() + areaheight)
		{
			canvas.drawTex(tooltipBg, canvas.mousex, canvas.mousey, Math.max(width, text.length() * 6 + 6), height,
					Math.max(width, text.length() * 6 + 6), height);
					
			canvas.getFontRenderer().drawString(text, canvas.mousex + 6, canvas.mousey + 6, 0xFFFFFFFF);
		}
	}
	
	@Override
	public void update()
	{
	
	}
	
}