package com.vinterdo.deusexmachina.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class Recipes 
{
	public static void init()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.essenceContainer, 16), "gig", "g g", "gig", 'g', "blockGlass", 'i', "ingotIron"));
	}
}
