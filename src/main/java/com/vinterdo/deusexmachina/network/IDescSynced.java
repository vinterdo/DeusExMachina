package com.vinterdo.deusexmachina.network;

import java.util.List;

import com.vinterdo.deusexmachina.inventory.SyncedField;

import net.minecraft.nbt.NBTTagCompound;

public interface IDescSynced
{
    public static enum Type
    {
        TILE_ENTITY, SEMI_BLOCK;
    }

    public Type getSyncType();

    public List<SyncedField> getDescriptionFields();

    public void writeToPacket(NBTTagCompound tag);

    public void readFromPacket(NBTTagCompound tag);

    public int getX();

    public int getY();

    public int getZ();

    public void onDescUpdate();
}