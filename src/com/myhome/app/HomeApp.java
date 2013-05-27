package com.myhome.app;


import java.util.ArrayList;
import java.util.List;

import com.example.myhome.R;
import com.myhome.utils.AppParser;
import com.myhome.utils.WorkFlowParser;
import com.myhome.widgets.FloatingWindow;
import android.app.Application;
import android.content.Context;
import android.util.Log;


public class HomeApp extends Application{
	
	//private ArrayList<HashMap<String, Object>> workflow;
	private ArrayList<String> appList=new ArrayList<String>();
	private WorkFlowParser mParser;
	private ArrayList<AppParser> appParsers;
	private FloatingWindow floatingWindow;
	private Context context;
	
	@Override
	public void onCreate() {
		Log.v("myhome", "appstart");
		
		super.onCreate();
		
		context=getApplicationContext();
		
		//parse the xml files
		mParser=new WorkFlowParser(context, "main");
		appList=mParser.getAppQueue();
		Log.v("myhome", appList.toString());
		appParsers=new ArrayList<AppParser>();
		for (int i=0;i<appList.size();i++){
			appParsers.add(new AppParser(appList.get(i), context));
		}
        
		//set floating window
		setFloatingWindow();
		floatingWindow.display();		
	}
	
	public ArrayList<String> getAppList(){
		return appList;
	}
	
	public ArrayList<AppParser> getAppParsers(){
		return appParsers;
	}
	
	
	public void setFloatingWindow(){
		
		floatingWindow=new FloatingWindow(getApplicationContext());
		floatingWindow.setDefaultParams();
		floatingWindow.setView(R.layout.floating_window);
		
	}
	
	public FloatingWindow getFloatingWindow(){
		return floatingWindow;
	}


}
