package com.myhome.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.myhome.R;
import com.myhome.activity.MyHome;
import com.myhome.widgets.DownloadNotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.RemoteViews;
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
		private NotificationManager notificationManager;
		
		
		public DownloadTask(Context context,String apkname){
			this.context=context;
			this.apkname=apkname;
			file=new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+apkname);
			notificationManager=(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
		}


		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			
			try {
				
				
				URL url = new URL(params[0]);
				HttpURLConnection urlConnection=(HttpURLConnection) url.openConnection();
				urlConnection.connect();
				int total=urlConnection.getContentLength();	
				InputStream in=urlConnection.getInputStream();
				FileOutputStream out=new FileOutputStream(file);
				byte[] buf=new byte[256];
				int count=0,current=0;
				
				((MyHome)context).apkDownloading();
                Log.v("myhome", "download start");
                
                DownloadNotification dnotification=new DownloadNotification(context, R.drawable.email, "downloading...", System.currentTimeMillis());
                dnotification.setProgress(total, current);
                dnotification.setText(apkname+": "+current*100/total+"%");
                notificationManager.notify(1, dnotification);

				if(urlConnection.getResponseCode()==200){
					while((count=in.read(buf))>0){
						current+=count;
						dnotification.setProgress(total, current);
		                dnotification.setText(apkname+": "+current*100/total+"%");
		                Log.v("myhome", total+","+current);
		                notificationManager.notify(1, dnotification); 
						out.write(buf, 0, count); 
					}
				}
				
				notificationManager.cancel(1);
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
			
			((MyHome)context).apkDownloaded();
			
			installapk(context, apkname);
			

		}
		
		
		
	}

}
