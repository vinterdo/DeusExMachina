package com.vinterdo.deusexmachina.multiblockstructures;

import java.util.List;

import com.vinterdo.deusexmachina.tileentity.TEDeus;
import com.vinterdo.deusexmachina.tileentity.base.TEMultiblock;
import com.vinterdo.deusexmachina.tileentity.base.TEMultiblockMaster;

import net.minecraft.tileentity.TileEntity;

public class StructureDeus extends MultiBlockStructure
{
	
	@Override
	public boolean isValidMultiblock(TEMultiblockMaster temm)
	{
		for (int x = 0; x < 5; x++)
		{
			for (int y = 0; y < 7; y++)
			{
				for (int z = 0; z < 5; z++)
				{
					TileEntity te = temm.getWorldObj().getTileEntity(temm.xCoord + x - 2, temm.yCoord + y - 6,
							temm.zCoord + z - 2);
					if (te == null)
					{
						return false;
					} else if (te instanceof TEDeus)
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
		for (int x = 0; x < 5; x++)
		{
			for (int y = 0; y < 7; y++)
			{
				for (int z = 0; z < 5; z++)
				{
					TileEntity te = tem.getWorldObj().getTileEntity(tem.xCoord + x - 2, tem.yCoord + y - 6,
							tem.zCoord + z - 2);
							
					if (te instanceof TEDeus)
					{
						members.add((TEMultiblock) te);
					}
				}
			}
		}
		
	}
	
}
