package com.vinterdo.deusexmachina.tileentity;

import java.util.ArrayList;

import com.vinterdo.deusexmachina.helpers.Helper;
import com.vinterdo.deusexmachina.helpers.NBTSaved;
import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.network.Synchronized;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

public class TEBlastFurnaceMaster extends TEIMultiblockMaster
{

    @Synchronized(id = 0)
    @NBTSaved(name = "burningTime")
    public int burningTime;
    @Synchronized(id = 1)
    @NBTSaved(name = "progress")
    public int progress;
    @Synchronized(id = 2)
    @NBTSaved(name = "progressTarget")
    public int progressTarget;

    public static final int PROGRESS_MULT    = 1;
    public static final int SMELT_TIME       = 100;
    public static final int SMELT_TIME_STEEL = 400;

    public TEBlastFurnaceMaster()
    {
        super();
        this.setNumOfStacks(9);
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        if (!worldObj.isRemote)
        {
            if (burningTime > 0)
            {
                smeltItems();
            } else
            {
                burnFuel();
            }
        }
    }

    private void burnFuel()
    {
        for (int i = 0; i < 3; i++)
        {
            if (stacks.get(i + 3) != null && TileEntityFurnace.isItemFuel(stacks.get(i + 3)))
            {
                burningTime += TileEntityFurnace.getItemBurnTime(stacks.get(i + 3));
                this.decrStackSize(i + 3, 1);
                this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
            }
        }
    }

    private void smeltItems()
    {
        burningTime -= 1;

        for (int i = 0; i < 3; i++)
        {
            if (stacks.get(i) != null)
            {
                if (stacks.get(i).getItem() == ModItems.steelDust
                        || FurnaceRecipes.smelting().getSmeltingResult(stacks.get(i)) != null)
                {
                    ItemStack target;
                    if (stacks.get(i).getItem() == ModItems.steelDust)
                    {
                        progressTarget = SMELT_TIME_STEEL;
                        target = new ItemStack(ModItems.steelIngot, 1);
                    } else
                    {
                        progressTarget = SMELT_TIME;
                        target = FurnaceRecipes.smelting().getSmeltingResult(stacks.get(i)).copy();
                        if (target.stackSize == 0)
                            target.stackSize = 1;
                    }

                    int spaceLeft = calcSpaceLeft(target);

                    if (spaceLeft - target.stackSize > 0)
                    {
                        progress += PROGRESS_MULT;
                        if (progress > progressTarget)
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
        for (int j = 0; j < 3; j++)
        {
            spaceLeft += (stacks.get(j + 6) == null) ? 64
                    : (Helper.areItemStacksStackable(stacks.get(j + 6), target))
                            ? stacks.get(j + 6).getMaxStackSize() - stacks.get(j + 6).stackSize : 0;
        }
        return spaceLeft;
    }

    private void addItemToOutput(ItemStack stack)
    {
        this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        for (int i = 0; i < 3; i++)
        {
            if (stacks.get(i + 6) == null)
            {
                stacks.set(i + 6, stack);
                return;
            } else if (stacks.get(i + 6).getItem() == stack.getItem())
            {
                if (stacks.get(i + 6).stackSize + stack.stackSize <= stacks.get(i + 6).getMaxStackSize())
                {
                    stacks.get(i + 6).stackSize += stack.stackSize;
                    return;
                } else
                {
                    int rest = stacks.get(i + 6).stackSize + stack.stackSize - stacks.get(i + 6).getMaxStackSize();
                    stacks.get(i + 6).stackSize = stacks.get(i + 6).getMaxStackSize();
                    stack.stackSize = rest;
                }
            }
        }

    }

    @Override
    public void tryForming()
    {
        members = new ArrayList<TEMultiblock>();
        for (int x = 0; x < 3; x++)
        {
            for (int y = 0; y < 4; y++)
            {
                for (int z = 0; z < 3; z++)
                {
                    TileEntity te = worldObj.getTileEntity(xCoord + x - 1, yCoord + y - 3, zCoord + z - 1);

                    if (te instanceof TEBlastFurnace)
                    {
                        members.add((TEMultiblock) te);
                    }
                }
            }
        }
        super.tryForming();
    }

    @Override
    public boolean isProperMultiblock()
    {
        for (int x = 0; x < 3; x++)
        {
            for (int y = 0; y < 4; y++)
            {
                for (int z = 0; z < 3; z++)
                {
                    TileEntity te = worldObj.getTileEntity(xCoord + x - 1, yCoord + y - 3, zCoord + z - 1);
                    if (te == null)
                    {
                        return false;
                    } else if (te instanceof TEBlastFurnace)
                    {
                        TEMultiblock tem = (TEMultiblock) te;
                        if (tem.getMaster() != null)
                            return false;
                    } else if (te != this)
                    {
                        return false;
                    }

                }
            }
        }

        return true;
    }

    @Override
    public String getInventoryName()
    {
        return ModBlocks.blastFurnaceMaster.getUnlocalizedName() + ".name";
    }

    @Override
    public boolean isItemValidForSlot(int slot, ItemStack itemStack)
    {
        if (slot <= 2)
            return true;
        if (slot > 2 && slot <= 5)
            return TileEntityFurnace.getItemBurnTime(itemStack) > 0;

        return false;
    }

}
