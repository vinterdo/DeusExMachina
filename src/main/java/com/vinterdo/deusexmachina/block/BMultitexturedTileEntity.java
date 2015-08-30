package com.vinterdo.deusexmachina.block;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public abstract class BMultitexturedTileEntity extends BTileEntityDEM
{
	protected IIcon[] icons = new IIcon[6];
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister)
	{
		icons[0] = iconRegister
				.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName() + "_bottom")));
		icons[1] = iconRegister
				.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName() + "_top")));
		icons[2] = iconRegister
				.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName() + "_front")));
		icons[3] = iconRegister
				.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName() + "_back")));
		icons[4] = iconRegister
				.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName() + "_right")));
		icons[5] = iconRegister
				.registerIcon(String.format("%s", getUnwrappedUnlocalizedName(this.getUnlocalizedName() + "_left")));
	}
	
	@Override
	public IIcon getIcon(int side, int meta)
	{
		if (side == 0 || side == 1)
			return this.icons[side];
			
		return this.icons[(side + meta) % 4 + 2];
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entity, ItemStack stack)
	{
		double diffX = x - entity.posX;
		double diffZ = z - entity.posZ;
		
		if (Math.abs(diffX) > Math.abs(diffZ))
		{
			if (diffX < 0)
			{
				world.setBlockMetadataWithNotify(x, y, z, 3, 0);
			} else
			{
				world.setBlockMetadataWithNotify(x, y, z, 0, 0);
			}
		} else
		{
			if (diffZ < 0)
			{
				world.setBlockMetadataWithNotify(x, y, z, 1, 0);
			} else
			{
				world.setBlockMetadataWithNotify(x, y, z, 2, 0);
			}
		}
		
	}
}
