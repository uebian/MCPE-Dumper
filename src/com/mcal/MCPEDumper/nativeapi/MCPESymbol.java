package com.mcal.MCPEDumper.nativeapi;

public class MCPESymbol
{
	private String name;
	private String demangledName;
	private int type;
	private int bind;
	
	public MCPESymbol(String name,String demangledName,int type,int bind)
	{
		this.type=type;
		this.demangledName=demangledName;
		this.name=name;
		this.bind=bind;
	}

	public void setDemangledName(String demangledName)
	{
		this.demangledName = demangledName;
	}

	public String getDemangledName()
	{
		return demangledName;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setType(int type)
	{
		this.type = type;
	}

	public int getType()
	{
		return type;
	}
	
	public void setBind(int type)
	{
		this.type = type;
	}

	public int getBind()
	{
		return type;
	}
}
