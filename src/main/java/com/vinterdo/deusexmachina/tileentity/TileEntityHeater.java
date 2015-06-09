package com.vinterdo.deusexmachina.tileentity;

import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.EnumSkyBlock;

import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.init.ModItems;

import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.IFuelHandler;

public class TileEntityHeater extends TileEntityDEM implements IInventory
{
	protected ItemStack[] stacks = new ItemStack[9];
	protected int burningTime = 0;
	
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
	
	public void updateEntity()
    {
		if(burningTime > 0)
		{
			--burningTime;
			if(burningTime == 0)
			{	
				this.worldObj.markBlockRangeForRenderUpdate(xCoord - 1, yCoord - 1, zCoord - 1 , xCoord + 1, yCoord + 1, zCoord + 1);
				this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
			}
		}
		else
		{
			for(int i=0; i < 9; i++)
			{
				int burn = TileEntityFurnace.getItemBurnTime(stacks[i]);
				if(burn > 0) 
				{
					decrStackSize(i, 1);
					burningTime += burn;
					this.worldObj.markBlockRangeForRenderUpdate(xCoord - 1, yCoord - 1, zCoord - 1 , xCoord + 1, yCoord + 1, zCoord + 1);
					this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
				}
			}
		}
    }
	
	public boolean isWorking()
	{
		return burningTime > 0;
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
		return ModBlocks.heater.getUnlocalizedName() + ".name";
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
		return TileEntityFurnace.getItemBurnTime(itemStack) > 0;
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);

        this.burningTime = tag.getShort("BurnTime");
		
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
		
		tag.setShort("BurnTime", (short)this.burningTime);
		
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
		for(ItemStack stack : stacks)
			ByteBufUtils.writeItemStack(buf, stack);
	}
	
	public void readFromPacket(ByteBuf buf)
	{
		for(int i=0 ; i < 6; i++)
			stacks[i] = ByteBufUtils.readItemStack(buf);
	}
}
