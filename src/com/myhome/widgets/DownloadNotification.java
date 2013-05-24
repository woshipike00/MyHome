package com.myhome.widgets;

import com.example.myhome.R;

import android.app.Notification;
import android.content.Context;
import android.widget.RemoteViews;

public class DownloadNotification extends Notification{
	
	public DownloadNotification(Context context,int icon,String tickerText,long when){
		
         this.icon=icon;
         this.tickerText=tickerText;
         this.when=when;
         this.contentView=new RemoteViews(context.getPackageName(), R.layout.download_notification);
         this.flags=Notification.FLAG_AUTO_CANCEL;
    
	}
	
	public void setProgress(int max,int progress){
		contentView.setProgressBar(R.id.progressBar1,max , progress,false);
	}
	
	public void setText(String text){
		contentView.setTextViewText(R.id.textView1, text);  
		
	}
	
	
	
}
