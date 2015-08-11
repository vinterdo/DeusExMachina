package com.vinterdo.deusexmachina.inventory;

import java.util.ArrayList;
import java.util.List;

import com.vinterdo.deusexmachina.network.NetworkHandler;
import com.vinterdo.deusexmachina.network.NetworkUtils;
import com.vinterdo.deusexmachina.network.PacketUpdateGui;
import com.vinterdo.deusexmachina.tileentity.TEDEM;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;

public abstract class ContainerDEM<Tile extends TEDEM> extends Container
{
    protected final Tile                 te;
    private final ArrayList<SyncedField> syncedFields = new ArrayList<SyncedField>();
    private boolean                      firstTick    = true;

    protected ContainerDEM(Tile te)
    {
        this.te = te;
        if (te != null)
            addSyncedFields(te);
    }

    protected void addPlayerSlots(InventoryPlayer playerInv, int x, int y)
    {
        addPlayerSlots(playerInv, x, y, x, y);
    }

    protected void addSyncedField(SyncedField field)
    {
        syncedFields.add(field);
        field.setLazy(false);
    }

    protected void addSyncedFields(Object annotatedObject)
    {
        ArrayList<SyncedField> fields = NetworkUtils.getSyncedFields(annotatedObject, GuiSynced.class);
        for (SyncedField field : fields)
            addSyncedField(field);
    }

    public void updateField(int index, Object value)
    {
        syncedFields.get(index).setValue(value);
        if (te != null)
            te.onGuiUpdate();
    }

    protected void addPlayerSlots(InventoryPlayer playerInv, int x, int y, int hx, int hy)
    {
        int i, j;
        for (i = 0; i < 3; ++i)
        {
            for (j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, x + j * 18, y + i * 18));
            }
        }

        for (i = 0; i < 9; ++i)
        {
            this.addSlotToContainer(new Slot(playerInv, i, hx + i * 18, hy + 58));
        }
    }

    @Override
    public void detectAndSendChanges()
    {
        super.detectAndSendChanges();
        for (int i = 0; i < syncedFields.size(); i++)
        {
            if (syncedFields.get(i).update() || firstTick)
            {
                sendToCrafters(new PacketUpdateGui(i, syncedFields.get(i)));
            }
        }
        firstTick = false;
    }

    protected void sendToCrafters(IMessage message)
    {
        for (ICrafting crafter : (List<ICrafting>) crafters)
        {
            if (crafter instanceof EntityPlayerMP)
            {
                NetworkHandler.sendTo(message, (EntityPlayerMP) crafter);
            }
        }
    }

}
