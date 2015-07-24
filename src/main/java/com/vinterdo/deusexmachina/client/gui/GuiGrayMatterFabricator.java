package com.vinterdo.deusexmachina.client.gui;

import com.vinterdo.deusexmachina.inventory.ContainerBlastFurnace;
import com.vinterdo.deusexmachina.inventory.ContainerEssenceProcessor;
import com.vinterdo.deusexmachina.inventory.ContainerGrayMatterFabricator;
import com.vinterdo.deusexmachina.inventory.ContainerHeater;
import com.vinterdo.deusexmachina.tileentity.TileEntityBlastFurnaceMaster;
import com.vinterdo.deusexmachina.tileentity.TileEntityDEM;
import com.vinterdo.deusexmachina.tileentity.TileEntityGrayMatterFabricatorMaster;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiGrayMatterFabricator extends GuiDEM
{
	TileEntityGrayMatterFabricatorMaster te;
	public GuiGrayMatterFabricator(InventoryPlayer playerInv, TileEntity te) 
	{
		super(new ContainerGrayMatterFabricator(playerInv, (TileEntityDEM)te), "grayMatterFabricator");
		this.te = (TileEntityGrayMatterFabricatorMaster) te;
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
