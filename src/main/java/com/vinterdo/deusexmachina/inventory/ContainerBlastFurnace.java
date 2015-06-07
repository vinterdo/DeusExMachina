package com.vinterdo.deusexmachina.inventory;

import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.tileentity.TileEntityDEM;
import com.vinterdo.deusexmachina.tileentity.TileEntityEssenceProcessor;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;

public class ContainerBlastFurnace extends ContainerDEM
{
	
	// TODO: code this shit
	public ContainerBlastFurnace(InventoryPlayer playerInv, TileEntityDEM te)
	{
		super(te);
		
		for(int i =0; i < 3; i++)
		{
			this.addSlotToContainer(new Slot((IInventory) te, i, 7, 14 + i * 19));
		}
		for(int i =0; i < 3; i++)
		{
			this.addSlotToContainer(new SlotFuel((IInventory) te, i + 6, 70, 30 + i * 19));
		}
		for(int i =0; i < 3; i++)
		{
			this.addSlotToContainer(new SlotOutput((IInventory) te, i + 3, 100, 14 + i * 19));
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
	            	if (!this.mergeItemStack(itemstack1, 6, 9, false))
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
