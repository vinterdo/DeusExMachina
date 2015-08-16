package com.vinterdo.deusexmachina.client.gui;

import com.vinterdo.deusexmachina.inventory.ContainerBlastFurnace;
import com.vinterdo.deusexmachina.inventory.ContainerEssenceProcessor;
import com.vinterdo.deusexmachina.inventory.ContainerGrayMatterFabricator;
import com.vinterdo.deusexmachina.inventory.ContainerHeater;
import com.vinterdo.deusexmachina.tileentity.TEBlastFurnaceMaster;
import com.vinterdo.deusexmachina.tileentity.TEGrayMatterFabricatorMaster;
import com.vinterdo.deusexmachina.tileentity.base.TEDEM;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiGrayMatterFabricator extends GuiDEM
{
	TEGrayMatterFabricatorMaster te;
	public GuiGrayMatterFabricator(InventoryPlayer playerInv, TileEntity te) 
	{
		super(new ContainerGrayMatterFabricator(playerInv, (TEDEM)te), "grayMatterFabricator");
		this.te = (TEGrayMatterFabricatorMaster) te;
	}

	@Override 
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mousex, int mousey)
	{
		super.drawGuiContainerBackgroundLayer(partialTick, mousex, mousey);

        if (this.te.getProgress() > 0)
        {
            int i1 = (int) (this.te.getProgressPercent() * 45f);
            this.drawTexturedModalRect(guiLeft + 64, guiTop + 38, 176, 0, i1 + 1, 16);
        }
	}
}
