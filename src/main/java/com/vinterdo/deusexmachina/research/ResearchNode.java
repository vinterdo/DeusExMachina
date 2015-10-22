package com.vinterdo.deusexmachina.research;

import java.util.ArrayList;
import java.util.Collection;

import org.lwjgl.opengl.GL11;

import com.vinterdo.deusexmachina.recipes.RecipeGrayMatter;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;

public class ResearchNode
{
	
	public ResearchNode(RecipeGrayMatter recipe, ResearchNode parent, String name, int rfPerSecond, int grayMatterCost,
			int time, int x, int y, ResourceLocation icon)
	{
		super();
		this.recipe = recipe;
		this.parent = parent;
		this.name = name;
		this.rfPerSecond = rfPerSecond;
		this.grayMatterCost = grayMatterCost;
		this.time = time;
		this.x = x;
		this.y = y;
		this.childrens = new ArrayList<ResearchNode>();
		this.icon = icon;
		
		if (parent != null)
			parent.childrens.add(this);
	}
	
	RecipeGrayMatter			recipe;
	Collection<ResearchNode>	childrens;
	ResearchNode				parent;
	String						name;
	boolean						discovered;
	int							rfPerSecond;
	int							grayMatterCost;
	int							time;
	ResourceLocation			icon;
	
	int	x;
	int	y;
	
	public void render(int mousex, int mousey, float partialTick, int offsetx, int offsety)
	{
		GL11.glDisable(GL11.GL_LIGHTING);
		
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(icon);
		GL11.glColor4d(1, 1, 1, 1);
		Gui.func_146110_a(x + offsetx, y + offsety, 0, 0, 16, 16, 16, 16);
	}
}
