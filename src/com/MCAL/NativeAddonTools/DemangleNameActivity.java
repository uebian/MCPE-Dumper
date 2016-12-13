package com.MCAL.NativeAddonTools;

import android.app.Activity;
import android.widget.TextView;
import android.os.Bundle;
import android.view.*;
import android.content.*;
import android.net.*;
import android.widget.*;


public class DemangleNameActivity extends Activity
{
	private EditText editText;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demangle_name_activity);
		
		editText=(EditText)findViewById(R.id.demanglenameactivityEditText);
    }
	
	public void convert(View view)
	{
		if(editText.getText().toString().isEmpty())
			return;
		String newName=NativeAddonTools.demangleName(editText.getText().toString());
		editText.getText().clear();
		editText.getText().append(newName);
	}
	public void clear(View view)
	{
		editText.getText().clear();
	}
	public void copy(View view)
	{
		ClipboardManager cmb = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);  
		cmb.setText(editText.getText().toString());
		com.gc.materialdesign.widgets.SnackBar bar=new com.gc.materialdesign.widgets.SnackBar(this,getString(R.string.done));
		bar.show();
	}
}
