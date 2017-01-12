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
	
	public MCPEClass(String name)
	{
		this.name = name;
		this.symbols = new Vector<MCPESymbol>();
	}
	

	public void setName(String name)
	{
		this.name = name;
	}
	
	public void addNewSymbol(MCPESymbol sym)
	{
		symbols.addElement(sym);
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
