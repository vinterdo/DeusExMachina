package com.vinterdo.deusexmachina.inventory;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;

import com.vinterdo.deusexmachina.network.Synchronized;
import com.vinterdo.deusexmachina.utility.LogHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.tileentity.TileEntity;

public abstract class ContainerDEM<T extends TileEntity> extends Container
{
    protected final T                       te;
    protected final HashMap<Field, Integer> synced = new HashMap<Field, Integer>();

    protected ContainerDEM(T _te)
    {
        te = _te;
    }

    protected void addPlayerSlots(InventoryPlayer playerInv, int x, int y)
    {
        addPlayerSlots(playerInv, x, y, x, y);
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

    public void detectAndSendChanges()
    {
        for (Field f : te.getClass().getFields())
        {
            if (f.isAnnotationPresent(Synchronized.class))
            {
            	if(!synced.containsKey(f))
            	{
            		synced.put(f, -1);
            	}
                try
                {
                    if ((int)(Integer)f.get(te) != (int)synced.get(f))
                    {
                        for (ICrafting crafter : (List<ICrafting>) crafters)
                        {
                            crafter.sendProgressBarUpdate(this, f.getAnnotation(Synchronized.class).id(), (Integer) f.get(te));
                        }
                        synced.put(f, (Integer) f.get(te));
                        //LogHelper.info("sent packet to " + f.toString() + " with value " + f.get(te).toString());
                    }
                } catch (Exception e)
                {
                    LogHelper.error("Illegal access while syncing fields to " + f.getName());
                }
            }
        }

    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void updateProgressBar(int id, int value)
    {
        super.updateProgressBar(id, value);
        for (Field f : te.getClass().getFields())
        {
            if (f.isAnnotationPresent(Synchronized.class) && f.getAnnotation(Synchronized.class).id() == id)
            {
            	try {
					f.set(te, (Integer)value);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
            }
        }
    }

}
