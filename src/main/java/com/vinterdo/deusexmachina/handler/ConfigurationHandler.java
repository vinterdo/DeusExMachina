package com.vinterdo.deusexmachina.handler;

import java.io.File;

import com.vinterdo.deusexmachina.reference.Reference;

import cpw.mods.fml.client.event.ConfigChangedEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.config.Configuration;

public class ConfigurationHandler
{
	public static Configuration configuration;
	
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
		/* Worldgen configuration */
		boolean generateCopper = configuration.getBoolean("generateCopper", "OreGen" + configuration.CATEGORY_SPLITTER + "Copper", true, "Whether or not is copper ore generated");
		int generateCopperMinHeight = configuration.getInt("generateCopperHeightMin", "OreGen" + configuration.CATEGORY_SPLITTER + "Copper", 5, 0, 128, "Minimal height at which Copper is generated");
		int generateCopperMaxHeight = configuration.getInt("generateCopperHeightMax", "OreGen" + configuration.CATEGORY_SPLITTER + "Copper", 64, 0, 128, "Maximal height at which Copper is generated");
		int generateCopperVeinsPerChunk = configuration.getInt("generateCopperVeinsPerChunk", "OreGen" + configuration.CATEGORY_SPLITTER + "Copper", 6, 0, 128, "Amount of copper veins generated in a single chunk");
		int generateCopperBlocksPerVein = configuration.getInt("generateCopperBlocksPerVein", "OreGen" + configuration.CATEGORY_SPLITTER + "Copper", 2, 0, 128, "Amount of copper blocks in a single vein (actual amount is this value plus random(1,4))");
		
		boolean generateEssence = configuration.getBoolean("generateEssence", "OreGen" + configuration.CATEGORY_SPLITTER + "Essence", true, "Whether or not is essence ore generated");
		int generateEssenceMinHeight = configuration.getInt("generateEssenceHeightMin", "OreGen" + configuration.CATEGORY_SPLITTER + "Essence", 5, 0, 128, "Minimal height at which essence is generated");
		int generateEssenceMaxHeight = configuration.getInt("generateEssenceHeightMax", "OreGen" + configuration.CATEGORY_SPLITTER + "Essence", 64, 0, 128, "Maximal height at which essence is generated");
		int generateEssenceVeinsPerChunk = configuration.getInt("generateEssenceVeinsPerChunk", "OreGen" + configuration.CATEGORY_SPLITTER + "Essence", 6, 0, 128, "Amount of essence veins generated in a single chunk");
		int generateEssenceBlocksPerVein = configuration.getInt("generateEssenceBlocksPerVein", "OreGen" + configuration.CATEGORY_SPLITTER + "Essence", 2, 0, 128, "Amount of essence blocks in a single vein (actual amount is this value plus random(1,4))");
		
		boolean generateTin = configuration.getBoolean("generateTin", "OreGen" + configuration.CATEGORY_SPLITTER + "Tin", true, "Whether or not is tin ore generated");
		int generateTinMinHeight = configuration.getInt("generateTinHeightMin", "OreGen" + configuration.CATEGORY_SPLITTER + "Tin", 5, 0, 128, "Minimal height at which tin is generated");
		int generateTinMaxHeight = configuration.getInt("generateTinHeightMax", "OreGen" + configuration.CATEGORY_SPLITTER + "Tin", 64, 0, 128, "Maximal height at which tin is generated");
		int generateTinVeinsPerChunk = configuration.getInt("generateTinVeinsPerChunk", "OreGen" + configuration.CATEGORY_SPLITTER + "Tin", 6, 0, 128, "Amount of tin veins generated in a single chunk");
		int generateTinBlocksPerVein = configuration.getInt("generateTinBlocksPerVein", "OreGen" + configuration.CATEGORY_SPLITTER + "Tin", 2, 0, 128, "Amount of tin blocks in a single vein (actual amount is this value plus random(1,4))");
		
		
		
		if (configuration.hasChanged())
			configuration.save();
	}
}
