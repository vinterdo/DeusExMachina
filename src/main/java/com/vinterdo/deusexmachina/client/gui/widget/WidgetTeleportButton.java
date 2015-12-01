package com.vinterdo.deusexmachina.client.gui.widget;

import org.lwjgl.opengl.GL11;

import com.vinterdo.deusexmachina.client.gui.generic.Canvas;
import com.vinterdo.deusexmachina.reference.Reference;
import com.vinterdo.deusexmachina.tileentity.TETeleportGateMaster;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.ResourceLocation;

public class WidgetTeleportButton extends WidgetTooltip
{
	
	protected static ResourceLocation	tooltipBg	= new ResourceLocation(
			Reference.MOD_ID + ":textures/gui/tooltipBg.png");
	protected TETeleportGateMaster		gate;
	protected String					textButton;
										
	public WidgetTeleportButton(TETeleportGateMaster gate, int x, int y, Canvas canvas)
	{
		super("", x, y, 150, 22, 150, 22, canvas);
		
		this.gate = gate;
	}
	
	@Override
	public void render()
	{
		if (gate != null)
		{
			GL11.glDisable(GL11.GL_LIGHTING);
			
			Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
			
			Minecraft.getMinecraft().getTextureManager().bindTexture(tooltipBg);
			GL11.glColor4d(1, 1, 1, 1);
			Gui.func_146110_a(start.x, start.y, 0, 0, width, height, width, height);
			canvas.getFontRenderer().drawString(textButton, this.start.x + 6, this.start.y + 6, 0xFFFFFFFF);
		}
		
		super.render();
	}
	
	@Override
	public void update()
	{
		setTextButton("[" + gate.xCoord + ", " + gate.yCoord + ", " + gate.zCoord + "] ");
		super.setText(gate.getWorldObj().getWorldInfo().getWorldName());
	}
	
	private void setTextButton(String string)
	{
		this.textButton = string;
		
	}
}
