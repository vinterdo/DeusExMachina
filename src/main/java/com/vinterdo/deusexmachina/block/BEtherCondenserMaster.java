package com.vinterdo.deusexmachina.block;

import com.vinterdo.deusexmachina.DeusExMachina;
import com.vinterdo.deusexmachina.creativetab.CreativeTabDEM;
import com.vinterdo.deusexmachina.handler.GuiHandler;
import com.vinterdo.deusexmachina.tileentity.TEBlastFurnaceMaster;
import com.vinterdo.deusexmachina.tileentity.TEEtherCondenserMaster;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BEtherCondenserMaster extends BTileEntityDEM {

	public BEtherCondenserMaster()
	{
		super();
		this.setBlockName("etherCondenserMaster");
		this.setHardness(5.0F);
		this.setCreativeTab(CreativeTabDEM.DEM_TAB);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_)
	{
		return new TEEtherCondenserMaster();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
			float hitY, float hitZ)
	{
		if (!world.isRemote)
		{
			TEEtherCondenserMaster te = (TEEtherCondenserMaster) world.getTileEntity(x, y, z);
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
		TEEtherCondenserMaster te = (TEEtherCondenserMaster) world.getTileEntity(x, y, z);
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
