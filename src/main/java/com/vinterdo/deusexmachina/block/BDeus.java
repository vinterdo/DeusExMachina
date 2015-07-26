package com.vinterdo.deusexmachina.block;

import com.vinterdo.deusexmachina.DeusExMachina;
import com.vinterdo.deusexmachina.creativetab.CreativeTabDEM;
import com.vinterdo.deusexmachina.handler.GuiHandler;
import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.tileentity.TEBlastFurnace;
import com.vinterdo.deusexmachina.tileentity.TEBlastFurnaceMaster;
import com.vinterdo.deusexmachina.tileentity.TECamoBlock;
import com.vinterdo.deusexmachina.tileentity.TEDeus;
import com.vinterdo.deusexmachina.tileentity.TEDeusMaster;
import com.vinterdo.deusexmachina.tileentity.TEGrayMatterCrafter;
import com.vinterdo.deusexmachina.tileentity.TEGrayMatterCrafterMaster;
import com.vinterdo.deusexmachina.tileentity.TEGrayMatterFabricator;
import com.vinterdo.deusexmachina.tileentity.TEGrayMatterFabricatorMaster;
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
import net.minecraftforge.common.util.ForgeDirection;


public class BDeus extends BTileEntityDEM
{
	public BDeus()
	{
		super();
		this.setBlockName("deus");
		this.setHardness(5.0F);
		this.setCreativeTab(CreativeTabDEM.DEM_TAB);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int p_149915_2_) 
	{
		return new TEDeus();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ)
	{
		if(!world.isRemote)
		{
			TEDeus te = (TEDeus)world.getTileEntity(x, y, z);
			if(te.isFormed()) 
			{
				player.openGui(DeusExMachina.instance, GuiHandler.GuiIDs.DEUS.ordinal(), world, te.getMaster().xCoord, te.getMaster().yCoord, te.getMaster().zCoord);
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
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
	{
		TEDeusMaster te = (TEDeusMaster) ((TEDeus)world.getTileEntity(x, y, z)).getMaster();
		if(te!= null) 
			te.removeMember((TEDeus)world.getTileEntity(x, y, z));

		super.breakBlock(world, x, y, z, par5, par6);
		
		if(te!= null) 
			te.tryForming();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side) 
	{
			TEDeus teMe = (TEDeus) world.getTileEntity(x - ForgeDirection.getOrientation(side).offsetX, y - ForgeDirection.getOrientation(side).offsetY, z - ForgeDirection.getOrientation(side).offsetZ);
			
			return !teMe.isFormed();
	}
	
    @Override
    public boolean isOpaqueCube() 
    {
            return false;
    }
}