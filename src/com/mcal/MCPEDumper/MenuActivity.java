package com.mcal.MCPEDumper;
import android.os.*;
import android.app.*;
import android.view.*;
import android.content.*;
import com.mcal.MCPEDumper.nativeapi.*;
import com.mcal.MCPEDumper.util.*;
import com.gc.materialdesign.widgets.*;

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
	
	public void showFloatingMenu(View view)
	{
		new FloatingButton(this,path).show();
	}
	
	private void _saveSymbols()
	{
		String [] strings=new String[Dumper.symbols.size()];
		for(int i=0;i<Dumper.symbols.size();++i)
			strings[i]=Dumper.symbols.get(i).getName();

		FileSaver saver=new FileSaver(this,Environment.getExternalStorageDirectory().toString()+"/MCPEDumper/symbols/","Symbols.txt",strings);
		saver.save();
		
		String [] strings_=new String[Dumper.symbols.size()];
		for(int i=0;i<Dumper.symbols.size();++i)
			strings_[i]=Dumper.symbols.get(i).getDemangledName();
		FileSaver saver_=new FileSaver(this,Environment.getExternalStorageDirectory().toString()+"/MCPEDumper/symbols/","Symbols_demangled.txt",strings_);
		saver_.save();
		
		new SnackBar(this,"Done").show();
	}
	
	private com.gc.materialdesign.widgets.ProgressDialog mDialog;
	
	public void saveSymbols(View view)
	{
		mDialog=new com.gc.materialdesign.widgets.ProgressDialog(this,"Saving...");
		mDialog.show();
		
		new Thread()
		{
			public void run()
			{
				_saveSymbols();
				dismissDialog();
			}
		}.start();
	}
	
	public void dismissDialog()
	{
		if(mDialog==null||!mDialog.isShowing())
			return;
		mDialog.dismiss();
		mDialog=null;
	}
}
