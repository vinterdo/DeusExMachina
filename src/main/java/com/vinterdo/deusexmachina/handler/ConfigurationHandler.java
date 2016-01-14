package com.vinterdo.deusexmachina.handler;

import java.io.File;

import com.vinterdo.deusexmachina.reference.Reference;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler
{
	public static Configuration configuration;
	
	// // Variable declarations
	
	// Worldgen
	public static boolean generateCopper;
	public static int generateCopperMinHeight;
	public static int generateCopperMaxHeight;
	public static int generateCopperVeinsPerChunk;
	public static int generateCopperBlocksPerVein;
	
	public static boolean generateEssence;
	public static int generateEssenceMinHeight;
	public static int generateEssenceMaxHeight;
	public static int generateEssenceVeinsPerChunk;
	public static int generateEssenceBlocksPerVein;
	
	public static boolean generateTin;
	public static int generateTinMinHeight;
	public static int generateTinMaxHeight;
	public static int generateTinVeinsPerChunk;
	public static int generateTinBlocksPerVein;
	
	// Machine settings
	
	// Blast Furnace Master
	
	public static int BFMMaxBurningTime;
	public static int BFMFuelConsumption;
	public static int BFMProgressMultiplier;
	public static int BFMSmeltTime;
	public static int BFMSmeltTimeSteel;
	public static int BFMHeaterMult;
	
	// Deus Master
	
	public static int DMFluidTankCapacity;
	public static int DMEnergyCapacity;
	public static int DMGMPerTick;
	public static int DMPrintingTime;
	public static int DMPrintingRFPerTick;
	
	// Essence Macerator
	
	public static int EMProgressMultiplier;
	public static int EMEssencePower;
	
	// Essence Processor
	
	public static int EPProgressMultiplier;
	public static int EPProgressTarget;
	
	// Gray Matter Crafter Master
	
	public static int GMCMFluidTankCapacity;
	public static int GMCMEnergyCapacity;
	public static int GMCMGMPerTick;
	
	// Gray Matter Fabricator Master
	
	public static int GMFMEssenceMaxStorage;
	public static int GMFMMatterMaxStorage;
	public static int GMFMFluidTankCapacity;
	public static int GMFMEnergyCapacity;
	public static int GMFMEnergyConsumption;
	
	// Heater
	
	public static int HMaxBurnTime;
	
	// Teleport Gate Master
	
	public static int TGMEnergyCapacity;
	public static int TGMEnergyConsumption;
	
	// Teleport Gate Master Master (haha)
	
	public static int TGMMEnergyCapacity;
	public static int TGMMEnergyConsumption;
	
	// // Variable declaration - END
	
	public static void init(File configFile)
	{
		if (configuration == null)
		{
			configuration = new Configuration(configFile);
			loadConfiguration();
		}
		
	}
	
	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if (event.modID.equalsIgnoreCase(Reference.MOD_ID))
		{
			loadConfiguration();
		}
	}
	
	private static void loadConfiguration()
	{
		// Worldgen configuration 
		generateCopper = configuration.getBoolean("generateCopper", "OreGen" + configuration.CATEGORY_SPLITTER + "Copper", true, "Whether or not is copper ore generated");
		generateCopperMinHeight = configuration.getInt("generateCopperHeightMin", "OreGen" + configuration.CATEGORY_SPLITTER + "Copper", 5, 0, 128, "Minimal height at which Copper is generated");
		generateCopperMaxHeight = configuration.getInt("generateCopperHeightMax", "OreGen" + configuration.CATEGORY_SPLITTER + "Copper", 64, 0, 128, "Maximal height at which Copper is generated");
		generateCopperVeinsPerChunk = configuration.getInt("generateCopperVeinsPerChunk", "OreGen" + configuration.CATEGORY_SPLITTER + "Copper", 6, 0, 128, "Amount of copper veins generated in a single chunk");
		generateCopperBlocksPerVein = configuration.getInt("generateCopperBlocksPerVein", "OreGen" + configuration.CATEGORY_SPLITTER + "Copper", 2, 0, 128, "Amount of copper blocks in a single vein (actual amount is this value plus random(1,4))");
		
		generateEssence = configuration.getBoolean("generateEssence", "OreGen" + configuration.CATEGORY_SPLITTER + "Essence", true, "Whether or not is essence ore generated");
		generateEssenceMinHeight = configuration.getInt("generateEssenceHeightMin", "OreGen" + configuration.CATEGORY_SPLITTER + "Essence", 5, 0, 128, "Minimal height at which essence is generated");
		generateEssenceMaxHeight = configuration.getInt("generateEssenceHeightMax", "OreGen" + configuration.CATEGORY_SPLITTER + "Essence", 64, 0, 128, "Maximal height at which essence is generated");
		generateEssenceVeinsPerChunk = configuration.getInt("generateEssenceVeinsPerChunk", "OreGen" + configuration.CATEGORY_SPLITTER + "Essence", 6, 0, 128, "Amount of essence veins generated in a single chunk");
		generateEssenceBlocksPerVein = configuration.getInt("generateEssenceBlocksPerVein", "OreGen" + configuration.CATEGORY_SPLITTER + "Essence", 2, 0, 128, "Amount of essence blocks in a single vein (actual amount is this value plus random(1,4))");
		
		generateTin = configuration.getBoolean("generateTin", "OreGen" + configuration.CATEGORY_SPLITTER + "Tin", true, "Whether or not is tin ore generated");
		generateTinMinHeight = configuration.getInt("generateTinHeightMin", "OreGen" + configuration.CATEGORY_SPLITTER + "Tin", 5, 0, 128, "Minimal height at which tin is generated");
		generateTinMaxHeight = configuration.getInt("generateTinHeightMax", "OreGen" + configuration.CATEGORY_SPLITTER + "Tin", 64, 0, 128, "Maximal height at which tin is generated");
		generateTinVeinsPerChunk = configuration.getInt("generateTinVeinsPerChunk", "OreGen" + configuration.CATEGORY_SPLITTER + "Tin", 6, 0, 128, "Amount of tin veins generated in a single chunk");
		generateTinBlocksPerVein = configuration.getInt("generateTinBlocksPerVein", "OreGen" + configuration.CATEGORY_SPLITTER + "Tin", 2, 0, 128, "Amount of tin blocks in a single vein (actual amount is this value plus random(1,4))");
		
		// Machine settings
		
		BFMMaxBurningTime = configuration.getInt("BFMMaxBurningTime", "MachineSettings" + configuration.CATEGORY_SPLITTER + "BurningFurnace", 10000, 1000, 1000, "Maximal burning time");
		BFMFuelConsumption = configuration.getInt("BFMFuelConsumption", "MachineSettings" + configuration.CATEGORY_SPLITTER + "BurningFurnace", 8, 0, 100, "Fuel consumption per tick");
		BFMProgressMultiplier = configuration.getInt("BFMProgressMultiplier", "MachineSettings" + configuration.CATEGORY_SPLITTER + "BurningFurnace", 8, 1, 100, "Processing speed (per tick)");
		BFMSmeltTime = configuration.getInt("BFMSmeltTime", "MachineSettings" + configuration.CATEGORY_SPLITTER + "BurningFurnace", 400, 0, 32000, "Smelting time of everything but steel");
		BFMSmeltTimeSteel = configuration.getInt("BFMSmeltTimeSteel", "MachineSettings" + configuration.CATEGORY_SPLITTER + "BurningFurnace", 3200, 0, 32000, "Smelting time of everything else");
		BFMHeaterMult = configuration.getInt("BFMHeaterMult", "MachineSettings" + configuration.CATEGORY_SPLITTER + "BurningFurnace", 1, 0, 10, "How much heater burning time is worth");
		
		DMFluidTankCapacity = configuration.getInt("DMFluidTankCapacity", "MachineSettings" + configuration.CATEGORY_SPLITTER + "DeusMaster", 10000, 1000, 100000, "Fluid tank capacity");
		DMEnergyCapacity = configuration.getInt("DMEnergyCapacity", "MachineSettings" + configuration.CATEGORY_SPLITTER + "DeusMaster", 100000, 10000, 1000000, "Energy capacity");
		DMGMPerTick = configuration.getInt("DMGMPerTick", "MachineSettings" + configuration.CATEGORY_SPLITTER + "DeusMaster", 10, 0, 128, "Gray matter consumption per tick");
		DMPrintingTime = configuration.getInt("DMPrintingTime", "MachineSettings" + configuration.CATEGORY_SPLITTER + "DeusMaster", 1200, 0, 10000, "Printing time");
		DMPrintingRFPerTick = configuration.getInt("DMPrintingRFPerTick", "MachineSettings" + configuration.CATEGORY_SPLITTER + "DeusMaster", 100, 0, 1000, "RF consumption per tick");
		
		EMProgressMultiplier = configuration.getInt("EMProgressMultiplier", "MachineSettings" + configuration.CATEGORY_SPLITTER + "EssenceMacerator", 100, 1, 1000, "Processing speed (per tick)");
		EMEssencePower = configuration.getInt("EMEssencePower", "MachineSettings" + configuration.CATEGORY_SPLITTER + "EssenceMacerator", 100, 1, 1000, "Power generated by a single essence unit");
		
		EPProgressMultiplier = configuration.getInt("EPProgressMultiplier", "MachineSettings" + configuration.CATEGORY_SPLITTER + "EssenceProcessor", 1, 1, 1000, "Processing speed (per tick)");
		EPProgressTarget = configuration.getInt("EPProgressTarget", "MachineSettings" + configuration.CATEGORY_SPLITTER + "EssenceProcessor", 100, 0, 10000, "How long the processing takes");
		
		GMCMFluidTankCapacity = configuration.getInt("GMCMFluidTankCapacity", "MachineSettings" + configuration.CATEGORY_SPLITTER + "GrayMatterCrafter", 10000, 1000, 100000, "Fluid tank capacity");
		GMCMEnergyCapacity = configuration.getInt("GMCMEnergyCapacity", "MachineSettings" + configuration.CATEGORY_SPLITTER + "GrayMatterCrafter", 100000, 10000, 1000000, "Energy capacity");
		GMCMGMPerTick = configuration.getInt("GMCMGMPerTick", "MachineSettings" + configuration.CATEGORY_SPLITTER + "GrayMatterCrafter", 10, 0, 100, "Gray matter consumption per tick");
		
		GMFMEssenceMaxStorage = configuration.getInt("GMFMEssenceMaxStorage", "MachineSettings" + configuration.CATEGORY_SPLITTER + "GrayMatterFabricator", 1000, 100, 10000, "Essence storage");
		GMFMMatterMaxStorage = configuration.getInt("GMFMMatterMaxStorage", "MachineSettings" + configuration.CATEGORY_SPLITTER + "GrayMatterFabricator", 1000, 100, 10000, "Gray matter storage");
		GMFMFluidTankCapacity = configuration.getInt("GMFMFluidTankCapacity", "MachineSettings" + configuration.CATEGORY_SPLITTER + "GrayMatterFabricator", 10000, 1000, 100000, "Fluid tank capacity");
		GMFMEnergyCapacity = configuration.getInt("GMFMEnergyCapacity", "MachineSettings" + configuration.CATEGORY_SPLITTER + "GrayMatterFabricator", 100000, 10000, 1000000, "Energy capacity");
		GMFMEnergyConsumption = configuration.getInt("GMFMEnergyConsumption", "MachineSettings" + configuration.CATEGORY_SPLITTER + "GrayMatterFabricator", 320, 0, 1000, "Energy consumption per tick");
		
		HMaxBurnTime = configuration.getInt("HMaxBurnTime", "MachineSettings" + configuration.CATEGORY_SPLITTER + "Heater", 4000, 1000, 10000, "Maximal burning time");
		
		TGMEnergyCapacity = configuration.getInt("TGMEnergyCapacity", "MachineSettings" + configuration.CATEGORY_SPLITTER + "TeleportGate", 100000, 10000, 1000000, "Energy capacity");
		TGMEnergyConsumption = configuration.getInt("TGMEnergyConsumption", "MachineSettings" + configuration.CATEGORY_SPLITTER + "TeleportGate", 320, 0, 1000, "Energy consumption per tick");
		
		TGMMEnergyCapacity = configuration.getInt("TGMMEnergyCapacity", "MachineSettings" + configuration.CATEGORY_SPLITTER + "TeleportMaster", 100000, 10000, 1000000, "Energy capacity");
		TGMMEnergyConsumption = configuration.getInt("TGMMEnergyConsumption", "MachineSettings" + configuration.CATEGORY_SPLITTER + "TeleportMaster", 320, 0, 1000, "Energy consumption per tick");
		
		if (configuration.hasChanged())
			configuration.save();
	}
}
