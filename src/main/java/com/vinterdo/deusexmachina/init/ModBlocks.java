package com.vinterdo.deusexmachina.init;

import net.minecraftforge.oredict.OreDictionary;

import com.vinterdo.deusexmachina.block.BBlastFurnace;
import com.vinterdo.deusexmachina.block.BBlastFurnaceMaster;
import com.vinterdo.deusexmachina.block.BCamo;
import com.vinterdo.deusexmachina.block.BCopperOre;
import com.vinterdo.deusexmachina.block.BDEM;
import com.vinterdo.deusexmachina.block.BDeus;
import com.vinterdo.deusexmachina.block.BDeusMaster;
import com.vinterdo.deusexmachina.block.BEssenceMacerator;
import com.vinterdo.deusexmachina.block.BEssenceOre;
import com.vinterdo.deusexmachina.block.BEssenceProcessor;
import com.vinterdo.deusexmachina.block.BGrayMatterCrafter;
import com.vinterdo.deusexmachina.block.BGrayMatterCrafterMaster;
import com.vinterdo.deusexmachina.block.BGrayMatterFabricator;
import com.vinterdo.deusexmachina.block.BGrayMatterFabricatorMaster;
import com.vinterdo.deusexmachina.block.BHeater;
import com.vinterdo.deusexmachina.block.BTileEntityDEM;
import com.vinterdo.deusexmachina.block.BTinOre;
import com.vinterdo.deusexmachina.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks 
{
	public static final BDEM essenceOre = new BEssenceOre();
	public static final BDEM tinOre = new BTinOre();
	public static final BDEM copperOre = new BCopperOre();
	
	public static final BTileEntityDEM essenceProcessor = new BEssenceProcessor();
	public static final BTileEntityDEM  camoBlock = new BCamo();
	public static final BTileEntityDEM  heater = new BHeater();
	public static final BTileEntityDEM  essenceMacerator = new BEssenceMacerator();
	
	public static final BTileEntityDEM  blastFurnaceMaster = new BBlastFurnaceMaster();
	public static final BTileEntityDEM  blastFurnace = new BBlastFurnace();
	
	public static final BTileEntityDEM  grayMatterFabricatorMaster = new BGrayMatterFabricatorMaster();
	public static final BTileEntityDEM  grayMatterFabricator = new BGrayMatterFabricator();
	
	public static final BTileEntityDEM grayMatterCrafter = new BGrayMatterCrafter();
	public static final BTileEntityDEM grayMatterCrafterMaster = new BGrayMatterCrafterMaster();
	
	public static final BTileEntityDEM deus = new BDeus();
	public static final BTileEntityDEM deusMaster = new BDeusMaster();
	
	public static void init()
	{
		GameRegistry.registerBlock(essenceOre, "essenceOre");
		GameRegistry.registerBlock(camoBlock, "camoBlock");
		GameRegistry.registerBlock(essenceProcessor, "essenceProcessor");
		GameRegistry.registerBlock(essenceMacerator, "essenceMacerator");
		GameRegistry.registerBlock(heater, "heater");
		GameRegistry.registerBlock(copperOre, "copperOre");
		GameRegistry.registerBlock(tinOre, "tinOre");
		

		GameRegistry.registerBlock(blastFurnace, "blastFurnace");
		GameRegistry.registerBlock(blastFurnaceMaster, "blastFurnaceMaster");

		GameRegistry.registerBlock(grayMatterFabricator, "grayMatterFabricator");
		GameRegistry.registerBlock(grayMatterFabricatorMaster, "grayMatterFabricatorMaster");

		GameRegistry.registerBlock(grayMatterCrafter, "grayMatterCrafter");
		GameRegistry.registerBlock(grayMatterCrafterMaster, "grayMatterCrafterMaster");

		GameRegistry.registerBlock(deus, "deus");
		GameRegistry.registerBlock(deusMaster, "deusMaster");

		OreDictionary.registerOre("oreCopper", copperOre);
		OreDictionary.registerOre("oreEssence", essenceOre);
		OreDictionary.registerOre("oreTin", tinOre);
	}
	
}
