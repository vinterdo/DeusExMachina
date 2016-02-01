package com.vinterdo.deusexmachina.init;

import com.vinterdo.deusexmachina.block.BBlastFurnace;
import com.vinterdo.deusexmachina.block.BBlastFurnaceMaster;
import com.vinterdo.deusexmachina.block.BCamo;
import com.vinterdo.deusexmachina.block.BCopperOre;
import com.vinterdo.deusexmachina.block.BDEM;
import com.vinterdo.deusexmachina.block.BDataBank;
import com.vinterdo.deusexmachina.block.BDeus;
import com.vinterdo.deusexmachina.block.BDeusMaster;
import com.vinterdo.deusexmachina.block.BEssenceMacerator;
import com.vinterdo.deusexmachina.block.BEssenceOre;
import com.vinterdo.deusexmachina.block.BEssenceProcessor;
import com.vinterdo.deusexmachina.block.BGrayMatterCrafter;
import com.vinterdo.deusexmachina.block.BGrayMatterCrafterMaster;
import com.vinterdo.deusexmachina.block.BGrayMatterCrafterPort;
import com.vinterdo.deusexmachina.block.BGrayMatterFabricator;
import com.vinterdo.deusexmachina.block.BGrayMatterFabricatorEnergyPort;
import com.vinterdo.deusexmachina.block.BGrayMatterFabricatorMaster;
import com.vinterdo.deusexmachina.block.BHeater;
import com.vinterdo.deusexmachina.block.BShield;
import com.vinterdo.deusexmachina.block.BShieldPylonBase;
import com.vinterdo.deusexmachina.block.BShieldPylonBlock;
import com.vinterdo.deusexmachina.block.BShieldPylonEnding;
import com.vinterdo.deusexmachina.block.BTeleportGate;
import com.vinterdo.deusexmachina.block.BTeleportGateMaster;
import com.vinterdo.deusexmachina.block.BTeleportMaster;
import com.vinterdo.deusexmachina.block.BTeleportMasterMaster;
import com.vinterdo.deusexmachina.block.BTerminal;
import com.vinterdo.deusexmachina.block.BTileEntityDEM;
import com.vinterdo.deusexmachina.block.BTinOre;
import com.vinterdo.deusexmachina.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModBlocks
{
	public static final BDEM			essenceOre						= new BEssenceOre();
	public static final BDEM			tinOre							= new BTinOre();
	public static final BDEM			copperOre						= new BCopperOre();
																		
	public static final BTileEntityDEM	terminal						= new BTerminal();
																		
	public static final BTileEntityDEM	essenceProcessor				= new BEssenceProcessor();
	public static final BTileEntityDEM	camoBlock						= new BCamo();
	public static final BTileEntityDEM	heater							= new BHeater();
	public static final BTileEntityDEM	essenceMacerator				= new BEssenceMacerator();
																		
	public static final BTileEntityDEM	blastFurnaceMaster				= new BBlastFurnaceMaster();
	public static final BTileEntityDEM	blastFurnace					= new BBlastFurnace();
																		
	public static final BTileEntityDEM	grayMatterFabricatorMaster		= new BGrayMatterFabricatorMaster();
	public static final BTileEntityDEM	grayMatterFabricator			= new BGrayMatterFabricator();
	public static final BTileEntityDEM	grayMatterFabricatorEnergyPort	= new BGrayMatterFabricatorEnergyPort();
																		
	public static final BTileEntityDEM	grayMatterCrafter				= new BGrayMatterCrafter();
	public static final BTileEntityDEM	grayMatterCrafterMaster			= new BGrayMatterCrafterMaster();
	public static final BTileEntityDEM	grayMatterCrafterPort			= new BGrayMatterCrafterPort();
																		
	public static final BTileEntityDEM	deus							= new BDeus();
	public static final BTileEntityDEM	deusMaster						= new BDeusMaster();
																		
	public static final BTileEntityDEM	shield							= new BShield();
	public static final BTileEntityDEM	shieldPylon						= new BShieldPylonBlock();
	public static final BTileEntityDEM	shieldPylonBase					= new BShieldPylonBase();
	public static final BTileEntityDEM	shieldPylonEnding				= new BShieldPylonEnding();
	public static final BTileEntityDEM	dataBank						= new BDataBank();
																		
	public static final BTileEntityDEM	teleportMaster					= new BTeleportMaster();
	public static final BTileEntityDEM	teleportMasterMaster			= new BTeleportMasterMaster();
																		
	public static final BTileEntityDEM	teleportGate					= new BTeleportGate();
	public static final BTileEntityDEM	teleportGateMaster				= new BTeleportGateMaster();
																		
	public static void init()
	{
		GameRegistry.registerBlock(terminal, "terminal");
		
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
		GameRegistry.registerBlock(grayMatterFabricatorEnergyPort, "grayMatterFabricatorEnergyPort");
		
		GameRegistry.registerBlock(grayMatterCrafter, "grayMatterCrafter");
		GameRegistry.registerBlock(grayMatterCrafterMaster, "grayMatterCrafterMaster");
		GameRegistry.registerBlock(grayMatterCrafterPort, "grayMatterCrafterPort");
		
		GameRegistry.registerBlock(deus, "deus");
		GameRegistry.registerBlock(deusMaster, "deusMaster");
		
		GameRegistry.registerBlock(teleportMaster, "teleportMaster");
		GameRegistry.registerBlock(teleportMasterMaster, "teleportMasterMaster");
		
		GameRegistry.registerBlock(teleportGate, "teleportGate");
		GameRegistry.registerBlock(teleportGateMaster, "teleportGateMaster");
		
		GameRegistry.registerBlock(shield, "shield");
		GameRegistry.registerBlock(shieldPylon, "shieldPylon");
		GameRegistry.registerBlock(shieldPylonBase, "shieldPylonBase");
		GameRegistry.registerBlock(shieldPylonEnding, "shieldPylonEnding");
		
		GameRegistry.registerBlock(dataBank, "dataBank");
		
		OreDictionary.registerOre("oreCopper", copperOre);
		OreDictionary.registerOre("oreEssence", essenceOre);
		OreDictionary.registerOre("oreTin", tinOre);
	}
	
}
