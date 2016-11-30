package com.MCAL.NativeAddonTools;

public class NativeAddonTools
{
	public native static void generateMethodTable(String path,String out_demangled,String out_undemangled,boolean useLine);
	public native static boolean hasFile(String path);
	public native static String demangleName(String name);
}
