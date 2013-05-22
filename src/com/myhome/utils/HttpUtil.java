package com.myhome.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class HttpUtil {
	
	public static boolean testHTTP(Context context){
		ConnectivityManager conManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo netInfo=conManager.getActiveNetworkInfo();
		if(netInfo!=null && netInfo.isConnected())
			return true;
		return false;
	}

}
