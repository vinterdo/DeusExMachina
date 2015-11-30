package com.vinterdo.deusexmachina.client.gui.widget;

import org.lwjgl.opengl.GL11;

import com.vinterdo.deusexmachina.client.gui.generic.Canvas;
import com.vinterdo.deusexmachina.tileentity.TETeleportGateMaster;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureMap;

public class WidgetTeleportButton extends WidgetTooltip
{
	protected TETeleportGateMaster gate;
	
	public WidgetTeleportButton(TETeleportGateMaster gate, int x, int y, Canvas canvas)
	{
		super("", x, y, 120, 22, 100, 20, canvas);
		this.gate = gate;
	}
	
	@Override
	public void render()
	{
		if (gate != null)
		{
			super.setText("[" + gate.xCoord + ", " + gate.yCoord + ", " + gate.zCoord + "] - "
					+ gate.getWorldObj().getProviderName());
			GL11.glDisable(GL11.GL_LIGHTING);
			
			Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
			
			Minecraft.getMinecraft().getTextureManager().bindTexture(super.tooltipBg);
			GL11.glColor4d(1, 1, 1, 1);
			Gui.func_146110_a(start.x, start.y, 0, 0, areawidth, areaheight, areawidth, areaheight);
			
			super.render();
		}
	}
}
