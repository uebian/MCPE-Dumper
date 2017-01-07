package com.mcal.MCPEDumper;

import android.os.*;
import android.app.*;

public class SaveActivity extends Activity
{
	private String[] savedFile;
	private String savePath;
	private String saveName;
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.save_activity);
		
		savedFile=getIntent().getExtras().getStringArray("savedFile");
		savePath=getIntent().getExtras().getString("savePath");
		saveName=getIntent().getExtras().getString("saveName");
	}
	
	
}
