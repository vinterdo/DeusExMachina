package com.vinterdo.deusexmachina.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;

public class TEShieldPylonEnding extends TileEntity
{
	public float	hitStr	= 0;
	public Vec3		hitPoint;
	
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
	}
	
	public void hit(Vec3 hitPoint)
	{
		hitStr = 1f;
		this.hitPoint = hitPoint;
	}
}