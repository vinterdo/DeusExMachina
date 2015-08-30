package com.vinterdo.deusexmachina.creativetab;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.reference.Reference;

public class CreativeTabDEM
{
	public static final CreativeTabs DEM_TAB = new CreativeTabs(Reference.MOD_ID)
	{
		@Override
		public Item getTabIconItem()
		{
			return ModItems.essence;
		}
		
		@Override
		public String getTranslatedTabLabel()
		{
			return Reference.MOD_NAME;
		}
	};
	
}
