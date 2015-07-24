package com.vinterdo.deusexmachina.handler;

import com.vinterdo.deusexmachina.client.gui.GuiBlastFurnace;
import com.vinterdo.deusexmachina.client.gui.GuiEssenceMacerator;
import com.vinterdo.deusexmachina.client.gui.GuiEssenceProcessor;
import com.vinterdo.deusexmachina.client.gui.GuiGrayMatterFabricator;
import com.vinterdo.deusexmachina.client.gui.GuiHeater;
import com.vinterdo.deusexmachina.inventory.ContainerBlastFurnace;
import com.vinterdo.deusexmachina.inventory.ContainerEssenceMacerator;
import com.vinterdo.deusexmachina.inventory.ContainerEssenceProcessor;
import com.vinterdo.deusexmachina.inventory.ContainerGrayMatterFabricator;
import com.vinterdo.deusexmachina.inventory.ContainerHeater;
import com.vinterdo.deusexmachina.tileentity.TEDEM;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler
{
	public enum GuiIDs
	{
		ESSENCE_PROCESSOR,
		HEATER,
		ESSENCE_MACERATOR,
		BLAST_FURNACE, 
		GRAY_MATTER_FABRICATOR;
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		switch(GuiIDs.values()[ID])
		{
		case ESSENCE_PROCESSOR:
			return new ContainerEssenceProcessor(player.inventory, (TEDEM)world.getTileEntity(x, y, z));
		case HEATER:
			return new ContainerHeater(player.inventory, (TEDEM)world.getTileEntity(x, y, z));
		case ESSENCE_MACERATOR:
			return new ContainerEssenceMacerator(player.inventory, (TEDEM)world.getTileEntity(x, y, z));
		case GRAY_MATTER_FABRICATOR:
			return new ContainerGrayMatterFabricator(player.inventory, (TEDEM)world.getTileEntity(x, y, z));
		case BLAST_FURNACE:
			return new ContainerBlastFurnace(player.inventory, (TEDEM)world.getTileEntity(x, y, z));
		
		}
		
		throw new IllegalArgumentException("No gui with id " + ID);
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) 
	{
		switch(GuiIDs.values()[ID])
		{
		case ESSENCE_PROCESSOR:
			return new GuiEssenceProcessor(player.inventory, (TEDEM)world.getTileEntity(x, y, z));
		case HEATER:
			return new GuiHeater(player.inventory, (TEDEM)world.getTileEntity(x, y, z));
		case ESSENCE_MACERATOR:
			return new GuiEssenceMacerator(player.inventory, (TEDEM)world.getTileEntity(x, y, z));
		case GRAY_MATTER_FABRICATOR:
			return new GuiGrayMatterFabricator(player.inventory, (TEDEM)world.getTileEntity(x, y, z));
		case BLAST_FURNACE:
			return new GuiBlastFurnace(player.inventory, (TEDEM)world.getTileEntity(x, y, z));
		
		}
		
		throw new IllegalArgumentException("No gui with id " + ID);
	}

}
