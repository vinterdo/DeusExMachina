package com.vinterdo.deusexmachina.client.gui;

import org.lwjgl.opengl.GL11;

import com.vinterdo.deusexmachina.inventory.ContainerEssenceMacerator;
import com.vinterdo.deusexmachina.inventory.ContainerEssenceProcessor;
import com.vinterdo.deusexmachina.tileentity.TileEntityDEM;
import com.vinterdo.deusexmachina.tileentity.TileEntityEssenceMacerator;
import com.vinterdo.deusexmachina.tileentity.TileEntityEssenceProcessor;

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;

public class GuiEssenceMacerator extends GuiDEM
{
	TileEntityEssenceMacerator te;
	public GuiEssenceMacerator(InventoryPlayer playerInv, TileEntity te) 
	{	
		super(new ContainerEssenceMacerator(playerInv, (TileEntityDEM)te), "essenceMacerator");
		this.te = (TileEntityEssenceMacerator)te;
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int mousex, int mousey)
	{
		super.drawGuiContainerForegroundLayer(mousex, mousey);
		//this.fontRendererObj.drawString("" + te.getProgress(), guiLeft + 10, guiTop + 10, 0xFF000000);
		
	}
	
	@Override 
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mousex, int mousey)
	{
		super.drawGuiContainerBackgroundLayer(partialTick, mousex, mousey);

        if (this.te.getProgress() > 0)
        {
            int i1 = (int) (this.te.getProgress() / (100f / 45f));
            this.drawTexturedModalRect(guiLeft + 62, guiTop + 38, 176, 0, i1 + 1, 16);
        }
	}
}
