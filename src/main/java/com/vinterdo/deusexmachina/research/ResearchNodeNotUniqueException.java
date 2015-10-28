package com.vinterdo.deusexmachina.research;

public class ResearchNodeNotUniqueException extends Exception
{
	String name;
	
	public ResearchNodeNotUniqueException(String name)
	{
		this.name = name;
	}
	
	@Override
	public String getMessage()
	{
		return "Duplicate recipe node name in research tree : " + name;
	}
}
