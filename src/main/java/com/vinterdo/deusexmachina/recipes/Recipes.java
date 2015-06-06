package com.vinterdo.deusexmachina.recipes;

import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.init.ModItems;

import cofh.api.modhelpers.ThermalExpansionHelper;
import cofh.thermalexpansion.util.crafting.PulverizerManager;
import cofh.thermalexpansion.util.crafting.PulverizerManager.RecipePulverizer;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Recipes 
{
	public static void init()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.essenceContainer, 8), "gig", "g g", "gig", 'g', "blockGlass", 'i', "ingotBronze"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.essenceProcessor, 1), "bbb", "beb", "bbb", 'b', "ingotBronze", 'e', "containerEssence"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.heater, 1), "sss", "scs", "sss", 's', "ingotSteel", 'c', Items.coal));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.camoBlock, 1), "ddd", "dsd", "ddd", 's', "ingotSteel", 'd', Blocks.dirt));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.bronzeDust, 3), "dustCopper", "dustCopper", "dustCopper", "dustTin"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.bronzeIngot, 1), "ingotCopper", "ingotCopper", "ingotCopper", "ingotTin"));
		
		GameRegistry.addSmelting(ModItems.bronzeDust, new ItemStack(ModItems.bronzeIngot), 1);
		GameRegistry.addSmelting(ModItems.copperDust, new ItemStack(ModItems.copperIngot), 1);
		GameRegistry.addSmelting(ModItems.tinDust, new ItemStack(ModItems.tinIngot), 1);
		GameRegistry.addSmelting(ModBlocks.copperOre, new ItemStack(ModItems.copperIngot), 1);
		GameRegistry.addSmelting(ModBlocks.tinOre, new ItemStack(ModItems.tinIngot), 1);
		
	}
}