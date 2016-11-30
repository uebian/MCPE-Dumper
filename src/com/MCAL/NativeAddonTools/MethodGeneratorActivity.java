package com.MCAL.NativeAddonTools;

import android.app.Activity;
import android.widget.TextView;
import android.os.Bundle;
import android.view.*;
import android.content.*;
import android.widget.*;
import android.app.*;
import android.content.res.*;
import android.os.*;

public class MethodGeneratorActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
	private EditText mcpeLibEdit;
	private EditText undemangledEdit;
	private EditText demangledEdit;
	private CheckBox useLines;
	
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.method_list_generator_activity);
		
		mcpeLibEdit= (EditText)findViewById(R.id.chooseactivityEditTextlibname);
		undemangledEdit= (EditText)findViewById(R.id.chooseactivityEditTextundemangledname);
		demangledEdit= (EditText)findViewById(R.id.chooseactivityEditTextdemangledname);
		useLines= (CheckBox)findViewById(R.id.chooseactivityCheckBoxUseLines);
		
		String storage=Environment.getExternalStorageDirectory().getPath();
		
		mcpeLibEdit.getText().append(Environment.getDataDirectory().getPath()+"/data/com.mojang.minecraftpe/lib/libminecraftpe.so");
		demangledEdit.getText().append(storage+"/MethodTableGenerator/demangled.txt");
		undemangledEdit.getText().append(storage+"/MethodTableGenerator/undemangled.txt");
		useLines.setChecked(false);
		
	}
	public void nextStep(View view)
	{
		new AlertDialog.Builder(this).setTitle(getString(R.string.running));
		if(mcpeLibEdit.getText().toString().isEmpty())
		{
			new AlertDialog.Builder(this).setTitle(getString(R.string.title_error)).setMessage(getString(R.string.message_error_no_enter)).create().show();
		}
		else if(!mcpeLibEdit.getText().toString().isEmpty()&&NativeAddonTools.hasFile(mcpeLibEdit.getText().toString()))
		{
			new AlertDialog.Builder(this).setTitle(getString(R.string.running)).setMessage(getString(R.string.message_running)).setNeutralButton(getString(R.string.dialog_continue),new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					String storage=Environment.getExternalStorageDirectory().getPath();
					NativeAddonTools.generateMethodTable(MethodGeneratorActivity.this.mcpeLibEdit.getEditableText().toString(),
									   demangledEdit.getText().toString().isEmpty()?storage+"/MethodTableGenerator/demangled.txt":demangledEdit.getText().toString(),
									   undemangledEdit.getText().toString().isEmpty()?storage+"/MethodTableGenerator/undemangled.txt":undemangledEdit.getText().toString(),
									   useLines.isChecked());
					Toast.makeText(MethodGeneratorActivity.this,getString(R.string.done),50).show();
					finish();
				}
			}).setNegativeButton(getString(R.string.dialog_cancel),new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					
				}
			}).create().show();
		}
		else
		{
			new AlertDialog.Builder(this).setTitle(getString(R.string.title_error)).setMessage(getString(R.string.message_error_file_not_found)).create().show();
		}
	}
}
