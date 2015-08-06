package com.vinterdo.deusexmachina.fluids;

import com.vinterdo.deusexmachina.creativetab.CreativeTabDEM;
import com.vinterdo.deusexmachina.init.ModFluids;
import com.vinterdo.deusexmachina.reference.Reference;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;

public class BFluidGrayMatter extends BlockFluidClassic
{
	@SideOnly(Side.CLIENT)
    protected IIcon stillIcon;
     
	public BFluidGrayMatter() 
	{
		super(ModFluids.grayMatter, Material.water);
		setCreativeTab(CreativeTabDEM.DEM_TAB);
		setBlockName("fluidGrayMatter");
	}

	@Override
    public IIcon getIcon(int side, int meta) 
	{
            return stillIcon;
    }
	
	@SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister register) 
	{
            stillIcon = register.registerIcon(Reference.MOD_ID + ":grayMatterStill");
    }
	
	@Override
    public boolean canDisplace(IBlockAccess world, int x, int y, int z) 
	{
            if (world.getBlock(x,  y,  z).getMaterial().isLiquid()) return false;
            return super.canDisplace(world, x, y, z);
    }
   
    @Override
    public boolean displaceIfPossible(World world, int x, int y, int z) 
    {
            if (world.getBlock(x,  y,  z).getMaterial().isLiquid()) return false;
            return super.displaceIfPossible(world, x, y, z);
    }
}