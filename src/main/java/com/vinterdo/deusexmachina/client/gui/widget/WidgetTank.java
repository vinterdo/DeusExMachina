package com.vinterdo.deusexmachina.client.gui.widget;

import org.lwjgl.opengl.GL11;

import com.vinterdo.deusexmachina.client.gui.generic.Canvas;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.util.IIcon;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidTank;

public class WidgetTank extends WidgetTooltip
{
	private final IFluidTank tank;
	
	public WidgetTank(IFluidTank tank, int x, int y, int width, int height, Canvas canvas)
	{
		super("", x, y, 120, 20, width, height, canvas);
		this.tank = tank;
	}
	
	public WidgetTank(IFluidTank tank, int x, int y, Canvas canvas)
	{
		super("", x, y, 120, 20, 22, 120, canvas);
		this.tank = tank;
	}
	
	@Override
	public void render()
	{
		GL11.glDisable(GL11.GL_LIGHTING);
		
		Fluid fluid = tank.getFluid() != null ? tank.getFluid().getFluid() : null;
		IIcon icon = fluid != null ? fluid.getStillIcon() : null;
		int amt = tank.getFluidAmount();
		int capacity = tank.getCapacity();
		
		super.setText(fluid == null ? amt + "/" + capacity : fluid.getName() + " : " + amt + "/" + capacity);
		
		if (fluid != null && icon != null && amt > 0 && capacity > 0)
		{
			Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
			
			double fluidPercentage = amt / (double) capacity;
			double fluidHeight = areaheight * fluidPercentage;
			
			GL11.glPushMatrix();
			{
				GL11.glTranslated(0, areaheight, 0);
				GL11.glEnable(GL11.GL_BLEND);
				while (fluidHeight > 0)
				{
					double moved = Math.min(fluidHeight, icon.getIconHeight());
					GL11.glTranslated(0, -moved, 0);
					Tessellator t = Tessellator.instance;
					t.startDrawingQuads();
					t.setColorOpaque_I(fluid.getColor(tank.getFluid()));
					{
						t.addVertexWithUV(start.x, start.y, 0, icon.getMinU(), icon.getMinV()
								+ (icon.getMaxV() - icon.getMinV()) * (1 - moved / icon.getIconHeight()));
						t.addVertexWithUV(start.x, start.y + moved, 0, icon.getMinU(), icon.getMaxV());
						t.addVertexWithUV(start.x + areawidth, start.y + moved, 0, icon.getMaxU(), icon.getMaxV());
						t.addVertexWithUV(start.x + areawidth, start.y, 0, icon.getMaxU(), icon.getMinV()
								+ (icon.getMaxV() - icon.getMinV()) * (1 - moved / icon.getIconHeight()));
					}
					t.draw();
					fluidHeight -= moved;
				}
				GL11.glDisable(GL11.GL_BLEND);
			}
			GL11.glPopMatrix();
		}
		
		GL11.glColor4d(1, 1, 1, 1);
		//Minecraft.getMinecraft().getTextureManager().bindTexture(Textures.WIDGET_TANK);
		//Gui.func_146110_a(x, y, 0, 0, 16, 64, 16, 64);
		super.render();
	}
	
	public FluidStack getFluid()
	{
		return tank.getFluid();
	}
	
	@SideOnly(Side.CLIENT)
	public FluidTank getTank()
	{
		return (FluidTank) tank;
	}
}