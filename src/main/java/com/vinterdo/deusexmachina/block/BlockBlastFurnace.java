package com.vinterdo.deusexmachina.block;

import com.vinterdo.deusexmachina.DeusExMachina;
import com.vinterdo.deusexmachina.creativetab.CreativeTabDEM;
import com.vinterdo.deusexmachina.handler.GuiHandler;
import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.tileentity.TileEntityBlastFurnace;
import com.vinterdo.deusexmachina.tileentity.TileEntityBlastFurnaceMaster;
import com.vinterdo.deusexmachina.tileentity.TileEntityCamoBlock;
import com.vinterdo.deusexmachina.tileentity.TileEntityHeater;
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
import net.minecraftforge.common.util.ForgeDirection;


public class BlockBlastFurnace extends BlockTileEntityDEM
{
	public BlockBlastFurnace()
	{
		super();
		this.setBlockName("blastFurnace");
		this.setHardness(5.0F);
		this.setCreativeTab(CreativeTabDEM.DEM_TAB);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) 
	{
		return new TileEntityBlastFurnace();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote)
		{
			TileEntityBlastFurnace te = (TileEntityBlastFurnace)world.getTileEntity(x, y, z);
			// TODO: add opening masters GUI : player.openGui(DeusExMachina.instance, GuiHandler.GuiIDs.BLAST_FURNACE.ordinal(), world, x, y, z);
			if(te.isFormed()) 
			{
				player.openGui(DeusExMachina.instance, GuiHandler.GuiIDs.BLAST_FURNACE.ordinal(), world, te.getMaster().xCoord, te.getMaster().yCoord, te.getMaster().zCoord);
				return true;
			}
			else
			{
				return false;
			}
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
		TileEntityBlastFurnaceMaster te = (TileEntityBlastFurnaceMaster) ((TileEntityBlastFurnace)world.getTileEntity(x, y, z)).getMaster();
		if(te!= null) 
			te.removeMember((TileEntityBlastFurnace)world.getTileEntity(x, y, z));

		super.breakBlock(world, x, y, z, par5, par6);
		
		if(te!= null) 
			te.tryForming();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) 
	{
			TileEntityBlastFurnace teMe = (TileEntityBlastFurnace) world.getTileEntity(x - ForgeDirection.getOrientation(side).offsetX, y - ForgeDirection.getOrientation(side).offsetY, z - ForgeDirection.getOrientation(side).offsetZ);
			
			return !teMe.isFormed();
	}
	
    @Override
    public boolean isOpaqueCube() 
    {
            return false;
    }
}