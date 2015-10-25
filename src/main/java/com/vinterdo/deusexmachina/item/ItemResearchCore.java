package com.vinterdo.deusexmachina.item;

import java.util.List;

import com.vinterdo.deusexmachina.creativetab.CreativeTabDEM;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class ItemResearchCore extends ItemDEM
{
	public ItemResearchCore()
	{
		super();
		this.setUnlocalizedName("researchCore");
		this.setCreativeTab(CreativeTabDEM.DEM_TAB);
	}
	
	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player)
	{
		itemStack.stackTagCompound = new NBTTagCompound();
		itemStack.stackTagCompound.setString("researchName", "none");
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		itemStack.stackTagCompound = new NBTTagCompound();
		itemStack.stackTagCompound.setString("researchName", "none");
		return itemStack;
	}
	
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
	{
		if (itemStack.stackTagCompound != null)
		{
			String research = itemStack.stackTagCompound.getString("researchName");
			list.add("Research: " + research);
			
		}
	}
}
