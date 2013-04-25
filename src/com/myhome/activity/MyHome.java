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
import com.myhome.widgets.MyAdapter;

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
import android.graphics.PixelFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ListView;
import android.widget.Toast;

public class MyHome extends Activity {

	private ListView listView;
	private List<HashMap<String, Object>> data;
	public static  Context context;
	private MyAdapter mAdapter;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.context=MyHome.this;
		setContentView(R.layout.my_home);
		initUI();
		
	}
	
	
	public void initUI(){
		listView=(ListView)findViewById(R.id.listView1); 
		mAdapter=new MyAdapter(MyHome.this, getdata(), R.layout.listitem);
		listView.setAdapter(mAdapter);
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

			
		}
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_home, menu);
		return true;
	}

}
