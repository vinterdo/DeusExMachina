package com.vinterdo.deusexmachina.handler;

import com.vinterdo.deusexmachina.client.gui.GuiBlastFurnace;
import com.vinterdo.deusexmachina.client.gui.GuiDeus;
import com.vinterdo.deusexmachina.client.gui.GuiEssenceMacerator;
import com.vinterdo.deusexmachina.client.gui.GuiEssenceProcessor;
import com.vinterdo.deusexmachina.client.gui.GuiGrayMatterCrafter;
import com.vinterdo.deusexmachina.client.gui.GuiGrayMatterFabricator;
import com.vinterdo.deusexmachina.client.gui.GuiHeater;
import com.vinterdo.deusexmachina.inventory.ContainerBlastFurnace;
import com.vinterdo.deusexmachina.inventory.ContainerDeus;
import com.vinterdo.deusexmachina.inventory.ContainerEssenceMacerator;
import com.vinterdo.deusexmachina.inventory.ContainerEssenceProcessor;
import com.vinterdo.deusexmachina.inventory.ContainerGrayMatterCrafter;
import com.vinterdo.deusexmachina.inventory.ContainerGrayMatterFabricator;
import com.vinterdo.deusexmachina.inventory.ContainerHeater;
import com.vinterdo.deusexmachina.tileentity.TEBlastFurnaceMaster;
import com.vinterdo.deusexmachina.tileentity.TEDEM;

import cpw.mods.fml.common.network.IGuiHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class GuiHandler implements IGuiHandler
{
    public enum GuiIDs
    {
        ESSENCE_PROCESSOR, HEATER, ESSENCE_MACERATOR, BLAST_FURNACE, GRAY_MATTER_FABRICATOR, DEUS, GRAY_MATTER_CRAFTER;
    }

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (GuiIDs.values()[ID])
        {
            case ESSENCE_PROCESSOR:
                return new ContainerEssenceProcessor(player.inventory, (TEDEM) world.getTileEntity(x, y, z));
            case HEATER:
                return new ContainerHeater(player.inventory, (TEDEM) world.getTileEntity(x, y, z));
            case ESSENCE_MACERATOR:
                return new ContainerEssenceMacerator(player.inventory, (TEDEM) world.getTileEntity(x, y, z));
            case GRAY_MATTER_FABRICATOR:
                return new ContainerGrayMatterFabricator(player.inventory, (TEDEM) world.getTileEntity(x, y, z));
            case BLAST_FURNACE:
                return new ContainerBlastFurnace(player.inventory, (TEBlastFurnaceMaster) world.getTileEntity(x, y, z));
            case GRAY_MATTER_CRAFTER:
                return new ContainerGrayMatterCrafter(player.inventory, (TEDEM) world.getTileEntity(x, y, z));
            case DEUS:
                return new ContainerDeus(player.inventory, (TEDEM) world.getTileEntity(x, y, z));

        }

        throw new IllegalArgumentException("No gui with id " + ID);
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z)
    {
        switch (GuiIDs.values()[ID])
        {
            case ESSENCE_PROCESSOR:
                return new GuiEssenceProcessor(player.inventory, (TEDEM) world.getTileEntity(x, y, z));
            case HEATER:
                return new GuiHeater(player.inventory, (TEDEM) world.getTileEntity(x, y, z));
            case ESSENCE_MACERATOR:
                return new GuiEssenceMacerator(player.inventory, (TEDEM) world.getTileEntity(x, y, z));
            case GRAY_MATTER_FABRICATOR:
                return new GuiGrayMatterFabricator(player.inventory, (TEDEM) world.getTileEntity(x, y, z));
            case GRAY_MATTER_CRAFTER:
                return new GuiGrayMatterCrafter(player.inventory, (TEDEM) world.getTileEntity(x, y, z));
            case BLAST_FURNACE:
                return new GuiBlastFurnace(player.inventory, (TEDEM) world.getTileEntity(x, y, z));
            case DEUS:
                return new GuiDeus(player.inventory, (TEDEM) world.getTileEntity(x, y, z));

        }

        throw new IllegalArgumentException("No gui with id " + ID);
    }

}
