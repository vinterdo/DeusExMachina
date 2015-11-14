package com.vinterdo.deusexmachina.handler;

import com.vinterdo.deusexmachina.client.gui.GuiBlastFurnace;
import com.vinterdo.deusexmachina.client.gui.GuiDataBank;
import com.vinterdo.deusexmachina.client.gui.GuiDeus;
import com.vinterdo.deusexmachina.client.gui.GuiEssenceMacerator;
import com.vinterdo.deusexmachina.client.gui.GuiEssenceProcessor;
import com.vinterdo.deusexmachina.client.gui.GuiGrayMatterCrafter;
import com.vinterdo.deusexmachina.client.gui.GuiGrayMatterFabricator;
import com.vinterdo.deusexmachina.client.gui.GuiHeater;
import com.vinterdo.deusexmachina.client.gui.GuiTeleportGate;
import com.vinterdo.deusexmachina.client.gui.GuiTeleportMaster;
import com.vinterdo.deusexmachina.client.gui.GuiTerminal;
import com.vinterdo.deusexmachina.inventory.ContainerBlastFurnace;
import com.vinterdo.deusexmachina.inventory.ContainerDataBank;
import com.vinterdo.deusexmachina.inventory.ContainerDeus;
import com.vinterdo.deusexmachina.inventory.ContainerEssenceMacerator;
import com.vinterdo.deusexmachina.inventory.ContainerEssenceProcessor;
import com.vinterdo.deusexmachina.inventory.ContainerGrayMatterCrafter;
import com.vinterdo.deusexmachina.inventory.ContainerGrayMatterFabricator;
import com.vinterdo.deusexmachina.inventory.ContainerHeater;
import com.vinterdo.deusexmachina.inventory.ContainerTeleportGate;
import com.vinterdo.deusexmachina.inventory.ContainerTeleportMaster;
import com.vinterdo.deusexmachina.inventory.ContainerTerminal;
import com.vinterdo.deusexmachina.tileentity.TEBlastFurnaceMaster;
import com.vinterdo.deusexmachina.tileentity.TEDataBank;
import com.vinterdo.deusexmachina.tileentity.TEDeusMaster;
import com.vinterdo.deusexmachina.tileentity.TEEssenceMacerator;
import com.vinterdo.deusexmachina.tileentity.TEEssenceProcessor;
import com.vinterdo.deusexmachina.tileentity.TEGrayMatterCrafterMaster;
import com.vinterdo.deusexmachina.tileentity.TEGrayMatterFabricatorMaster;
import com.vinterdo.deusexmachina.tileentity.TEHeater;
import com.vinterdo.deusexmachina.tileentity.TETeleportGateMaster;
import com.vinterdo.deusexmachina.tileentity.TETeleportMasterMaster;
import com.vinterdo.deusexmachina.tileentity.TETerminal;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{
	public enum GuiIDs
	{
		TERMINAL, ESSENCE_PROCESSOR, HEATER, ESSENCE_MACERATOR, BLAST_FURNACE, GRAY_MATTER_FABRICATOR, DEUS, GRAY_MATTER_CRAFTER, DATA_BANK, TELEPORT_MASTER, TELEPORT_GATE;
	}
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (GuiIDs.values()[ID])
		{
			case TERMINAL:
				return new ContainerTerminal(player.inventory, (TETerminal) world.getTileEntity(x, y, z));
			case ESSENCE_PROCESSOR:
				return new ContainerEssenceProcessor(player.inventory,
						(TEEssenceProcessor) world.getTileEntity(x, y, z));
			case HEATER:
				return new ContainerHeater(player.inventory, (TEHeater) world.getTileEntity(x, y, z));
			case ESSENCE_MACERATOR:
				return new ContainerEssenceMacerator(player.inventory,
						(TEEssenceMacerator) world.getTileEntity(x, y, z));
			case GRAY_MATTER_FABRICATOR:
				return new ContainerGrayMatterFabricator(player.inventory,
						(TEGrayMatterFabricatorMaster) world.getTileEntity(x, y, z));
			case BLAST_FURNACE:
				return new ContainerBlastFurnace(player.inventory, (TEBlastFurnaceMaster) world.getTileEntity(x, y, z));
			case GRAY_MATTER_CRAFTER:
				return new ContainerGrayMatterCrafter(player.inventory,
						(TEGrayMatterCrafterMaster) world.getTileEntity(x, y, z));
			case DEUS:
				return new ContainerDeus(player.inventory, (TEDeusMaster) world.getTileEntity(x, y, z));
			case DATA_BANK:
				return new ContainerDataBank(player.inventory, (TEDataBank) world.getTileEntity(x, y, z));
			case TELEPORT_MASTER:
				return new ContainerTeleportMaster(player.inventory,
						(TETeleportMasterMaster) world.getTileEntity(x, y, z));
			case TELEPORT_GATE:
				return new ContainerTeleportGate(player.inventory,
						(TETeleportGateMaster) world.getTileEntity(x, y, z));
		}
		
		throw new IllegalArgumentException("No gui with id " + ID);
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
	{
		switch (GuiIDs.values()[ID])
		{
			case TERMINAL:
				return new GuiTerminal(player.inventory, world.getTileEntity(x, y, z));
			case ESSENCE_PROCESSOR:
				return new GuiEssenceProcessor(player.inventory, world.getTileEntity(x, y, z));
			case HEATER:
				return new GuiHeater(player.inventory, world.getTileEntity(x, y, z));
			case ESSENCE_MACERATOR:
				return new GuiEssenceMacerator(player.inventory, world.getTileEntity(x, y, z));
			case GRAY_MATTER_FABRICATOR:
				return new GuiGrayMatterFabricator(player.inventory, world.getTileEntity(x, y, z));
			case GRAY_MATTER_CRAFTER:
				return new GuiGrayMatterCrafter(player.inventory, world.getTileEntity(x, y, z));
			case BLAST_FURNACE:
				return new GuiBlastFurnace(player.inventory, world.getTileEntity(x, y, z));
			case DEUS:
				return new GuiDeus(player.inventory, world.getTileEntity(x, y, z));
			case DATA_BANK:
				return new GuiDataBank(player.inventory, world.getTileEntity(x, y, z));
			case TELEPORT_MASTER:
				return new GuiTeleportMaster(player.inventory, world.getTileEntity(x, y, z));
			case TELEPORT_GATE:
				return new GuiTeleportGate(player.inventory, world.getTileEntity(x, y, z));
				
		}
		
		throw new IllegalArgumentException("No gui with id " + ID);
	}
	
}
