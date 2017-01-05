package com.mcal.MCPEDumper;

import android.app.*;
import android.os.*;
import java.util.*;
import android.widget.*;
import android.view.*;
import android.content.*;
import com.mcal.MCPEDumper.nativeapi.*;
import com.gc.materialdesign.widgets.*;

public class SymbolsActivity extends Activity
{
    /** Called when the activity is first created. */
	private ListView list; 
    private List<Map<String, Object>> data; 
    @Override 
    public void onCreate(Bundle savedInstanceState)
	{ 
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.classes_activity);
        list = (ListView)findViewById(R.id.classes_activity_list_view); 
		data = getData();
		ClassesAdapter adapter = new ClassesAdapter(this);
		list.setAdapter(adapter);
    }

    private List<Map<String, Object>> getData() 
    { 
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(); 
        Map<String, Object> map;
        for (long i=0;i < MCPEDumper.getSize();++i)
        { 
            map = new HashMap<String, Object>(); 
			if(MCPEDumper.getTypeAt(i)==1)
				map.put("img", R.drawable.box_blue); 
			else if(MCPEDumper.getTypeAt(i)==2)
				map.put("img", R.drawable.box_red);
			else map.put("img", R.drawable.box_pink);
            map.put("title", MCPEDumper.getDemangledNameAt(i)); 
            map.put("info", MCPEDumper.getNameAt(i)); 
            list.add(map); 
        } 
        return list; 
    } 
 
    static class ViewHolder 
    { 
		public ImageView img; 
		public TextView title; 
		public TextView info; 
    }

    public class ClassesAdapter extends BaseAdapter 
    {     
		private LayoutInflater mInflater = null; 
		private ClassesAdapter(Context context) 
		{ 
			this.mInflater = LayoutInflater.from(context); 
		} 

		@Override 
		public int getCount()
		{ 
			return data.size(); 
		} 

		@Override 
		public Object getItem(int position)
		{ 
			return position; 
		} 

		@Override 
		public long getItemId(int position)
		{  
			return position; 
		} 
 
		@Override 
		public View getView(int position, View convertView, ViewGroup parent)
		{ 
			ViewHolder holder = null; 
			
			if (convertView == null) 
			{ 
				holder = new ViewHolder(); 
				convertView = mInflater.inflate(R.layout.classes_activity_list_item, null); 
				holder.img = (ImageView)convertView.findViewById(R.id.img); 
				holder.title = (TextView)convertView.findViewById(R.id.classesactivitylistitemTextViewtop); 
				holder.info = (TextView)convertView.findViewById(R.id.classesactivitylistitemTextViewbottom); 
				convertView.setTag(holder); 
			}
			else 
			{ 
				holder = (ViewHolder)convertView.getTag(); 
			} 
			holder.img.setBackgroundResource((Integer)data.get(position).get("img")); 
			holder.title.setText((String)data.get(position).get("title")); 
			holder.info.setText((String)data.get(position).get("info")); 

			return convertView; 
		} 
    }
	
	SnackBar bar;

	@Override
	public void onBackPressed()
	{
		bar=new SnackBar(this,"Press again to exit");
		bar.show();
		bar.setDismissTimer(2500);
		bar.setOnBackPressedListener(new SnackBar.OnBackPressedListener()
			{

				@Override
				public void onBackPressed()
				{
					SymbolsActivity.this.finish();
				}


			});
	} 
}
