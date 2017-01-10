package com.mcal.MCPEDumper.nativeapi;
import java.util.*;
import com.mcal.MCPEDumper.vtable.*;

public class Dumper
{
	public static Vector<MCPESymbol> symbols=new Vector<MCPESymbol>();
	public static Vector<MCPEVtable> exploed=new Vector<MCPEVtable>();
	public static Vector<MCPEClass> classes=new Vector<MCPEClass>();

	public static void readData(String path)
	{
		symbols.clear();
		exploed.clear();
		classes.clear();
		for (int i=0;i < MCPEDumper.getSize();++i)
		{
			String demangledName=MCPEDumper.getDemangledNameAt(i);
			if (demangledName == null || demangledName.isEmpty() || demangledName == "" || demangledName == " ")
				demangledName = MCPEDumper.getNameAt(i);
			symbols.addElement(new MCPESymbol(MCPEDumper.getNameAt(i), demangledName, MCPEDumper.getTypeAt(i), MCPEDumper.getBindAt(i)));
		}


	}

	public static boolean hasClass(String name)
	{
		String symbolMainName=new String();
		if (name.indexOf("(") != -1)
			symbolMainName = name.substring(0, name.indexOf("("));
		else
			symbolMainName = name;

		if (symbolMainName.lastIndexOf("::") != -1)
			return true;
		else if (symbolMainName.startsWith("vtable"))
			return true;
		return false;
	}

	public static String getClassName(String demangledName)
	{
		String symbolMainName=new String();
		if (demangledName.indexOf("(") != -1)
			symbolMainName = demangledName.substring(0, demangledName.indexOf("("));
		else
			symbolMainName = demangledName;

		if (symbolMainName.lastIndexOf("::") != -1)
			return symbolMainName.substring(0, symbolMainName.lastIndexOf("::"));
		else if (symbolMainName.startsWith("vtable"))
			return symbolMainName.substring(symbolMainName.lastIndexOf(" ") + 1, symbolMainName.length());
		else
			return "NULL";
	}
}
