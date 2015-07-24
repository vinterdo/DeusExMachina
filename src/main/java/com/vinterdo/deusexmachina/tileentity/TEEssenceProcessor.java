package com.vinterdo.deusexmachina.tileentity;

import io.netty.buffer.ByteBuf;

import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.utility.LogHelper;

import cpw.mods.fml.common.network.ByteBufUtils;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TEEssenceProcessor extends TEDEM implements IInventory
{
	protected ItemStack[] stacks = new ItemStack[9];
	protected int progress;
	
	
	public int getProgress()
	{
		return progress;
	}
	
	public void setProgress(int val)
	{
		progress = val;
	}
	
	@Override
	public void updateEntity()
	{
		if(!worldObj.isRemote)
		{
			TileEntity te = worldObj.getTileEntity(xCoord, yCoord - 1, zCoord);
			
			int essences = 0, capsules = 0;
			for(int i =0; i < 3; i++)
			{
				if(stacks[i] != null && stacks[i].getItem() == ModItems.essenceContainer)
				{
					capsules += stacks[i].stackSize;
				}
				if(stacks[i + 3] != null && stacks[i + 3].getItem() == Item.getItemFromBlock(ModBlocks.essenceOre))
				{
					essences += stacks[i + 3].stackSize;
				}
			}
			
			if(te != null && te instanceof TEHeater && ((TEHeater)te).isWorking() && essences > 0 && capsules > 0)
			{
				progress++;
				
				if(progress >= 100)
				{
					progress -= 100;
					for(int i =0; i < 3; i++)
					{
						if(stacks[6 + i] == null)
						{
							stacks[6 + i] = new ItemStack(ModItems.essence);
							substractItems();
							return;
						}
						else if(stacks[6 + i].getItem() == ModItems.essence && stacks[6 + i].stackSize < 64)
						{
							stacks[6 + i].stackSize++;
							substractItems();
							return;
						}
					}
				}
			}
		}
	}

	private void substractItems() {
		boolean done = false;
		for(int j =0; j < 3 && !done; j++)
		{
			if(stacks[j] != null && stacks[j].getItem() == ModItems.essenceContainer)
			{
				decrStackSize(j, 1);
				//stacks[j].stackSize -= 1;
				done = true;
			}
		}
		done = false;
		for(int j =0; j < 3 && !done; j++)
		{
			if(stacks[j + 3] != null && stacks[j + 3].getItem() == Item.getItemFromBlock(ModBlocks.essenceOre))
			{
				decrStackSize(j+3, 1);
				//stacks[j + 3].stackSize -= 1;
				done = true;
			}
		}
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
		return ModBlocks.essenceProcessor.getUnlocalizedName() + ".name";
	}

	@Override
	public boolean hasCustomInventoryName() 
	{
		return false;
	}

	@Override
	public int getInventoryStackLimit() 
	{
		// TODO Auto-generated method stub
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
		if(slot < 3 && itemStack.getItem() == ModItems.essenceContainer) return true;
		if(slot >= 3 && slot < 6 && itemStack.getItem() instanceof ItemBlock) 
		{
			Block block = ((ItemBlock)itemStack.getItem()).field_150939_a;
			return block == ModBlocks.essenceOre;
		}
		return false;
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		
		NBTTagList stackTag = tag.getTagList("stacks", 10);
		stacks = new ItemStack[9];
		
		for(int i =0 ; i < stackTag.tagCount(); i++)
		{
			NBTTagCompound t = stackTag.getCompoundTagAt(i);
			int index = t.getByte("index");
			stacks[index] = ItemStack.loadItemStackFromNBT(t);
		}
		
		progress = tag.getInteger("progress");
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		
		NBTTagList stackTag = new NBTTagList();
		for(int i=0; i < 9; i++)
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
	}
	
	public void writeToPacket(ByteBuf buf)
	{
		for(ItemStack stack : stacks)
			ByteBufUtils.writeItemStack(buf, stack);
	}
	
	public void readFromPacket(ByteBuf buf)
	{
		for(int i=0 ; i < 9; i++)
			stacks[i] = ByteBufUtils.readItemStack(buf);
	}

}
