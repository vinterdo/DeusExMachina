package com.vinterdo.deusexmachina.proxy;

import net.minecraft.entity.player.EntityPlayer;

public interface IProxy
{
	
	void registerRenderThings();
	
	EntityPlayer getClientPlayer();
}
