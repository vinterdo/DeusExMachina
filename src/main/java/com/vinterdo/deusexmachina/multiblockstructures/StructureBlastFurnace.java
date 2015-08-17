package com.vinterdo.deusexmachina.multiblockstructures;

import java.util.List;

import com.vinterdo.deusexmachina.tileentity.TEBlastFurnace;
import com.vinterdo.deusexmachina.tileentity.base.TEMultiblock;
import com.vinterdo.deusexmachina.tileentity.base.TEMultiblockMaster;

import net.minecraft.tileentity.TileEntity;

public class StructureBlastFurnace extends MultiBlockStructure
{
	
	@Override
	public boolean isValidMultiblock(TEMultiblockMaster temm)
	{
		for (int x = 0; x < 3; x++)
		{
			for (int y = 0; y < 4; y++)
			{
				for (int z = 0; z < 3; z++)
				{
					TileEntity te = temm.getWorldObj().getTileEntity(temm.xCoord + x - 1, temm.yCoord + y - 3,
							temm.zCoord + z - 1);
					if (te == null)
					{
						return false;
					} else if (te instanceof TEBlastFurnace)
					{
						TEMultiblock tem = (TEMultiblock) te;
						if (tem.getMaster() != null)
							return false;
					} else if (te != temm)
					{
						return false;
					}
				}
			}
		}
		
		return true;
	}
	
	@Override
	public void getMembers(TEMultiblockMaster tem, List<TEMultiblock> members)
	{
		for (int x = 0; x < 3; x++)
		{
			for (int y = 0; y < 4; y++)
			{
				for (int z = 0; z < 3; z++)
				{
					TileEntity te = tem.getWorldObj().getTileEntity(tem.xCoord + x - 1, tem.yCoord + y - 3,
							tem.zCoord + z - 1);
							
					if (te instanceof TEBlastFurnace)
					{
						members.add((TEMultiblock) te);
					}
				}
			}
		}
		
	}
	
}
