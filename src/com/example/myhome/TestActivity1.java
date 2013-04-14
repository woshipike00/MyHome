package com.example.myhome;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TestActivity1 extends Activity{

	private Button button;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test1);
		initUI();
	}
	
	public void initUI(){
		button=(Button)findViewById(R.id.button1);
		button.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*setResult(RESULT_OK,new Intent());
				TestActivity1.this.finish();*/
				ArrayList<HashMap<String, Object>> workflow=(ArrayList<HashMap<String, Object>>)getIntent().getSerializableExtra("workflow");
				if(workflow.size()>0){
					String classname=(String)workflow.get(0).get("intent");
					workflow.remove(0);
					
					Intent intent=new Intent();
					intent.putExtra("workflow", workflow);
					intent.setClassName(TestActivity1.this, classname);
					startActivity(intent);
				}
				else {
					Log.v("myhome", "workflows size=0");
				}
			}
			
		});
	}
	
	

}
