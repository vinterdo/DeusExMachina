package com.vinterdo.deusexmachina.proxy;

import com.vinterdo.deusexmachina.renderers.BlastFurnaceRenderer;
import com.vinterdo.deusexmachina.tileentity.TileEntityBlastFurnaceMaster;

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
	
	public void registerRenderThings()
	{
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBlastFurnaceMaster.class, new BlastFurnaceRenderer());
	}
}
