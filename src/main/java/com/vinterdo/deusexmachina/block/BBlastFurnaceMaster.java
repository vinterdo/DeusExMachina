package com.vinterdo.deusexmachina.block;

import com.vinterdo.deusexmachina.DeusExMachina;
import com.vinterdo.deusexmachina.creativetab.CreativeTabDEM;
import com.vinterdo.deusexmachina.handler.GuiHandler;
import com.vinterdo.deusexmachina.tileentity.TEBlastFurnace;
import com.vinterdo.deusexmachina.tileentity.TEBlastFurnaceMaster;
import com.vinterdo.deusexmachina.tileentity.TECamoBlock;
import com.vinterdo.deusexmachina.tileentity.TEHeater;
import com.vinterdo.deusexmachina.utility.LogHelper;

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

public class BBlastFurnaceMaster extends BTileEntityDEM
{
	public BBlastFurnaceMaster()
	{
		super();
		this.setBlockName("blastFurnaceMaster");
		this.setHardness(5.0F);
		this.setCreativeTab(CreativeTabDEM.DEM_TAB);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_)
	{
		return new TEBlastFurnaceMaster();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
			float hitY, float hitZ)
	{
		if (!world.isRemote)
		{
			TEBlastFurnaceMaster te = (TEBlastFurnaceMaster) world.getTileEntity(x, y, z);
			if (te.isFormed())
				player.openGui(DeusExMachina.instance, GuiHandler.GuiIDs.BLAST_FURNACE.ordinal(), world, x, y, z);
			//LogHelper.info("formed");
			else
				te.tryForming();
		}
		
		return true;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		super.registerBlockIcons(iconRegister);
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
	{
		TEBlastFurnaceMaster te = (TEBlastFurnaceMaster) world.getTileEntity(x, y, z);
		te.destroyMultiblock();
		
		super.breakBlock(world, x, y, z, par5, par6);
	}
	
	@Override
	public int getRenderType()
	{
		return -1;
	}
	
	//It's not an opaque cube, so you need this.
	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
	//It's not a normal block, so you need this too.
	public boolean renderAsNormalBlock()
	{
		return false;
	}
}