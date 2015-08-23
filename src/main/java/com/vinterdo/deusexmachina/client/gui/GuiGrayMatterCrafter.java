package com.vinterdo.deusexmachina.client.gui;

import com.vinterdo.deusexmachina.inventory.ContainerGrayMatterCrafter;
import com.vinterdo.deusexmachina.tileentity.TEGrayMatterCrafterMaster;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiGrayMatterCrafter extends GuiDEM
{
	TEGrayMatterCrafterMaster te;
	
	public GuiGrayMatterCrafter(InventoryPlayer playerInv, TileEntity te)
	{
		super(new ContainerGrayMatterCrafter(playerInv, (TEGrayMatterCrafterMaster) te), "grayMatterCrafter");
		this.te = (TEGrayMatterCrafterMaster) te;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mousex, int mousey)
	{
		super.drawGuiContainerBackgroundLayer(partialTick, mousex, mousey);
		
		if (te.progress > 0)
		{
			int i1 = (int) (((float) te.progress / (float) te.progressTarget) * 45f);
			this.drawTexturedModalRect(guiLeft + 64, guiTop + 38, 176, 0, i1 + 1, 16);
		}
	}
}
