package com.vinterdo.deusexmachina.renderers;

import java.nio.FloatBuffer;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.vinterdo.deusexmachina.reference.Reference;
import com.vinterdo.deusexmachina.tileentity.TEDeusMaster;

import assets.deusexmachina.models.ModelBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

@SideOnly(Side.CLIENT)
public class DeusRenderer extends TileEntitySpecialRenderer
{
	private static final ResourceLocation	skyLocation			= new ResourceLocation(
			Reference.MOD_ID + ":textures/effects/end_sky.png");
	private static final ResourceLocation	endPortalLocation	= new ResourceLocation(
			Reference.MOD_ID + ":textures/effects/end_portal.png");
	private static final Random				random				= new Random(31100L);
	FloatBuffer								floatBuffer			= GLAllocation.createDirectFloatBuffer(16);
																
	private final IModelCustom				model				= AdvancedModelLoader
			.loadModel(new ResourceLocation(Reference.MOD_ID + ":models/deus.obj"));
	private final ModelBlock				modelBlock			= new ModelBlock();
	private final ResourceLocation			textures			= (new ResourceLocation(
			Reference.MOD_ID + ":models/pallete2.png"));
			
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale)
	{
		if (((TEDeusMaster) te).isFormed())
		{
			int meta = te.getBlockMetadata();
			
			GL11.glPushMatrix();
			GL11.glTranslatef((float) x + 0.5F, (float) y - 6, (float) z + 0.5F);
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
			
			renderEnd(x + 0.5D, y - 6D, z + 0.5D);
		} else
		{
			GL11.glPushMatrix();
			GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
			ResourceLocation textures = (new ResourceLocation(Reference.MOD_ID + ":models/BlockDeus.png"));
			Minecraft.getMinecraft().renderEngine.bindTexture(textures);
			GL11.glPushMatrix();
			GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
			this.modelBlock.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
			GL11.glPopMatrix();
			GL11.glPopMatrix();
			
			renderEndBlock(x, y, z);
		}
	}
	
	private void renderEnd(double x, double y, double z)
	{
		float f1 = (float) this.field_147501_a.field_147560_j;
		float f2 = (float) this.field_147501_a.field_147561_k;
		float f3 = (float) this.field_147501_a.field_147558_l;
		GL11.glDisable(GL11.GL_LIGHTING);
		random.setSeed(31100L);
		float f4 = 0.75F;
		for (int i = 0; i < 16; ++i)
		{
			GL11.glPushMatrix();
			float f5 = (16 - i) / 128f;
			float f6 = 0.0925F;
			float f7 = 1.0F / (f5 + 1.0F);
			
			if (i == 0)
			{
				this.bindTexture(skyLocation);
				f7 = 0.1F;
				f5 = 65.0F;
				f6 = 0.125F;
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			}
			
			if (i == 1)
			{
				this.bindTexture(endPortalLocation);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
				f6 = 0.5F;
			}
			
			float f8 = (float) (-(y + f4));
			float f9 = f8 + ActiveRenderInfo.objectY;
			float f10 = f8 + f5 + ActiveRenderInfo.objectY;
			float f11 = f9 / f10;
			f11 += (-0.5 + f4);
			GL11.glTranslatef(f1, f11, f3);
			GL11.glTexGeni(GL11.GL_S, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
			GL11.glTexGeni(GL11.GL_T, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
			GL11.glTexGeni(GL11.GL_R, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
			GL11.glTexGeni(GL11.GL_Q, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_EYE_LINEAR);
			GL11.glTexGen(GL11.GL_S, GL11.GL_OBJECT_PLANE, this.func_147525_a(1.0F, 0.0F, 0.0F, 0.0F));
			GL11.glTexGen(GL11.GL_T, GL11.GL_OBJECT_PLANE, this.func_147525_a(0.0F, 0.0F, 1.0F, 0.0F));
			GL11.glTexGen(GL11.GL_R, GL11.GL_OBJECT_PLANE, this.func_147525_a(0.0F, 0.0F, 0.0F, 1.0F));
			GL11.glTexGen(GL11.GL_Q, GL11.GL_EYE_PLANE, this.func_147525_a(0.0F, 1.0F, 0.0F, 0.0F));
			GL11.glEnable(GL11.GL_TEXTURE_GEN_S);
			GL11.glEnable(GL11.GL_TEXTURE_GEN_T);
			GL11.glEnable(GL11.GL_TEXTURE_GEN_R);
			GL11.glEnable(GL11.GL_TEXTURE_GEN_Q);
			GL11.glPopMatrix();
			GL11.glMatrixMode(GL11.GL_TEXTURE);
			//GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
			GL11.glPushMatrix();
			GL11.glLoadIdentity();
			GL11.glTranslatef(0.0F, Minecraft.getSystemTime() % 7000L / 7000.0F, 0.0F);
			GL11.glScalef(f6, f6, f6);
			GL11.glTranslatef(0.5F, 0.5F, 0.0F);
			//GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
			GL11.glTranslatef(-f1, -f3, -f2);
			f9 = f8 + ActiveRenderInfo.objectY;
			GL11.glTranslatef(ActiveRenderInfo.objectX * f5 / f9, ActiveRenderInfo.objectZ * f5 / f9, -f2);
			Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawingQuads();
			f11 = random.nextFloat() * 0.5F + 0.1F;
			float f12 = random.nextFloat() * 0.5F + 0.4F;
			float f13 = random.nextFloat() * 0.5F + 0.5F;
			
			if (i == 0)
			{
				f13 = 1.0F;
				f12 = 1.0F;
				f11 = 1.0F;
			}
			
			tessellator.setColorRGBA_F(f11 * f7, f12 * f7, f13 * f7, 1.0F);
			tessellator.setColorRGBA_F(f11 * f7, f12 * f7, f13 * f7, 0.5F);
			
			tessellator.addVertexWithUV(x + 2.2D, y + 2.4D, z - 2.2D, 0, 0);
			tessellator.addVertexWithUV(x + 2.2D, y + 2.4D, z + 2.2D, 0, 1);
			tessellator.addVertexWithUV(x + 2.2D, y, z + 2.2D, 1, 1);
			tessellator.addVertexWithUV(x + 2.2D, y, z - 2.2D, 1, 0);
			
			tessellator.addVertexWithUV(x - 2.2D, y, z - 2.2D, 0, 0);
			tessellator.addVertexWithUV(x - 2.2D, y + 2.4D, z - 2.2D, 0, 1);
			tessellator.addVertexWithUV(x + 2.2D, y + 2.4D, z - 2.2D, 1, 1);
			tessellator.addVertexWithUV(x + 2.2D, y, z - 2.2D, 1, 0);
			
			tessellator.addVertexWithUV(x - 2.2D, y, z - 2.2D, 0, 0);
			tessellator.addVertexWithUV(x - 2.2D, y, z + 2.2D, 0, 1);
			tessellator.addVertexWithUV(x - 2.2D, y + 2.4D, z + 2.2D, 1, 1);
			tessellator.addVertexWithUV(x - 2.2D, y + 2.4D, z - 2.2D, 1, 0);
			
			tessellator.addVertexWithUV(x - 2.2D, y + 2.4D, z + 2.2D, 0, 0);
			tessellator.addVertexWithUV(x - 2.2D, y, z + 2.2D, 0, 1);
			tessellator.addVertexWithUV(x + 2.2D, y, z + 2.2D, 1, 1);
			tessellator.addVertexWithUV(x + 2.2D, y + 2.4D, z + 2.2D, 1, 0);
			
			tessellator.draw();
			GL11.glPopMatrix();
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
		}
		
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_S);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_T);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_R);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_Q);
		GL11.glEnable(GL11.GL_LIGHTING);
	}
	
	private void renderEndBlock(double x, double y, double z)
	{
		float f1 = (float) this.field_147501_a.field_147560_j;
		float f2 = (float) this.field_147501_a.field_147561_k;
		float f3 = (float) this.field_147501_a.field_147558_l;
		GL11.glDisable(GL11.GL_LIGHTING);
		random.setSeed(31100L);
		float f4 = 0.75F;
		
		for (int i = 0; i < 16; ++i)
		{
			GL11.glPushMatrix();
			GL11.glScalef(2.5f, 2.5f, 2.5f);
			float f5 = 16 - i;
			float f6 = 0.0625F;
			float f7 = 1.0F / (f5 + 1.0F);
			
			if (i == 0)
			{
				this.bindTexture(skyLocation);
				f7 = 0.1F;
				f5 = 65.0F;
				f6 = 0.125F;
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			}
			
			if (i == 1)
			{
				this.bindTexture(endPortalLocation);
				GL11.glEnable(GL11.GL_BLEND);
				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
				f6 = 0.5F;
			}
			
			float f8 = (float) (-(y + f4));
			float f9 = f8 + ActiveRenderInfo.objectY;
			float f10 = f8 + f5 + ActiveRenderInfo.objectY;
			float f11 = f9 / f10;
			f11 += (float) (y + f4);
			GL11.glTranslatef(f1, f11, f3);
			GL11.glTexGeni(GL11.GL_S, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
			GL11.glTexGeni(GL11.GL_T, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
			GL11.glTexGeni(GL11.GL_R, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_OBJECT_LINEAR);
			GL11.glTexGeni(GL11.GL_Q, GL11.GL_TEXTURE_GEN_MODE, GL11.GL_EYE_LINEAR);
			GL11.glTexGen(GL11.GL_S, GL11.GL_OBJECT_PLANE, this.func_147525_a(1.0F, 0.0F, 0.0F, 0.0F));
			GL11.glTexGen(GL11.GL_T, GL11.GL_OBJECT_PLANE, this.func_147525_a(0.0F, 0.0F, 1.0F, 0.0F));
			GL11.glTexGen(GL11.GL_R, GL11.GL_OBJECT_PLANE, this.func_147525_a(0.0F, 0.0F, 0.0F, 1.0F));
			GL11.glTexGen(GL11.GL_Q, GL11.GL_EYE_PLANE, this.func_147525_a(0.0F, 1.0F, 0.0F, 0.0F));
			GL11.glEnable(GL11.GL_TEXTURE_GEN_S);
			GL11.glEnable(GL11.GL_TEXTURE_GEN_T);
			GL11.glEnable(GL11.GL_TEXTURE_GEN_R);
			GL11.glEnable(GL11.GL_TEXTURE_GEN_Q);
			GL11.glPopMatrix();
			GL11.glMatrixMode(GL11.GL_TEXTURE);
			GL11.glPushMatrix();
			GL11.glLoadIdentity();
			GL11.glTranslatef(0.0F, Minecraft.getSystemTime() % 7000L / 7000.0F, 0.0F);
			GL11.glScalef(f6, f6, f6);
			GL11.glTranslatef(0.5F, 0.5F, 0.0F);
			GL11.glRotatef((i * i * 4321 + i * 9) * 2.0F, 0.0F, 0.0F, 1.0F);
			GL11.glTranslatef(-0.5F, -0.5F, 0.0F);
			GL11.glTranslatef(-f1, -f3, -f2);
			f9 = f8 + ActiveRenderInfo.objectY;
			GL11.glTranslatef(ActiveRenderInfo.objectX * f5 / f9, ActiveRenderInfo.objectZ * f5 / f9, -f2);
			Tessellator tessellator = Tessellator.instance;
			tessellator.startDrawingQuads();
			f11 = random.nextFloat() * 0.5F + 0.1F;
			float f12 = random.nextFloat() * 0.5F + 0.4F;
			float f13 = random.nextFloat() * 0.5F + 0.5F;
			
			if (i == 0)
			{
				f13 = 1.0F;
				f12 = 1.0F;
				f11 = 1.0F;
			}
			
			tessellator.setColorRGBA_F(f11 * f7, f12 * f7, f13 * f7, 1.0F);
			
			//tessellator.addVertexWithUV(x, y, z, 0, 0);
			//tessellator.addVertexWithUV(x, y, z, 0, 1);
			//tessellator.addVertexWithUV(x, y, z, 1, 1);
			//tessellator.addVertexWithUV(x, y, z, 1, 0);
			
			tessellator.addVertexWithUV(x, y, z + 0.01f, 0, 0);
			tessellator.addVertexWithUV(x, y + 1, z + 0.01f, 1, 0);
			tessellator.addVertexWithUV(x + 1, y + 1, z + 0.01f, 1, 1);
			tessellator.addVertexWithUV(x + 1, y, z + 0.01f, 0, 1);
			
			tessellator.addVertexWithUV(x, y, z + 0.99f, 0, 0);
			tessellator.addVertexWithUV(x + 1, y, z + 0.99f, 1, 0);
			tessellator.addVertexWithUV(x + 1, y + 0.99f, z + 1, 1, 1);
			tessellator.addVertexWithUV(x, y + 0.99f, z + 1, 0, 1);
			
			tessellator.addVertexWithUV(x, y + 0.01f, z, 0, 0);
			tessellator.addVertexWithUV(x + 1, y + 0.01f, z, 1, 0);
			tessellator.addVertexWithUV(x + 1, y + 0.01f, z + 1, 1, 1);
			tessellator.addVertexWithUV(x, y + 0.01f, z + 1, 0, 1);
			
			tessellator.addVertexWithUV(x, y + 0.99f, z, 0, 0);
			tessellator.addVertexWithUV(x, y + 0.99f, z + 1, 1, 0);
			tessellator.addVertexWithUV(x + 1, y + 0.99f, z + 1, 1, 1);
			tessellator.addVertexWithUV(x + 1, y + 0.99f, z, 0, 1);
			
			tessellator.addVertexWithUV(x + 0.01f, y, z, 0, 0);
			tessellator.addVertexWithUV(x + 0.01f, y, z + 1, 1, 0);
			tessellator.addVertexWithUV(x + 0.01f, y + 1, z + 1, 1, 1);
			tessellator.addVertexWithUV(x + 0.01f, y + 1, z, 0, 1);
			
			tessellator.addVertexWithUV(x + 0.99f, y, z, 0, 0);
			tessellator.addVertexWithUV(x + 0.99f, y + 1, z, 1, 0);
			tessellator.addVertexWithUV(x + 0.99f, y + 1, z + 1, 1, 1);
			tessellator.addVertexWithUV(x + 0.99f, y, z + 1, 0, 1);
			
			tessellator.draw();
			GL11.glPopMatrix();
			GL11.glMatrixMode(GL11.GL_MODELVIEW);
		}
		
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_S);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_T);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_R);
		GL11.glDisable(GL11.GL_TEXTURE_GEN_Q);
		GL11.glEnable(GL11.GL_LIGHTING);
	}
	
	private FloatBuffer func_147525_a(float p_147525_1_, float p_147525_2_, float p_147525_3_, float p_147525_4_)
	{
		this.floatBuffer.clear();
		this.floatBuffer.put(p_147525_1_).put(p_147525_2_).put(p_147525_3_).put(p_147525_4_);
		this.floatBuffer.flip();
		return this.floatBuffer;
	}
	
}