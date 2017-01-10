package com.mcal.MCPEDumper.nativeapi;
import java.util.*;
import java.util.regex.*;

public class Searcher
{
	public static Vector<MCPESymbol> search(String key)
	{
		Vector<MCPESymbol> returnValue=new Vector<MCPESymbol>();
		if (key == null || key.isEmpty() || key == "" || key == " ")
			return returnValue;

		for (MCPESymbol symbol:Dumper.symbols)
		{
			if (symbol.getDemangledName() != null && symbol.getDemangledName().indexOf(key) != -1)
			{
				returnValue.addElement(symbol);
			}
		}

		return returnValue;
	}

	public static Vector<MCPESymbol> searchWithPattern(String role)
	{
		Vector<MCPESymbol> returnValue=new Vector<MCPESymbol>();
		try
		{
			if (role == null || role.isEmpty() || role == "" || role == " ")
				return returnValue;
			Pattern p = Pattern.compile(role);

			for (MCPESymbol symbol:Dumper.symbols)
			{
				if (symbol.getDemangledName() != null)
				{
					Matcher m = p.matcher(symbol.getDemangledName());
					if (m.find())
						returnValue.addElement(symbol);
				}
			}
		}
		catch (Exception e)
		{
			return returnValue;
		}
		return returnValue;
	}
}
