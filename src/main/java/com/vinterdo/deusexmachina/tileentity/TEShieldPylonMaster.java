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

public class TEShieldPylonMaster extends TEIMultiblockMaster
{
	 public static final MultiBlockStructure structure           = new StructureDeus(); 
	 @Override 
	    public boolean isProperMultiblock() // Sprawdzenie czy multiblock jest prawidlowy
	    {
	        return structure.isValidMultiblock(this);
	    }
}
