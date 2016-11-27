package com.MCAL.NativeAddonTools;

import android.app.Activity;
import android.widget.TextView;
import android.os.Bundle;
import android.view.*;
import android.content.*;
import android.net.*;


public class FinalActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.final_activity);
    }
	public void exit(View view)
	{
		System.exit(0);
	}
	public void githubPage(View view)
	{
		Intent intent = new Intent();        
        intent.setAction("android.intent.action.VIEW");    
        Uri content_url = Uri.parse("https://github.com/ModelPart/MCPELib-Decompiler.git");   
        intent.setData(content_url);  
        startActivity(intent);
	}
}
