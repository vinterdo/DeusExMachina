package com.vinterdo.deusexmachina.block;

import com.vinterdo.deusexmachina.DeusExMachina;
import com.vinterdo.deusexmachina.creativetab.CreativeTabDEM;
import com.vinterdo.deusexmachina.handler.GuiHandler;
import com.vinterdo.deusexmachina.tileentity.TECamoBlock;
import com.vinterdo.deusexmachina.tileentity.TEHeater;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BHeater extends BTileEntityDEM
{
	IIcon workingIcon;
	
	public BHeater()
	{
		super();
		this.setBlockName("heater");
		this.setHardness(5.0F);
		this.setCreativeTab(CreativeTabDEM.DEM_TAB);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_)
	{
		return new TEHeater();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
			float hitY, float hitZ)
	{
		if (!world.isRemote)
		{
			player.openGui(DeusExMachina.instance, GuiHandler.GuiIDs.HEATER.ordinal(), world, x, y, z);
		}
		
		return true;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		super.registerBlockIcons(iconRegister);
		workingIcon = iconRegister
				.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName() + "_working")));
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
	{
		TEHeater te = (TEHeater) world.getTileEntity(x, y, z);
		if (te.isWorking())
		{
			return workingIcon;
		} else
		{
			return super.getIcon(world, x, y, z, side);
		}
	}
	
	public int getLightValue(IBlockAccess world, int x, int y, int z)
	{
		TEHeater te = (TEHeater) world.getTileEntity(x, y, z);
		if (te != null)
		{
			if (te.isWorking())
			{
				return 14;
			}
		}
		return 0;
	}
	
}
