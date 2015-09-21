package com.vinterdo.deusexmachina.research;

import java.util.Collection;

import com.vinterdo.deusexmachina.recipes.RecipeGrayMatter;

public class ResearchNode
{
	RecipeGrayMatter			recipe;
	Collection<ResearchNode>	childrens;
	ResearchNode				parent;
	int							rfPerSecond;
	int							grayMatterCost;
	int							time;
	
	/* GUI related */
	int	x;
	int	y;
}
