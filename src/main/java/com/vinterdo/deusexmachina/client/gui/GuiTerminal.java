package com.vinterdo.deusexmachina.client.gui;

import buildcraft.api.core.EnumColor;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

@SideOnly(Side.CLIENT)
public class GuiTerminal extends GuiScreen
{
	private static String updateProgress = "";
	
	@Override
	public void initGui()
	{
		buttonList.clear();
		buttonList.add(new GuiButton(0, width / 2 - 100, height / 4 + 72 + 12, "Update"));
		buttonList.add(new GuiButton(1, width / 2 - 100, height / 4 + 96 + 12, "Cancel"));
	}
	
	public static void updateInfo(String info)
	{
		updateProgress = info;
	}
	
	@Override
	protected void actionPerformed(GuiButton guibutton)
	{
		if (guibutton.id == 0)
		{
			// update
		}
		if (guibutton.id == 1)
		{
			mc.displayGuiScreen(null);
		}
	}
	
	public void writeText(String text, int yAxis)
	{
		drawString(fontRendererObj, text, width / 2 - 140, (height / 4 - 60) + 20 + yAxis, 0xa0a0a0);
	}
	
	@Override
	public boolean doesGuiPauseGame()
	{
		return false;
	}
	
	@Override
	public void drawScreen(int mouseX, int mouseY, float partialTick)
	{
		drawDefaultBackground();
		drawCenteredString(fontRendererObj, EnumColor.BLUE + "Mekanism" + EnumColor.GRAY + " by aidancbrady", width / 2,
				(height / 4 - 60) + 20, 0xffffff);
				
		int size = 36;
		
		writeText(EnumColor.GRAY + "Newest version: " + "latestVersionNumber", size + 9);
		writeText(EnumColor.GRAY + "*blablablablablablabla", size + 18);
		writeText(EnumColor.GRAY + "*blablablablablablabla", size + 27);
		writeText(EnumColor.GRAY + updateProgress, size + 45);
		
		super.drawScreen(mouseX, mouseY, partialTick);
	}
}