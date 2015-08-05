package com.vinterdo.deusexmachina.tileentity;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;

import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.init.ModFluids;
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
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TEGrayMatterFabricatorMaster extends TEMultiblockMaster implements IInventory, IFluidHandler
{

	protected ItemStack[] stacks = new ItemStack[3]; // 0 - matter, 1 - essence - 2 capsules
	protected int essence;
	protected int matter;
	
	protected int essenceStorage = 10000;
	protected int matterStorage = 10000;
	
	protected int progress;
	protected int progressTarget;
	
	protected FluidTank tank = new FluidTank(10000);
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
		if(isFormed())
		{
			if(essence > 0 && matter > 0 && tank.getFluidAmount() < tank.getCapacity()) 
			{
				tank.fill(new FluidStack(ModFluids.grayMatter, 1), true);
				
				essence--;
				matter--;
			}
			
			if(matter == 0 && (getStackInSlot(0) != null))
			{
				matter += 1000;
				decrStackSize(0, 1);
			}
			
			if(essence == 0 && (stacks[2] == null || stacks[2].stackSize < stacks[2].getMaxStackSize()) && (getStackInSlot(1) != null))
			{
				essence += 1000;
				decrStackSize(1, 1);
				if(stacks[2] == null)
				{
					stacks[2] = new ItemStack(ModItems.essenceContainer, 1);
				}
				else
				{
					stacks[2].stackSize++;
				}
			}
		}
	}
	

	@Override
	public void tryForming()
	{
		members = new ArrayList<TEMultiblock>();
		for(int x =0; x < 5; x++)
		{
			for(int y =0; y < 2; y++)
			{
				for(int z =0; z < 5; z++)
				{
					TileEntity te = worldObj.getTileEntity(xCoord + x - 2, yCoord + y -1, zCoord + z - 2);
					
					if(te instanceof TEGrayMatterFabricator)
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
		for(int x =0; x < 5; x++)
		{
			for(int y =0; y < 2; y++)
			{
				for(int z =0; z < 5; z++)
				{
					TileEntity te = worldObj.getTileEntity(xCoord + x - 2, yCoord + y -1, zCoord + z - 2);
					if(te == null)
					{
						return false;
					}
					else if(te instanceof TEGrayMatterFabricator)
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
		return ModBlocks.grayMatterFabricatorMaster.getUnlocalizedName() + ".name";
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
		if(slot == 0 && itemStack.getItem() == ModItems.steelIngot) // todo : add other materials
			return true;
		if(slot == 1 && itemStack.getItem() == ModItems.essence)
			return true;
		
		return false;
	}
	
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);

        this.progress = tag.getShort("progress");
        this.progressTarget = tag.getShort("progressTarget");
		
		NBTTagList stackTag = tag.getTagList("stacks", 10);
		stacks = new ItemStack[3];
		
		for(int i =0 ; i < stackTag.tagCount(); i++)
		{
			NBTTagCompound t = stackTag.getCompoundTagAt(i);
			int index = t.getByte("index");
			stacks[index] = ItemStack.loadItemStackFromNBT(t);
		}

		tank.readFromNBT(tag);
	}
	
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		
		tag.setShort("progress", (short)this.progress);
		tag.setShort("progressTarget", (short)this.progressTarget);
		
		NBTTagList stackTag = new NBTTagList();
		for(int i=0; i < 3; i++)
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
		
		tank.writeToNBT(tag);
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
			worldObj.markBlockRangeForRenderUpdate(xCoord - 2, yCoord, zCoord - 2, xCoord + 2, yCoord - 1, zCoord + 2);
		
		for(int i=0 ; i < 3; i++)
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


	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) 
	{
		return 0;
	}


	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) 
	{
		if(resource.getFluid() == ModFluids.grayMatter) return drain(from, resource.amount, doDrain);
		return null;
	}


	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) 
	{
		return tank.drain(maxDrain, doDrain);
	}


	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) 
	{
		return false;
	}


	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) 
	{
		return fluid == ModFluids.grayMatter;
	}


	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) 
	{
		return new FluidTankInfo[] {this.tank.getInfo()};
	}
}
