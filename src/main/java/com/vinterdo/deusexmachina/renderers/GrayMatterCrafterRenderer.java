package com.vinterdo.deusexmachina.renderers;

import org.lwjgl.opengl.GL11;

import com.vinterdo.deusexmachina.reference.Reference;
import com.vinterdo.deusexmachina.tileentity.TEGrayMatterCrafterMaster;

import assets.deusexmachina.models.ModelBlock;
import assets.deusexmachina.models.ModelGrayMatterCrafter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

public class GrayMatterCrafterRenderer extends TileEntitySpecialRenderer
{
	private final ModelGrayMatterCrafter	model;
	private final ModelBlock				modelBlock;
											
	public GrayMatterCrafterRenderer()
	{
		this.model = new ModelGrayMatterCrafter();
		this.modelBlock = new ModelBlock();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale)
	{
		if (((TEGrayMatterCrafterMaster) te).isFormed())
		{
			GL11.glPushMatrix();
			GL11.glTranslatef((float) x + 0.5F, (float) y - 0.5F, (float) z + 0.5F);
			ResourceLocation textures = (new ResourceLocation(Reference.MOD_ID + ":models/GrayMatterCrafter.png"));
			Minecraft.getMinecraft().renderEngine.bindTexture(textures);
			GL11.glPushMatrix();
			GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
			this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		} else
		{
			GL11.glPushMatrix();
			GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
			ResourceLocation textures = (new ResourceLocation(Reference.MOD_ID + ":models/BlockGrayMatterCrafter.png"));
			Minecraft.getMinecraft().renderEngine.bindTexture(textures);
			GL11.glPushMatrix();
			GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
			this.modelBlock.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		}
		GL11.glPopMatrix();
		GL11.glPopMatrix();
		
	}
}
