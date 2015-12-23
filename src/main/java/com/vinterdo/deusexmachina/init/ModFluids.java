package com.vinterdo.deusexmachina.init;

import com.vinterdo.deusexmachina.fluids.BFluidGrayMatter;
import com.vinterdo.deusexmachina.fluids.BLifeFluid;
import com.vinterdo.deusexmachina.fluids.FluidGrayMatter;
import com.vinterdo.deusexmachina.fluids.LifeFluid;
import com.vinterdo.deusexmachina.reference.Reference;
import com.vinterdo.deusexmachina.utility.LogHelper;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;

public class ModFluids
{
	public static Fluid grayMatter = new FluidGrayMatter();
	public static Fluid lifeFluid = new LifeFluid();
	
	public static Block grayMatterBlock;
	public static Block lifeFluidBlock;
	
	public static void init()
	{
		FluidRegistry.registerFluid(grayMatter);
		grayMatterBlock = new BFluidGrayMatter();
		GameRegistry.registerBlock(grayMatterBlock,
				Reference.MOD_ID + "_" + grayMatterBlock.getUnlocalizedName().substring(5));
		grayMatter.setUnlocalizedName(grayMatter.getUnlocalizedName());
		
		FluidRegistry.registerFluid(lifeFluid);
		lifeFluidBlock = new BLifeFluid();
		GameRegistry.registerBlock(lifeFluidBlock,
				Reference.MOD_ID + "_" + lifeFluidBlock.getUnlocalizedName().substring(5));
		lifeFluid.setUnlocalizedName(lifeFluid.getUnlocalizedName());
	}
	
	public static class TextureHook
	{
		@SubscribeEvent
		@SideOnly(Side.CLIENT)
		public void textureHook(TextureStitchEvent.Post event)
		{
			if (event.map.getTextureType() == 0)
			{
				grayMatter.setIcons(grayMatterBlock.getBlockTextureFromSide(1),
						grayMatterBlock.getBlockTextureFromSide(2));
				lifeFluid.setIcons(lifeFluidBlock.getBlockTextureFromSide(1),
						lifeFluidBlock.getBlockTextureFromSide(2));
				LogHelper.info("register fluid icons");
			}
		}
	}
}
