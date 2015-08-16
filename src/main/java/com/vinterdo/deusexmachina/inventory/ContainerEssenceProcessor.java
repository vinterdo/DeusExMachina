package com.vinterdo.deusexmachina.inventory;


import java.util.List;

import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.tileentity.TEEssenceProcessor;
import com.vinterdo.deusexmachina.tileentity.base.TEDEM;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ContainerEssenceProcessor extends ContainerDEM
{
	int lastProgress = -1;
	int lastPower = -1;
	TEEssenceProcessor te;
	
	public ContainerEssenceProcessor(InventoryPlayer playerInv, TEDEM te)
	{
		super(te);
		this.te = (TEEssenceProcessor)te;
		
		for(int i =0; i < 3; i++)
		{
			this.addSlotToContainer(new SlotItemOnly((IInventory) te, i, 25, 14 + i * 21, ModItems.essenceContainer));
		}
		for(int i =3; i < 6; i++)
		{
			this.addSlotToContainer(new SlotItemOnly((IInventory) te, i, 45, 14 + (i - 3) * 21, Item.getItemFromBlock(ModBlocks.essenceOre)));
		}
		for(int i =6; i < 9; i++)
		{
			this.addSlotToContainer(new SlotOutput((IInventory) te, i, 134, 14 + (i - 6) * 21));
		}
		
		
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
	}
	
	@Override
	public boolean canInteractWith(EntityPlayer player) 
	{
		return ((IInventory)te).isUseableByPlayer(player); // TODO - fix
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
            	if(itemstack1.getItem() == ModItems.essenceContainer)
            	{
	            	if (!this.mergeItemStack(itemstack1, 0, 3, false))
	                	return null;
            	}
            	
            	if(itemstack1.getItem() == Item.getItemFromBlock(ModBlocks.essenceOre))
            	{
	            	if (!this.mergeItemStack(itemstack1, 3, 6, false))
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
