package com.mcal.MCPEDumper.nativeapi;
import java.util.*;
import com.mcal.MCPEDumper.vtable.*;

public class Dumper
{
	public static Vector<MCPESymbol> symbols=new Vector<MCPESymbol>();
	public static Vector<MCPEVtable> exploed=new Vector<MCPEVtable>();
	public static void readData(String path)
	{
		symbols.clear();
		for(int i=0;i<MCPEDumper.getSize();++i)
		{
			symbols.addElement(new MCPESymbol(MCPEDumper.getNameAt(i),MCPEDumper.getDemangledNameAt(i),MCPEDumper.getTypeAt(i),MCPEDumper.getBindAt(i)));
		}
	}
}
