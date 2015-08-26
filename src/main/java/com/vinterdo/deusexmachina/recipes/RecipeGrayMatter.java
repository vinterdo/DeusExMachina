package com.vinterdo.deusexmachina.recipes;

import java.util.ArrayList;

import com.vinterdo.deusexmachina.helpers.Helper;

import net.minecraft.item.ItemStack;

public class RecipeGrayMatter
{
	private static ArrayList<RecipeGrayMatter> recipes = new ArrayList<RecipeGrayMatter>();
	
	public int	grayMatter;
	public int	rfPerTick;
	public int	time;
	
	public ItemStack[][] grid;
	
	public ItemStack output;
	
	public RecipeGrayMatter(ItemStack[][] grid, int rfPerTick, int grayMatter, int time, ItemStack output)
	{
		this.grid = grid;
		this.rfPerTick = rfPerTick;
		this.grayMatter = grayMatter;
		this.time = time;
		this.output = output;
	}
	
	public static void addRecipe(RecipeGrayMatter rec)
	{
		recipes.add(rec);
	}
	
	public static RecipeGrayMatter getMatchingRecipe(ItemStack[][] grid)
	{
		for (int i = 0; i < recipes.size(); i++)
		{
			if (gridsMatch(recipes.get(i), grid))
				return recipes.get(i);
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
}
