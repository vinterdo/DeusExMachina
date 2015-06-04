package com.vinterdo.deusexmachina.init;

import com.vinterdo.deusexmachina.item.ItemDEM;
import com.vinterdo.deusexmachina.item.ItemEssence;
import com.vinterdo.deusexmachina.item.ItemEssenceContainer;
import com.vinterdo.deusexmachina.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems 
{
	public static final ItemDEM essence = new ItemEssence();
	public static final ItemDEM essenceContainer = new ItemEssenceContainer();
	
	public static void init()
	{
		GameRegistry.registerItem(essence, "Essence");
		GameRegistry.registerItem(essenceContainer, "EssenceContainer");
	}
}
