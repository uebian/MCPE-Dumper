package com.mcal.MCPEDumper;
import android.app.*;
import android.os.*;
import android.widget.*;
import com.mcal.MCPEDumper.nativeapi.*;
import android.view.*;

public class NameManglerActivity extends Activity
{
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.name_mangler_activity);
		int width = getWindowManager().getDefaultDisplay().getWidth();

		EditText editText1=(EditText)findViewById(R.id.namemangleractivityEditText1);
		EditText editText2=(EditText)findViewById(R.id.namemangleractivityEditText2);

		ViewGroup.LayoutParams params1=editText1.getLayoutParams();
		params1.width=width/2-1;
		editText1.setLayoutParams(params1);

		ViewGroup.LayoutParams params2=editText2.getLayoutParams();
		params2.width=width/2-1;
		editText2.setLayoutParams(params2);
	}

	public void mangle(View view)
	{
		EditText editText1=(EditText)findViewById(R.id.namemangleractivityEditText1);
		EditText editText2=(EditText)findViewById(R.id.namemangleractivityEditText2);
		if(editText1.getText()==null||editText1.getText().toString()==null)
			return;
		String toName=MCPEDumper.mangle(editText1.getText().toString());
		editText2.getText().clear();
		editText2.getText().append(toName);
	}
}
