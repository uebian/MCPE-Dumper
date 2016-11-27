package com.MCAL.NativeAddonTools;

import android.app.Activity;
import android.widget.TextView;
import android.os.Bundle;
import android.view.*;
import android.content.*;
import android.widget.*;
import android.app.*;
import android.content.res.*;

public class ChooseAddressActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
	EditText edit;
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.choose_activity);
		edit= (EditText)findViewById(R.id.chooseactivityEditText);
	}
	public void nextStep(View view)
	{
		new AlertDialog.Builder(this).setTitle(getString(R.string.running));
		if(edit.getText().toString().isEmpty())
		{
			new AlertDialog.Builder(this).setTitle(getString(R.string.title_error)).setMessage(getString(R.string.message_error_no_enter)).create().show();
		}
		else if(!edit.getText().toString().isEmpty()&&Decompiler.hasFile(edit.getText().toString()))
		{
			new AlertDialog.Builder(this).setTitle(getString(R.string.running)).setMessage(getString(R.string.message_running)).setNeutralButton(getString(R.string.dialog_continue),new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					Decompiler.decompile(ChooseAddressActivity.this.edit.getEditableText().toString());
					Intent intent=new Intent(ChooseAddressActivity.this,FinalActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
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
