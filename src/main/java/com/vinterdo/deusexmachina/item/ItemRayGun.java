package com.vinterdo.deusexmachina.item;

import java.util.List;

import com.vinterdo.deusexmachina.creativetab.CreativeTabDEM;

import buildcraft.core.lib.utils.BlockUtils;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.world.BlockEvent;

public class ItemRayGun extends ItemDEM 
{
	int maxEnergy = 1000; //TODO : Change value
	int energyUsage = 20; //TODO : Change value
	
	public ItemRayGun() 
	{
		super();
		this.setUnlocalizedName("rayGun").setCreativeTab(CreativeTabDEM.DEM_TAB).setMaxStackSize(1);
	}
	
	public void createTags(ItemStack itemStack)
	{
		itemStack.stackTagCompound = new NBTTagCompound();
		itemStack.stackTagCompound.setInteger("maxEnergy", maxEnergy);
		itemStack.stackTagCompound.setInteger("energy", maxEnergy);
	}
	
	@Override
	public void onCreated(ItemStack itemStack, World world, EntityPlayer player)
	{
		createTags(itemStack);
	}
	
	@Override
	public void addInformation(ItemStack itemStack, EntityPlayer player,
            List list, boolean par4) 
	{
			if (itemStack.stackTagCompound == null) 
			{
				createTags(itemStack);
			}
			
			int energy = itemStack.stackTagCompound.getInteger("energy");
			int menergy = itemStack.stackTagCompound.getInteger("maxEnergy");
			list.add(EnumChatFormatting.BLUE + "Energy: " + energy + "/" + menergy);
    }
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {	
    	int x = player.rayTrace(100,1.0f).blockX;
    	int y = player.rayTrace(100,1.0f).blockY;
    	int z = player.rayTrace(100,1.0f).blockZ;
    	
    	if(!world.isRemote)
    	{
	    	if(world.getBlock(x, y, z) != Blocks.bedrock)
	    	{
	    		Block block = world.getBlock(x, y, z);
	    		int meta = world.getBlockMetadata(x, y, z);
	    		
	    		BlockEvent.BreakEvent breakEvent = new BlockEvent.BreakEvent(x, y, z, world, block, meta,
						player);
				MinecraftForge.EVENT_BUS.post(breakEvent);
				
				if (!breakEvent.isCanceled()) {
					List<ItemStack> stacks = BlockUtils.getItemStackFromBlock((WorldServer) world, x, y, z);
	
					if (stacks != null) {
						for (ItemStack s : stacks) {
							if (s != null) {
								mineStack(s, world, x, y, z);
							}
						}
					}
					
					if (itemStack.stackTagCompound == null) 
					{
						createTags(itemStack);
					}
					
					int energy = itemStack.stackTagCompound.getInteger("energy");
					energy -= energyUsage;
					itemStack.stackTagCompound.setInteger("energy", energy);
	
					/*world.playAuxSFXAtEntity(
							null,
							2001,
							x, y, z,
							Block.getIdFromBlock(block)
									+ (meta << 12));*/
	
					world.setBlockToAir(x, y, z);
				} else {
					//hasFailed = true;
				}
	    		
	    	}
    	}
	
    	return itemStack;
    }
	
	public void mineStack(ItemStack stack, World world, int x, int y, int z) 
	{
		// Lastly, throw the object away
		if (stack.stackSize > 0) {
			float f = world.rand.nextFloat() * 0.8F + 0.1F;
			float f1 = world.rand.nextFloat() * 0.8F + 0.1F;
			float f2 = world.rand.nextFloat() * 0.8F + 0.1F;

			EntityItem entityitem = new EntityItem(world,x + f, y + f1 + 0.5F, z + f2, stack);

			//entityitem.lifespan = BuildCraftCore.itemLifespan * 20;
			entityitem.delayBeforeCanPickup = 10;

			float f3 = 0.05F;
			entityitem.motionX = (float) world.rand.nextGaussian() * f3;
			entityitem.motionY = (float) world.rand.nextGaussian() * f3 + 1.0F;
			entityitem.motionZ = (float) world.rand.nextGaussian() * f3;
			world.spawnEntityInWorld(entityitem);
		}
	}


}
