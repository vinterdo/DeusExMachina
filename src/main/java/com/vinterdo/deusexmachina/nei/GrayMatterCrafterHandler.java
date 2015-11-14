package com.vinterdo.deusexmachina.nei;
/*package com.vinterdo.deusexmachina.nei;

import codechicken.nei.recipe.TemplateRecipeHandler;

public class GrayMatterCrafterHandler extends TemplateRecipeHandler
{
	
	ResourceLocation texture = new ResourceLocation(
			Reference.MOD_ID.toLowerCase() + ":textures/gui/grayMatterCrafter.png");
			
	@Override
	public String getRecipeName()
	{
		return "Gray Matter Crafter"; // TODO: add localization
	}
	
	@Override
	public String getGuiTexture()
	{
		return Reference.MOD_ID.toLowerCase() + ":textures/gui/grayMatterCrafter.png";
	}
	
	@Override
	public void drawBackground(int recipe)
	{
		GL11.glColor4f(1, 1, 1, 1);
		FMLClientHandler.instance().getClient().getTextureManager().bindTexture(texture);
		GL11.glColor4f(1, 1, 1, 1);
		Gui.func_146110_a(0, 0, 5, 11, 166, 130, 256, 256);
	}
	
	@Override
	public Class<? extends GuiContainer> getGuiClass()
	{
		return GuiGrayMatterCrafter.class;
	}
	
	@Override
	public boolean hasOverlay(GuiContainer gui, Container container, int recipe)
	{
		return false;
	}
	
	public static class CachedGrayMatterRecipe extends CachedShapedRecipe
	{
		RecipeGrayMatter recipe;
		
		public CachedGrayMatterRecipe(RecipeGrayMatter recipe)
		{
			super(4, 4, Array2DTo1D(recipe.grid), recipe.output);
			this.recipe = recipe;
		}
		
		private static Object[] Array2DTo1D(Object[][] arr)
		{
			Object[] ret = new Object[arr.length * arr[0].length];
			for (int i = 0; i < arr.length; i++)
			{
				for (int j = 0; j < arr[i].length; j++)
				{
					ret[i * arr[0].length + j] = arr[i][j];
				}
			}
			return ret;
		}
		
	}
	
	@Override
	public void loadCraftingRecipes(String outputId, Object... results)
	{
		if (outputId.equals("alchemicalwizardry.altar") && getClass() == NEIAltarRecipeHandler.class)
		{
			for (AltarRecipe recipe : AltarRecipeRegistry.altarRecipes)
			{
				if (recipe != null && recipe.result != null)
					arecipes.add(new CachedAltarRecipe(recipe));
			}
		} else
		{
			super.loadCraftingRecipes(outputId, results);
		}
	}
	
	@Override
	public void loadCraftingRecipes(ItemStack result)
	{
		for (AltarRecipe recipe : AltarRecipeRegistry.altarRecipes)
		{
			if (NEIServerUtils.areStacksSameTypeCrafting(recipe.result, result))
			{
				if (recipe != null && recipe.result != null)
					arecipes.add(new CachedAltarRecipe(recipe));
			}
		}
	}
	
	@Override
	public void loadUsageRecipes(ItemStack ingredient)
	{
		for (AltarRecipe recipe : AltarRecipeRegistry.altarRecipes)
		{
			if (NEIServerUtils.areStacksSameTypeCrafting(recipe.requiredItem, ingredient))
			{
				if (recipe != null && recipe.result != null)
					arecipes.add(new CachedAltarRecipe(recipe));
			}
		}
	}
	
	//Mouse Position helper
	public Point getMouse(int width, int height)
	{
		Point mousepos = getMousePosition();
		int guiLeft = (width - 176) / 2;
		int guiTop = (height - 166) / 2;
		Point relMouse = new Point(mousepos.x - guiLeft, mousepos.y - guiTop);
		return relMouse;
	}
	
	//width helper, getting width normal way hates me on compile
	public int getGuiWidth(GuiRecipe gui)
	{
		try
		{
			Field f = gui.getClass().getField("width");
			return (Integer) f.get(gui);
		} catch (NoSuchFieldException e)
		{
			try
			{
				Field f = gui.getClass().getField("field_146294_l");
				return (Integer) f.get(gui);
			} catch (Exception e2)
			{
				return 0;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	//height helper, getting height normal way hates me on compile
	public int getGuiHeight(GuiRecipe gui)
	{
		try
		{
			Field f = gui.getClass().getField("height");
			return (Integer) f.get(gui);
		} catch (NoSuchFieldException e)
		{
			try
			{
				Field f = gui.getClass().getField("field_146295_m");
				return (Integer) f.get(gui);
			} catch (Exception e2)
			{
				return 0;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public String getOverlayIdentifier()
	{
		return "altarrecipes";
	}
	
	@Override
	public void loadTransferRects()
	{
		transferRects.add(new RecipeTransferRect(new Rectangle(90, 32, 22, 16), "alchemicalwizardry.altar"));
	}
	
	public static Point getMousePosition()
	{
		Dimension size = displaySize();
		Dimension res = displayRes();
		return new Point(Mouse.getX() * size.width / res.width,
				size.height - Mouse.getY() * size.height / res.height - 1);
	}
	
	public static Dimension displaySize()
	{
		Minecraft mc = Minecraft.getMinecraft();
		ScaledResolution res = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		return new Dimension(res.getScaledWidth(), res.getScaledHeight());
	}
	
	public static Dimension displayRes()
	{
		Minecraft mc = Minecraft.getMinecraft();
		return new Dimension(mc.displayWidth, mc.displayHeight);
	}
}*/
