package com.vinterdo.deusexmachina.init;

import net.minecraft.tileentity.TileEntity;

import com.vinterdo.deusexmachina.reference.Reference;
import com.vinterdo.deusexmachina.tileentity.TileEntityCamoBlock;
import com.vinterdo.deusexmachina.tileentity.TileEntityEssenceProcessor;
import com.vinterdo.deusexmachina.tileentity.TileEntityHeater;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModTileEntities 
{
	
	public static void init()
	{
		GameRegistry.registerTileEntity(TileEntityCamoBlock.class, Reference.MOD_ID + ":" + "camoBlock");
		GameRegistry.registerTileEntity(TileEntityEssenceProcessor.class, Reference.MOD_ID + ":" + "essenceProcessor");
		GameRegistry.registerTileEntity(TileEntityHeater.class, Reference.MOD_ID + ":" + "heater");
	}
}
