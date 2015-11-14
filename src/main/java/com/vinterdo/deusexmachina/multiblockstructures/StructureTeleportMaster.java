package com.vinterdo.deusexmachina.multiblockstructures;

import java.util.List;

import com.vinterdo.deusexmachina.tileentity.TETeleportMaster;
import com.vinterdo.deusexmachina.tileentity.base.TEMultiblock;
import com.vinterdo.deusexmachina.tileentity.base.TEMultiblockMaster;

import net.minecraft.tileentity.TileEntity;

public class StructureTeleportMaster extends MultiBlockStructure
{
	public StructureTeleportMaster()
	{
		super();
		for (int x = 0; x < 5; x++)
		{
			for (int y = 0; y < 5; y++)
			{
				for (int z = 0; z < 5; z++)
				{
					if(!(x == 2 && y == 4 && z == 2))
						this.addBlock(TETeleportMaster.class, x - 2, y - 4, z - 2);
				}
			}
		}
	}
	/*
	@Override
	public boolean isValidMultiblock(TEMultiblockMaster temm)
	{
		for (int x = 0; x < 5; x++)
		{
			for (int y = 0; y < 5; y++)
			{
				for (int z = 0; z < 5; z++)
				{
					TileEntity te = temm.getWorldObj().getTileEntity(temm.xCoord + x - 2, temm.yCoord + y - 4,
							temm.zCoord + z - 2);
					if (te == null)
					{
						return false;
					} else if (te instanceof TETeleportMaster)
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
							
					if (te instanceof TETeleportMaster)
					{
						members.add((TEMultiblock) te);
					}
				}
			}
		}
		
	}
	*/
}
