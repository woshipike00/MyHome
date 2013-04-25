package com.myhome.activity;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.myhome.R;
import com.myhome.utils.APPDownload;

import android.R.integer;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter{
	
	 
    private LayoutInflater mInflater;
    private List<HashMap<String, Object>> data;
    private int resource;
    private Context context;
    private ArrayList<HashMap<String, Object>> workflow;
    
    static int count=0;
     
    public MyAdapter(Context context,List<HashMap<String, Object>>data, int resource){
        this.mInflater = LayoutInflater.from(context);
        this.data=data;
        this.resource=resource;
        this.context=context;
        workflow=((HomeApp)context.getApplicationContext()).getWorkFlow();
    }
    
    private class ViewHolder{
    	ImageView imageView;
    	TextView textView;
    	Button button;
    }
    
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return data.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos=position;
    	
        ViewHolder holder = null;
        if (convertView == null) {
             
            holder=new ViewHolder();  
             
            convertView = mInflater.inflate(R.layout.listitem, null);
            holder.imageView = (ImageView)convertView.findViewById(R.id.imageView1);
            holder.textView = (TextView)convertView.findViewById(R.id.textView1);
            holder.button = (Button)convertView.findViewById(R.id.button1);
            convertView.setTag(holder);
             
        }else {
             
            holder = (ViewHolder)convertView.getTag();
        }
         
         
        holder.imageView.setImageResource((Integer)data.get(position).get("image"));
        holder.textView.setText((String)data.get(position).get("content"));  
        holder.button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(count==0){
					Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					((MyHome)context).startActivityForResult(intent, 1);
					count++;
					

				}
				else {
					boolean isinstalled=HomeApp.findpackage("com.sina.weibo");
					if(isinstalled){
						Intent intent=new Intent(Intent.ACTION_SEND	);
						intent.setType("image/*");
						intent.putExtra(Intent.EXTRA_TEXT, "test" );
						intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/pic1.png")));
						intent.setClassName("com.sina.weibo", "com.sina.weibo.EditActivity");
						((MyHome)context).startActivity(intent);
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
        	
        });

        return convertView;
    }
}

