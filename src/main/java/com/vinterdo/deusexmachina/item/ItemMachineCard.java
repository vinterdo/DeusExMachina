package com.vinterdo.deusexmachina.item;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import com.vinterdo.deusexmachina.creativetab.CreativeTabDEM;

public class ItemMachineCard extends ItemDEM
{
	public ItemMachineCard()
	{
		super();
		this.setUnlocalizedName("machineCard");
		this.setCreativeTab(CreativeTabDEM.DEM_TAB);
	}
	

	
	@Override   
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
	{
		super.onItemUseFirst(stack, player, world, x, y, z, side, hitX, hitY, hitZ);
		if(stack.stackTagCompound == null)
			stack.stackTagCompound = new NBTTagCompound();
		
		stack.stackTagCompound.setInteger("x", x);
		stack.stackTagCompound.setInteger("y", y);
		stack.stackTagCompound.setInteger("z", z);
		stack.stackTagCompound.setString("name", world.getBlock(x, y, z).getLocalizedName());
		
		return false;
	}
	
	@Override
	public boolean isFull3D()
	{
		return true;
	}
	

	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
	{
		if (itemStack.stackTagCompound != null)
		{
			list.add("[" + itemStack.stackTagCompound.getInteger("x") + ", " + itemStack.stackTagCompound.getInteger("y") + ", " + itemStack.stackTagCompound.getInteger("z") + "]" );
			list.add(itemStack.stackTagCompound.getString("name"));
		}
	}
}
