package com.vinterdo.deusexmachina.research;

import java.util.ArrayList;
import java.util.Collection;

import org.lwjgl.opengl.GL11;

import com.vinterdo.deusexmachina.client.gui.GuiButtonResearch;
import com.vinterdo.deusexmachina.client.gui.GuiDeus;
import com.vinterdo.deusexmachina.recipes.RecipeGrayMatter;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class ResearchNode
{
	
	RecipeGrayMatter recipe;
	
	public RecipeGrayMatter getRecipe()
	{
		return recipe;
	}
	
	public void setRecipe(RecipeGrayMatter recipe)
	{
		this.recipe = recipe;
	}
	
	public Collection<ResearchNode> getChildrens()
	{
		return childrens;
	}
	
	public void setChildrens(Collection<ResearchNode> childrens)
	{
		this.childrens = childrens;
	}
	
	public ResearchNode getParent()
	{
		return parent;
	}
	
	public void setParent(ResearchNode parent)
	{
		this.parent = parent;
		if (parent != null)
			parent.childrens.add(this);
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public boolean isDiscovered()
	{
		return discovered;
	}
	
	public void setDiscovered(boolean discovered)
	{
		this.discovered = discovered;
	}
	
	public int getRfPerSecond()
	{
		return rfPerSecond;
	}
	
	public void setRfPerSecond(int rfPerSecond)
	{
		this.rfPerSecond = rfPerSecond;
	}
	
	public int getGrayMatterCost()
	{
		return grayMatterCost;
	}
	
	public void setGrayMatterCost(int grayMatterCost)
	{
		this.grayMatterCost = grayMatterCost;
	}
	
	public int getTime()
	{
		return time;
	}
	
	public void setTime(int time)
	{
		this.time = time;
	}
	
	Collection<ResearchNode>	childrens;
	ResearchNode				parent;
	String						name;
	boolean						discovered;
	int							rfPerSecond;
	int							grayMatterCost;
	int							time;
	ResourceLocation			icon;
	GuiDeus						gui;
	int							x;
	int							y;
	int							guiTop;
	int							guiLeft;
	private GuiButtonResearch	printButton;
								
	public ResearchNode(RecipeGrayMatter recipe, ResearchNode parent, int rfPerSecond, int grayMatterCost, int time,
			int x, int y, ResourceLocation icon)
	{
		super();
		this.recipe = recipe;
		this.parent = parent;
		this.name = recipe.getName();
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
	
	void setRender(GuiDeus gui)
	{
		this.gui = gui;
		guiLeft = gui.getLeft();
		guiTop = gui.getTop();
		
		printButton = new GuiButtonResearch(0, x, y, 16, 16, "", this);
		gui.addButton(printButton);
	}
	
	public void render(int mousex, int mousey, float partialTick, int offsetx, int offsety)
	{
		int maxX = guiLeft + 229;
		int maxY = guiTop + 171;
		
		int nodePosX = x + offsetx;
		int nodePosY = y + offsety;
		
		printButton.xPosition = nodePosX;
		printButton.yPosition = nodePosY;
		
		if (nodePosX - 5 + 16 > guiLeft && nodePosX + 16 - 16 < maxX && nodePosY - 5 + 16 > guiTop
				&& nodePosY + 16 - 16 < maxY)
		{
			printButton.enabled = true;
			GL11.glDisable(GL11.GL_LIGHTING);
			
			Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
			
			Minecraft.getMinecraft().getTextureManager().bindTexture(icon);
			
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
			
			if (gui.te.activeResearch != null && gui.te.activeResearch.getString("recipe").equals(this.name))
			{
				GL11.glColor4d(0.5, 0.5, 0.5, 1);
				Gui.func_146110_a(renderx - 3, rendery - 3, startx, starty, w + 6, h + 6, 22, 22);
			}
			
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
			
			Gui.func_146110_a(renderx, rendery, startx, starty, w, h, 16, 16);
		} else
		{
			
			printButton.enabled = false;
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
	
	public NBTTagCompound toNBT()
	{
		NBTTagCompound tag = new NBTTagCompound();
		tag.setString("parent", this.parent == null ? "NULL" : this.parent.getName());
		tag.setBoolean("discovered", discovered);
		tag.setInteger("rfPerSec", this.rfPerSecond);
		tag.setInteger("time", this.time);
		tag.setInteger("x", this.x);
		tag.setInteger("y", this.y);
		tag.setInteger("grayMatterCost", this.grayMatterCost);
		tag.setString("icon", icon.getResourceDomain() + ":" + icon.getResourcePath());
		tag.setString("recipe", recipe.getName());
		return tag;
	}
	
	public static ResearchNode fromNBT(NBTTagCompound tag)
	{
		ResearchNode node = new ResearchNode(RecipeGrayMatter.getRecipe(tag.getString("recipe")), null,
				tag.getInteger("rfPerSec"), tag.getInteger("grayMatterCost"), tag.getInteger("time"),
				tag.getInteger("x"), tag.getInteger("y"), new ResourceLocation(tag.getString("icon")));
		node.setDiscovered(tag.getBoolean("discovered"));
		return node;
	}
}
