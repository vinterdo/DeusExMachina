package com.vinterdo.deusexmachina.item;

import java.util.List;

import com.vinterdo.deusexmachina.creativetab.CreativeTabDEM;
import com.vinterdo.deusexmachina.research.ResearchTree;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

public class ItemDeusDataMatrix extends ItemDEM
{
	public ItemDeusDataMatrix()
	{
		super();
		this.setUnlocalizedName("deusDataMatrix");
		this.setCreativeTab(CreativeTabDEM.DEM_TAB);
	}
	
	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player)
	{
		itemStack.stackTagCompound = new NBTTagCompound();
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
	{
		
		ResearchTree tree = new ResearchTree();
		tree.createTree();
		itemStack.stackTagCompound = tree.toNBT();
		
		return itemStack;
	}
	
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player, List list, boolean par4)
	{
		if (itemStack.stackTagCompound != null)
		{
			NBTTagList nbtlist = itemStack.stackTagCompound.getTagList("tree", Constants.NBT.TAG_COMPOUND);
			NBTTagCompound tag = null;
			for (int i = 0; i < nbtlist.tagCount(); i++)
			{
				tag = nbtlist.getCompoundTagAt(i);
				if (tag.getBoolean("discovered"))
					list.add(tag.getString("recipe"));
					
			}
		}
	}
}
