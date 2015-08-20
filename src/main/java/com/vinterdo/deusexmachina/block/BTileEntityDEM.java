package com.vinterdo.deusexmachina.block;

import com.vinterdo.deusexmachina.reference.Reference;
import com.vinterdo.deusexmachina.tileentity.base.TEI;
import com.vinterdo.deusexmachina.tileentity.base.TEIMultiblockMaster;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class BTileEntityDEM extends BlockContainer
{
	public BTileEntityDEM(Material material)
	{
		super(material);
	}
	
	public BTileEntityDEM()
	{
		super(Material.rock);
	}
	
	@Override
	public String getUnlocalizedName()
	{
		return String.format("tile.%s%s", Reference.MOD_ID.toLowerCase() + ":",
				getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		blockIcon = iconRegister
				.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName())));
	}
	
	protected String getUnwrappedUnlocalizedName(String unlocalizedName)
	{
		return unlocalizedName.substring(unlocalizedName.indexOf(".") + 1);
	}
	
	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int meta, EntityPlayer player)
	{
		TileEntity te = world.getTileEntity(x, y, z);
		if (te instanceof TEI)
		{
			((TEI) te).dropInventoryInWorld();
		}
		if (te instanceof TEIMultiblockMaster)
		{
			((TEIMultiblockMaster) te).dropInventoryInWorld();
		}
		super.onBlockHarvested(world, x, y, z, meta, player);
	}
	
}
