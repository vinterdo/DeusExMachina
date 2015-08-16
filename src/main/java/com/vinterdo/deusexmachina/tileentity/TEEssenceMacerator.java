package com.vinterdo.deusexmachina.tileentity;

import io.netty.buffer.ByteBuf;
import cofh.thermalexpansion.util.crafting.PulverizerManager;
import cofh.thermalexpansion.util.crafting.PulverizerManager.RecipePulverizer;

import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.recipes.RecipeMacerator;
import com.vinterdo.deusexmachina.reference.ModIds;
import com.vinterdo.deusexmachina.tileentity.base.TEDEM;
import com.vinterdo.deusexmachina.utility.LogHelper;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.Optional;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TEEssenceMacerator extends TEDEM implements IInventory
{
	protected ItemStack[] stacks = new ItemStack[8];
	protected int progress;
	protected int power;
	
	protected int progressTarget;
	
	public float getProgressPercent()
	{
		return (float)progress / (float)progressTarget;
	}
	
	public static final int progressMultipler = 100;
	public static final int essencePower = 100;
	
	public int getProgress()
	{
		return progress;
	}
	
	public void setProgress(int val)
	{
		progress = val;
	}
	
	public int getPower()
	{
		return power;
	}
	
	public void setPower(int val)
	{
		power = val;
	}
	
	public int getProgressTarget()
	{
		return progressTarget;
	}
	
	public void setProgressTarget(int val)
	{
		progressTarget = val;
	}
	
	@Override
	public void updateEntity()
	{
		if(!worldObj.isRemote)
		{
			if(power > 0)
			{
				if(Loader.isModLoaded(ModIds.TE))
				{
					processOresTE();
				}
				else
				{
					processOresVanilla();
				}
			}
			else
			{
				if(stacks[3] != null && stacks[3].getItem() == ModItems.essence && ((stacks[7] != null && stacks[7].stackSize < stacks[7].getMaxStackSize()) || stacks[7] == null))
				{
					decrStackSize(3, 1);
					
					if(stacks[7] != null)
					{
						stacks[7].stackSize++;
					}
					else
					{
						stacks[7] = new ItemStack(ModItems.essenceContainer);
					}
					power += essencePower;
				}
			}
		}
	}

	private void processOresVanilla() 
	{
		for(int i=0; i < 3; i++)
		{
			if(stacks[i] != null)
			{
				RecipeMacerator recipe = RecipeMacerator.getRecipe(stacks[i]);
				if(recipe != null && isSpaceInOutput(recipe.getOutput()))
				{
					progressTarget = recipe.getEnergy();
					power--;
					progress+= progressMultipler;
					if(progress > recipe.getEnergy())
					{
						addItemToOutput(recipe.getOutput());
						consumeItem(i);
						progress = 0;
					}
					return;
				}
			}
		}
	}

	@Optional.Method(modid = ModIds.TE)
	private void processOresTE() 
	{
		for(int i=0; i < 3; i++)
		{
			if(stacks[i] != null)
			{
				RecipePulverizer recipe = PulverizerManager.getRecipe(stacks[i]);
				if(recipe != null && isSpaceInOutput(recipe.getPrimaryOutput()))
				{
					progressTarget = recipe.getEnergy();
					power--;
					progress+= progressMultipler;
					if(progress > recipe.getEnergy())
					{
						addItemToOutput(recipe.getPrimaryOutput());
						consumeItem(i);
						progress = 0;
					}
					return;
				}
			}
		}
	}
	
	private void consumeItem(int slot)
	{
		decrStackSize(slot, 1);
	}
	
	private void addItemToOutput(ItemStack stack)
	{
		for(int i=0; i < 3; i++)
		{
			if(stacks[i +  4] == null)
			{
				stacks[i+4] = stack;
				return;
			}
			else if(stacks[i + 4].getItem() == stack.getItem())
			{
				if(stacks[i+4].stackSize + stack.stackSize < stacks[i+4].getMaxStackSize())
				{
					stacks[i+4].stackSize += stack.stackSize;
					return;
				}
				else
				{
					int rest = stacks[i+4].stackSize + stack.stackSize - stacks[i+4].getMaxStackSize();
					stacks[i+4].stackSize = stacks[i+4].getMaxStackSize();
					stack.stackSize = rest;
				}
			}
		}
	}
	
	private boolean isSpaceInOutput(ItemStack input)
	{
		if(!(stacks[7] == null || (stacks[7].getItem() == ModItems.essenceContainer && stacks[7].stackSize < stacks[7].getMaxStackSize())))
		{
			return false;
		}
		
		int spaceLeft = 0;
		
		for(int i=0; i < 3; i++)
		{
			if(stacks[i + 4] == null) spaceLeft += input.getMaxStackSize();
			else if(stacks[i + 4].getItem() == input.getItem()) spaceLeft += stacks[i + 4].getMaxStackSize() - stacks[i + 4].stackSize;
		}
		
		return spaceLeft > input.stackSize;
	}
	
	@Override
	public int getSizeInventory() 
	{
		return stacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int slot) 
	{
		return this.stacks[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount)
    {
        if (this.stacks[slot] != null)
        {
            ItemStack itemstack;

            if (this.stacks[slot].stackSize <= amount)
            {
                itemstack = this.stacks[slot];
                setInventorySlotContents(slot, null);
                return itemstack;
            }
            else
            {
                itemstack = this.stacks[slot].splitStack(amount);

                if (this.stacks[slot].stackSize == 0)
                {
                	setInventorySlotContents(slot, null);
                }

                this.markDirty();
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

	@Override
	public ItemStack getStackInSlotOnClosing(int slot)
    {
        if (this.stacks[slot] != null)
        {
            ItemStack itemstack = this.stacks[slot];
            this.stacks[slot] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack)
    {
        this.stacks[slot] = stack;

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
    }

	@Override
	public String getInventoryName() 
	{
		return ModBlocks.essenceMacerator.getUnlocalizedName() + ".name";
	}

	@Override
	public boolean hasCustomInventoryName() 
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit() 
	{
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : player.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

	@Override
	public void openInventory() 
	{
	}

	@Override
	public void closeInventory() 
	{		
	}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack) 
	{
		if(slot < 3) return true;
		if(slot == 3 && itemStack.getItem() == ModItems.essence) return true;
		return false;
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		
		NBTTagList stackTag = tag.getTagList("stacks", 10);
		stacks = new ItemStack[8];
		
		for(int i =0 ; i < stackTag.tagCount(); i++)
		{
			NBTTagCompound t = stackTag.getCompoundTagAt(i);
			int index = t.getByte("index");
			stacks[index] = ItemStack.loadItemStackFromNBT(t);
		}
		
		progress = tag.getInteger("progress");
		power = tag.getInteger("power");
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		
		NBTTagList stackTag = new NBTTagList();
		for(int i=0; i < 8; i++)
		{
			if(stacks[i] != null)
			{
				NBTTagCompound t = new NBTTagCompound();
				stacks[i].writeToNBT(t);
				t.setByte("index", (byte)i);
				stackTag.appendTag(t);
			}
		}
		
		tag.setTag("stacks", stackTag);
		
		tag.setInteger("progress", progress);
		tag.setInteger("power", power);
	}
	
	public void writeToPacket(ByteBuf buf)
	{
		for(ItemStack stack : stacks)
			ByteBufUtils.writeItemStack(buf, stack);
	}
	
	public void readFromPacket(ByteBuf buf)
	{
		for(int i=0 ; i < 8; i++)
			stacks[i] = ByteBufUtils.readItemStack(buf);
	}

}
