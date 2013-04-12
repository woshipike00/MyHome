package com.example.myhome;

import android.app.Application;

public class HomeApp extends Application{
	
	private static int[] status;
	
	public static int[] getstatus(){
		return status;
	}

}
