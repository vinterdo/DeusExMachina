package com.vinterdo.deusexmachina.init;

import net.minecraft.tileentity.TileEntity;

import com.vinterdo.deusexmachina.reference.Reference;
import com.vinterdo.deusexmachina.tileentity.TEBlastFurnace;
import com.vinterdo.deusexmachina.tileentity.TEBlastFurnaceMaster;
import com.vinterdo.deusexmachina.tileentity.TECamoBlock;
import com.vinterdo.deusexmachina.tileentity.TEEssenceMacerator;
import com.vinterdo.deusexmachina.tileentity.TEEssenceProcessor;
import com.vinterdo.deusexmachina.tileentity.TEGrayMatterCrafter;
import com.vinterdo.deusexmachina.tileentity.TEGrayMatterCrafterMaster;
import com.vinterdo.deusexmachina.tileentity.TEGrayMatterFabricator;
import com.vinterdo.deusexmachina.tileentity.TEGrayMatterFabricatorMaster;
import com.vinterdo.deusexmachina.tileentity.TEHeater;

import cpw.mods.fml.common.registry.GameRegistry;

public class ModTileEntities 
{
	
	public static void init()
	{
		GameRegistry.registerTileEntity(TECamoBlock.class, Reference.MOD_ID + ":" + "camoBlock");
		GameRegistry.registerTileEntity(TEEssenceProcessor.class, Reference.MOD_ID + ":" + "essenceProcessor");
		GameRegistry.registerTileEntity(TEHeater.class, Reference.MOD_ID + ":" + "heater");
		GameRegistry.registerTileEntity(TEEssenceMacerator.class, Reference.MOD_ID + ":" + "essenceMacerator");
		GameRegistry.registerTileEntity(TEBlastFurnace.class, Reference.MOD_ID + ":" + "blastFurnace");
		GameRegistry.registerTileEntity(TEBlastFurnaceMaster.class, Reference.MOD_ID + ":" + "blastFurnaceMaster");
		GameRegistry.registerTileEntity(TEGrayMatterFabricator.class, Reference.MOD_ID + ":" + "grayMatterFabricator");
		GameRegistry.registerTileEntity(TEGrayMatterFabricatorMaster.class, Reference.MOD_ID + ":" + "grayMatterFabricatorMaster");
		GameRegistry.registerTileEntity(TEGrayMatterCrafter.class, Reference.MOD_ID + ":" + "grayMatterCrafter");
		GameRegistry.registerTileEntity(TEGrayMatterCrafterMaster.class, Reference.MOD_ID + ":" + "grayMatterCrafterMaster");
	
	}
}
