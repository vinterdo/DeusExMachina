package com.vinterdo.deusexmachina.tileentity;

import com.vinterdo.deusexmachina.init.ModBlocks;
import com.vinterdo.deusexmachina.tileentity.base.TEI;

import net.minecraft.inventory.IInventory;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;

public class TEShieldPylonBase extends TEI
{
	public float	hitStr	= 0;
	public Vec3		hitPoint;
	
	public TEShieldPylonBase()
	{
	 super.setNumOfStacks(4);
	}
	
	@Override
	public void updateEntity()
	{
		if (getWorldObj().isRemote)
		{
			if (hitStr > 0)
			{
				hitStr -= 0.01f;
			}
		
		}
		else
		{
			if(getWorldObj().getBlock(xCoord+1, yCoord+1, zCoord+1).isAir(getWorldObj(), xCoord+1, yCoord+1, zCoord+1)) 
			getWorldObj().setBlock(xCoord+1, yCoord+1, zCoord+1, ModBlocks.shield);}
	}
	
	public void hit(Vec3 hitPoint)
	{
		hitStr = 1f;
		this.hitPoint = hitPoint;
	}
}