package com.myhome.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.myhome.activity.MyHome;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

public class APPDownload {
	
	
	//安装apk文件
	public static void installapk(Context context,String apkname){
		
		String filename=Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+apkname;
		Intent install=new Intent(Intent.ACTION_VIEW);
		install.setDataAndType(Uri.fromFile(new File(filename)), "application/vnd.android.package-archive");
		context.startActivity(install);
	}
	
	
	public static class DownloadTask extends AsyncTask<String, Integer, String>{
	
		private File file;
		private Context context;
		private String apkname;
		
		
		public DownloadTask(Context context,String apkname){
			this.context=context;
			this.apkname=apkname;
			file=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+apkname);
		}


		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			try {
				URL url = new URL(params[0]);
				HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
				InputStream in=urlConnection.getInputStream();
				FileOutputStream out=new FileOutputStream(file);
				byte[] buf=new byte[256];
				int count=0;


				Log.v("myhome", "download start");

				if(urlConnection.getResponseCode()==200){
					while((count=in.read(buf))>0){
						
						//Log.v("myhome", "downloading..."+current);
						out.write(buf, 0, count);
					}
				}
				Log.v("myhome", "apk downloaded");
				urlConnection.disconnect();
				in.close();
				out.close();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null; 
			
			
			
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			
			Toast.makeText(context, "download complete!", Toast.LENGTH_SHORT).show();
			installapk(context, apkname);
			

		}
		
		
		
	}

}
