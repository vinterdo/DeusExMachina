package com.vinterdo.deusexmachina.item;

import java.util.List;

import com.vinterdo.deusexmachina.creativetab.CreativeTabDEM;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class ItemMachineCard extends ItemDEM
{
	public ItemMachineCard()
	{
		super();
		this.setUnlocalizedName("machineCard");
		this.setCreativeTab(CreativeTabDEM.DEM_TAB);
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side,
			float hitX, float hitY, float hitZ)
	{
		super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
		if (stack.stackTagCompound == null)
			stack.stackTagCompound = new NBTTagCompound();
			
		stack.stackTagCompound.setInteger("x", x);
		stack.stackTagCompound.setInteger("y", y);
		stack.stackTagCompound.setInteger("z", z);
		stack.stackTagCompound.setInteger("world", world.provider.dimensionId);
		stack.stackTagCompound.setString("name", world.getBlock(x, y, z).getLocalizedName());
		return false;
	}
	
	@Override
	public int getItemStackLimit()
	{
		return 1;
	}
	
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
	{
		if (itemStack.stackTagCompound != null)
		{
			list.add(
					"[" + itemStack.stackTagCompound.getInteger("x") + ", " + itemStack.stackTagCompound.getInteger("y")
							+ ", " + itemStack.stackTagCompound.getInteger("z") + "]");
			list.add(itemStack.stackTagCompound.getString("name"));
		}
	}
	
	public static TileEntity getTargetTE(ItemStack stack)
	{
		if (stack != null && stack.stackTagCompound != null)
		{
			int x = stack.stackTagCompound.getInteger("x");
			int y = stack.stackTagCompound.getInteger("y");
			int z = stack.stackTagCompound.getInteger("z");
			int worldId = stack.stackTagCompound.getInteger("world");
			
			return DimensionManager.getWorld(worldId).getTileEntity(x, y, z);
		}
		return null;
	}
}
