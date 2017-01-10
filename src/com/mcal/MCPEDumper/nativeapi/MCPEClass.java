package com.mcal.MCPEDumper.nativeapi;
import java.util.*;

public class MCPEClass
{
	String name;
	Vector<MCPESymbol> symbols;

	public MCPEClass(String name, Vector<MCPESymbol> symbols)
	{
		this.name = name;
		this.symbols = symbols;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setSymbols(Vector<MCPESymbol> symbols)
	{
		this.symbols = symbols;
	}

	public Vector<MCPESymbol> getSymbols()
	{
		return symbols;
	}
}
