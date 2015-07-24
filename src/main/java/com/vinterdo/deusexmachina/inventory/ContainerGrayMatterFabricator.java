package com.vinterdo.deusexmachina.inventory;

import java.util.List;

import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.tileentity.TEBlastFurnaceMaster;
import com.vinterdo.deusexmachina.tileentity.TEDEM;
import com.vinterdo.deusexmachina.tileentity.TEEssenceMacerator;
import com.vinterdo.deusexmachina.tileentity.TEEssenceProcessor;
import com.vinterdo.deusexmachina.tileentity.TEGrayMatterFabricatorMaster;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerGrayMatterFabricator extends ContainerDEM
{
	int lastProgress = -1;
	int lastPower = -1;
	int lastProgressTarget = -1;
	TEGrayMatterFabricatorMaster te;
	
	public ContainerGrayMatterFabricator(InventoryPlayer playerInv, TEDEM _te)
	{
		super(_te);
		te = (TEGrayMatterFabricatorMaster) _te;
		
		this.addSlotToContainer(new Slot((IInventory) te, 0, 25, 14 + 0 * 21));
		this.addSlotToContainer(new Slot((IInventory) te, 1, 60 + 0 * 20, 56 ));
		this.addSlotToContainer(new SlotOutput((IInventory) te, 2, 134, 14 + 0 * 21));
		
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
		if(lastProgressTarget != te.getProgressTarget())
		{
			for(ICrafting crafter : (List<ICrafting>)crafters)
			{
				crafter.sendProgressBarUpdate(this, 1, te.getProgressTarget());
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

            if (target < 3)
            {
                if (!this.mergeItemStack(itemstack1, 9, 45, true))
                {
                    return null;
                }
            }
            else 
            {	
            	if(itemstack1.getItem() == ModItems.steelIngot)
            	{
	            	if (!this.mergeItemStack(itemstack1, 0, 1, false))
	                	return null;
            	}
            	else if(itemstack1.getItem() == ModItems.essence)
            	{
            		if (!this.mergeItemStack(itemstack1, 1, 2, false))
                    	return null;
            	}
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
