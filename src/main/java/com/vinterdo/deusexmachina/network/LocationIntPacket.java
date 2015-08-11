package com.vinterdo.deusexmachina.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import io.netty.buffer.ByteBuf;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * MineChess
 * 
 * @author MineMaarten www.minemaarten.com
 * @license Lesser GNU Public License v3 (http://www.gnu.org/licenses/lgpl.html)
 */

public abstract class LocationIntPacket<REQ extends IMessage> extends AbstractPacket<REQ>
{
    public static final double PACKET_DISTANCE = 64D;
    protected int              x, y, z;

    public LocationIntPacket()
    {
    }

    public LocationIntPacket(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public void toBytes(ByteBuf buf)
    {
        buf.writeInt(x);
        buf.writeInt(y);
        buf.writeInt(z);
    }

    @Override
    public void fromBytes(ByteBuf buf)
    {
        x = buf.readInt();
        y = buf.readInt();
        z = buf.readInt();
    }

    public NetworkRegistry.TargetPoint getTargetPoint(World world)
    {
        return getTargetPoint(world, PACKET_DISTANCE);
    }

    public NetworkRegistry.TargetPoint getTargetPoint(World world, double updateDistance)
    {
        return new NetworkRegistry.TargetPoint(world.provider.dimensionId, x, y, z, updateDistance);
    }

    protected Block getBlock(World world)
    {
        return world.getBlock(x, y, z);
    }

    protected TileEntity getTileEntity(World world)
    {
        return world.getTileEntity(x, y, z);
    }
}