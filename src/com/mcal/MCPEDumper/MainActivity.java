package com.mcal.MCPEDumper;

import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import com.gc.materialdesign.widgets.*;
import com.mcal.MCPEDumper.util.*;

import com.gc.materialdesign.widgets.ProgressDialog;
import com.mcal.MCPEDumper.nativeapi.*;


public class MainActivity extends Activity
{

	private static final int FILE_SELECT_CODE = 0;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		
		FloatingMenu menu=new FloatingMenu(this);
		menu.createFloatView();
	}

	public void chooseSystem(View view)
	{
		Intent intent = MainActivity.this.getPackageManager().getLaunchIntentForPackage("com.mojang.minecraftpe");
		if(intent==null)
		{
			com.gc.materialdesign.widgets.Dialog dialog=new com.gc.materialdesign.widgets.Dialog(this,"Error","No MCPE Found");
			dialog.show();
			return;
		}
		if(MCPEDumper.hasFile("/data/data/com.mojang.minecraftpe/lib/libminecraftpe.so"))
			loadSo("/data/data/com.mojang.minecraftpe/lib/libminecraftpe.so");
		else
		{
			com.gc.materialdesign.widgets.Dialog dialog=new com.gc.materialdesign.widgets.Dialog(this,"Error","No libminecraftpe.so Found");
			dialog.show();
		}
	}
	
	
	public void chooseSdcard(View view)
	{
		showFileChooser("*/*");
	}
	
	private void showFileChooser(String type)
	{
		Intent intent = new Intent(Intent.ACTION_GET_CONTENT); 
		intent.setType(type); 
		intent.addCategory(Intent.CATEGORY_OPENABLE);
		try
		{
			startActivityForResult( Intent.createChooser(intent, "Select libminecraftpe.so to dump"), FILE_SELECT_CODE);
		} 
		catch(android.content.ActivityNotFoundException ex)
		{
			Toast.makeText(this, "Please install a File Manager.",  Toast.LENGTH_SHORT).show();
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	{
		switch(requestCode)
		{
			case FILE_SELECT_CODE:      
			if(resultCode == RESULT_OK)
			{  
				Uri uri = data.getData();
				String path = FileUtils.getPath(this, uri);
				if(path.endsWith(".so")&&MCPEDumper.hasFile(path))
					loadSo(path);
			}
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}
	
	private void loadSo(final String path)
	{
		showProgressDialog();
		new Thread()
		{
			public void run()
			{
				MCPEDumper.load(path);
				MainActivity.this.toClassesActivity();
			}
		}.start();
	}
	
	ProgressDialog dialog;
	
	public void showProgressDialog()
	{
		dialog=new ProgressDialog(MainActivity.this,"loading...");
		dialog.show();
	}
	public void dismissProgressDialog()
	{
		if(dialog!=null)
			dialog.dismiss();
		dialog=null;
	}
	public void toClassesActivity()
	{
		Intent intent=new Intent(MainActivity.this,SymbolsActivity.class);
		startActivity(intent);
		dismissProgressDialog();
	}
	
    static
	{
        System.loadLibrary("mcpedumper");
    }
}
