package com.vinterdo.deusexmachina.multiblockstructures;

import java.util.List;

import com.vinterdo.deusexmachina.tileentity.TEEtherCondenser;
import com.vinterdo.deusexmachina.tileentity.base.TEMultiblock;
import com.vinterdo.deusexmachina.tileentity.base.TEMultiblockMaster;

import net.minecraft.tileentity.TileEntity;

public class StructureEtherCondenser extends MultiBlockStructure 
{
	@Override
    public boolean isValidMultiblock(TEMultiblockMaster temm)
    {
        for (int x = 0; x < 3; x++)
        {
            for (int y = 0; y < 3; y++)
            {
                for (int z = 0; z < 3; z++)
                {
                    TileEntity te = temm.getWorldObj().getTileEntity(temm.xCoord + x - 1, temm.yCoord + y - 2,
                            temm.zCoord + z - 1);
                    if (te == null)
                    {
                        return false;
                    } else if (te instanceof TEEtherCondenser)
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
            for (int y = 0; y < 3; y++)
            {
                for (int z = 0; z < 3; z++)
                {
                    TileEntity te = tem.getWorldObj().getTileEntity(tem.xCoord + x - 1, tem.yCoord + y - 2,
                            tem.zCoord + z - 1);

                    if (te instanceof TEEtherCondenser)
                    {
                        members.add((TEMultiblock) te);
                    }
                }
            }
        }

    }
}
