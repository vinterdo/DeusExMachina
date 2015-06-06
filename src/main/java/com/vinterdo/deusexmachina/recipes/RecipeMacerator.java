package com.vinterdo.deusexmachina.recipes;

import java.util.ArrayList;

import net.minecraft.item.ItemStack;

public class RecipeMacerator 
{
	private static ArrayList<RecipeMacerator> recipes = new ArrayList<RecipeMacerator>();
	
	public static RecipeMacerator getRecipe(ItemStack input)
	{
		for(RecipeMacerator ch : recipes)
		{
			if(ch.input.getItem() == input.getItem())
			{
				return ch;
			}
		}
		
		return null;
	}
	
	private ItemStack input;
	private ItemStack output;
	private int energy;
	
	public RecipeMacerator(ItemStack _input, ItemStack _output, int _energy)
	{
		this.input = _input;
		this.output = _output;
		this.energy = _energy;
	}
	
	public int getEnergy()
	{
		return energy;
	}
	
	public ItemStack getOutput()
	{
		return output;
	}
	
	public ItemStack getInput()
	{
		return input;
	}
	
}
