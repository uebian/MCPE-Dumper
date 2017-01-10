package com.mcal.MCPEDumper.util;
import com.mcal.MCPEDumper.nativeapi.*;
import java.util.*;

public class HeaderGenerator
{
	private MCPEClass mcpeClass;
	private MCPEVtable vtable;
	
	public HeaderGenerator(MCPEClass mcpeClass,MCPEVtable vtable)
	{
		this.mcpeClass=mcpeClass;
		this.vtable=vtable;
	}
	
	public String[] generate()
	{
		try
		{
			Vector<String> lines=new Vector<String>();
			
			lines.add("#pragma once\n");
			lines.add("\n");
			lines.add("class "+mcpeClass.getName()+"\n");
			lines.add("{\n");
			lines.add("public:\n");
			
			lines.add("	//Methods\n");
			if(getMethods()!=null)
			{
				for(MCPESymbol symbol:getObjects())
				{
					String mname=getMethodDefinition(symbol);
					lines.add("	"+mname+"\n");
				}
			}
			lines.add("public:\n");
			lines.add("	//Objects\n");
			if(getObjects()!=null)
			{
				for(MCPESymbol symbol:getObjects())
				{
					String mname=getObjectDefinition(symbol);
					lines.add("	"+mname+"\n");
				}
			}
			return (String[])lines.toArray();
		}
		catch(Exception e){}
		return null;
	}
	
	private static String getObjectDefinition(MCPESymbol symbol)
	{
		return "UNKNOW"+symbol.getName();
	}
	
	private static String getMethodDefinition(MCPESymbol symbol)
	{
		return "ERROR"+symbol.getName();
	}
	
	private MCPESymbol[] getObjects()
	{
		return null;
	}

	private MCPESymbol[] getMethods()
	{
		return null;
	}
}
