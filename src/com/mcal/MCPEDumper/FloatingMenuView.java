package com.mcal.MCPEDumper;
import android.content.*;
import android.view.*;
import android.widget.*;
import com.gc.materialdesign.views.*;
import com.mcal.MCPEDumper.nativeapi.*;
import java.util.*;

public class FloatingMenuView extends RelativeLayout
{
	FloatingMenu menu;
	Context context;
	EditText editText;
	TextView text;
	
	FloatingMenuView(Context c,FloatingMenu menu)
	{
		super(c);
		this.menu=menu;
		this.context=c;
		
		View view = LayoutInflater.from(c).inflate(R.layout.floating_menu, null); 
		ButtonFlat imageButtonClose=(ButtonFlat)view.findViewById(R.id.floatingmenuButtonClose);
		imageButtonClose.setOnClickListener(new View.OnClickListener()
			{

				@Override
				public void onClick(View p1)
				{
					FloatingMenuView.this.menu.dismiss();
					new FloatingButton(FloatingMenuView.this.context).show();
				}

		});
		ButtonFlat imageButtonHide=(ButtonFlat)view.findViewById(R.id.floatingmenuButtonHide);
		imageButtonHide.setOnClickListener(new View.OnClickListener()
			{

				@Override
				public void onClick(View p1)
				{
					FloatingMenuView.this.menu.dismiss();
				}

			});
		editText=(EditText)view.findViewById(R.id.floatingmenuEditText);
		
		ButtonFlat imageButtonClear=(ButtonFlat)view.findViewById(R.id.floatingmenuButtonClear);
		imageButtonClear.setOnClickListener(new View.OnClickListener()
			{

				@Override
				public void onClick(View p1)
				{
					FloatingMenuView.this.editText.getText().clear();
				}

			});
		ButtonFlat imageButtonOpen=(ButtonFlat)view.findViewById(R.id.floatingmenuButtonOpen);
		imageButtonOpen.setOnClickListener(new View.OnClickListener()
			{

				@Override
				public void onClick(View p1)
				{
					FloatingMenuView.this.context.startActivity(new Intent(FloatingMenuView.this.context,SymbolsActivity.class));
				}

			});
		ButtonFlat imageButtonSearch=(ButtonFlat)view.findViewById(R.id.floatingmenuButtonSearch);
		imageButtonSearch.setOnClickListener(new View.OnClickListener()
			{

				@Override
				public void onClick(View p1)
				{
					search(FloatingMenuView.this.editText.getText().toString());
				}

			});
		text=(TextView)view.findViewById(R.id.floatingmenuTextView);
		this.addView(view);
	}
	
	private void search(String name)
	{
		try
		{
			String localText = new String();
			Vector<MCPESymbol> symbols=new Vector<MCPESymbol>();
			symbols=Searcher.search(name);
			for (MCPESymbol symbol:symbols)
				localText = localText + symbol.getDemangledName() + "\n";
			text.setText(localText);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
