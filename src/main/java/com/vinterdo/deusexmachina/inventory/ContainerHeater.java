package com.vinterdo.deusexmachina.inventory;

import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.tileentity.TEDEM;
import com.vinterdo.deusexmachina.tileentity.TEEssenceProcessor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerHeater extends ContainerDEM
{
	
	public ContainerHeater(InventoryPlayer playerInv, TEDEM te)
	{
		super(te);
		
		for(int i =0; i < 3; i++)
		{
			for(int j =0; j < 3; j++)
			{
				this.addSlotToContainer(new SlotFuel((IInventory) te, j + i * 3, 7 + j * 19, 14 + i * 19));
			}
		}
		
		addPlayerSlots(playerInv, 8, 84);
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

            if (target < 9)
            {
                if (!this.mergeItemStack(itemstack1, 9, 45, true))
                {
                    return null;
                }
            }
            else 
            {	
            	if(TileEntityFurnace.isItemFuel(itemstack1))
            	{
	            	if (!this.mergeItemStack(itemstack1, 0, 9, false))
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
