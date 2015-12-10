package com.vinterdo.deusexmachina.tileentity;

import java.util.ArrayList;

import com.vinterdo.deusexmachina.handler.ConfigurationHandler;
import com.vinterdo.deusexmachina.helpers.NBTSaved;
import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.init.ModFluids;
import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.multiblockstructures.MultiBlockStructure;
import com.vinterdo.deusexmachina.multiblockstructures.StructureDeus;
import com.vinterdo.deusexmachina.network.Synchronized;
import com.vinterdo.deusexmachina.tileentity.base.TEIMultiblockMaster;
import com.vinterdo.deusexmachina.tileentity.base.TEMultiblock;
import com.vinterdo.deusexmachina.utility.LogHelper;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;

public class TEDeusMaster extends TEIMultiblockMaster implements IFluidHandler, IEnergyReceiver
{
	private static final int				FLUID_TANK_CAPACITY		= ConfigurationHandler.DMFluidTankCapacity;
	private static final int				ENERGY_CAPACITY			= ConfigurationHandler.DMEnergyCapacity;
	private static final int				GM_PER_TICK				= ConfigurationHandler.DMGMPerTick;
	private static final int				PRINTING_TIME			= ConfigurationHandler.DMPrintingTime;
	private static final int				PRINTING_RF_PER_TICK	= ConfigurationHandler.DMPrintingRFPerTick;
	@Synchronized(id = 0)
	@NBTSaved(name = "progress")
	public int								progress				= 0;
	@Synchronized(id = 1)
	@NBTSaved(name = "progressTarget")
	public int								progressTarget			= 100;
	@Synchronized(id = 2)
	@NBTSaved(name = "tank")
	public FluidTank						tank					= new FluidTank(FLUID_TANK_CAPACITY);
	@Synchronized(id = 3)
	@NBTSaved(name = "energy")
	public EnergyStorage					energy					= new EnergyStorage(ENERGY_CAPACITY);
	@Synchronized(id = 4)
	@NBTSaved(name = "gmConsumed")
	public int								gmConsumed;
	@Synchronized(id = 5)
	@NBTSaved(name = "gmTarget")
	public int								gmTarget;
	@Synchronized(id = 6)
	public int								coreChanged;
	@Synchronized(id = 7)
	public int								highlightX;
	@Synchronized(id = 8)
	public int								highlightY;														// 0 - false, 1 - true
											
	public static final MultiBlockStructure	structure				= new StructureDeus();
	private ItemStack						oldStack				= null;
																	
	private enum DeusState
	{
		IDLE(0), PRINTING(1), RESEARCHING(2);
		
		private final int value;
		
		private DeusState(int value)
		{
			this.value = value;
		}
		
		public int getValue()
		{
			return value;
		}
	}
	
	@Synchronized(id = 7)
	@NBTSaved(name = "deusState")
	private int				state	= DeusState.IDLE.getValue();
	public NBTTagCompound	activeResearch;
							
	public TEDeusMaster()
	{
		super();
		tank.setFluid(new FluidStack(ModFluids.grayMatter, 0));
		setNumOfStacks(3);
	}
	
	@Override
	public void updateEntity()
	{
		coreChanged = oldStack == stacks.get(2) ? 1 : 0;
		if (!worldObj.isRemote && formed)
		{
			oldStack = stacks.get(2);
			
			switch (state)
			{
				case 0: // IDLE
					// do nothing :)
					highlightX = Integer.MIN_VALUE;
					highlightY = Integer.MIN_VALUE;
					break;
					
				case 1: // PRINTING
					if (!isActiveResearchValid() || !activeResearch.getBoolean("discovered"))
					{
						activeResearch = null;
						state = DeusState.IDLE.getValue();
						return;
					}
					
					highlightX = activeResearch.getInteger("x");
					highlightY = activeResearch.getInteger("y");
					progressTarget = PRINTING_TIME;
					if (progress < progressTarget)
					{
						if ((energy.getEnergyStored() > PRINTING_RF_PER_TICK) && canPrint())
						{
							energy.extractEnergy(PRINTING_RF_PER_TICK, false);
							progress++;
						}
					} else
					{
						this.printResearch(activeResearch.getString("recipe"));
						state = DeusState.IDLE.getValue();
						activeResearch = null;
						progress = 0;
					}
					break;
					
				case 2: // RESEARCHING
					if (!isActiveResearchValid()
							|| !(activeResearch.getString("parent").equals("NULL") || isParentDiscovered()))
					{
						activeResearch = null;
						state = DeusState.IDLE.getValue();
						return;
					}
					
					highlightX = activeResearch.getInteger("x");
					highlightY = activeResearch.getInteger("y");
					gmTarget = activeResearch.getInteger("grayMatterCost");
					progressTarget = activeResearch.getInteger("time");
					if (progress < progressTarget || gmConsumed < activeResearch.getInteger("grayMatterCost"))
					{
						if (energy.getEnergyStored() > activeResearch.getInteger("rfPerSec"))
						{
							int fluidToExtract = Math.min(activeResearch.getInteger("grayMatterCost") - gmConsumed,
									Math.min(GM_PER_TICK, tank.getFluidAmount()));
							gmConsumed += fluidToExtract;
							tank.drain(fluidToExtract, true);
							
							energy.extractEnergy(activeResearch.getInteger("rfPerSec"), false);
							if (progress < progressTarget)
								progress++;
						}
						
					} else if (gmConsumed >= activeResearch.getInteger("grayMatterCost"))
					{
						activeResearch.setBoolean("discovered", true);
						
						NBTTagList list = getStackInSlot(2).stackTagCompound.getTagList("tree",
								Constants.NBT.TAG_COMPOUND);
						NBTTagCompound tag = null;
						for (int i = 0; i < list.tagCount(); i++)
						{
							tag = list.getCompoundTagAt(i);
							if (tag.getString("recipe").equals(activeResearch.getString("recipe")))
							{
								tag.setBoolean("discovered", true);
								list.removeTag(i);
								list.appendTag(tag);
								break;
							}
						}
						
						worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
						this.markDirty();
						LogHelper.info("researched");
						state = DeusState.IDLE.getValue();
						activeResearch = null;
						progress = 0;
						gmConsumed = 0;
						gmTarget = 100;
					}
					break;
			}
			
		}
		super.updateEntity();
	}
	
	private boolean isParentDiscovered()
	{
		NBTTagList list = getStackInSlot(2).stackTagCompound.getTagList("tree", Constants.NBT.TAG_COMPOUND);
		NBTTagCompound tag = null;
		for (int i = 0; i < list.tagCount(); i++)
		{
			tag = list.getCompoundTagAt(i);
			if (tag.getString("recipe").equals(activeResearch.getString("parent")))
			{
				return tag.getBoolean("discovered");
			}
		}
		return false;
	}
	
	private boolean isActiveResearchValid()
	{
		ItemStack stack = getStackInSlot(2);
		if (stack == null || stack.stackTagCompound == null)
			return false;
		NBTTagList list = stack.stackTagCompound.getTagList("tree", Constants.NBT.TAG_COMPOUND);
		
		NBTTagCompound tag = null;
		for (int i = 0; i < list.tagCount(); i++)
		{
			tag = list.getCompoundTagAt(i);
			if (tag.getString("recipe").equals(activeResearch.getString("recipe")))
			{
				if (state == DeusState.PRINTING.getValue())
				{
					return tag.getBoolean("discovered");
				} else if (state == DeusState.RESEARCHING.getValue())
				{
					return !tag.getBoolean("discovered");
				}
			}
			
		}
		
		return false;
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
	public String getInventoryName()
	{
		return ModBlocks.grayMatterCrafterMaster.getUnlocalizedName() + ".name";
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack itemStack)
	{
		
		return false;
	}
	
	public void printResearch(String name)
	{
		ItemStack firstStack = stacks.get(0);
		ItemStack secondStack = stacks.get(1);
		if (canPrint())
		{
			this.decrStackSize(0, 1);
			ItemStack research = new ItemStack(ModItems.researchCore);
			research.stackTagCompound = new NBTTagCompound();
			research.stackTagCompound.setString("researchName", name);
			stacks.set(1, research);
		}
	}
	
	private boolean canPrint()
	{
		
		ItemStack firstStack = stacks.get(0);
		ItemStack secondStack = stacks.get(1);
		return firstStack != null && firstStack.getItem() == ModItems.researchCore && secondStack == null;
	}
	
	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill)
	{
		if (resource.getFluid() == ModFluids.grayMatter)
			return tank.fill(resource, doFill);
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
		return fluid == ModFluids.grayMatter;
	}
	
	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid)
	{
		return false;
	}
	
	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from)
	{
		return new FluidTankInfo[]
		{ this.tank.getInfo() };
	}
	
	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return true;
	}
	
	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		if (energy.getEnergyStored() < energy.getMaxEnergyStored())
			return energy.receiveEnergy(maxReceive, simulate);
		else
			return 0;
	}
	
	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return energy.getEnergyStored();
	}
	
	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return energy.getMaxEnergyStored();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tag)
	{
		super.readFromNBT(tag);
		tank.readFromNBT(tag);
		energy.readFromNBT(tag);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tag)
	{
		tank.writeToNBT(tag);
		energy.writeToNBT(tag);
		super.writeToNBT(tag);
	}
	
	@Override
	public boolean isUseableByPlayer(EntityPlayer player)
	{
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false
				: player.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 128.0D;
				
	}
	
	public void onButtonPressed(int id, NBTTagCompound tag)
	{
		if (id == 0)
		{
			boolean discovered = tag.getBoolean("discovered");
			
			switch (state)
			{
				case 0: // IDLE
					state = discovered ? DeusState.PRINTING.getValue() : DeusState.RESEARCHING.getValue();
					activeResearch = tag;
					break;
				case 1: // PRINTING
				case 2: // RESEARCHING
					//state = DeusState.IDLE.getValue();
					//activeResearch = null;
					//progress = 0;
					//progressTarget = 100;
					break;
			}
		}
	}
}
