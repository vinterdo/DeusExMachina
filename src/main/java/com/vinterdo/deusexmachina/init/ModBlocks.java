package com.vinterdo.deusexmachina.init;

import net.minecraftforge.oredict.OreDictionary;

import com.vinterdo.deusexmachina.block.BlockBlastFurnace;
import com.vinterdo.deusexmachina.block.BlockBlastFurnaceMaster;
import com.vinterdo.deusexmachina.block.BlockCamo;
import com.vinterdo.deusexmachina.block.BlockCopperOre;
import com.vinterdo.deusexmachina.block.BlockDEM;
import com.vinterdo.deusexmachina.block.BlockEssenceMacerator;
import com.vinterdo.deusexmachina.block.BlockEssenceOre;
import com.vinterdo.deusexmachina.block.BlockEssenceProcessor;
import com.vinterdo.deusexmachina.block.BlockGrayMatterFabricator;
import com.vinterdo.deusexmachina.block.BlockGrayMatterFabricatorMaster;
import com.vinterdo.deusexmachina.block.BlockHeater;
import com.vinterdo.deusexmachina.block.BlockTileEntityDEM;
import com.vinterdo.deusexmachina.block.BlockTinOre;
import com.vinterdo.deusexmachina.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks 
{
	public static final BlockDEM essenceOre = new BlockEssenceOre();
	public static final BlockDEM tinOre = new BlockTinOre();
	public static final BlockDEM copperOre = new BlockCopperOre();
	
	public static final BlockTileEntityDEM essenceProcessor = new BlockEssenceProcessor();
	public static final BlockTileEntityDEM  camoBlock = new BlockCamo();
	public static final BlockTileEntityDEM  heater = new BlockHeater();
	public static final BlockTileEntityDEM  essenceMacerator = new BlockEssenceMacerator();
	
	public static final BlockTileEntityDEM  blastFurnaceMaster = new BlockBlastFurnaceMaster();
	public static final BlockTileEntityDEM  blastFurnace = new BlockBlastFurnace();
	
	public static final BlockTileEntityDEM  grayMatterFabricatorMaster = new BlockGrayMatterFabricatorMaster();
	public static final BlockTileEntityDEM  grayMatterFabricator = new BlockGrayMatterFabricator();
	
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
		

		OreDictionary.registerOre("oreCopper", copperOre);
		OreDictionary.registerOre("oreEssence", essenceOre);
		OreDictionary.registerOre("oreTin", tinOre);
	}
	
}
