package com.MCAL.NativeAddonTools;

import android.app.Activity;
import android.widget.TextView;
import android.os.Bundle;
import android.view.*;
import android.content.*;
import android.net.*;
import android.widget.*;
import java.util.*;
import android.content.pm.*;
import android.os.*;


public class GenTemplateActivity extends Activity
{
	private Spinner spinner;
	private EditText projectName;
	private EditText appName;
	private EditText mcpeVersionName;
	private EditText packageName;
	private com.gc.materialdesign.views.CheckBox useActivity;
	private com.gc.materialdesign.views.CheckBox useAssets;
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gen_template_activity);
		
		spinner=(Spinner)findViewById(R.id.gentemplateactivitySpinner);
		projectName=(EditText)findViewById(R.id.genactivityprojectName);
		appName=(EditText)findViewById(R.id.genactivityprojectAppName);
		mcpeVersionName=(EditText)findViewById(R.id.genactivitytargetMCPE);
		packageName=(EditText)findViewById(R.id.genactivityprojectPackage);
		useActivity=(com.gc.materialdesign.views.CheckBox)findViewById(R.id.genactivityuseJavaCode);
		useAssets=(com.gc.materialdesign.views.CheckBox)findViewById(R.id.genactivityuseAssets);
		
		projectName.getText().append("MyNativeAddon");
		appName.getText().append("My Native-Addon");
		mcpeVersionName.getText().append(getMCPEVersionName());
		packageName.getText().append("com.MCAL.MyNativeAddon");
    }
	
	public void nextStep(View view)
	{
		NativeAddonTools.generateTemplate(Environment.getExternalStorageDirectory().getPath()+"AppProjects",projectName.getText().toString(),appName.getText().toString(),packageName.getText().toString(),mcpeVersionName.getText().toString(),useActivity.isCheck(),useAssets.isCheck());
	}
	
	private String getMCPEVersionName()
	{
		try
		{
        	PackageInfo packageInfo = getPackageManager().getPackageInfo("com.mojang.minecraftpe",PackageManager.DONT_KILL_APP);
			return new String(packageInfo.versionName);
		}
		catch(Exception e)
		{
			
		}
		return "0.0.0";
	}
}
