package com.vinterdo.deusexmachina.renderers;

import org.lwjgl.opengl.GL11;

import com.vinterdo.deusexmachina.reference.Reference;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class EssenceMaceratorRenderer extends TileEntitySpecialRenderer
{
	private final IModelCustom		model		= AdvancedModelLoader
			.loadModel(new ResourceLocation(Reference.MOD_ID + ":models/essenceMacerator.obj"));;
	private final ResourceLocation	textures	= (new ResourceLocation(Reference.MOD_ID + ":models/pallete.png"));
	
	private void adjustRotatePivotViaMeta(World world, int x, int y, int z)
	{
		int meta = world.getBlockMetadata(x, y, z);
		GL11.glPushMatrix();
		GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F);
		GL11.glPopMatrix();
	}
	
	private void adjustLightFixture(World world, int i, int j, int k, Block block)
	{
		Tessellator tess = Tessellator.instance;
		float brightness = block.getLightValue(world, i, j, k);
		int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
		int modulousModifier = skyLight % 65536;
		int divModifier = skyLight / 65536;
		tess.setColorOpaque_F(brightness, brightness, brightness);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, modulousModifier, divModifier);
	}
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale)
	{
		adjustLightFixture(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord, te.getBlockType());
		
		int meta = te.getBlockMetadata();
		meta = meta == 0 ? 0 : meta == 1 ? 3 : meta == 2 ? 1 : meta == 3 ? 2 : meta == 4 ? 2 : 0;
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y, (float) z + 0.5F);
		GL11.glScalef(1 / 18f, 1 / 18f, 1 / 18f);
		GL11.glRotatef(meta * (-90), 0.0F, 1.0F, 0.0F);
		Minecraft.getMinecraft().renderEngine.bindTexture(textures);
		GL11.glPushMatrix();
		this.model.renderAll();
		
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}
