package com.vinterdo.deusexmachina.client.gui;

import com.vinterdo.deusexmachina.inventory.ContainerBlastFurnace;
import com.vinterdo.deusexmachina.inventory.ContainerEssenceProcessor;
import com.vinterdo.deusexmachina.inventory.ContainerHeater;
import com.vinterdo.deusexmachina.tileentity.TileEntityBlastFurnaceMaster;
import com.vinterdo.deusexmachina.tileentity.TileEntityDEM;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiBlastFurnace extends GuiDEM
{
	TileEntityBlastFurnaceMaster te;
	public GuiBlastFurnace(InventoryPlayer playerInv, TileEntity te) 
	{
		super(new ContainerBlastFurnace(playerInv, (TileEntityDEM)te), "blastFurnace");
		this.te = (TileEntityBlastFurnaceMaster) te;
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
