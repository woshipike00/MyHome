package com.example.activity;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;

public class WidgetInit extends Activity{
	
	private int appwidgetid;
	
	public void onCreate (Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setResult(RESULT_CANCELED);
		
		Intent intent=getIntent();
		Bundle extras=intent.getExtras();
		if(extras!=null)
		
			appwidgetid=extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
		
		if(appwidgetid==AppWidgetManager.INVALID_APPWIDGET_ID)
			finish();
		
		Intent result=new Intent();
		result.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appwidgetid);
		setResult(RESULT_OK,result);
		finish();
		
	}
	

}
