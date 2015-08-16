package com.vinterdo.deusexmachina.tileentity;

import java.lang.reflect.Field;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;
import com.vinterdo.deusexmachina.helpers.NBTSaved;
import com.vinterdo.deusexmachina.network.DescriptionHandler;

import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.tileentity.TileEntity;

public class TEDEM extends TileEntity
{
	@Override
    public Packet getDescriptionPacket()
    {
        ByteBuf buf = Unpooled.buffer();
        buf.writeInt(xCoord);
        buf.writeInt(yCoord);
        buf.writeInt(zCoord);
        writeToPacket(buf);
        return new FMLProxyPacket(buf, DescriptionHandler.CHANNEL);
    }

    public void writeToPacket(ByteBuf buf)
    {

    }

    public void readFromPacket(ByteBuf buf)
    {

    }
    
    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);
        
        for(Field f : this.getClass().getFields())
        {
        	if(f.getAnnotation(NBTSaved.class) != null)
        	{
        		String id = f.getAnnotation(NBTSaved.class).name();
        		if(tag.getTag(id) == null) continue;
        		try 
        		{
					readFieldFromNBT(f, tag, id);
				} 
        		catch (IllegalArgumentException e) 
        		{
					e.printStackTrace();
				} 
        		catch (IllegalAccessException e) 
        		{
					e.printStackTrace();
				}
        	}
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        
        for(Field f : this.getClass().getFields())
        {
        	if(f.getAnnotation(NBTSaved.class) != null)
        	{
        		String id = f.getAnnotation(NBTSaved.class).name();
        		try 
        		{
					setTag(tag, f.get(this), id);
				} 
        		catch (IllegalArgumentException e) 
        		{
					e.printStackTrace();
				} 
        		catch (IllegalAccessException e) 
        		{
					e.printStackTrace();
				}
        	}
        }
    }
    
    private void readFieldFromNBT(Field f, NBTTagCompound tag, String id) throws IllegalArgumentException, IllegalAccessException
    {
    	byte type = tag.getTag(id).getId();
    	switch(type)
    	{
    	case 1:
    		f.setByte(this, tag.getByte(id));
    		break;
    	case 2:
    		f.setShort(this, tag.getShort(id));
    		break;
    	case 3:
    		f.setInt(this, tag.getInteger(id));
    		break;
    	case 4:
    		f.setLong(this, tag.getLong(id));
    		break;
    	case 5:
    		f.setFloat(this, tag.getFloat(id));
    		break;
    	case 6:
    		f.setDouble(this, tag.getDouble(id));
    		break;
    	case 7:
    		f.set(this, tag.getByteArray(id));
    		break;
    	case 8:
    		f.set(this, tag.getString(id));
    		break;
    	case 10:
    		f.set(this, tag.getCompoundTag(id));
    		break;
    	case 11:
    		f.set(this, tag.getIntArray(id));
    		break;
    	}
    	
    }
    
    private void setTag(NBTTagCompound tag, Object val, String id)
    {
    	if(val.getClass() == Byte.class)
    	{
    		tag.setByte(id, (Byte)val);
    		return;
    	}
    	if(val.getClass() == Short.class)
    	{
    		tag.setShort(id, (Short)val);
    		return;
    	}
    	if(val.getClass() == Integer.class)
    	{
    		tag.setInteger(id, (Integer)val);
    		return;
    	}
    	if(val.getClass() == Long.class)
    	{
    		tag.setLong(id, (Long)val);
    		return;
    	}
    	if(val.getClass() == Float.class)
    	{
    		tag.setFloat(id, (Float)val);
    		return;
    	}
    	if(val.getClass() == Double.class)
    	{
    		tag.setDouble(id, (Double)val);
    		return;
    	}
    	if(val.getClass() == byte[].class)
    	{
    		tag.setByteArray(id, (byte[])val);
    		return;
    	}
    	if(val.getClass() == String.class)
    	{
    		tag.setString(id, (String)val);
    		return;
    	}
    	if(val.getClass() == NBTTagCompound.class)
    	{
    		tag.setTag(id, (NBTTagCompound)val);
    		return;
    	}
    	if(val.getClass() == int[].class)
    	{
    		tag.setIntArray(id, (int[])val);
    		return;
    	}
    }

    
}
