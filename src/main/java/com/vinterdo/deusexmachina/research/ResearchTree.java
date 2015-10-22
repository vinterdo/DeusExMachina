package com.vinterdo.deusexmachina.research;

import com.vinterdo.deusexmachina.recipes.Recipes;
import com.vinterdo.deusexmachina.reference.Reference;

import net.minecraft.util.ResourceLocation;

public class ResearchTree
{
	ResearchNode root;
	
	public void createTree()
	{
		root = new ResearchNode(Recipes.infusedDiamondRecipe, null, "infused diamond", 100, 5000, 2000, 100, 100,
				new ResourceLocation(Reference.MOD_ID + ":textures/gui/research/researchNanocube.png"));
	}
	
	public void renderTree(int mousex, int mousey, float partialTick, int offsetx, int offsety)
	{
		root.render(mousex, mousey, partialTick, offsetx, offsety);
	}
}
