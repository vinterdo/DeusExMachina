package com.vinterdo.deusexmachina.renderers;

import org.lwjgl.opengl.GL11;

import com.vinterdo.deusexmachina.reference.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class EssenceProcessorRenderer extends TileEntitySpecialRenderer
{
	private final IModelCustom		model		= AdvancedModelLoader
			.loadModel(new ResourceLocation(Reference.MOD_ID + ":models/essenceProcessor.obj"));
	private final ResourceLocation	textures	= (new ResourceLocation(Reference.MOD_ID + ":models/pallete.png"));
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale)
	{
		int meta = te.getBlockMetadata();
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y, (float) z + 0.5F);
		GL11.glScalef(1 / 18f, 1 / 18f, 1 / 18f);
		if (meta == 1)
			meta = 4;
		if (meta == 0)
			meta = 1;
		GL11.glRotatef(meta * (-90), 0.0F, 1.0F, 0.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(textures);
		GL11.glPushMatrix();
		this.model.renderAll();
		
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}
