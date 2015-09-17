package com.vinterdo.deusexmachina.proxy;

import com.vinterdo.deusexmachina.renderers.BlastFurnaceRenderer;
import com.vinterdo.deusexmachina.renderers.DeusRenderer;
import com.vinterdo.deusexmachina.renderers.EssenceMaceratorRenderer;
import com.vinterdo.deusexmachina.renderers.EssenceProcessorRenderer;
import com.vinterdo.deusexmachina.renderers.GrayMatterCrafterRenderer;
import com.vinterdo.deusexmachina.renderers.GrayMatterFabricatorRenderer;
import com.vinterdo.deusexmachina.renderers.TerminalRenderer;
import com.vinterdo.deusexmachina.tileentity.TEBlastFurnaceMaster;
import com.vinterdo.deusexmachina.tileentity.TEDeusMaster;
import com.vinterdo.deusexmachina.tileentity.TEEssenceMacerator;
import com.vinterdo.deusexmachina.tileentity.TEEssenceProcessor;
import com.vinterdo.deusexmachina.tileentity.TEGrayMatterCrafterMaster;
import com.vinterdo.deusexmachina.tileentity.TEGrayMatterFabricatorMaster;
import com.vinterdo.deusexmachina.tileentity.TETerminal;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;

public class ClientProxy extends CommonProxy
{
	
	@Override
	public EntityPlayer getClientPlayer()
	{
		return Minecraft.getMinecraft().thePlayer;
	}
	
	@Override
	public void registerRenderThings()
	{
		ClientRegistry.bindTileEntitySpecialRenderer(TEBlastFurnaceMaster.class, new BlastFurnaceRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TEGrayMatterFabricatorMaster.class,
				new GrayMatterFabricatorRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TEGrayMatterCrafterMaster.class, new GrayMatterCrafterRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TEDeusMaster.class, new DeusRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TEEssenceProcessor.class, new EssenceProcessorRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TETerminal.class, new TerminalRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TEEssenceMacerator.class, new EssenceMaceratorRenderer());
	}
}
