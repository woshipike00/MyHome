package com.example.myhome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import android.widget.Toast;

public class MyHome extends Activity {

	private ListView listView;
	private List<HashMap<String, Object>> data;
	private Context context;
	private MyAdapter mAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.context=MyHome.this;
		setContentView(R.layout.my_home);
		initUI();
		mAdapter=new MyAdapter(MyHome.this, getdata(), R.layout.listitem);
		listView.setAdapter(mAdapter);
		
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

	/*@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode==1 && resultCode==RESULT_OK){
			Log.v("myhome", "onactivityresult");
			Toast.makeText(context, "test1", Toast.LENGTH_SHORT).show();
			mAdapter.notifyDataSetChanged();
		}
	}*/


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_home, menu);
		return true;
	}

}
