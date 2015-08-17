package com.vinterdo.deusexmachina.multiblockstructures;

import java.util.List;

import com.vinterdo.deusexmachina.tileentity.base.TEMultiblock;
import com.vinterdo.deusexmachina.tileentity.base.TEMultiblockMaster;

public abstract class MultiBlockStructure
{
	public abstract boolean isValidMultiblock(TEMultiblockMaster tem);
	
	public abstract void getMembers(TEMultiblockMaster tem, List<TEMultiblock> members);
}
