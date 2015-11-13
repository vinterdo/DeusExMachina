package com.vinterdo.deusexmachina.multiblockstructures;

import com.vinterdo.deusexmachina.tileentity.TETeleportGate;
import com.vinterdo.deusexmachina.tileentity.TETeleportMaster;

public class StructureTeleportGate extends MultiBlockStructure {
	public StructureTeleportGate()
	{
		super();
		for (int x = 0; x < 3; x++)
		{
			for (int y = 0; y < 4; y++)
			{
				for (int z = 0; z < 3; z++)
				{
					if(!(x == 1 && y == 3 && z == 0))
						this.addBlock(TETeleportGate.class, x - 1, y - 3, z);
				}
			}
		}
	}
}
