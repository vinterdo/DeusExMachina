package com.vinterdo.deusexmachina.renderers;

import java.nio.FloatBuffer;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import com.vinterdo.deusexmachina.reference.Reference;
import com.vinterdo.deusexmachina.tileentity.TEShield;

import assets.deusexmachina.models.ModelBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class ShieldRenderer extends TileEntitySpecialRenderer
{
	private static final ResourceLocation	skyLocation			= new ResourceLocation(
			Reference.MOD_ID + ":textures/effects/end_sky.png");
	private static final ResourceLocation	endPortalLocation	= new ResourceLocation(
			Reference.MOD_ID + ":textures/effects/end_portal.png");
	private static final Random				random				= new Random(31100L);
	FloatBuffer								floatBuffer			= GLAllocation.createDirectFloatBuffer(16);
	
	private final ModelBlock modelBlock = new ModelBlock();
	
	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale)
	{
		GL11.glEnable(GL11.GL_CULL_FACE);
		TEShield shield = (TEShield) te;
		if (shield.hitStr > 0)
		{
		
		}
		float f1 = (float) this.field_147501_a.field_147560_j;
		float f2 = (float) this.field_147501_a.field_147561_k;
		float f3 = (float) this.field_147501_a.field_147558_l;
		GL11.glDisable(GL11.GL_LIGHTING);
		random.setSeed(31100L);
		float f4 = 0.75F;
		for (int i = 0; i < 8; ++i)
		{
			GL11.glPushMatrix();
			float f5 = (8 - i) / 1f;
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
			
			float f8 = f4;
			float f9 = f8 + ActiveRenderInfo.objectY;
			float f10 = f8 + ActiveRenderInfo.objectY;
			float f11 = f9 / f10;
			GL11.glPopMatrix();
			GL11.glMatrixMode(GL11.GL_TEXTURE);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
			GL11.glPushMatrix();
			GL11.glLoadIdentity();
			GL11.glTranslatef(te.xCoord, te.yCoord, te.zCoord);
			GL11.glTranslatef((Minecraft.getSystemTime()) % 7000L / 7000.0F,
					(Minecraft.getSystemTime()) % 7000L / 7000.0F, Minecraft.getSystemTime() % 7000L / 7000.0F);
			GL11.glScalef(f5, f5, f5);
			GL11.glTranslatef(-f1, -f3, -f2);
			f9 = f8 + ActiveRenderInfo.objectY;
			GL11.glTranslatef(ActiveRenderInfo.objectX * f5 / f9, ActiveRenderInfo.objectZ * f5 / f9, 0);
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
			
			tessellator.setColorRGBA_F(0.7f, 0.35f, 0f, 0.3F);
			
			//tessellator.addVertexWithUV(x, y, z, 0, 0);
			//tessellator.addVertexWithUV(x, y, z, 0, 1);
			//tessellator.addVertexWithUV(x, y, z, 1, 1);
			//tessellator.addVertexWithUV(x, y, z, 1, 0);
			
			if (!(te.getWorldObj().getTileEntity(te.xCoord, te.yCoord, te.zCoord - 1) instanceof TEShield))
			{
				tessellator.addVertexWithUV(x, y, z, 0, 0);
				tessellator.addVertexWithUV(x, y + 1, z, 1, 0);
				tessellator.addVertexWithUV(x + 1, y + 1, z, 1, 1);
				tessellator.addVertexWithUV(x + 1, y, z, 0, 1);
			}
			if (!(te.getWorldObj().getTileEntity(te.xCoord, te.yCoord, te.zCoord + 1) instanceof TEShield))
			{
				tessellator.addVertexWithUV(x, y, z + 1f, 0, 0);
				tessellator.addVertexWithUV(x + 1, y, z + 1f, 1, 0);
				tessellator.addVertexWithUV(x + 1, y + 1f, z + 1, 1, 1);
				tessellator.addVertexWithUV(x, y + 1f, z + 1, 0, 1);
			}
			if (!(te.getWorldObj().getTileEntity(te.xCoord, te.yCoord - 1, te.zCoord) instanceof TEShield))
			{
				tessellator.addVertexWithUV(x, y, z, 0, 0);
				tessellator.addVertexWithUV(x + 1, y, z, 1, 0);
				tessellator.addVertexWithUV(x + 1, y, z + 1, 1, 1);
				tessellator.addVertexWithUV(x, y, z + 1, 0, 1);
			}
			if (!(te.getWorldObj().getTileEntity(te.xCoord, te.yCoord + 1, te.zCoord) instanceof TEShield))
			{
				tessellator.addVertexWithUV(x, y + 1f, z, 0, 0);
				tessellator.addVertexWithUV(x, y + 1f, z + 1, 1, 0);
				tessellator.addVertexWithUV(x + 1, y + 1f, z + 1, 1, 1);
				tessellator.addVertexWithUV(x + 1, y + 1f, z, 0, 1);
			}
			if (!(te.getWorldObj().getTileEntity(te.xCoord - 1, te.yCoord, te.zCoord) instanceof TEShield))
			{
				tessellator.addVertexWithUV(x, y, z, 0, 0);
				tessellator.addVertexWithUV(x, y, z + 1, 1, 0);
				tessellator.addVertexWithUV(x, y + 1, z + 1, 1, 1);
				tessellator.addVertexWithUV(x, y + 1, z, 0, 1);
			}
			if (!(te.getWorldObj().getTileEntity(te.xCoord + 1, te.yCoord, te.zCoord) instanceof TEShield))
			{
				tessellator.addVertexWithUV(x + 1f, y, z, 0, 0);
				tessellator.addVertexWithUV(x + 1f, y + 1, z, 1, 0);
				tessellator.addVertexWithUV(x + 1f, y + 1, z + 1, 1, 1);
				tessellator.addVertexWithUV(x + 1f, y, z + 1, 0, 1);
			}
			
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