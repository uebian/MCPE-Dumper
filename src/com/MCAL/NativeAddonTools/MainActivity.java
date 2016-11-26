package com.MCAL.NativeAddonTools;

import android.app.Activity;
import android.widget.TextView;
import android.os.Bundle;
import android.view.*;
import android.content.*;


public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
		
		//decompile("/sdcard/libminecraftpe.so");
    }

	public native void decompile(String path);

	public void startCompile(View view)
	{
		Intent intent=new Intent(this,ChooseAddressActivity.class);
		startActivity(intent);
	}
	
    static
	{
        System.loadLibrary("nativeaddontools");
    }
}
