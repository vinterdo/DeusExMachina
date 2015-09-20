package com.vinterdo.deusexmachina.tileentity;

import java.util.ArrayList;

import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.multiblockstructures.MultiBlockStructure;
import com.vinterdo.deusexmachina.multiblockstructures.StructureDeus;
import com.vinterdo.deusexmachina.tileentity.base.TEMultiblock;
import com.vinterdo.deusexmachina.tileentity.base.TEMultiblockMaster;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TEDeusMaster extends TEMultiblockMaster implements IInventory, IFluidHandler
{
	
	public static final MultiBlockStructure structure = new StructureDeus();
	
	protected ItemStack[] stacks = new ItemStack[3];
	
	protected int	progress;
	protected int	progressTarget;
	
	protected FluidStack	grayMatter;
	protected int			grayMatterCapacity	= 10000;
	
	@Override
	public void updateEntity()
	{
		super.updateEntity();
		
	}
	
	@Override
	public void tryForming()
	{
		members = new ArrayList<TEMultiblock>();
		structure.getMembers(this, members);
		super.tryForming();
	}
	
	@Override
	public boolean isProperMultiblock()
	{
		return structure.isValidMultiblock(this);
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
			} else
			{
				itemstack = this.stacks[slot].splitStack(amount);
				
				if (this.stacks[slot].stackSize == 0)
				{
					setInventorySlotContents(slot, null);
				}
				
				this.markDirty();
				return itemstack;
			}
		} else
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
		} else
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
		return ModBlocks.deusMaster.getUnlocalizedName() + ".name";
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
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false
				: player.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 64.0D;
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
		
		return false;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		
		this.progress = tag.getShort("progress");
		this.progressTarget = tag.getShort("progressTarget");
		
		NBTTagList stackTag = tag.getTagList("stacks", 10);
		stacks = new ItemStack[3];
		
		for (int i = 0; i < stackTag.tagCount(); i++)
		{
			NBTTagCompound t = stackTag.getCompoundTagAt(i);
			int index = t.getByte("index");
			stacks[index] = ItemStack.loadItemStackFromNBT(t);
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		super.writeToNBT(tag);
		
		tag.setShort("progress", (short) this.progress);
		tag.setShort("progressTarget", (short) this.progressTarget);
		
		NBTTagList stackTag = new NBTTagList();
		for (int i = 0; i < 3; i++)
		{
			if (stacks[i] != null)
			{
				NBTTagCompound t = new NBTTagCompound();
				stacks[i].writeToNBT(t);
				t.setByte("index", (byte) i);
				stackTag.appendTag(t);
			}
		}
		
		tag.setTag("stacks", stackTag);
	}
	
	@Override
	public void writeToPacket(ByteBuf buf)
	{
		super.writeToPacket(buf);
		for (ItemStack stack : stacks)
			ByteBufUtils.writeItemStack(buf, stack);
	}
	
	@Override
	public void readFromPacket(ByteBuf buf)
	{
		boolean oldFormed = formed;
		super.readFromPacket(buf);
		if (oldFormed != formed)
			worldObj.markBlockRangeForRenderUpdate(xCoord - 2, yCoord, zCoord - 2, xCoord + 2, yCoord - 1, zCoord + 2);
			
		for (int i = 0; i < 3; i++)
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
		return (float) progress / (float) progressTarget;
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		return 0;
	}
	
	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain)
	{
		return null;
	}
	
	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain)
	{
		return null;
	}
	
	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid)
	{
		return false;
	}
	
	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		return false;
	}
	
	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return null;
	}
	
	@Override
	public AxisAlignedBB getRenderBoundingBox()
	{
		//return super.getRenderBoundingBox();
		return AxisAlignedBB.getBoundingBox(xCoord - 5, yCoord - 10, zCoord - 5, xCoord + 5, yCoord + 2, zCoord + 5);
	}
}
