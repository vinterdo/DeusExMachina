package com.vinterdo.deusexmachina.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.world.World;

public class NetworkHandler
{

    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel("PneumaticCraft");
    private static int                       discriminant;

    /*
     * The integer is the ID of the message, the Side is the side this message
     * will be handled (received) on!
     */
    public static void init()
    {
        new DescPacketHandler();

        INSTANCE.registerMessage(PacketDescription.class, PacketDescription.class, discriminant++, Side.CLIENT);
        INSTANCE.registerMessage(PacketUpdateGui.class, PacketUpdateGui.class, discriminant++, Side.CLIENT);

    }

    /*
     * public static void INSTANCE.registerMessage(Class<? extends
     * AbstractPacket<? extends IMessage>> clazz){
     * INSTANCE.registerMessage(clazz, clazz, discriminant++, Side.SERVER,
     * discriminant++, Side.SERVER); }
     */

    public static void sendToAll(IMessage message)
    {
        INSTANCE.sendToAll(message);
    }

    public static void sendTo(IMessage message, EntityPlayerMP player)
    {
        INSTANCE.sendTo(message, player);
    }

    public static void sendToAllAround(LocationIntPacket message, World world, double distance)
    {
        sendToAllAround(message, message.getTargetPoint(world, distance));
    }

    public static void sendToAllAround(LocationIntPacket message, World world)
    {
        sendToAllAround(message, message.getTargetPoint(world));
    }

    public static void sendToAllAround(LocationDoublePacket message, World world)
    {
        sendToAllAround(message, message.getTargetPoint(world));
    }

    public static void sendToAllAround(IMessage message, NetworkRegistry.TargetPoint point)
    {
        INSTANCE.sendToAllAround(message, point);
    }

    public static void sendToDimension(IMessage message, int dimensionId)
    {
        INSTANCE.sendToDimension(message, dimensionId);
    }

    public static void sendToServer(IMessage message)
    {
        INSTANCE.sendToServer(message);
    }
}