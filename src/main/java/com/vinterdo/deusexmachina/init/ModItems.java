package com.vinterdo.deusexmachina.init;

import com.vinterdo.deusexmachina.item.ItemBronzeDust;
import com.vinterdo.deusexmachina.item.ItemBronzeIngot;
import com.vinterdo.deusexmachina.item.ItemCopperDust;
import com.vinterdo.deusexmachina.item.ItemCopperIngot;
import com.vinterdo.deusexmachina.item.ItemDEM;
import com.vinterdo.deusexmachina.item.ItemDeusDataMatrix;
import com.vinterdo.deusexmachina.item.ItemEssence;
import com.vinterdo.deusexmachina.item.ItemEssenceContainer;
import com.vinterdo.deusexmachina.item.ItemInfusedDiamond;
import com.vinterdo.deusexmachina.item.ItemRayGun;
import com.vinterdo.deusexmachina.item.ItemResearchCore;
import com.vinterdo.deusexmachina.item.ItemSteelDust;
import com.vinterdo.deusexmachina.item.ItemSteelIngot;
import com.vinterdo.deusexmachina.item.ItemTinDust;
import com.vinterdo.deusexmachina.item.ItemTinIngot;
import com.vinterdo.deusexmachina.reference.Reference;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraftforge.oredict.OreDictionary;

@GameRegistry.ObjectHolder(Reference.MOD_ID)
public class ModItems
{
	public static final ItemDEM	essence				= new ItemEssence();
	public static final ItemDEM	essenceContainer	= new ItemEssenceContainer();
	
	public static final ItemDEM	copperIngot	= new ItemCopperIngot();
	public static final ItemDEM	tinIngot	= new ItemTinIngot();
	public static final ItemDEM	steelIngot	= new ItemSteelIngot();
	public static final ItemDEM	bronzeIngot	= new ItemBronzeIngot();
	
	public static final ItemDEM	copperDust	= new ItemCopperDust();
	public static final ItemDEM	tinDust		= new ItemTinDust();
	public static final ItemDEM	steelDust	= new ItemSteelDust();
	public static final ItemDEM	bronzeDust	= new ItemBronzeDust();
	
	public static final ItemDEM	infusedDiamond	= new ItemInfusedDiamond();
	public static final ItemDEM	researchCore	= new ItemResearchCore();
	public static final ItemDEM	deusDataMatrix	= new ItemDeusDataMatrix();
	
	public static final ItemDEM rayGun 		= new ItemRayGun();
	
	public static void init()
	{
		GameRegistry.registerItem(essence, "essence");
		GameRegistry.registerItem(essenceContainer, "essenceContainer");
		
		GameRegistry.registerItem(copperIngot, "copperIngot");
		GameRegistry.registerItem(tinIngot, "tinIngot");
		GameRegistry.registerItem(steelIngot, "steelIngot");
		GameRegistry.registerItem(bronzeIngot, "bronzeIngot");
		
		GameRegistry.registerItem(copperDust, "copperDust");
		GameRegistry.registerItem(tinDust, "tinDust");
		GameRegistry.registerItem(steelDust, "steelDust");
		GameRegistry.registerItem(bronzeDust, "bronzeDust");
		
		GameRegistry.registerItem(infusedDiamond, "infusedDiamond");
		GameRegistry.registerItem(researchCore, "researchCore");
		GameRegistry.registerItem(deusDataMatrix, "deusDataMatrix");
		
		GameRegistry.registerItem(rayGun, "rayGun");
		
		OreDictionary.registerOre("ingotBronze", bronzeIngot);
		OreDictionary.registerOre("ingotSteel", steelIngot);
		OreDictionary.registerOre("ingotCopper", copperIngot);
		OreDictionary.registerOre("ingotTin", tinIngot);
		OreDictionary.registerOre("dustBronze", bronzeDust);
		OreDictionary.registerOre("dustTin", tinDust);
		OreDictionary.registerOre("dustCopper", copperDust);
		OreDictionary.registerOre("dustSteel", steelDust);
		OreDictionary.registerOre("essence", essence);
		OreDictionary.registerOre("containerEssence", essenceContainer);
		
	}
}
