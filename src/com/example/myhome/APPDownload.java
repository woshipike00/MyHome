package com.example.myhome;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

public class APPDownload {
	
	
	//下载apk文件
	public static void httpdownload(String httpurl , String filename) throws IOException{
		File file=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+filename);
		if(file.exists()){
			Log.v("myhome", "file already exists");
			return;
		}
		URL url=new URL(httpurl); 
		HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
		InputStream in=urlConnection.getInputStream();
		FileOutputStream out=new FileOutputStream(file);
		byte[] buf=new byte[256];
		int count=0;
		int total=in.available();
		int current=0;
		
		Log.v("myhome", "download start");

		if(urlConnection.getResponseCode()==200){
			int i=0;
			while((count=in.read(buf))>0){
				current+=count;
				Log.v("myhome", "downloading..."+current);
				out.write(buf, 0, count);
			}
		}
		Log.v("myhome", "apk downloaded");
		urlConnection.disconnect();
		in.close();
		out.close();
	}
	
	
	//安装apk文件
	public static void installapk(Context context,String apkname){
		
		String filename=Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+apkname;
		Intent install=new Intent(Intent.ACTION_VIEW);
		install.setDataAndType(Uri.fromFile(new File(filename)), "application/vnd.android.package-archive");
		context.startActivity(install);
	}
	
	public static void getapkinstalled(Context context,String httpurl,String apkname) throws IOException{
		httpdownload(httpurl, apkname);
		installapk(context, apkname);
	}

}
