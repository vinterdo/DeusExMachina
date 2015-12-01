package com.vinterdo.deusexmachina.inventory;

import com.vinterdo.deusexmachina.init.ModItems;
import com.vinterdo.deusexmachina.item.ItemMachineCard;
import com.vinterdo.deusexmachina.tileentity.TETeleportGateMaster;
import com.vinterdo.deusexmachina.tileentity.TETeleportMasterMaster;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class SlotTeleportMaster extends SlotItemOnly
{
	TETeleportMasterMaster te;
	
	public SlotTeleportMaster(TETeleportMasterMaster te, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_)
	{
		super(te, p_i1824_2_, p_i1824_3_, p_i1824_4_, ModItems.machineCard);
		this.te = te;
	}
	
	@Override
	public void putStack(ItemStack stack)
	{
		ItemStack oldStack = this.inventory.getStackInSlot(this.slotNumber);
		
		super.putStack(stack);
		
		TileEntity gate = ItemMachineCard.getTargetTE(stack);
		if (gate != null && gate instanceof TETeleportGateMaster)
		{
			if (!te.teleports.contains(gate))
			{
				te.teleports.set(this.slotNumber, (TETeleportGateMaster) gate);
			}
		}
		
		if (te.gui != null)
			te.gui.markToRefresh();
	}
	
	@Override
	public ItemStack decrStackSize(int amount)
	{
		te.teleports.set(this.slotNumber, null);
		if (te.gui != null)
			te.gui.markToRefresh();
		return super.decrStackSize(amount);
	}
}
