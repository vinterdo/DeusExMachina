package com.vinterdo.deusexmachina.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.minecraft.tileentity.TileEntity;

import com.vinterdo.deusexmachina.DeusExMachina;
import com.vinterdo.deusexmachina.proxy.CommonProxy;
import com.vinterdo.deusexmachina.reference.Reference;
import com.vinterdo.deusexmachina.tileentity.base.TEDEM;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;

@Sharable
public class DescriptionHandler extends SimpleChannelInboundHandler<FMLProxyPacket>
{
	public static final String CHANNEL = Reference.MOD_ID + ":Description";
	
	static 
	{
		NetworkRegistry.INSTANCE.newChannel(CHANNEL, new DescriptionHandler());
	}
	
	public static void init()
	{
		
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, FMLProxyPacket msg) throws Exception 
	{
		ByteBuf buf = msg.payload();
		int x, y, z;
		x = buf.readInt();
		y = buf.readInt();
		z = buf.readInt();
		
		TileEntity te = ((CommonProxy) DeusExMachina.proxy).getClientPlayer().worldObj.getTileEntity(x, y, z);
		if(te instanceof TEDEM)
		{
			((TEDEM)te).readFromPacket(buf);
		}
	}

}
