package com.vinterdo.deusexmachina.nei;

import com.vinterdo.deusexmachina.reference.Reference;

import codechicken.nei.api.IConfigureNEI;

public class NEIConfig implements IConfigureNEI
{
	
	@Override
	public String getName()
	{
		return Reference.MOD_NAME + ":" + "NEIConfig";
	}
	
	@Override
	public String getVersion()
	{
		return "0.1"; // TODO: implement autoversioning
	}
	
	@Override
	public void loadConfig()
	{
		//API.registerRecipeHandler(new GrayMatterCrafterHandler());
		//API.registerUsageHandler(new GrayMatterCrafterHandler());
		//API.setGuiOffset(GuiGrayMatterCrafter.class, 0, 0);
		
	}
	
}