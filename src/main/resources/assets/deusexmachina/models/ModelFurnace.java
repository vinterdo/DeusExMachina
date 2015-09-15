package assets.deusexmachina.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelFurnace extends ModelBase
{
	//fields
	ModelRenderer	Glowne;
	ModelRenderer	Bok1;
	ModelRenderer	Bok2;
	ModelRenderer	Bok3;
	ModelRenderer	Bok4;
	ModelRenderer	Podstawa;
	ModelRenderer	Noga1;
	ModelRenderer	Noga2;
	ModelRenderer	Noga3;
	ModelRenderer	Noga4;
	ModelRenderer	Shape1;
	ModelRenderer	Shape2;
	ModelRenderer	Shape3;
	ModelRenderer	Shape4;
	
	public ModelFurnace()
	{
		textureWidth = 256;
		textureHeight = 256;
		
		Glowne = new ModelRenderer(this, 0, 176);
		Glowne.addBox(-16F, -48F, -16F, 32, 48, 32);
		Glowne.setRotationPoint(0F, 16F, 0F);
		Glowne.setTextureSize(256, 256);
		Glowne.mirror = true;
		setRotation(Glowne, 0F, 0F, 0F);
		Bok1 = new ModelRenderer(this, 0, 124);
		Bok1.addBox(0F, 0F, 0F, 24, 48, 4);
		Bok1.setRotationPoint(-12F, -32F, 16F);
		Bok1.setTextureSize(256, 256);
		Bok1.mirror = true;
		setRotation(Bok1, 0F, 0F, 0F);
		Bok2 = new ModelRenderer(this, 56, 124);
		Bok2.addBox(0F, 0F, 0F, 24, 48, 4);
		Bok2.setRotationPoint(-12F, -32F, -20F);
		Bok2.setTextureSize(256, 256);
		Bok2.mirror = true;
		setRotation(Bok2, 0F, 0F, 0F);
		Bok3 = new ModelRenderer(this, 0, 52);
		Bok3.addBox(0F, 0F, 0F, 4, 48, 24);
		Bok3.setRotationPoint(-20F, -32F, -12F);
		Bok3.setTextureSize(256, 256);
		Bok3.mirror = true;
		setRotation(Bok3, 0F, 0F, 0F);
		Bok4 = new ModelRenderer(this, 56, 52);
		Bok4.addBox(0F, 0F, 0F, 4, 48, 24);
		Bok4.setRotationPoint(16F, -32F, -12F);
		Bok4.setTextureSize(256, 256);
		Bok4.mirror = true;
		setRotation(Bok4, 0F, 0F, 0F);
		Podstawa = new ModelRenderer(this, 0, 0);
		Podstawa.addBox(0F, 0F, 0F, 46, 1, 46);
		Podstawa.setRotationPoint(-23F, 23F, -23F);
		Podstawa.setTextureSize(256, 256);
		Podstawa.mirror = true;
		setRotation(Podstawa, 0F, 0F, 0F);
		Noga1 = new ModelRenderer(this, 112, 52);
		Noga1.addBox(0F, 0F, 0F, 4, 7, 4);
		Noga1.setRotationPoint(-16F, 16F, -16F);
		Noga1.setTextureSize(256, 256);
		Noga1.mirror = true;
		setRotation(Noga1, 0F, 0F, 0F);
		Noga2 = new ModelRenderer(this, 112, 63);
		Noga2.addBox(0F, 0F, 0F, 4, 7, 4);
		Noga2.setRotationPoint(12F, 16F, -16F);
		Noga2.setTextureSize(256, 256);
		Noga2.mirror = true;
		setRotation(Noga2, 0F, 0F, 0F);
		Noga3 = new ModelRenderer(this, 112, 74);
		Noga3.addBox(0F, 0F, 0F, 4, 7, 4);
		Noga3.setRotationPoint(12F, 16F, 12F);
		Noga3.setTextureSize(256, 256);
		Noga3.mirror = true;
		setRotation(Noga3, 0F, 0F, 0F);
		Noga4 = new ModelRenderer(this, 112, 85);
		Noga4.addBox(0F, 0F, 0F, 4, 7, 4);
		Noga4.setRotationPoint(-16F, 16F, 12F);
		Noga4.setTextureSize(256, 256);
		Noga4.mirror = true;
		setRotation(Noga4, 0F, 0F, 0F);
		Shape1 = new ModelRenderer(this, 0, 50);
		Shape1.addBox(0F, 0F, 0F, 46, 1, 1);
		Shape1.setRotationPoint(-23F, 22F, 22F);
		Shape1.setTextureSize(256, 256);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 0, 48);
		Shape2.addBox(0F, 0F, 0F, 46, 1, 1);
		Shape2.setRotationPoint(-23F, 22F, -23F);
		Shape2.setTextureSize(256, 256);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
		Shape3 = new ModelRenderer(this, 128, 211);
		Shape3.addBox(0F, 0F, 0F, 1, 1, 44);
		Shape3.setRotationPoint(22F, 22F, -22F);
		Shape3.setTextureSize(256, 256);
		Shape3.mirror = true;
		setRotation(Shape3, 0F, 0F, 0F);
		Shape4 = new ModelRenderer(this, 128, 166);
		Shape4.addBox(0F, 0F, 0F, 1, 1, 44);
		Shape4.setRotationPoint(-23F, 22F, -22F);
		Shape4.setTextureSize(256, 256);
		Shape4.mirror = true;
		setRotation(Shape4, 0F, 0F, 0F);
		
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		//super.render(entity, f, f1, f2, f3, f4, f5);
		
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Glowne.render(f5);
		Bok1.render(f5);
		Bok2.render(f5);
		Bok3.render(f5);
		Bok4.render(f5);
		Podstawa.render(f5);
		Noga1.render(f5);
		Noga2.render(f5);
		Noga3.render(f5);
		Noga4.render(f5);
		Shape1.render(f5);
		Shape2.render(f5);
		Shape3.render(f5);
		Shape4.render(f5);
		
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
	
}
