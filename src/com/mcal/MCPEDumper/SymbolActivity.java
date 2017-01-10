package com.mcal.MCPEDumper;
import android.app.*;
import android.os.*;
import android.widget.*;
import com.gc.materialdesign.views.*;
import android.transition.*;
import android.view.*;
import com.mcal.MCPEDumper.vtable.VtableDumper;
import com.mcal.MCPEDumper.nativeapi.*;
import android.content.*;
import com.mcal.MCPEDumper.vtable.*;

public class SymbolActivity extends Activity
{
	private String path;
	private String name;
	private int type;
	private String demangledName;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


		setContentView(R.layout.symbol_activity);
		type = getIntent().getExtras().getInt("type");
		name = getIntent().getExtras().getString("name");
		demangledName = getIntent().getExtras().getString("demangledName");
		path = getIntent().getExtras().getString("filePath");

		ImageView imageTitile=(ImageView)findViewById(R.id.symbolactivityImageView);
		if (type == 1)
			imageTitile.setImageResource(R.drawable.box_blue);
		else if (type == 2)
			imageTitile.setImageResource(R.drawable.box_red);
		else
			imageTitile.setImageResource(R.drawable.box_pink);

		TextView textName=(TextView)findViewById(R.id.symbolactivityTextViewName);
		textName.setText(name);

		TextView textDemangledName=(TextView)findViewById(R.id.symbolactivityTextViewDemangledName);
		textDemangledName.setText(demangledName);

		String arguments=new String();
		if (demangledName.indexOf("(") != -1 && demangledName.lastIndexOf(")") != -1)
			arguments = demangledName.substring(demangledName.indexOf("(") + 1, demangledName.lastIndexOf(")"));
		else
			arguments = "NULL";
		TextView textArguments=(TextView)findViewById(R.id.symbolactivityTextViewArguments);
		textArguments.setText(arguments);

		String symbolMainName=new String();
		if (demangledName.indexOf("(") != -1)
			symbolMainName = demangledName.substring(0, demangledName.indexOf("("));
		else
			symbolMainName = demangledName;
		String className=new String();
		if (symbolMainName.lastIndexOf("::") != -1)
			className = symbolMainName.substring(0, symbolMainName.lastIndexOf("::"));
		else if(symbolMainName.startsWith("vtable"))
			className=symbolMainName.substring(symbolMainName.lastIndexOf(" ")+1,symbolMainName.length());
		else
			className = "NULL";
		TextView textClassName=(TextView)findViewById(R.id.symbolactivityTextClass);
		textClassName.setText(className);

		String symbolName=new String();
		if (symbolMainName.lastIndexOf("::") != -1)
			symbolName = symbolMainName.substring(symbolMainName.lastIndexOf("::") + 2, symbolMainName.length());
		else
			symbolName = symbolMainName;

		TextView textSymbolName=(TextView)findViewById(R.id.symbolactivityTextViewSymbolMainName);
		textSymbolName.setText(symbolName);

		String typeName=Tables.symbol_type.get(type);
		
		TextView textTypeName=(TextView)findViewById(R.id.symbolactivityTextViewType);
		textTypeName.setText(typeName);
	
		if(name.startsWith("_ZTV"))
		{
			findViewById(R.id.symbolactivityButtonFloat).setVisibility(View.VISIBLE);
			findViewById(R.id.symbolactivityTextViewButtonFloat).setVisibility(View.VISIBLE);
		}
		
		if(className!="NULL")
		{
			findViewById(R.id.symbolactivityButtonFloatClass).setVisibility(View.VISIBLE);
			findViewById(R.id.symbolactivityTextViewButtonFloatClass).setVisibility(View.VISIBLE);
		}
	}
	
	public void toVtableActivity(View view)
	{
		showProgressDialog();
		new Thread()
		{
			public void run()
			{
				MCPEVtable vtable=VtableDumper.dump(SymbolActivity.this.path,SymbolActivity.this.name);
				if(vtable!=null)
					SymbolActivity.this.toVtableActivity_(vtable);
				dismissProgressDialog();
			}
		}.start();
	}
	
	public void toClassActivity(View view)
	{
		
	}
	
	public void toVtableActivity_(MCPEVtable vtable)
	{
		Bundle bundle=new Bundle();
		bundle.putString("name",name);
		bundle.putString("path",path);
		Dumper.exploed.addElement(vtable);
		Intent intent=new Intent(this,VtableActivity.class);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	
	com.gc.materialdesign.widgets.ProgressDialog dialog;

	public void showProgressDialog()
	{
		dialog=new com.gc.materialdesign.widgets.ProgressDialog(this,"loading...");
		dialog.show();
	}
	public void dismissProgressDialog()
	{
		if(dialog!=null)
			dialog.dismiss();
		dialog=null;
	}
}
