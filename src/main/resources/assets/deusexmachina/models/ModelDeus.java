

package assets.deusexmachina.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelDeus extends ModelBase
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
  
  public ModelDeus()
  {
    textureWidth = 512;
    textureHeight = 256;
    
      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(0F, 0F, 0F, 82, 16, 82);
      Shape1.setRotationPoint(-41F, 7F, -41F);
      Shape1.setTextureSize(512, 256);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 400, 0);
      Shape2.addBox(0F, 0F, 0F, 3, 155, 3);
      Shape2.setRotationPoint(-1F, -133F, 1F);
      Shape2.setTextureSize(512, 256);
      Shape2.mirror = true;
      setRotation(Shape2, 0.3403392F, 0.7853982F, 0F);
      Shape3 = new ModelRenderer(this, 420, 0);
      Shape3.addBox(0F, 0F, 0F, 3, 155, 3);
      Shape3.setRotationPoint(1F, -133F, 0F);
      Shape3.setTextureSize(512, 256);
      Shape3.mirror = true;
      setRotation(Shape3, 0.3403392F, 2.356194F, 0F);
      Shape4 = new ModelRenderer(this, 440, 0);
      Shape4.addBox(0F, 0F, 0F, 3, 155, 3);
      Shape4.setRotationPoint(-2F, -133F, -1F);
      Shape4.setTextureSize(512, 256);
      Shape4.mirror = true;
      setRotation(Shape4, 0.3403392F, -0.7853982F, 0F);
      Shape5 = new ModelRenderer(this, 460, 0);
      Shape5.addBox(0F, 0F, 0F, 3, 155, 3);
      Shape5.setRotationPoint(0F, -132F, -2F);
      Shape5.setTextureSize(512, 256);
      Shape5.mirror = true;
      setRotation(Shape5, 0.3403392F, -2.356194F, 0F);
      Shape6 = new ModelRenderer(this, 0, 100);
      Shape6.addBox(0F, 0F, 0F, 10, 10, 10);
      Shape6.setRotationPoint(-5.5F, -136F, -5.5F);
      Shape6.setTextureSize(512, 256);
      Shape6.mirror = true;
      setRotation(Shape6, 0F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 0, 120);
      Shape7.addBox(0F, 0F, 0F, 16, 8, 82);
      Shape7.setRotationPoint(-8F, 0F, -41F);
      Shape7.setTextureSize(512, 256);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 200, 120);
      Shape8.addBox(0F, 0F, 0F, 82, 8, 16);
      Shape8.setRotationPoint(-41F, 0F, -8F);
      Shape8.setTextureSize(512, 256);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
    Shape6.render(f5);
    Shape7.render(f5);
    Shape8.render(f5);
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
