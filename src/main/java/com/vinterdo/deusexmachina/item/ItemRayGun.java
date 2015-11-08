package com.vinterdo.deusexmachina.item;

import com.vinterdo.deusexmachina.creativetab.CreativeTabDEM;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemRayGun extends ItemDEM {

	public ItemRayGun() 
	{
		super();
		this.setUnlocalizedName("rayGun").setCreativeTab(CreativeTabDEM.DEM_TAB);
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World world, EntityPlayer player)
    {	
    	int x = player.rayTrace(100,1.0f).blockX;
    	int y = player.rayTrace(100,1.0f).blockY;
    	int z = player.rayTrace(100,1.0f).blockZ;
    	
    		
    	if(world.getBlock(x, y, z) != Blocks.bedrock)
    	{
    		world.getBlock(x, y, z).dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
    		world.setBlockToAir(x, y, z);
    	}
	
    	return par1ItemStack;
    }

}
