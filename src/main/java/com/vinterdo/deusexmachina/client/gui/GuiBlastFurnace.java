package com.vinterdo.deusexmachina.client.gui;

import com.vinterdo.deusexmachina.inventory.ContainerBlastFurnace;
import com.vinterdo.deusexmachina.tileentity.TEBlastFurnaceMaster;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiBlastFurnace extends GuiDEM
{
    TEBlastFurnaceMaster te;

    public GuiBlastFurnace(InventoryPlayer playerInv, TileEntity te)
    {
        super(new ContainerBlastFurnace(playerInv, (TEBlastFurnaceMaster) te), "blastFurnace");
        this.te = (TEBlastFurnaceMaster) te;
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
