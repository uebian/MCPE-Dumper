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
	private com.gc.materialdesign.views.CheckBox useLines;
	
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.method_list_generator_activity);
		
		mcpeLibEdit= (EditText)findViewById(R.id.chooseactivityEditTextlibname);
		undemangledEdit= (EditText)findViewById(R.id.chooseactivityEditTextundemangledname);
		demangledEdit= (EditText)findViewById(R.id.chooseactivityEditTextdemangledname);
		useLines= (com.gc.materialdesign.views.CheckBox)findViewById(R.id.chooseactivityCheckBoxUseLines);
		
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
			new com.gc.materialdesign.widgets.Dialog(this,getString(R.string.title_error),getString(R.string.message_error_no_enter)).show();
		}
		else if(!mcpeLibEdit.getText().toString().isEmpty()&&NativeAddonTools.hasFile(mcpeLibEdit.getText().toString()))
		{
			com.gc.materialdesign.widgets.Dialog dialog=new com.gc.materialdesign.widgets.Dialog(this,getString(R.string.running),getString(R.string.message_running));
			dialog.addButtonAccept(getString(R.string.dialog_continue),new View.OnClickListener()
				{

					@Override
					public void onClick(View p1)
					{
						String storage=Environment.getExternalStorageDirectory().getPath();
						NativeAddonTools.generateMethodTable(MethodGeneratorActivity.this.mcpeLibEdit.getEditableText().toString(),
															 demangledEdit.getText().toString().isEmpty()?storage+"/MethodTableGenerator/demangled.txt":demangledEdit.getText().toString(),
															 undemangledEdit.getText().toString().isEmpty()?storage+"/MethodTableGenerator/undemangled.txt":undemangledEdit.getText().toString(),
															 useLines.isCheck());
						com.gc.materialdesign.widgets.SnackBar bar=new com.gc.materialdesign.widgets.SnackBar(MethodGeneratorActivity.this,getString(R.string.done));
						bar.show();
						finish();
					}
				});
			dialog.addButtonCancel(getString(R.string.dialog_cancel));
			dialog.show();
		}
		else
		{
			new com.gc.materialdesign.widgets.Dialog(this,getString(R.string.title_error),getString(R.string.message_error_file_not_found)).show();
		}
	}
}
