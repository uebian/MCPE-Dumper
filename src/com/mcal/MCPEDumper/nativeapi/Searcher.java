package com.mcal.MCPEDumper.nativeapi;
import java.util.*;

public class Searcher
{
	public static Vector<MCPESymbol> search(String key)
	{
		Vector<MCPESymbol> returnValue=new Vector<MCPESymbol>();
		if(key==null||key.isEmpty()||key==""||key==" ")
			return returnValue;
		try
		{
			for (MCPESymbol symbol:Dumper.symbols)
			{
				if (symbol.getDemangledName()!=null&&symbol.getDemangledName().indexOf(key) != -1)
				{
					returnValue.addElement(symbol);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return returnValue;
	}
	
	public static Vector<MCPESymbol> searchClass(String key,boolean allChar,boolean size)
	{
		return null;
	}
}
