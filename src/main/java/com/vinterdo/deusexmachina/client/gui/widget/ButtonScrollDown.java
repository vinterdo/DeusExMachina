package com.vinterdo.deusexmachina.client.gui.widget;

import com.vinterdo.deusexmachina.client.gui.GuiDeus;

import net.minecraft.util.ResourceLocation;

public class ButtonScrollDown extends WidgetButton
{
	GuiDeus deus;
	
	public ButtonScrollDown(GuiDeus deus, int x, int y, int w, int h, ResourceLocation icon)
	{
		super(x, y, w, h, icon);
		this.deus = deus;
	}
	
	@Override
	public void onClick()
	{
	
	}
	
}
