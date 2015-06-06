package com.vinterdo.deusexmachina;

import org.apache.logging.log4j.Level;

import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import com.vinterdo.deusexmachina.handler.ConfigurationHandler;
import com.vinterdo.deusexmachina.handler.GuiHandler;
import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.init.ModTileEntities;
import com.vinterdo.deusexmachina.network.DescriptionHandler;
import com.vinterdo.deusexmachina.proxy.*;
import com.vinterdo.deusexmachina.recipes.Recipes;
import com.vinterdo.deusexmachina.reference.ModIds;
import com.vinterdo.deusexmachina.reference.Reference;
import com.vinterdo.deusexmachina.utility.LogHelper;
import com.vinterdo.deusexmachina.worldgen.WorldGeneratorOres;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.*;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.Optional;

@Mod(modid=Reference.MOD_ID, name=Reference.MOD_NAME, version=Reference.VERSION, guiFactory = Reference.GUI_FACTORY_CLASS)
public class DeusExMachina 
{
	@Instance(Reference.MOD_ID)
	public static DeusExMachina instance;
	
	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide= Reference.SERVER_PROXY_CLASS)
	public static IProxy proxy;
	
	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		LogHelper.log(Level.INFO, "start");
		ConfigurationHandler.init(event.getSuggestedConfigurationFile());
		FMLCommonHandler.instance().bus().register(new ConfigurationHandler());
		ModItems.init();
		ModBlocks.init();
		ModTileEntities.init();
		Recipes.init();
		DescriptionHandler.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiHandler());
		GameRegistry.registerWorldGenerator(new WorldGeneratorOres(), 0);
		
		if(Loader.isModLoaded(ModIds.THAUMCRAFT))
		{
			thaumcraftApi();
		}
	}
	
	@Optional.Method(modid = ModIds.THAUMCRAFT)
	private void thaumcraftApi()
	{
		ThaumcraftApi.registerObjectTag("essence", new AspectList().add(Aspect.AIR, 2));
	}
	
	@Mod.EventHandler
	public void init(FMLInitializationEvent event)
	{
		
	}
	
	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		
	}
}
