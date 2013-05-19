package com.myhome.widgets;

import com.example.myhome.R;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

public class FloatingWindow {
	
	private WindowManager windowManager;
	private WindowManager.LayoutParams params;
	private View windowView;
	private TextView mTextView;
	private ImageView mImageView;
	private LayoutInflater layoutInflater;
	
	public FloatingWindow(Context context){
		windowManager=(WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		params=new LayoutParams();
	}
	
	public void setDefaultParams(){
		
		
		//set floating window to be global "TYPE_PHONE"
		params.type=LayoutParams.TYPE_PHONE;
		//background transparent
		params.format=PixelFormat.TRANSLUCENT;
		params.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
		params.gravity=Gravity.LEFT | Gravity.TOP;
		params.x=0;
		params.y=0;
		params.width=WindowManager.LayoutParams.WRAP_CONTENT;
		params.height=WindowManager.LayoutParams.WRAP_CONTENT;

	}
	
	public void setView(int resource){
		windowView=layoutInflater.inflate(resource, null);
		mImageView=(ImageView)windowView.findViewById(R.id.imageView1);
		mTextView=(TextView)windowView.findViewById(R.id.textView1);
		mTextView.setTextColor(Color.WHITE);
		mImageView.setAlpha(150);
		
		//set floating window movable
		windowView.setOnTouchListener(new View.OnTouchListener() {
			
			float startx=0;
			float starty=0;
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				float x=event.getRawX();
				float y=event.getRawY()-25;
				
				
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					startx=event.getX();
					starty=event.getY();	
					break;
				case MotionEvent.ACTION_MOVE:
					updatePosition(x-startx, y-starty);
					break;
				case MotionEvent.ACTION_UP:
					
					break;

				}
				
				return false;
			}
		});
	}
	
	public void updatePosition(float x,float y){
		params.x=(int)x;
		params.y=(int)y;
		windowManager.updateViewLayout(windowView, params);
	}
	
	public void display(){

		windowManager.addView(windowView, params);
	}
	
	public void hide(){
		windowManager.removeView(windowView);
	}
	
	public void changeContent(String content){
		mTextView.setText(content);
	}

}
