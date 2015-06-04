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
		if(configuration == null)
		{
			configuration = new Configuration(configFile);
			loadConfiguration();
		}
		
	}
	
	@SubscribeEvent
	public void onConfigurationChangedEvent(ConfigChangedEvent.OnConfigChangedEvent event)
	{
		if(event.modID.equalsIgnoreCase(Reference.MOD_ID))
		{
			loadConfiguration();
		}
	}
	
	private static void loadConfiguration()
	{
		boolean generateEssence = configuration.getBoolean("generateEssenceOre", "OreGen", true, "");
		
		if(configuration.hasChanged())
			configuration.save();
	}
}
