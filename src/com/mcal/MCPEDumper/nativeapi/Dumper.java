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
		for(int i=0;i<MCPEDumper.getSize();++i)
		{
			String demangledName=MCPEDumper.getDemangledNameAt(i);
			if(demangledName==null||demangledName.isEmpty()||demangledName==""||demangledName==" ")
				demangledName=MCPEDumper.getNameAt(i);
			symbols.addElement(new MCPESymbol(MCPEDumper.getNameAt(i),demangledName,MCPEDumper.getTypeAt(i),MCPEDumper.getBindAt(i)));
		}
	}
}
