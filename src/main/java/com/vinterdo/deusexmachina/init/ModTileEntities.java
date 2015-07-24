package com.vinterdo.deusexmachina.init;

import net.minecraft.tileentity.TileEntity;

import com.vinterdo.deusexmachina.reference.Reference;
import com.vinterdo.deusexmachina.tileentity.TileEntityBlastFurnace;
import com.vinterdo.deusexmachina.tileentity.TileEntityBlastFurnaceMaster;
import com.vinterdo.deusexmachina.tileentity.TileEntityCamoBlock;
import com.vinterdo.deusexmachina.tileentity.TileEntityEssenceMacerator;
import com.vinterdo.deusexmachina.tileentity.TileEntityEssenceProcessor;
import com.vinterdo.deusexmachina.tileentity.TileEntityGrayMatterFabricator;
import com.vinterdo.deusexmachina.tileentity.TileEntityGrayMatterFabricatorMaster;
import com.vinterdo.deusexmachina.tileentity.TileEntityHeater;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModTileEntities 
{
	
	public static void init()
	{
		GameRegistry.registerTileEntity(TileEntityCamoBlock.class, Reference.MOD_ID + ":" + "camoBlock");
		GameRegistry.registerTileEntity(TileEntityEssenceProcessor.class, Reference.MOD_ID + ":" + "essenceProcessor");
		GameRegistry.registerTileEntity(TileEntityHeater.class, Reference.MOD_ID + ":" + "heater");
		GameRegistry.registerTileEntity(TileEntityEssenceMacerator.class, Reference.MOD_ID + ":" + "essenceMacerator");
		GameRegistry.registerTileEntity(TileEntityBlastFurnace.class, Reference.MOD_ID + ":" + "blastFurnace");
		GameRegistry.registerTileEntity(TileEntityBlastFurnaceMaster.class, Reference.MOD_ID + ":" + "blastFurnaceMaster");
		GameRegistry.registerTileEntity(TileEntityGrayMatterFabricator.class, Reference.MOD_ID + ":" + "grayMatterFabricator");
		GameRegistry.registerTileEntity(TileEntityGrayMatterFabricatorMaster.class, Reference.MOD_ID + ":" + "grayMatterFabricatorMaster");
	
	}
}
