package com.vinterdo.deusexmachina.client.gui;

import com.vinterdo.deusexmachina.inventory.ContainerShieldBase;
import com.vinterdo.deusexmachina.tileentity.TEShieldPylonBase;
import com.vinterdo.deusexmachina.tileentity.base.TEDEM;

import net.minecraft.entity.player.InventoryPlayer;

public class GuiShieldBase extends GuiDEM
{
	TEShieldPylonBase				te;

											
	public GuiShieldBase(InventoryPlayer playerInv, TEDEM te)
	{
		super(new ContainerShieldBase(playerInv, (TEShieldPylonBase) te), "shieldBase", te);
		this.te = (TEShieldPylonBase) te;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mousex, int mousey)
	{
		super.drawGuiContainerBackgroundLayer(partialTick, mousex, mousey);
	}
}
