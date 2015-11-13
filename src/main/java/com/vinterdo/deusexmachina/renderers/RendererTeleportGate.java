package com.vinterdo.deusexmachina.renderers;

import org.lwjgl.opengl.GL11;

import com.vinterdo.deusexmachina.reference.Reference;
import com.vinterdo.deusexmachina.tileentity.base.TEMultiblockMaster;

import assets.deusexmachina.models.ModelBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

@SideOnly(Side.CLIENT)
public class RendererTeleportGate extends TileEntitySpecialRenderer
{
	private final IModelCustom		model		= AdvancedModelLoader
			.loadModel(new ResourceLocation(Reference.MOD_ID + ":models/teleportGate.obj"));
	private final ModelBlock		modelBlock	= new ModelBlock();
	private final ResourceLocation	textures	= (new ResourceLocation(Reference.MOD_ID + ":models/pallete.png"));
	private ResourceLocation		textures2	= (new ResourceLocation(
			Reference.MOD_ID + ":models/BlockTeleportGate.png"));
			
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale)
	{
		if (((TEMultiblockMaster) te).isFormed())
		{
			int meta = te.getBlockMetadata();
			
			GL11.glPushMatrix();
			
			GL11.glTranslatef((float) x + 0.5F, (float) y - 3, (float) z + 0.5F);if(((TEMultiblockMaster) te).rotation != null)
			{
				switch(((TEMultiblockMaster) te).rotation)
				{
					case WEST:
						GL11.glTranslatef(-1f, 0, 0f);
						break;
					case EAST:
						GL11.glTranslatef(1f, 0, 0f);
						GL11.glRotatef(180f, 0, 1, 0);
						break;
					case NORTH:
						GL11.glTranslatef(0f, 0,  1f);
						GL11.glRotatef(90f, 0, 1, 0);
						break;
					case SOUTH:
						GL11.glTranslatef(0f, 0, -1f);
						GL11.glRotatef(270f, 0, 1, 0);
						break;
				default:
					break;
				}
			}
			GL11.glScalef(3/5.0f *1 / 16f, 4/7.0f / 18f, 3/5.0f / 16f);
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
			
		} else
		{
			GL11.glPushMatrix();
			GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
			Minecraft.getMinecraft().renderEngine.bindTexture(textures2);
			GL11.glPushMatrix();
			GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
			this.modelBlock.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			GL11.glPopMatrix();
			
		}
		
	}
	
}