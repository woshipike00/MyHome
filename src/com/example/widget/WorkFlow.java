package com.example.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.util.Log;

public class WorkFlow extends AppWidgetProvider{
	
	public void onUpdate(Context context, AppWidgetManager appwidgetmanager, int[] widgetids) {
		int N=widgetids.length;
		for(int i=0;i<N;i++){
			Log.v("widget", "widget update: "+widgetids[i]);
		}
	}
	
	public void onDeleted(Context context, int[] appWidgetIds) {  
	        // TODO Auto-generated method stub  
	        final int N = appWidgetIds.length;  
	        for (int i = 0; i < N; i++) {  
	            int appWidgetId = appWidgetIds[i];  
	            Log.v("widget", "this is [" + appWidgetId + "] onDelete!");  
	        }  
	 }  

}
