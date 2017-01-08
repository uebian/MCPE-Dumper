package com.mcal.MCPEDumper;
import android.os.*;
import android.app.*;
import android.view.*;
import android.content.*;

public class MenuActivity extends Activity
{
	private String path;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.menu_activity);
		
		path=getIntent().getExtras().getString("filePath");
	}
	
	public void toNameDemangler(View view)
	{
		startActivity(new Intent(this,NameDemanglerActivity.class));
	}
	
	public void toNameMangler(View view)
	{
		startActivity(new Intent(this,NameManglerActivity.class));
	}
	
	public void showFloatingMenu(View view)
	{
		new FloatingButton(this,path).show();
	}
}
