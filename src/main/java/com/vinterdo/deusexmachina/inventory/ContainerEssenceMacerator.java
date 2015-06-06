package com.vinterdo.deusexmachina.inventory;


import java.util.List;

import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.tileentity.TileEntityDEM;
import com.vinterdo.deusexmachina.tileentity.TileEntityEssenceMacerator;
import com.vinterdo.deusexmachina.tileentity.TileEntityEssenceProcessor;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerEssenceMacerator extends ContainerDEM
{
	int lastProgress = -1;
	int lastPower = -1;
	int lastProgressTarget = -1;
	TileEntityEssenceMacerator te;
	
	public ContainerEssenceMacerator(InventoryPlayer playerInv, TileEntityDEM te)
	{
		super(te);
		this.te = (TileEntityEssenceMacerator)te;
		
		for(int i =0; i < 3; i++)
		{
			this.addSlotToContainer(new Slot((IInventory) te, i, 25, 14 + i * 21));
		}
		
		this.addSlotToContainer(new SlotItemOnly((IInventory) te, 3, 45, 35, ModItems.essence));
		
		for(int i =4; i < 7; i++)
		{
			this.addSlotToContainer(new SlotOutput((IInventory) te, i, 134, 14 + (i - 4) * 21));
		}

		this.addSlotToContainer(new SlotOutput((IInventory) te, 7, 113, 35));
		
		addPlayerSlots(playerInv, 8, 84);
	}
	
	public void detectAndSendChanges()
	{
		super.detectAndSendChanges();
		
		if(lastProgress != te.getProgress())
		{
			for(ICrafting crafter : (List<ICrafting>)crafters)
			{
				crafter.sendProgressBarUpdate(this, 0, te.getProgress());
			}
			lastProgress = te.getProgress();
		}
		
		if(lastPower != te.getPower())
		{
			for(ICrafting crafter : (List<ICrafting>)crafters)
			{
				crafter.sendProgressBarUpdate(this, 1, te.getPower());
			}
			lastProgress = te.getPower();
		}
		
		if(lastProgressTarget != te.getProgressTarget())
		{
			for(ICrafting crafter : (List<ICrafting>)crafters)
			{
				crafter.sendProgressBarUpdate(this, 2, te.getProgressTarget());
			}
			lastProgressTarget = te.getProgressTarget();
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int value)
	{
		super.updateProgressBar(id, value);
		if(id == 0)
		{
			te.setProgress(value);
		}
		else if(id == 1)
		{
			te.setPower(value);
		}
		else if(id == 2)
		{
			te.setProgressTarget(value);
		}
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) 
	{
		return ((IInventory)te).isUseableByPlayer(player); 
	}
	

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int target)
    {
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(target);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (target < 7)
            {
                if (!this.mergeItemStack(itemstack1, 7, 43, true))
                {
                    return null;
                }
            }
            else 
            {	
            	if(itemstack1.getItem() == ModItems.essence)
            	{
	            	if (!this.mergeItemStack(itemstack1, 3, 4, false))
	                	return null;
            	}
            	
	            if (!this.mergeItemStack(itemstack1, 0, 3, false))
	                return null;
            	
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(player, itemstack1);
        }

        return itemstack;
    }

}
