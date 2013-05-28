package com.myhome.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.myhome.R;
import com.myhome.app.HomeApp;
import com.myhome.utils.APPDownload;
import com.myhome.utils.AppParser;
import com.myhome.utils.HttpUtil;
import com.myhome.utils.IntentUtil;
import com.myhome.utils.PackageUtil;
import com.myhome.widgets.FloatingWindow;
import com.myhome.widgets.MyPagerAdapter;

import android.os.Bundle;
import android.app.Activity;
import android.app.Application;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MyHome extends Activity {

	private Context context;
	private Application mApp;
	private List<View> viewList;
	private LayoutInflater mLayoutInflater;
	private ViewPager mViewPager;
	private FloatingWindow mfWindow;
	private ArrayList<String> appList;
	private ArrayList<AppParser> appParsers;
	private ArrayList<HashMap<String, String>> confs;
	private static Intent srcIntent;
	private static int count=0;
	private static boolean isdownloading=false;
	
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
		appList=((HomeApp)mApp).getAppList();


	}

	
	
	public void initViewPager(){
		viewList=new ArrayList<View>();
		View workflow1=mLayoutInflater.inflate(R.layout.workflow, null);
		workflow1.setBackgroundResource(R.drawable.workflow1);
		((TextView)(workflow1.findViewById(R.id.title))).setText("workflow1");
		((TextView)(workflow1.findViewById(R.id.content))).setText("工作流程：\n\n\n1. 拍摄照片\n\n2. 使用美图秀秀美化\n\n3. 将照片发送到微博\n\n4. 短信通知好友");
		viewList.add(workflow1);
		
		View workflow2=mLayoutInflater.inflate(R.layout.workflow, null);
		workflow2.setBackgroundResource(R.drawable.workflow2);
		((TextView)(workflow2.findViewById(R.id.title))).setText("workflow2");
		viewList.add(workflow2);
		
		View workflow3=mLayoutInflater.inflate(R.layout.workflow, null);
		workflow3.setBackgroundResource(R.drawable.workflow3);
		((TextView)(workflow3.findViewById(R.id.title))).setText("workflow3");
		viewList.add(workflow3);
		
		mViewPager.setAdapter(new MyPagerAdapter(context, viewList));
		
		ImageButton btn=(ImageButton) workflow1.findViewById(R.id.imageButton1);
        btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if(count>=appParsers.size()){
					Log.v("myhome", "workflow end");
					count=0;
					mfWindow.changeContent("start");
					return;
				}
				
				
				AppParser appParser=appParsers.get(count);
				String curapp=appList.get(count);
				
				//check if we need to install the new software
				if(appParser.getType().equals("Third_party")){
					boolean isinstalled=PackageUtil.findpackage(context.getApplicationContext(),appParser.getPkgName());
					Log.v("myhome", isinstalled+"");
					if(isinstalled){
						Log.v("myhome", "the package has been installed");
						isdownloading=false;

					}
					else {
						if(isdownloading){
							Toast.makeText(context, "the apk is downloading", Toast.LENGTH_SHORT).show();
							return;

						}
												
						//check the newwork state
						if(!HttpUtil.testHTTP(context)){
							Toast.makeText(context, "please check the network state!", Toast.LENGTH_SHORT).show();
							return;
						}
						
						APPDownload.DownloadTask mDownloadTask=new APPDownload.DownloadTask(context, curapp+".apk");
						mDownloadTask.execute(appParser.getDownloadLink());
						
						return;
					}
				}
				
				
				Log.v("myhome", "set intent");
				//set the new intent and start
				Intent newIntent=new Intent();
				confs=appParsers.get(count).getMethodList();
				IntentUtil.confIntent(newIntent, srcIntent, confs);
				
				if(appParser.getStartType().equals("startActivity"))
					((MyHome)context).startActivity(newIntent);
				else
					((MyHome)context).startActivityForResult(newIntent, 1);

				if(count>=appList.size()-1)
					mfWindow.changeContent("end");
				else
					mfWindow.changeContent(appList.get(count)+" --> "+appList.get(count+1));
				count++;			    
				
			}
        	
        });
        

		
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode==1 && resultCode==RESULT_OK){
			Log.v("myhome", "onactivityresult");
			
			srcIntent=data;
		}
	}
	
	public void apkDownloading(){
		isdownloading=true;
	}
	
	public void apkDownloaded(){
		isdownloading=false;
	}
	
	public FloatingWindow getFloatingWindow(){
		return mfWindow;
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		
		menu.add(0, 1, 1, "share");
		super.onCreateOptionsMenu(menu);
		
		return true;
	}


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub

		if(item.getItemId()==1){
			
			String workflow="start--";
			for (int i=0;i<appList.size();i++){
				workflow+=appList.get(i)+"--";
			}
			workflow+="end";
			Intent intent=new Intent(Intent.ACTION_SEND);
			intent.setType("text/plain");
			intent.putExtra(Intent.EXTRA_TEXT, workflow);
			startActivity(Intent.createChooser(intent, getTitle()));
		}
		return true;
	}
	
	

}
