package com.vinterdo.deusexmachina.tileentity;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;

import com.vinterdo.deusexmachina.helpers.Helper;
import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.utility.LogHelper;

import cpw.mods.fml.common.network.ByteBufUtils;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

public class TEBlastFurnaceMaster extends TEMultiblockMaster implements IInventory
{

	protected ItemStack[] stacks = new ItemStack[9]; // 0..2 - input, 3..5 - fuel 6..8 - output
	protected int burningTime;
	protected int progress;
	protected int progressTarget;
	
	public static final int PROGRESS_MULT = 1; 
	public static final int SMELT_TIME = 100; 
	public static final int SMELT_TIME_STEEL = 400; 
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		if(!worldObj.isRemote)
		{
			if(burningTime > 0)
			{
				smeltItems();
			}
			else
			{
				burnFuel();
			}
		}
	}

	private void burnFuel() 
	{
		for(int i=0; i < 3; i++)
		{
			if(stacks[i + 3] != null && TileEntityFurnace.isItemFuel(stacks[i+3]))
			{
				burningTime += TileEntityFurnace.getItemBurnTime(stacks[i + 3]);
				this.decrStackSize(i + 3, 1);
			}
		}
	}

	private void smeltItems() 
	{
		burningTime -= 1;
		
		for(int i = 0; i < 3; i++)
		{
			if(stacks[i] != null)
			{
				if(stacks[i].getItem() == ModItems.steelDust || FurnaceRecipes.smelting().getSmeltingResult(stacks[i]) != null)
				{
					ItemStack target;
					if(stacks[i].getItem() == ModItems.steelDust)
					{
						progressTarget = SMELT_TIME_STEEL;
						target = new ItemStack(ModItems.steelIngot, 1);
					}
					else
					{
						progressTarget = SMELT_TIME;
						target = FurnaceRecipes.smelting().getSmeltingResult(stacks[i]).copy();
						if(target.stackSize == 0) target.stackSize = 1;
					}
					
					int spaceLeft = calcSpaceLeft(target);
					
					if(spaceLeft - target.stackSize > 0)
					{
						progress += PROGRESS_MULT;
						if(progress > progressTarget)
						{
							addItemToOutput(target);
							this.decrStackSize(i, 1);
							progress -= progressTarget;
						}
					}
					break;
				}
			}
		}
	}

	private int calcSpaceLeft(ItemStack target) 
	{
		int spaceLeft = 0;
		for(int j=0; j < 3; j++)
		{
			spaceLeft += (stacks[j + 6] == null) ? 64 : (Helper.areItemStacksStackable(stacks[j+6],target)) ? stacks[j + 6].getMaxStackSize() - stacks[j + 6].stackSize : 0;
		}
		return spaceLeft;
	}
	
	private void addItemToOutput(ItemStack stack)
	{
		for(int i=0; i < 3; i++)
		{
			if(stacks[i +  6] == null)
			{
				stacks[i+6] = stack;
				return;
			}
			else if(stacks[i + 6].getItem() == stack.getItem())
			{
				if(stacks[i+6].stackSize + stack.stackSize <= stacks[i+6].getMaxStackSize())
				{
					stacks[i+6].stackSize += stack.stackSize;
					return;
				}
				else
				{
					int rest = stacks[i+6].stackSize + stack.stackSize - stacks[i+6].getMaxStackSize();
					stacks[i+6].stackSize = stacks[i+6].getMaxStackSize();
					stack.stackSize = rest;
				}
			}
		}
	}
	

	@Override
	public void tryForming()
	{
		members = new ArrayList<TEMultiblock>();
		for(int x =0; x < 3; x++)
		{
			for(int y =0; y < 4; y++)
			{
				for(int z =0; z < 3; z++)
				{
					TileEntity te = worldObj.getTileEntity(xCoord + x - 1, yCoord + y - 3, zCoord + z - 1);
					
					if(te instanceof TEBlastFurnace)
					{
						members.add((TEMultiblock)te);
					}
				}
			}
		}
		super.tryForming();
	}
	
	@Override
	public boolean isProperMultiblock()
	{
		for(int x =0; x < 3; x++)
		{
			for(int y =0; y < 4; y++)
			{
				for(int z =0; z < 3; z++)
				{
					TileEntity te = worldObj.getTileEntity(xCoord + x - 1, yCoord + y - 3, zCoord + z - 1);
					if(te == null)
					{
						return false;
					}
					else if(te instanceof TEBlastFurnace)
					{
						TEMultiblock tem = (TEMultiblock)te;
						if(tem.getMaster() != null) 
							return false;
					}
					else if(te != this)
					{
						return false;
					}
					
				}
			}
		}
		
		return true;
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
		return ModBlocks.blastFurnaceMaster.getUnlocalizedName() + ".name";
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
		if(slot <= 2)
			return true;
		if(slot > 2 && slot <= 5)
			return TileEntityFurnace.getItemBurnTime(itemStack) > 0;
		
		return false;
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);

        this.burningTime = tag.getShort("burningTimes");
        this.progress = tag.getShort("progress");
        this.progressTarget = tag.getShort("progressTarget");
		
		NBTTagList stackTag = tag.getTagList("stacks", 10);
		stacks = new ItemStack[9];
		
		for(int i =0 ; i < stackTag.tagCount(); i++)
		{
			NBTTagCompound t = stackTag.getCompoundTagAt(i);
			int index = t.getByte("index");
			stacks[index] = ItemStack.loadItemStackFromNBT(t);
		}
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		
		tag.setShort("burningTime", (short)this.burningTime);
		tag.setShort("progress", (short)this.progress);
		tag.setShort("progressTarget", (short)this.progressTarget);
		
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
	}
	
	public void writeToPacket(ByteBuf buf)
	{
		super.writeToPacket(buf);
		for(ItemStack stack : stacks)
			ByteBufUtils.writeItemStack(buf, stack);
	}
	
	public void readFromPacket(ByteBuf buf)
	{
		boolean oldFormed = formed;
		super.readFromPacket(buf);
		if(oldFormed != formed)
			worldObj.markBlockRangeForRenderUpdate(xCoord - 1, yCoord, zCoord - 1, xCoord + 1, yCoord - 4, zCoord + 1);
		
		for(int i=0 ; i < 6; i++)
			stacks[i] = ByteBufUtils.readItemStack(buf);
	}
	
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
		return burningTime;
	}
	
	public void setPower(int val)
	{
		burningTime = val;
	}
	
	public int getProgressTarget()
	{
		return progressTarget;
	}
	
	public void setProgressTarget(int val)
	{
		progressTarget = val;
	}
	
	public float getProgressPercent()
	{
		return (float)progress / (float)progressTarget;
	}
}
