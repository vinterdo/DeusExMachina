package com.vinterdo.deusexmachina.research;

import com.vinterdo.deusexmachina.client.gui.GuiDeus;
import com.vinterdo.deusexmachina.recipes.Recipes;
import com.vinterdo.deusexmachina.reference.Reference;

import net.minecraft.util.ResourceLocation;

public class ResearchTree
{
	ResearchNode	root;
	int				guiLeft;
	int				guiTop;
	
	public ResearchTree(int guiLeft, int guiTop)
	{
		this.guiLeft = guiLeft;
		this.guiTop = guiTop;
	}
	
	public void createTree(GuiDeus guiDeus)
	{
		root = new ResearchNode(Recipes.infusedDiamondRecipe, null, "root", 100, 5000, 2000, 100, 100,
				new ResourceLocation(Reference.MOD_ID + ":textures/gui/research/researchNanocube.png"), guiDeus);
				
		ResearchNode node1 = new ResearchNode(Recipes.infusedDiamondRecipe, root, "child", 100, 5000, 2000, 200, 150,
				new ResourceLocation(Reference.MOD_ID + ":textures/gui/research/researchNanocube.png"), guiDeus);
				
		ResearchNode node2 = new ResearchNode(Recipes.infusedDiamondRecipe, node1, "grandchild1", 100, 5000, 2000, 50,
				200, new ResourceLocation(Reference.MOD_ID + ":textures/gui/research/researchNanocube.png"), guiDeus);
		ResearchNode node3 = new ResearchNode(Recipes.infusedDiamondRecipe, node1, "grandchild2", 100, 5000, 2000, 150,
				200, new ResourceLocation(Reference.MOD_ID + ":textures/gui/research/researchNanocube.png"), guiDeus);
		ResearchNode node4 = new ResearchNode(Recipes.infusedDiamondRecipe, node1, "grandchild3", 100, 5000, 2000, 250,
				200, new ResourceLocation(Reference.MOD_ID + ":textures/gui/research/researchNanocube.png"), guiDeus);
	}
	
	public void renderTree(int mousex, int mousey, float partialTick, int offsetx, int offsety)
	{
		root.render(mousex, mousey, partialTick, offsetx, offsety);
	}
}
