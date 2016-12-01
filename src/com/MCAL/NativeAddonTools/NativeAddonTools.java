package com.MCAL.NativeAddonTools;

public class NativeAddonTools
{
	public native static void generateMethodTable(String path,String out_demangled,String out_undemangled,boolean useLine);
	public native static boolean hasFile(String path);
	public native static String demangleName(String name);
	public native static void generateTemplate(String path,String projectName,String appName,String packageName,String targetMCPE,boolean useActivities,boolean useAssets);
}
