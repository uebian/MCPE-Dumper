package com.mcal.MCPEDumper;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.graphics.*;
import android.view.View.*;
import com.gc.materialdesign.widgets.*;

public class FloatingButton
{
	public static int xPos=0;
	public static int yPos=0;
	public boolean isAdded = false;
	public WindowManager wm;
	public WindowManager.LayoutParams params; 
	public View floatView;

	Context context;

	FloatingButton(Context c)
	{
		context = c;
	}

	public void show()
	{  
		floatView = new View(context);
		floatView.setClickable(true);
		
		
		floatView.setBackgroundResource(R.drawable.box_pink);
		floatView.setOnClickListener(new View.OnClickListener()
			{

				@Override
				public void onClick(View p1)
				{
					FloatingMenu menu=new FloatingMenu(context);
					menu.show();
					FloatingButton.this.dismiss();
				}
				
			
		});
		wm = (WindowManager) context.getApplicationContext()  .getSystemService(Context.WINDOW_SERVICE);  
		params = new WindowManager.LayoutParams();  

	
		params.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;  
	
		params.format = PixelFormat.TRANSPARENT;


		params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL  
			| WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;    
		params.width = 50;  
		params.height = 50;  
		params.x = xPos;  
		params.y = yPos;  
  
		
		floatView.setOnTouchListener(new OnTouchListener() {  
				int lastX, lastY;  
				int paramX, paramY;  

				public boolean onTouch(View v, MotionEvent event)
				{  
					switch (event.getAction())
					{  
						case MotionEvent.ACTION_DOWN:  
							lastX = (int) event.getRawX();  
							lastY = (int) event.getRawY();  
							paramX = params.x;  
							paramY = params.y;  
							break;  
						case MotionEvent.ACTION_MOVE:  
							int dx = (int) event.getRawX() - lastX;  
							int dy = (int) event.getRawY() - lastY;  
							params.x = paramX + dx;  
							params.y = paramY + dy;    
							wm.updateViewLayout(floatView, params);  
							break;
					}
					FloatingButton.xPos=params.x;
					FloatingButton.yPos=params.y;
					return false;
				}  
			});  

		wm.addView(floatView, params);
		isAdded = true;
	}
	
	public void dismiss()
	{
		wm.removeView(floatView);
	}
}
