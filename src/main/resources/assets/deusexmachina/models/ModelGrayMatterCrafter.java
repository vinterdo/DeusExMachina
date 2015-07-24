package assets.deusexmachina.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGrayMatterCrafter extends ModelBase
{
  //fields
    ModelRenderer Base;
    ModelRenderer Top;
    ModelRenderer Edge1;
    ModelRenderer Edge2;
    ModelRenderer Edge3;
    ModelRenderer Edge4;
    ModelRenderer Core1;
    ModelRenderer Core2;
    ModelRenderer Port1;
    ModelRenderer Port2;
    ModelRenderer Port3;
    ModelRenderer Port4;
    ModelRenderer Screen1;
    ModelRenderer Screen2;
    ModelRenderer Screen3;
    ModelRenderer Screen4;
  
  public ModelGrayMatterCrafter()
  {
    textureWidth = 256;
    textureHeight = 256;
    
      Base = new ModelRenderer(this, 0, 72);
      Base.addBox(0F, 0F, 0F, 48, 4, 48);
      Base.setRotationPoint(-24F, 20F, -24F);
      Base.setTextureSize(256, 256);
      Base.mirror = true;
      setRotation(Base, 0F, 0F, 0F);
      Top = new ModelRenderer(this, 0, 72);
      Top.addBox(0F, 0F, 0F, 48, 4, 48);
      Top.setRotationPoint(-24F, -18F, -24F);
      Top.setTextureSize(256, 256);
      Top.mirror = true;
      setRotation(Top, 0F, 0F, 0F);
      Edge1 = new ModelRenderer(this, 0, 72);
      Edge1.addBox(0F, 0F, 0F, 4, 34, 4);
      Edge1.setRotationPoint(-24F, -14F, -24F);
      Edge1.setTextureSize(256, 256);
      Edge1.mirror = true;
      setRotation(Edge1, 0F, 0F, 0F);
      Edge2 = new ModelRenderer(this, 0, 72);
      Edge2.addBox(0F, 0F, 0F, 4, 34, 4);
      Edge2.setRotationPoint(-24F, -14F, 20F);
      Edge2.setTextureSize(256, 256);
      Edge2.mirror = true;
      setRotation(Edge2, 0F, 0F, 0F);
      Edge3 = new ModelRenderer(this, 0, 72);
      Edge3.addBox(0F, 0F, 0F, 4, 34, 4);
      Edge3.setRotationPoint(20F, -14F, -24F);
      Edge3.setTextureSize(256, 256);
      Edge3.mirror = true;
      setRotation(Edge3, 0F, 0F, 0F);
      Edge4 = new ModelRenderer(this, 0, 72);
      Edge4.addBox(0F, 0F, 0F, 4, 34, 4);
      Edge4.setRotationPoint(20F, -14F, 20F);
      Edge4.setTextureSize(256, 256);
      Edge4.mirror = true;
      setRotation(Edge4, 0F, 0F, 0F);
      Core1 = new ModelRenderer(this, 0, 0);
      Core1.addBox(0F, 0F, 0F, 24, 34, 32);
      Core1.setRotationPoint(-12F, -14F, -16F);
      Core1.setTextureSize(256, 256);
      Core1.mirror = true;
      setRotation(Core1, 0F, 0F, 0F);
      Core2 = new ModelRenderer(this, 0, 124);
      Core2.addBox(0F, 0F, 0F, 32, 34, 24);
      Core2.setRotationPoint(-16F, -14F, -12F);
      Core2.setTextureSize(256, 256);
      Core2.mirror = true;
      setRotation(Core2, 0F, 0F, 0F);
      Port1 = new ModelRenderer(this, 112, 0);
      Port1.addBox(0F, 0F, 0F, 16, 12, 16);
      Port1.setRotationPoint(8F, 8F, -8F);
      Port1.setTextureSize(256, 256);
      Port1.mirror = true;
      setRotation(Port1, 0F, 0F, 0F);
      Port2 = new ModelRenderer(this, 112, 0);
      Port2.addBox(0F, 0F, 0F, 16, 12, 16);
      Port2.setRotationPoint(-8F, 8F, 8F);
      Port2.setTextureSize(256, 256);
      Port2.mirror = true;
      setRotation(Port2, 0F, 0F, 0F);
      Port3 = new ModelRenderer(this, 112, 0);
      Port3.addBox(0F, 0F, 0F, 16, 12, 16);
      Port3.setRotationPoint(-8F, 8F, -24F);
      Port3.setTextureSize(256, 256);
      Port3.mirror = true;
      setRotation(Port3, 0F, 0F, 0F);
      Port4 = new ModelRenderer(this, 112, 0);
      Port4.addBox(0F, 0F, 0F, 16, 12, 16);
      Port4.setRotationPoint(-24F, 8F, -8F);
      Port4.setTextureSize(256, 256);
      Port4.mirror = true;
      setRotation(Port4, 0F, 0F, 0F);
      Screen1 = new ModelRenderer(this, 112, 50);
      Screen1.addBox(0F, 0F, 0F, 32, 16, 1);
      Screen1.setRotationPoint(-16F, -14F, -24F);
      Screen1.setTextureSize(256, 256);
      Screen1.mirror = true;
      setRotation(Screen1, 0.5235988F, 0F, 0F);
      Screen2 = new ModelRenderer(this, 112, 50);
      Screen2.addBox(0F, 0F, 0F, 32, 16, 1);
      Screen2.setRotationPoint(-16F, -14F, 23F);
      Screen2.setTextureSize(256, 256);
      Screen2.mirror = true;
      setRotation(Screen2, -0.5235988F, 0F, 0F);
      Screen3 = new ModelRenderer(this, 112, 50);
      Screen3.addBox(0F, 0F, 0F, 32, 16, 1);
      Screen3.setRotationPoint(-24F, -14F, 16F);
      Screen3.setTextureSize(256, 256);
      Screen3.mirror = true;
      setRotation(Screen3, 0.5235988F, 1.570796F, 0F);
      Screen4 = new ModelRenderer(this, 112, 50);
      Screen4.addBox(0F, 0F, 0F, 32, 16, 1);
      Screen4.setRotationPoint(24F, -14F, -16F);
      Screen4.setTextureSize(256, 256);
      Screen4.mirror = true;
      setRotation(Screen4, 0.5235988F, -1.570796F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Base.render(f5);
    Top.render(f5);
    Edge1.render(f5);
    Edge2.render(f5);
    Edge3.render(f5);
    Edge4.render(f5);
    Core1.render(f5);
    Core2.render(f5);
    Port1.render(f5);
    Port2.render(f5);
    Port3.render(f5);
    Port4.render(f5);
    Screen1.render(f5);
    Screen2.render(f5);
    Screen3.render(f5);
    Screen4.render(f5);
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
