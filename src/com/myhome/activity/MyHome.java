package com.myhome.activity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.myhome.R;
import com.myhome.app.HomeApp;
import com.myhome.utils.APPDownload;
import com.myhome.utils.AppParser;
import com.myhome.utils.IntentUtil;
import com.myhome.utils.PackageUtil;
import com.myhome.widgets.FloatingWindow;
import com.myhome.widgets.MyPagerAdapter;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.Application;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class MyHome extends Activity {

	private Context context;
	private Application mApp;
	private List<View> viewList;
	private LayoutInflater mLayoutInflater;
	private ViewPager mViewPager;
	private FloatingWindow mfWindow;
	private ArrayList<AppParser> appParsers;

	private static int count=0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);	
		
		//clear the title bar
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
				
		setContentView(R.layout.my_home);
		init();
	}
	
	
	public void init(){
		
		this.context=MyHome.this;
		this.mApp=getApplication();
		mLayoutInflater=(LayoutInflater) getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
		mfWindow=((HomeApp)getApplication()).getFloatingWindow();
		mViewPager=(ViewPager)findViewById(R.id.viewpagerLayout);
		initViewPager();
		appParsers=((HomeApp)mApp).getAppParsers();

	}

	
	
	public void initViewPager(){
		viewList=new ArrayList<View>();
		View workflow1=mLayoutInflater.inflate(R.layout.workflow, null);
		workflow1.setBackgroundResource(R.drawable.workflow1);
		((TextView)(workflow1.findViewById(R.id.textView1))).setText("workflow1");
		viewList.add(workflow1);
		
		View workflow2=mLayoutInflater.inflate(R.layout.workflow, null);
		workflow2.setBackgroundResource(R.drawable.workflow2);
		((TextView)(workflow2.findViewById(R.id.textView1))).setText("workflow2");
		viewList.add(workflow2);
		
		View workflow3=mLayoutInflater.inflate(R.layout.workflow, null);
		workflow3.setBackgroundResource(R.drawable.workflow3);
		((TextView)(workflow3.findViewById(R.id.textView1))).setText("workflow3");
		viewList.add(workflow3);
		
		mViewPager.setAdapter(new MyPagerAdapter(context, viewList));
		
		ImageButton btn=(ImageButton) workflow1.findViewById(R.id.imageButton1);
        btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(count==0){
					
					Intent intent=new Intent();
					IntentUtil.confIntent(intent, null, appParsers.get(count).getMethodList());
					if(appParsers.get(count).getType().equals("startActivity"))
						((MyHome)context).startActivity(intent);
					else
						((MyHome)context).startActivityForResult(intent, 1);

					
					/*Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
					((MyHome)context).startActivityForResult(intent, 1);*/
					count++;
					mfWindow.changeContent("camera --> weibo");
					

				}
				else if(count==1){
					boolean isinstalled=PackageUtil.findpackage(context.getApplicationContext(),"com.sina.weibo");
					if(isinstalled){
						Intent intent=new Intent(Intent.ACTION_SEND	);
						intent.setType("image/*");
						intent.putExtra(Intent.EXTRA_TEXT, "test" );
						intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/pic1.png")));
						intent.setClassName("com.sina.weibo", "com.sina.weibo.EditActivity");
						((MyHome)context).startActivity(intent);
						count++;
						mfWindow.changeContent("weibo --> sms");
					}
					else{
						try {
							APPDownload.getapkinstalled(context, "http://gdown.baidu.com/data/wisegame/6dbb6f730eb41a57/weibo.apk", "weibo.apk");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					  
					}
				}
				
				else {
					Intent sendIntent = new Intent(Intent.ACTION_SENDTO,Uri.parse("sms:")); 
					//sendIntent.putExtra("address", "123456789"); 
					sendIntent.putExtra("sms_body", "I hava sent a photo to my sina weibo!"); 
					startActivity(sendIntent);
					mfWindow.changeContent("end");

				}

				
			}
        	
        });
		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode==1 && resultCode==RESULT_OK){
			Log.v("myhome", "onactivityresult");
			
			Intent newIntent=new Intent();
			newIntent.putExtras(data.getExtras());
			newIntent.setClassName("com.example.myhome", "com.myhome.activity.PhotoHandleActivity");
			startActivityForResult(newIntent, 2);
			
			
		}
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_home, menu);
		return true;
	}

}
