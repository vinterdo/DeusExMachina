package com.vinterdo.deusexmachina.init;

import com.vinterdo.deusexmachina.fluids.BFluidGrayMatter;
import com.vinterdo.deusexmachina.fluids.FluidGrayMatter;
import com.vinterdo.deusexmachina.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluids 
{
	public static Fluid grayMatter = new FluidGrayMatter();
	
	public static Block grayMatterBlock ;
	
	public static void init()
	{
		FluidRegistry.registerFluid(grayMatter);
		grayMatterBlock = new BFluidGrayMatter();
		GameRegistry.registerBlock(grayMatterBlock, Reference.MOD_ID + "_" + grayMatterBlock.getUnlocalizedName().substring(5));
		grayMatter.setUnlocalizedName(grayMatter.getUnlocalizedName());
	}
}
