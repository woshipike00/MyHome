package com.example.myhome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
				/*Intent intent=new Intent();
				intent.setClass(context, TestActivity1.class);
				((MyHome)context).startActivityForResult(intent, 1);
				data.get(pos).put("image", R.drawable.email);*/
				
				String classname=(String)workflow.get(0).get("intent");
				workflow.remove(0);
				
				Intent intent=new Intent();
				intent.putExtra("workflow", workflow);
				intent.setClassName(context, classname);
				((MyHome)context).startActivity(intent);
				
			}
        	
        });

        return convertView;
    }

     
}
