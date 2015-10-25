package com.vinterdo.deusexmachina.recipes;

import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.init.ModItems;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

public class Recipes
{
	public static RecipeGrayMatter	infusedDiamondRecipe;
	private static RecipeGrayMatter	researchCoreRecipe;
	
	public static void init()
	{
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.essenceContainer, 8), "gig", "g g", "gig",
				'g', "blockGlass", 'i', "ingotBronze"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.essenceProcessor, 1), "bbb", "beb", "bbb",
				'b', "ingotBronze", 'e', "containerEssence"));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.heater, 1), "sss", "scs", "sss", 's',
				"ingotIron", 'c', Items.coal));
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.camoBlock, 1), "ddd", "dsd", "ddd", 's',
				"ingotSteel", 'd', Blocks.dirt));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.bronzeDust, 3), "dustCopper", "dustCopper",
				"dustCopper", "dustTin"));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.bronzeIngot, 1), "ingotCopper",
				"ingotCopper", "ingotCopper", "ingotTin"));
				
		GameRegistry.addSmelting(ModItems.bronzeDust, new ItemStack(ModItems.bronzeIngot), 1);
		GameRegistry.addSmelting(ModItems.copperDust, new ItemStack(ModItems.copperIngot), 1);
		GameRegistry.addSmelting(ModItems.tinDust, new ItemStack(ModItems.tinIngot), 1);
		GameRegistry.addSmelting(ModBlocks.copperOre, new ItemStack(ModItems.copperIngot), 1);
		GameRegistry.addSmelting(ModBlocks.tinOre, new ItemStack(ModItems.tinIngot), 1);
		
		RecipeMacerator.addRecipe(
				new RecipeMacerator(new ItemStack(ModBlocks.copperOre), new ItemStack(ModItems.copperDust, 2), 4000));
		RecipeMacerator.addRecipe(
				new RecipeMacerator(new ItemStack(ModBlocks.tinOre), new ItemStack(ModItems.tinDust, 2), 4000));
		RecipeMacerator.addRecipe(
				new RecipeMacerator(new ItemStack(ModItems.copperIngot), new ItemStack(ModItems.copperDust, 1), 4000));
		RecipeMacerator.addRecipe(
				new RecipeMacerator(new ItemStack(ModItems.tinIngot), new ItemStack(ModItems.tinDust, 1), 4000));
		RecipeMacerator.addRecipe(
				new RecipeMacerator(new ItemStack(ModItems.steelIngot), new ItemStack(ModItems.steelDust, 1), 6000));
		RecipeMacerator.addRecipe(
				new RecipeMacerator(new ItemStack(ModItems.bronzeIngot), new ItemStack(ModItems.bronzeDust, 1), 5000));
				
		ItemStack grid[][] =
		{
				{ new ItemStack(ModItems.steelIngot, 1), new ItemStack(ModItems.steelIngot, 1),
						new ItemStack(ModItems.steelIngot, 1), new ItemStack(ModItems.steelIngot, 1) },
				{ new ItemStack(ModItems.steelIngot, 1), new ItemStack(Items.diamond, 1),
						new ItemStack(Items.diamond, 1), new ItemStack(ModItems.steelIngot, 1) },
				{ new ItemStack(ModItems.steelIngot, 1), new ItemStack(Items.diamond, 1),
						new ItemStack(Items.diamond, 1), new ItemStack(ModItems.steelIngot, 1) },
				{ new ItemStack(ModItems.steelIngot, 1), new ItemStack(ModItems.steelIngot, 1),
						new ItemStack(ModItems.steelIngot, 1), new ItemStack(ModItems.steelIngot, 1) } };
		infusedDiamondRecipe = new RecipeGrayMatter(grid, 300, 5000, 2000, new ItemStack(ModItems.infusedDiamond, 1));
		RecipeGrayMatter.addRecipe(infusedDiamondRecipe);
		
		ItemStack grid2[][] =
		{
				{ new ItemStack(Items.glowstone_dust, 1), new ItemStack(Items.glowstone_dust, 1),
						new ItemStack(Items.glowstone_dust, 1), new ItemStack(Items.glowstone_dust, 1) },
				{ new ItemStack(Items.glowstone_dust, 1), new ItemStack(ModItems.infusedDiamond, 1),
						new ItemStack(ModItems.infusedDiamond, 1), new ItemStack(Items.glowstone_dust, 1) },
				{ new ItemStack(Items.glowstone_dust, 1), new ItemStack(ModItems.infusedDiamond, 1),
						new ItemStack(ModItems.infusedDiamond, 1), new ItemStack(Items.glowstone_dust, 1) },
				{ new ItemStack(Items.iron_ingot, 1), new ItemStack(Items.ender_pearl, 1),
						new ItemStack(Items.ender_pearl, 1), new ItemStack(Items.iron_ingot, 1) } };
		researchCoreRecipe = new RecipeGrayMatter(grid2, 300, 5000, 2000, new ItemStack(ModItems.researchCore, 1));
		RecipeGrayMatter.addRecipe(researchCoreRecipe);
	}
}
