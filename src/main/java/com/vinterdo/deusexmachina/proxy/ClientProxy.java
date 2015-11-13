package com.vinterdo.deusexmachina.proxy;

import com.vinterdo.deusexmachina.renderers.BlastFurnaceRenderer;
import com.vinterdo.deusexmachina.renderers.DataBankRenderer;
import com.vinterdo.deusexmachina.renderers.DeusRenderer;
import com.vinterdo.deusexmachina.renderers.EssenceMaceratorRenderer;
import com.vinterdo.deusexmachina.renderers.EssenceProcessorRenderer;
import com.vinterdo.deusexmachina.renderers.GrayMatterCrafterRenderer;
import com.vinterdo.deusexmachina.renderers.GrayMatterFabricatorRenderer;
import com.vinterdo.deusexmachina.renderers.RendererTeleportGate;
import com.vinterdo.deusexmachina.renderers.RendererTeleportMaster;
import com.vinterdo.deusexmachina.renderers.ShieldRenderer;
import com.vinterdo.deusexmachina.renderers.TerminalRenderer;
import com.vinterdo.deusexmachina.tileentity.TEBlastFurnaceMaster;
import com.vinterdo.deusexmachina.tileentity.TEDataBank;
import com.vinterdo.deusexmachina.tileentity.TEDeusMaster;
import com.vinterdo.deusexmachina.tileentity.TEEssenceMacerator;
import com.vinterdo.deusexmachina.tileentity.TEEssenceProcessor;
import com.vinterdo.deusexmachina.tileentity.TEGrayMatterCrafterMaster;
import com.vinterdo.deusexmachina.tileentity.TEGrayMatterFabricatorMaster;
import com.vinterdo.deusexmachina.tileentity.TEShield;
import com.vinterdo.deusexmachina.tileentity.TETeleportGateMaster;
import com.vinterdo.deusexmachina.tileentity.TETeleportMasterMaster;
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
		ClientRegistry.bindTileEntitySpecialRenderer(TEShield.class, new ShieldRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TEDataBank.class, new DataBankRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TETeleportMasterMaster.class, new RendererTeleportMaster());
		ClientRegistry.bindTileEntitySpecialRenderer(TETeleportGateMaster.class, new RendererTeleportGate());
	}
}
