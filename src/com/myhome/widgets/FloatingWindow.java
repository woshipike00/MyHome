package com.myhome.widgets;

import com.example.myhome.R;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

public class FloatingWindow {
	
	private WindowManager windowManager;
	private WindowManager.LayoutParams params;
	private View windowView;
	private LayoutInflater layoutInflater;
	
	public FloatingWindow(Context context){
		windowManager=(WindowManager) context.getSystemService(context.WINDOW_SERVICE);
		layoutInflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		params=new LayoutParams();
	}
	
	public void setDefaultParams(){
		
		
		//set floating window to be global "TYPE_PHONE"
		params.type=LayoutParams.TYPE_PHONE;
		//background transparent
		params.format=PixelFormat.RGBA_8888;
		params.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
		params.gravity=Gravity.CENTER_VERTICAL | Gravity.RIGHT;
		params.x=0;
		params.y=0;
		params.width=WindowManager.LayoutParams.WRAP_CONTENT;
		params.height=WindowManager.LayoutParams.WRAP_CONTENT;

	}
	
	public void setView(int resource){
		View windowView=layoutInflater.inflate(resource, null);
		TextView tv=(TextView)windowView.findViewById(R.id.textView1);
		tv.setTextColor(Color.BLACK);
	}
	
	public void display(){
		windowManager.addView(windowView, params);
	}
	
	public void hide(){
		windowManager.removeView(windowView);
	}

}
