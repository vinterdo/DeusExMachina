package com.vinterdo.deusexmachina.recipes;

import java.util.HashMap;
import java.util.Map.Entry;

import com.vinterdo.deusexmachina.helpers.Helper;

import net.minecraft.item.ItemStack;

public class RecipeGrayMatter
{
	private static HashMap<String, RecipeGrayMatter>	recipes	= new HashMap<String, RecipeGrayMatter>();
																
	public int											grayMatter;
	public int											rfPerTick;
	public int											time;
														
	private String										name;
														
	public ItemStack[][]								grid;
														
	public ItemStack									output;
														
	public boolean										isPublic;
														
	public RecipeGrayMatter(String name, ItemStack[][] grid, int rfPerTick, int grayMatter, int time, ItemStack output,
			boolean isPublic)
	{
		this.name = name;
		this.grid = grid;
		this.rfPerTick = rfPerTick;
		this.grayMatter = grayMatter;
		this.time = time;
		this.output = output;
		this.isPublic = isPublic;
	}
	
	public static void addRecipe(RecipeGrayMatter rec)
	{
		recipes.put(rec.getName(), rec);
	}
	
	public String getName()
	{
		return name;
	}
	
	public static RecipeGrayMatter getMatchingRecipe(ItemStack[][] grid)
	{
		for (Entry<String, RecipeGrayMatter> recipe : recipes.entrySet())
		{
			if (gridsMatch(recipe.getValue(), grid))
				return recipe.getValue();
		}
		return null;
	}
	
	private static boolean gridsMatch(RecipeGrayMatter recipeGrayMatter, ItemStack[][] grid)
	{
		for (int y = 0; y < 4; y++)
		{
			for (int x = 0; x < 4; x++)
			{
				if (grid[x][y] == null && recipeGrayMatter.grid[x][y] == null)
					continue;
				if (grid[x][y] == null && recipeGrayMatter.grid[x][y] != null)
					return false;
				if (grid[x][y] != null && recipeGrayMatter.grid[x][y] == null)
					return false;
				if (!(Helper.areItemStacksStackable(grid[x][y], recipeGrayMatter.grid[x][y])
						&& grid[x][y].stackSize >= recipeGrayMatter.grid[x][y].stackSize))
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public static RecipeGrayMatter getRecipe(String name)
	{
		return recipes.get(name);
	}
}
