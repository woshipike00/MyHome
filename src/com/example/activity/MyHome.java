package com.example.activity;

import com.example.myhome.R;

import android.os.Bundle;
import android.R.string;
import android.app.Activity;
import android.appwidget.AppWidgetHost;
import android.appwidget.AppWidgetHostView;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProviderInfo;
import android.view.Menu;
import android.widget.LinearLayout;

public class MyHome extends Activity {
	
	private final int HOST_ID=1000;
	private LinearLayout llayout;
	private AppWidgetHost mAppWidgetHost;
	private AppWidgetManager appWidgetManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_home);
        llayout = (LinearLayout)findViewById(R.id.linearlayout) ;  
        
        mAppWidgetHost = new AppWidgetHost(MyHome.this, HOST_ID) ;   
          
        //为了保证AppWidget的及时更新 ， 必须在Activity的onCreate/onStar方法调用该方法  

        mAppWidgetHost.startListening() ;  
          
        //获得AppWidgetManager对象  
        appWidgetManager = AppWidgetManager.getInstance(MyHome.this) ;  
        
        addWidget();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_home, menu);
		return true;
	}
	
	public void addWidget(){
        int newAppWidgetId = mAppWidgetHost.allocateAppWidgetId() ;  
        AppWidgetProviderInfo appWidgetProviderInfo;  
        
        //AppWidgetHostView hostView = mAppWidgetHost.createView(MyHome.this, newAppWidgetId, appWidgetProviderInfo);  
                  
        
        //添加至LinearLayout父视图中  
        //llayout.addView(hostView) ;      
		
	}

}
