package com.mcal.MCPEDumper.nativeapi;

public class MCPEDumper
{
	public static native void load(String path);
	public static native boolean hasFile(String path);
	public static native String getNameAt(long pos);
	public static native short getTypeAt(long pos);
	public static native long getSize();
}
