package assets.deusexmachina.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;


public class ModelGrayMatterFabricator extends ModelBase
{
  //fields
    ModelRenderer Column1;
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
  
  public ModelGrayMatterFabricator()
  {
    textureWidth = 512;
    textureHeight = 256;
    
      Column1 = new ModelRenderer(this, 240, 188);
      Column1.addBox(0F, 0F, 0F, 16, 16, 16);
      Column1.setRotationPoint(-32F, 0F, 16F);
      Column1.setTextureSize(512, 256);
      Column1.mirror = true;
      setRotation(Column1, 0F, 0F, 0F);
      Shape1 = new ModelRenderer(this, 0, 172);
      Shape1.addBox(-40F, 0F, -40F, 80, 4, 80);
      Shape1.setRotationPoint(0F, 20F, 0F);
      Shape1.setTextureSize(512, 256);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 0, 109);
      Shape2.addBox(-24F, 0F, -24F, 48, 15, 48);
      Shape2.setRotationPoint(0F, 8F, 0F);
      Shape2.setTextureSize(512, 256);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 0, 220);
      Shape3.addBox(0F, 0F, 0F, 16, 16, 16);
      Shape3.setRotationPoint(16F, 0F, 16F);
      Shape3.setTextureSize(512, 256);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 0, 188);
      Shape4.addBox(0F, 0F, 0F, 16, 16, 16);
      Shape4.setRotationPoint(-32F, 0F, -32F);
      Shape4.setTextureSize(512, 256);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 240, 220);
      Shape5.addBox(0F, 0F, 0F, 16, 16, 16);
      Shape5.setRotationPoint(16F, 0F, -32F);
      Shape5.setTextureSize(512, 256);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      Shape6 = new ModelRenderer(this, 192, 132);
      Shape6.addBox(-12F, 0F, -12F, 24, 16, 24);
      Shape6.setRotationPoint(0F, -8F, 0F);
      Shape6.setTextureSize(512, 256);
      Shape6.mirror = true;
      setRotation(Shape6, 0F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 192, 101);
      Shape7.addBox(-12F, 0F, -12F, 24, 7, 24);
      Shape7.setRotationPoint(-28F, 16F, -28F);
      Shape7.setTextureSize(512, 256);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 192, 70);
      Shape8.addBox(0F, 0F, 0F, 24, 7, 24);
      Shape8.setRotationPoint(-40F, 16F, 16F);
      Shape8.setTextureSize(512, 256);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 192, 39);
      Shape9.addBox(0F, 0F, 0F, 24, 7, 24);
      Shape9.setRotationPoint(16F, 16F, 16F);
      Shape9.setTextureSize(512, 256);
      Shape9.mirror = true;
      setRotation(Shape9, 0F, 0F, 0F);
      Shape10 = new ModelRenderer(this, 192, 8);
      Shape10.addBox(0F, 0F, 0F, 24, 7, 24);
      Shape10.setRotationPoint(16F, 16F, -40F);
      Shape10.setTextureSize(512, 256);
      Shape10.mirror = true;
      setRotation(Shape10, 0F, 0F, 0F);
      Shape11 = new ModelRenderer(this, 96, 54);
      Shape11.addBox(-16F, 0F, -32F, 16, 23, 32);
      Shape11.setRotationPoint(8F, 0F, 40F);
      Shape11.setTextureSize(512, 256);
      Shape11.mirror = true;
      setRotation(Shape11, -0.2443461F, 0F, 0F);
      Shape13 = new ModelRenderer(this, 0, 54);
      Shape13.addBox(0F, 0F, 0F, 16, 23, 32);
      Shape13.setRotationPoint(-8F, 0F, -40F);
      Shape13.setTextureSize(512, 256);
      Shape13.mirror = true;
      setRotation(Shape13, 0.2443461F, 0F, 0F);
      Shape14 = new ModelRenderer(this, 96, 15);
      Shape14.addBox(-32F, 0F, -16F, 32, 23, 16);
      Shape14.setRotationPoint(40F, 0F, 8F);
      Shape14.setTextureSize(512, 256);
      Shape14.mirror = true;
      setRotation(Shape14, 0F, 0F, 0.2443461F);
      Shape15 = new ModelRenderer(this, 0, 15);
      Shape15.addBox(0F, 0F, 0F, 32, 23, 16);
      Shape15.setRotationPoint(-40F, 0F, -8F);
      Shape15.setTextureSize(512, 256);
      Shape15.mirror = true;
      setRotation(Shape15, 0F, 0F, -0.2443461F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Column1.render(f5);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
    Shape6.render(f5);
    Shape7.render(f5);
    Shape8.render(f5);
    Shape9.render(f5);
    Shape10.render(f5);
    Shape11.render(f5);
    Shape13.render(f5);
    Shape14.render(f5);
    Shape15.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
  }

}
