package com.myhome.activity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;

public class PhotoHandleActivity extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		//保存图片到sd卡
		Intent data=getIntent();
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
		
		Intent result=new Intent();
		result.putExtra("filepath", filepath);
		
		setResult(RESULT_OK, result);
		finish();
	}
	
	

}
