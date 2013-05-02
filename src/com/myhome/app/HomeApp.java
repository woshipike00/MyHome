package com.myhome.app;


import java.util.ArrayList;
import java.util.HashMap;
import com.example.myhome.R;
import com.myhome.widgets.FloatingWindow;
import android.app.Application;
import android.util.Log;


public class HomeApp extends Application{
	
	private ArrayList<HashMap<String, Object>> workflow;
	private FloatingWindow floatingWindow;
	
	@Override
	public void onCreate() {
		Log.v("myhome", "appstart");
		
		super.onCreate();
		
        workflow=new ArrayList<HashMap<String,Object>>();
        
		//set floating window
		setFloatingWindow();
		floatingWindow.display();		
	}

	
	public ArrayList<HashMap<String, Object>> getWorkFlow(){
		return workflow;
	}
	
	
	public void setworkflow(){
		
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
