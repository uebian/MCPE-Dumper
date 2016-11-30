package com.MCAL.NativeAddonTools;

import android.app.Activity;
import android.widget.TextView;
import android.os.Bundle;
import android.view.*;
import android.content.*;
import android.net.*;

public class AboutActivity extends Activity
{
	@Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_activity);
    }
	
	public void githubPage(View view)
	{
		Intent intent = new Intent();        
        intent.setAction("android.intent.action.VIEW");    
        Uri content_url = Uri.parse("https://github.com/ModelPart/Native-Addon-Tools.git");   
        intent.setData(content_url);  
        startActivity(intent);
	}
	public void joinMCAL(View view)
	{
		joinQQGroup("hYhxG2RkDOOzaRnDIVdNWAM5aptKO4m8");
	}
	
	public boolean joinQQGroup(String key)
	{
		Intent intent = new Intent();
		intent.setData(Uri.parse("mqqopensdkapi://bizAgent/qm/qr?url=http%3A%2F%2Fqm.qq.com%2Fcgi-bin%2Fqm%2Fqr%3Ffrom%3Dapp%26p%3Dandroid%26k%3D" + key));
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		try
		{
			startActivity(intent);
			return true;
		}
		catch (Exception e)
		{
			return false;
		}
	}
}
