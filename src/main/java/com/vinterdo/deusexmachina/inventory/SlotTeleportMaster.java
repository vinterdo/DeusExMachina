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
	
	public SlotTeleportMaster(TETeleportMasterMaster p_i1824_1_, int p_i1824_2_, int p_i1824_3_, int p_i1824_4_)
	{
		super(p_i1824_1_, p_i1824_2_, p_i1824_3_, p_i1824_4_, ModItems.machineCard);
		this.te = p_i1824_1_;
		
	}
	
	@Override
	public void putStack(ItemStack p_75215_1_)
	{
		ItemStack oldStack = this.inventory.getStackInSlot(this.slotNumber);
		
		super.putStack(p_75215_1_);
		
		TileEntity gate = ItemMachineCard.getTargetTE(p_75215_1_);
		if (gate != null && gate instanceof TETeleportGateMaster)
		{
			if (!te.teleports.contains(gate))
			{
				te.teleports.set(this.slotNumber, (TETeleportGateMaster) gate);
			}
		}
		
	}
	
	@Override
	public ItemStack decrStackSize(int slot)
	{
		te.teleports.set(slot, null);
		return super.decrStackSize(slot);
	}
}
