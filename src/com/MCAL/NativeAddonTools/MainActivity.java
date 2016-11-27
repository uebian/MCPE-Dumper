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
		Intent intent=new Intent(this,ChooseAddressActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(intent);
	}
	public void githubPage(View view)
	{
		Intent intent = new Intent();        
        intent.setAction("android.intent.action.VIEW");    
        Uri content_url = Uri.parse("https://github.com/ModelPart/MCPELib-Decompiler.git");   
        intent.setData(content_url);  
        startActivity(intent);
	}
	public void exit(View view)
	{
		finish();
	}
	
	
    static
	{
        System.loadLibrary("nativeaddontools");
    }
}
