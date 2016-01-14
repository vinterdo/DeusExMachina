package com.vinterdo.deusexmachina.init;

import com.vinterdo.deusexmachina.reference.Reference;
import com.vinterdo.deusexmachina.tileentity.TEBlastFurnace;
import com.vinterdo.deusexmachina.tileentity.TEBlastFurnaceMaster;
import com.vinterdo.deusexmachina.tileentity.TECamoBlock;
import com.vinterdo.deusexmachina.tileentity.TEDataBank;
import com.vinterdo.deusexmachina.tileentity.TEDeus;
import com.vinterdo.deusexmachina.tileentity.TEDeusMaster;
import com.vinterdo.deusexmachina.tileentity.TEEssenceMacerator;
import com.vinterdo.deusexmachina.tileentity.TEEssenceProcessor;
import com.vinterdo.deusexmachina.tileentity.TEGrayMatterCrafter;
import com.vinterdo.deusexmachina.tileentity.TEGrayMatterCrafterMaster;
import com.vinterdo.deusexmachina.tileentity.TEGrayMatterCrafterPort;
import com.vinterdo.deusexmachina.tileentity.TEGrayMatterFabricator;
import com.vinterdo.deusexmachina.tileentity.TEGrayMatterFabricatorMaster;
import com.vinterdo.deusexmachina.tileentity.TEHeater;
import com.vinterdo.deusexmachina.tileentity.TEIERGrayMatterFabricatorEnergyPort;
import com.vinterdo.deusexmachina.tileentity.TEShield;
import com.vinterdo.deusexmachina.tileentity.TEShieldPylonBlock;
import com.vinterdo.deusexmachina.tileentity.TEShieldPylonBase;
import com.vinterdo.deusexmachina.tileentity.TEShieldPylonEnding;
import com.vinterdo.deusexmachina.tileentity.TEShieldPylonMaster;
import com.vinterdo.deusexmachina.tileentity.TEShieldPylonSlave;
import com.vinterdo.deusexmachina.tileentity.TETeleportGate;
import com.vinterdo.deusexmachina.tileentity.TETeleportGateMaster;
import com.vinterdo.deusexmachina.tileentity.TETeleportMaster;
import com.vinterdo.deusexmachina.tileentity.TETeleportMasterMaster;
import com.vinterdo.deusexmachina.tileentity.TETerminal;

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
		GameRegistry.registerTileEntity(TEIERGrayMatterFabricatorEnergyPort.class,
				Reference.MOD_ID + ":" + "grayMatterFabricatorEnergyPort");
		GameRegistry.registerTileEntity(TEGrayMatterFabricatorMaster.class,
				Reference.MOD_ID + ":" + "grayMatterFabricatorMaster");
		GameRegistry.registerTileEntity(TEGrayMatterCrafter.class, Reference.MOD_ID + ":" + "grayMatterCrafter");
		GameRegistry.registerTileEntity(TEGrayMatterCrafterPort.class,
				Reference.MOD_ID + ":" + "grayMatterCrafterPort");
		GameRegistry.registerTileEntity(TEGrayMatterCrafterMaster.class,
				Reference.MOD_ID + ":" + "grayMatterCrafterMaster");
		GameRegistry.registerTileEntity(TEDeus.class, Reference.MOD_ID + ":" + "deus");
		GameRegistry.registerTileEntity(TEDeusMaster.class, Reference.MOD_ID + ":" + "deusMaster");
		GameRegistry.registerTileEntity(TEShieldPylonBlock.class, Reference.MOD_ID + ":" + "shieldPylon");
		GameRegistry.registerTileEntity(TEShieldPylonBase.class, Reference.MOD_ID + ":" + "shieldPylonBase");
		GameRegistry.registerTileEntity(TEShieldPylonEnding.class, Reference.MOD_ID + ":" + "shieldPylonEnding");
		GameRegistry.registerTileEntity(TEShieldPylonMaster.class, Reference.MOD_ID + ":" + "shieldPylonMaster");
		GameRegistry.registerTileEntity(TEShieldPylonSlave.class, Reference.MOD_ID + ":" + "shieldPylonSlave");
		GameRegistry.registerTileEntity(TEShield.class, Reference.MOD_ID + ":" + "shield");
		GameRegistry.registerTileEntity(TETerminal.class, Reference.MOD_ID + ":" + "terminal");
		GameRegistry.registerTileEntity(TEDataBank.class, Reference.MOD_ID + ":" + "dataBank");
		GameRegistry.registerTileEntity(TETeleportMaster.class, Reference.MOD_ID + ":" + "teleportMaster");
		GameRegistry.registerTileEntity(TETeleportMasterMaster.class, Reference.MOD_ID + ":" + "teleportMasterMaster");
		GameRegistry.registerTileEntity(TETeleportGate.class, Reference.MOD_ID + ":" + "teleportGate");
		GameRegistry.registerTileEntity(TETeleportGateMaster.class, Reference.MOD_ID + ":" + "teleportGateMaster");
		
	}
}
