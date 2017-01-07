package com.mcal.MCPEDumper.nativeapi;
import java.util.*;

public class MCPEVtable
{
	private String name=new String();
	private Vector<MCPESymbol> vtables=new Vector<MCPESymbol>();
	
	public MCPEVtable(String name, Vector<MCPESymbol> vtables)
	{
		this.name = name;
		this.vtables = vtables;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setVtables(Vector<MCPESymbol> vtables)
	{
		this.vtables = vtables;
	}

	public Vector<MCPESymbol> getVtables()
	{
		return vtables;
	}
}
