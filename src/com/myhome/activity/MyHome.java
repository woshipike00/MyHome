package com.myhome.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.myhome.R;
import com.myhome.utils.APPDownload;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

public class MyHome extends Activity {

	private ListView listView;
	private List<HashMap<String, Object>> data;
	public static  Context context;
	private MyAdapter mAdapter;
	private final int CAMERA_PIC_GET=1;
	private NotificationManager notificationManager;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.context=MyHome.this;
		setContentView(R.layout.my_home);
		initUI();
		mAdapter=new MyAdapter(MyHome.this, getdata(), R.layout.listitem);
		listView.setAdapter(mAdapter);
		
        MyAdapter.count=0;
		Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		//intent.setClass(context, ProxyActivity.class);
		notificationManager=(NotificationManager)context.getSystemService(context.NOTIFICATION_SERVICE);
		Notification notification=new Notification(R.drawable.computer_monitor, "myhome", System.currentTimeMillis());
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		PendingIntent contentIntent=PendingIntent.getActivity(context, 0, intent, 0);
		notification.setLatestEventInfo(context, "step", "current: myhome\n"+"next: camera", contentIntent);
		notification.flags=Notification.FLAG_AUTO_CANCEL;
		notificationManager.notify(0, notification);

		
	}
	
	
	public void initUI(){
		listView=(ListView)findViewById(R.id.listView1); 
	}
	
	public List<HashMap<String, Object>> getdata(){
		data=new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> map1=new HashMap<String, Object>();
		map1.put("image", R.drawable.chart);
		map1.put("content", "workflow1");
		data.add(map1);
		
		HashMap<String, Object> map2=new HashMap<String, Object>();
		map2.put("image", R.drawable.computer_monitor);
		map2.put("content", "workflow2");
		data.add(map2);
		
		return data;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode==1 && resultCode==RESULT_OK){
			Log.v("myhome", "onactivityresult");

			//MyAdapter.count++;
			
			//保存图片到sd卡
			Bitmap pic=(Bitmap)data.getExtras().get("data");
			String filepath=Environment.getExternalStorageDirectory().getAbsolutePath()+"/pic1.png";
			try {
				FileOutputStream out=new FileOutputStream(new File(filepath));
				pic.compress(Bitmap.CompressFormat.PNG,100,out);
				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			// bad
			boolean isinstalled=HomeApp.findpackage("com.sina.weibo");
			if(isinstalled){
				Intent intent=new Intent(Intent.ACTION_SEND	);
				intent.setType("image/*");
				intent.putExtra(Intent.EXTRA_TEXT, "test" );
				intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/pic1.png")));
				intent.setClassName("com.sina.weibo", "com.sina.weibo.EditActivity");
				
				Notification notification=new Notification(R.drawable.computer_monitor, "myhome", System.currentTimeMillis());
				intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				PendingIntent contentIntent=PendingIntent.getActivity(context, 0, intent, 0);
				notification.setLatestEventInfo(context, "step", "current: camera\n"+"next: sina weibo", contentIntent);
				notification.flags=Notification.FLAG_AUTO_CANCEL;
				notificationManager.notify(0, notification);
				
				//((MyHome)context).startActivity(intent);
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
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_home, menu);
		return true;
	}

}
