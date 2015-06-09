package com.vinterdo.deusexmachina.block;

import com.vinterdo.deusexmachina.creativetab.CreativeTabDEM;
import com.vinterdo.deusexmachina.tileentity.TileEntityCamoBlock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockCamo extends BlockTileEntityDEM 
{
	public BlockCamo()
	{
		super();
		this.setBlockName("camoBlock");
		this.setHardness(4.0F);
		this.setCreativeTab(CreativeTabDEM.DEM_TAB);
	}
	
	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) 
	{
		return new TileEntityCamoBlock();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote)
		{
			TileEntityCamoBlock te = (TileEntityCamoBlock)world.getTileEntity(x, y, z);
			ItemStack stack = player.getCurrentEquippedItem();
			te.setCamo(stack, side);			
		}
		return true;
	}
	
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess world, int x, int y, int z, int side)
	{
		TileEntityCamoBlock te = (TileEntityCamoBlock)world.getTileEntity(x, y, z);
		ItemStack stack = te.getCamo(side);
		if(stack != null && stack.getItem() instanceof ItemBlock)
		{
			Block block = ((ItemBlock)stack.getItem()).field_150939_a;
			return block.getIcon(side, stack.getItemDamage());
		}
		else
		{
			return super.getIcon(world, x, y, z, side);
		}
	}
	
	@Override
    public boolean isOpaqueCube() 
    {
            return false;
    }
}
