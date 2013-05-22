package com.myhome.activity;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

public class GetMTXXPicActivity extends Activity{

	private Map<Long, String> map=new HashMap<Long, String>();
	private final String dirpath=Environment.getExternalStorageDirectory().getAbsolutePath()+"/MTXX";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//遍历MTXX文件夹下所有文件
		File dir=new File(dirpath);
		if(!dir.isDirectory()){
			Log.v("myhome", "no dir");
			return;
		}
		
		File[] filelist=dir.listFiles();
		for (int i=0;i<filelist.length;i++){
			File curfile=filelist[i];
			if(curfile.isFile())
				map.put(curfile.lastModified(), curfile.getName());
		}
		
		
		//对所有文件按时间排序，取出最新的照片
		ArrayList<Map.Entry<Long,String>> list=new ArrayList<Map.Entry<Long,String>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Long, String>>() {

			@Override
			public int compare(Entry<Long, String> e1, Entry<Long, String> e2) {
				// TODO Auto-generated method stub
				
				return (int) (e1.getKey()-e2.getKey());
			}
			
		});
		
		String filename=list.get(list.size()-1).getValue();
		Intent intent=new Intent();
		intent.putExtra("photouri", Uri.fromFile(new File(dirpath+"/"+filename)));
		setResult(RESULT_OK);
		finish();
		
	}
	
	

}
