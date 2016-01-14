package com.vinterdo.deusexmachina.client.gui.widget;

import com.vinterdo.deusexmachina.research.ResearchNode;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;

public class GuiButtonResearch extends GuiButton
{
	public ResearchNode research;
	
	public GuiButtonResearch(int p_i1021_1_, int p_i1021_2_, int p_i1021_3_, int p_i1021_4_, int p_i1021_5_,
			String p_i1021_6_, ResearchNode research)
	{
		super(p_i1021_1_, p_i1021_2_, p_i1021_3_, p_i1021_4_, p_i1021_5_, p_i1021_6_);
		this.research = research;
	}
	
	@Override
	public void drawButton(Minecraft m, int x, int y)
	{
		//GL11.glColor4f(1f, 1f, 1f, 0.1f);
		//super.drawButton(m, x, y);
	}
}
