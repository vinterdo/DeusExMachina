package com.vinterdo.deusexmachina.block;

import com.vinterdo.deusexmachina.DeusExMachina;
import com.vinterdo.deusexmachina.creativetab.CreativeTabDEM;
import com.vinterdo.deusexmachina.handler.GuiHandler;
import com.vinterdo.deusexmachina.tileentity.TEEtherCondenser;
import com.vinterdo.deusexmachina.tileentity.TEEtherCondenserMaster;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class BEtherCondenser extends BTileEntityDEM {

	public BEtherCondenser()
    {
        super();
        this.setBlockName("etherCondenser");
        this.setHardness(5.0F);
        this.setCreativeTab(CreativeTabDEM.DEM_TAB);
    }

    @Override
    public TileEntity createNewTileEntity(World world, int p_149915_2_)
    {
        return new TEEtherCondenser(); // zwracamy tile entity dla slavea
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX,
            float hitY, float hitZ)
    {
    // sprawdzamy czy slave nale¿y do jakiegos mastera (isFormed()), je¿eli tak otwieramy GUI tego mastera. Jezeli nie nalezy, nic nie robimy
        if (!world.isRemote)
        {
        	TEEtherCondenser te = (TEEtherCondenser) world.getTileEntity(x, y, z);
            if (te.isFormed())
            {
                player.openGui(DeusExMachina.instance, GuiHandler.GuiIDs.DEUS.ordinal(), world, te.getMaster().xCoord,
                        te.getMaster().yCoord, te.getMaster().zCoord);
                return true;
            } else
            {
                return false;
            }
        }

        return true;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block par5, int par6)
    {
      // Próbujemy dostaæ mastera multiblocku. Je¿eli on istnieje (nasz slave nale¿al do jakiegos multiblocku) to usuwamy go z multiblockow. To co zrobi multiblock zalezy ju¿ od implementacji mastera - mo¿e po prostu zniszczyc multiblock, lub normalnie kontynuowac swoja egzystencje. 
    	TEEtherCondenserMaster te = (TEEtherCondenserMaster) ((TEEtherCondenser) world.getTileEntity(x, y, z)).getMaster();
        if (te != null)
            te.removeMember((TEEtherCondenser) world.getTileEntity(x, y, z));

        super.breakBlock(world, x, y, z, par5, par6);

        if (te != null)
            te.tryForming();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
    {
     // Wa¿ne! Gdy slave jest sformowany nie chcemy go normalnie renderowac - wiec pobieram sobie tileEntity ktore odpowiada za nasz blok (teMe) i sprawdzam czy jest zformowane. Jak jest, nie chce zeby zadna strona tego bloku sie renderowala. 
    	TEEtherCondenser teMe = (TEEtherCondenser) world.getTileEntity(x - ForgeDirection.getOrientation(side).offsetX,
                y - ForgeDirection.getOrientation(side).offsetY, z - ForgeDirection.getOrientation(side).offsetZ);

        return !teMe.isFormed();
    }

    @Override // Nie jest przezroczyste
    public boolean isOpaqueCube()
    {
        return false;
    }
}
