package com.vinterdo.deusexmachina.tileentity;

import java.util.ArrayList;

import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.multiblockstructures.MultiBlockStructure;
import com.vinterdo.deusexmachina.multiblockstructures.StructureEtherCondenser;
import com.vinterdo.deusexmachina.tileentity.base.TEIMultiblockMaster;
import com.vinterdo.deusexmachina.tileentity.base.TEIUniversalMultiblock;
import com.vinterdo.deusexmachina.tileentity.base.TEMultiblock;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class TEEtherCondenserMaster extends TEIMultiblockMaster {

	
	public static final MultiBlockStructure structure = new StructureEtherCondenser();
	
	public TEEtherCondenserMaster()
    {
        super();
        setNumOfStacks(3);
    }

    @Override
    public void updateEntity()
    {
       
    }

    @Override // Próba zformowania multiblocku. Je¿eli multiblock jest ju¿ zformowany, wywalamy starych slavów i próbujemy formowaæ jeszcze raz.
    public void tryForming()
    {
        members = new ArrayList<TEMultiblock>();
        structure.getMembers(this, members);
        super.tryForming();
    }

    @Override 
    public boolean isProperMultiblock() // Sprawdzenie czy multiblock jest prawidlowy
    {
        return structure.isValidMultiblock(this);
    }

    @Override
    public String getInventoryName() // nazwa ekwipunku
    {
        return ModBlocks.etherCondenserMaster.getUnlocalizedName() + ".name";
    }


    // Wczytywanie i zapisywanie danych do NBT
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
    }

    // Sprawdzenie czy gracz moze uzywac multiblocku. Trzeba pamietac ze gracz klikajac na jakakolwiek czesc multibloku tak naprawde uzywa Mastera.
    @Override
    public boolean isUseableByPlayer(EntityPlayer player)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false
                : player.getDistanceSq(this.xCoord + 0.5D, this.yCoord + 0.5D, this.zCoord + 0.5D) <= 128.0D;

    }

}
