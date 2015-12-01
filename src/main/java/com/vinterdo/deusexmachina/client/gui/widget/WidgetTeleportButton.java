package com.vinterdo.deusexmachina.client.gui.widget;

import com.vinterdo.deusexmachina.client.gui.generic.Canvas;
import com.vinterdo.deusexmachina.reference.Reference;
import com.vinterdo.deusexmachina.tileentity.TETeleportGateMaster;

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
			canvas.drawTex(tooltipBg, start.x, start.y, width, height);
			canvas.drawString(textButton, this.start.x + 6, this.start.y + 6, areawidth - 6, 0xFFFFFFFF);
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
