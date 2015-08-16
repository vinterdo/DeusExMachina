package com.vinterdo.deusexmachina.tileentity.base;

import java.util.ArrayList;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class TEIMultiblockMaster extends TEMultiblockMaster implements IInventory
{
    protected ArrayList<ItemStack> stacks;

    public void setNumOfStacks(int num)
    {
        stacks = new ArrayList<ItemStack>(num);
        for (int i = 0; i < num; i++)
        {
            stacks.add(null);
        }

    }

    @Override
    public int getSizeInventory()
    {
        return stacks.size();
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        return stacks.get(slot);
    }

    @Override
    public ItemStack decrStackSize(int slot, int amount)
    {
        if (this.stacks.get(slot) != null)
        {
            ItemStack itemstack;

            if (stacks.get(slot).stackSize <= amount)
            {
                itemstack = stacks.get(slot);
                setInventorySlotContents(slot, null);
                return itemstack;
            } else
            {
                itemstack = stacks.get(slot).splitStack(amount);

                if (stacks.get(slot).stackSize == 0)
                {
                    setInventorySlotContents(slot, null);
                }

                markDirty();
                return itemstack;
            }
        } else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot)
    {
        ItemStack itemstack = stacks.get(slot);
        stacks.set(slot, null);
        return itemstack;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack stack)
    {
        stacks.set(slot, stack);

        if (stack != null && stack.stackSize > this.getInventoryStackLimit())
        {
            stack.stackSize = this.getInventoryStackLimit();
        }

        this.markDirty();
    }

    @Override
    public String getInventoryName()
    {
        return "";
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
                : player.getDistanceSq((double) this.xCoord + 0.5D, (double) this.yCoord + 0.5D,
                        (double) this.zCoord + 0.5D) <= 64.0D;
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
        return slot < stacks.size();
    }

    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);

        NBTTagList stackTag = new NBTTagList();
        for (int i = 0; i < stacks.size(); i++)
        {
            if (stacks.get(i) != null)
            {
                NBTTagCompound t = new NBTTagCompound();
                stacks.get(i).writeToNBT(t);
                t.setByte("index", (byte) i);
                stackTag.appendTag(t);
            }
        }

        tag.setTag("stacks", stackTag);
    }

    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);

        NBTTagList stackTag = tag.getTagList("stacks", 10);
        // setNumOfStacks(stackTag.tagCount());

        for (int i = 0; i < stackTag.tagCount(); i++)
        {
            NBTTagCompound t = stackTag.getCompoundTagAt(i);
            int index = t.getByte("index");
            stacks.set(index, ItemStack.loadItemStackFromNBT(t));
        }
    }

    public void writeToPacket(ByteBuf buf)
    {
        super.writeToPacket(buf);
        for (ItemStack stack : stacks)
            ByteBufUtils.writeItemStack(buf, stack);
    }

    public void readFromPacket(ByteBuf buf)
    {
        super.readFromPacket(buf);

        for (int i = 0; i < stacks.size(); i++)
            stacks.set(i, ByteBufUtils.readItemStack(buf));
    }

}
