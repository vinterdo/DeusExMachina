package com.vinterdo.deusexmachina.client.gui;

import org.lwjgl.opengl.GL11;

import com.vinterdo.deusexmachina.client.gui.widget.WidgetRF;
import com.vinterdo.deusexmachina.client.gui.widget.WidgetTank;
import com.vinterdo.deusexmachina.inventory.ContainerGrayMatterFabricator;
import com.vinterdo.deusexmachina.reference.Reference;
import com.vinterdo.deusexmachina.tileentity.TEGrayMatterFabricatorMaster;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class GuiGrayMatterFabricator extends GuiDEM
{
	TEGrayMatterFabricatorMaster	te;
	ResourceLocation				matterTex	= new ResourceLocation(
			Reference.MOD_ID + ":textures/gui/matterProgressbar.png");
	ResourceLocation				essenceTex	= new ResourceLocation(
			Reference.MOD_ID + ":textures/gui/essenceProgressbar.png");
			
	WidgetTank						widgetTank;
	WidgetRF						widgetEnergy;
									
	public GuiGrayMatterFabricator(InventoryPlayer playerInv, TileEntity te)
	{
		super(new ContainerGrayMatterFabricator(playerInv, (TEGrayMatterFabricatorMaster) te), "grayMatterFabricator");
		this.te = (TEGrayMatterFabricatorMaster) te;
	}
	
	@Override
	public void initGui()
	{
		super.initGui();
		
		widgetTank = new WidgetTank(this.te.tank, guiLeft + 134, guiTop + 14, 16, 58, canvas);
		widgetEnergy = new WidgetRF(this.te.energy, guiLeft + 114, guiTop + 14, 16, 58, canvas);
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTick, int mousex, int mousey)
	{
		super.drawGuiContainerBackgroundLayer(partialTick, mousex, mousey);
		
		drawMatterProgressbar();
		drawEssenceProgressbar();
		
	}
	
	private void drawMatterProgressbar()
	{
		int width = 32;
		GL11.glDisable(GL11.GL_LIGHTING);
		
		int amt = te.matter;
		int capacity = te.MATTER_MAX_STORAGE;
		
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		
		float percentage = (float) amt / (float) capacity;
		float pwidth = width * percentage;
		GL11.glDisable(GL11.GL_LIGHTING);
		Minecraft.getMinecraft().getTextureManager().bindTexture(matterTex);
		GL11.glColor4d(1, 1, 1, 1);
		Gui.func_146110_a(guiLeft + 43, guiTop + 16, 0, 0, (int) pwidth, 12, 12, 12);
	}
	
	private void drawEssenceProgressbar()
	{
		int width = 32;
		GL11.glDisable(GL11.GL_LIGHTING);
		
		int amt = te.essence;
		int capacity = te.ESSENCE_MAX_STORAGE;
		
		Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.locationBlocksTexture);
		
		float percentage = (float) amt / (float) capacity;
		float pwidth = width * percentage;
		GL11.glDisable(GL11.GL_LIGHTING);
		Minecraft.getMinecraft().getTextureManager().bindTexture(essenceTex);
		GL11.glColor4d(1, 1, 1, 1);
		Gui.func_146110_a(guiLeft + 43, guiTop + 58, 0, 0, (int) pwidth, 12, 12, 12);
	}
}
