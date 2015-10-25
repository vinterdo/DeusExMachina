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
	int	guiTop;
	int	guiLeft;
	
	public ResearchNode(RecipeGrayMatter recipe, ResearchNode parent, String name, int rfPerSecond, int grayMatterCost,
			int time, int x, int y, ResourceLocation icon, int guiLeft, int guiTop)
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
		this.guiTop = guiTop;
		this.guiLeft = guiLeft;
		
		if (parent != null)
			parent.childrens.add(this);
	}
	
	public void render(int mousex, int mousey, float partialTick, int offsetx, int offsety)
	{
		int maxX = guiLeft + 229;
		int maxY = guiTop + 171;
		
		int nodePosX = x + offsetx;
		int nodePosY = y + offsety;
		
		if (nodePosX - 5 + 16 > guiLeft && nodePosX + 16 - 16 < maxX && nodePosY - 5 + 16 > guiTop
				&& nodePosY + 16 - 16 < maxY)
		{
			GL11.glDisable(GL11.GL_LIGHTING);
			
			Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
			
			Minecraft.getMinecraft().getTextureManager().bindTexture(icon);
			if (discovered)
			{
				
				GL11.glColor4d(1, 1, 1, 1);
			} else if (parent != null && parent.discovered || parent == null)
			{
				double highligh = Math.sin((System.currentTimeMillis() % 500) * Math.PI * 2 / 500f) * 0.3;
				GL11.glColor4d(0.7 + highligh, 0.7 + highligh, 0.7 + highligh, 1);
			} else
			{
				GL11.glColor4d(0.5, 0.5, 0.5, 1);
			}
			
			int olX = nodePosX + 16 - maxX;
			int ogX = nodePosX - guiLeft - 5;
			int olY = nodePosY + 16 - maxY;
			int ogY = nodePosY - guiTop - 5;
			
			int renderx = nodePosX < guiLeft + 5 ? guiLeft + 5 : nodePosX;
			int rendery = nodePosY < guiTop + 5 ? guiTop + 5 : nodePosY;
			int startx = nodePosX < guiLeft + 5 ? -ogX : 0;
			int starty = nodePosY < guiTop + 5 ? -ogY : 0;
			int w = nodePosX + 16 > maxX ? 16 - olX : nodePosX < guiLeft + 5 ? 16 + ogX : 16;
			int h = nodePosY + 16 > maxY ? 16 - olY : nodePosY < guiTop + 5 ? 16 + ogY : 16;
			
			Gui.func_146110_a(renderx, rendery, startx, starty, w, h, 16, 16);
		}
		
		int vertStartX = clamp(nodePosX + 8 - 3, guiLeft + 5, maxX);
		if (childrens.size() > 0)
		{
			Gui.drawRect(vertStartX, clamp(nodePosY + 16, guiTop + 5, maxY), clamp(nodePosX + 8 + 3, guiLeft + 5, maxX),
					clamp(nodePosY + 16 + 10, guiTop + 5, maxY), 0xFFAAAAAA);
		}
		
		for (ResearchNode node : childrens)
		{
			int vertStartY = clamp(nodePosY + 16 + 4, guiTop + 5, maxY);
			int vertEndX = clamp(node.x + offsetx + 8 - 3, guiLeft + 5, maxX);
			int vertEndY = clamp(nodePosY + 16 + 6 + 4, guiTop + 5, maxY);
			
			Gui.drawRect(vertStartX, vertStartY, vertEndX, vertEndY, 0xFFAAAAAA);
			int horEndX = clamp(node.x + offsetx + 8 + 3, guiLeft + 5, maxX);
			int horEndY = clamp(node.y + offsety, guiTop + 5, maxY);
			Gui.drawRect(vertEndX, vertStartY, horEndX, horEndY, 0xFFAAAAAA);
			node.render(mousex, mousey, partialTick, offsetx, offsety);
		}
	}
	
	private static int clamp(int val, int min, int max)
	{
		return Math.max(min, Math.min(max, val));
	}
}
