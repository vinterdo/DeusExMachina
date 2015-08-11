package com.vinterdo.deusexmachina.network;

import com.vinterdo.deusexmachina.inventory.ContainerDEM;
import com.vinterdo.deusexmachina.inventory.SyncedField;
import com.vinterdo.deusexmachina.inventory.SyncedField.SyncedBoolean;
import com.vinterdo.deusexmachina.inventory.SyncedField.SyncedDouble;
import com.vinterdo.deusexmachina.inventory.SyncedField.SyncedEnum;
import com.vinterdo.deusexmachina.inventory.SyncedField.SyncedFloat;
import com.vinterdo.deusexmachina.inventory.SyncedField.SyncedFluidTank;
import com.vinterdo.deusexmachina.inventory.SyncedField.SyncedInt;
import com.vinterdo.deusexmachina.inventory.SyncedField.SyncedItemStack;
import com.vinterdo.deusexmachina.inventory.SyncedField.SyncedString;

import cpw.mods.fml.common.network.ByteBufUtils;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public class PacketUpdateGui extends AbstractPacket<PacketUpdateGui>
{
    private int    syncId;
    private Object value;
    private byte   type;

    public PacketUpdateGui()
    {
    }

    public PacketUpdateGui(int syncId, SyncedField syncField)
    {
        this.syncId = syncId;
        value = syncField.getValue();
        type = getType(syncField);
    }

    public static byte getType(SyncedField syncedField)
    {
        if (syncedField instanceof SyncedInt)
            return 0;
        else if (syncedField instanceof SyncedFloat)
            return 1;
        else if (syncedField instanceof SyncedDouble)
            return 2;
        else if (syncedField instanceof SyncedBoolean)
            return 3;
        else if (syncedField instanceof SyncedString)
            return 4;
        else if (syncedField instanceof SyncedEnum)
            return 5;
        else if (syncedField instanceof SyncedItemStack)
            return 6;
        else if (syncedField instanceof SyncedFluidTank)
            return 7;
        else
        {
            throw new IllegalArgumentException("Invalid sync type! " + syncedField);
        }
    }

    public static Object readField(ByteBuf buf, int type)
    {
        switch (type)
        {
            case 0:
                return buf.readInt();
            case 1:
                return buf.readFloat();
            case 2:
                return buf.readDouble();
            case 3:
                return buf.readBoolean();
            case 4:
                return ByteBufUtils.readUTF8String(buf);
            case 5:
                return buf.readByte();
            case 6:
                return ByteBufUtils.readItemStack(buf);
            case 7:
                if (!buf.readBoolean())
                    return null;
                return new FluidStack(FluidRegistry.getFluid(ByteBufUtils.readUTF8String(buf)), buf.readInt(),
                        ByteBufUtils.readTag(buf));
        }
        throw new IllegalArgumentException("Invalid sync type! " + type);
    }

    public static void writeField(ByteBuf buf, Object value, int type)
    {
        switch (type)
        {
            case 0:
                buf.writeInt((Integer) value);
                break;
            case 1:
                buf.writeFloat((Float) value);
                break;
            case 2:
                buf.writeDouble((Double) value);
                break;
            case 3:
                buf.writeBoolean((Boolean) value);
                break;
            case 4:
                ByteBufUtils.writeUTF8String(buf, (String) value);
                break;
            case 5:
                buf.writeByte((Byte) value);
                break;
            case 6:
                ByteBufUtils.writeItemStack(buf, (ItemStack) value);
                break;
            case 7:
                buf.writeBoolean(value != null);
                if (value != null)
                {
                    FluidStack stack = (FluidStack) value;
                    ByteBufUtils.writeUTF8String(buf, stack.getFluid().getName());
                    buf.writeInt(stack.amount);
                    ByteBufUtils.writeTag(buf, stack.tag);
                }
                break;
        }
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        syncId = buf.readInt();
        type = buf.readByte();
        value = readField(buf, type);
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(syncId);
        buf.writeByte(type);
        writeField(buf, value, type);
    }

    @Override
    public void handleClientSide(PacketUpdateGui message, EntityPlayer player)
    {
        Container container = player.openContainer;
        if (container instanceof ContainerDEM)
        {
            ((ContainerDEM) container).updateField(message.syncId, message.value);
        }
    }

    @Override
    public void handleServerSide(PacketUpdateGui message, EntityPlayer player)
    {
    }

}