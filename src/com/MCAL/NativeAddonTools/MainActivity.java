package com.MCAL.NativeAddonTools;

import android.app.Activity;
import android.widget.TextView;
import android.os.Bundle;
import android.view.*;
import android.content.*;
import android.net.*;


public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

	public void startCompile(View view)
	{
		Intent intent=new Intent(this,MethodGeneratorActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	public void exit(View view)
	{
		finish();
	}
	public void toAbout(View view)
	{
		Intent intent=new Intent(this,AboutActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	public void demangleName(View view)
	{
		Intent intent=new Intent(this,DemangleNameActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	
	
    static
	{
        System.loadLibrary("nativeaddontools");
    }
}
