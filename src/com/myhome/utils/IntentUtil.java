package com.myhome.utils;

import java.util.ArrayList;
import java.util.HashMap;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class IntentUtil {
	
	public static final int ADD_CATEGORY=0;
	public static final int ADD_FLAGS=1;
	public static final int PUTEXTRA_STRING=2;
	public static final int PUTEXTRA_INT=3;
	public static final int PUTEXTRA_DOUBLE=4;
	public static final int PUTEXTRA_PARCELABLE=5;
	public static final int PUTEXTRAS=6;
	public static final int SET_ACTION=7;
	public static final int SET_CLASSNAME=8;
	public static final int SET_DATA=9;
	public static final int SET_DATAANDNORMALIZE=10;
	public static final int SET_DATAANDTYPE=11;
	public static final int SET_DATAANDTYPEANDNORMALIZE=12;
	public static final int SET_FLAGS=13;
	public static final int SET_PACKAGE=14;
	public static final int SET_TYPE=15;
	public static final int SET_TYPEANDNORMALIZE=16;
	
	//public static final int GETINTEXTRA=16;
	//public static final int GETDOUBLEEXTRA=17;
	//public static final int GETSTRINGEXTRA=18;
	//public static final int GETEXTRAS=19;

	

	
	/**
	 * 
	 * @author pike
	 * @param intent intent to be set
	 * @param srcIntent intent get in the onActivityResult method
	 * @param confs methods to set the intent read from the xml file
	 */
	public static void confIntent(Intent intent, Intent srcIntent, ArrayList<HashMap<String, String>> confs){
		for (HashMap<String, String> conf:confs){
			setIntent(intent, srcIntent, conf);
		}
	}
	
	public static void setIntent(Intent intent, Intent srcIntent, HashMap<String, String> conf){
		int flag=Integer.parseInt(conf.get("flag"));
		String source=conf.get("source");
		
		switch (flag) {
		case ADD_CATEGORY:
			intent.addCategory(conf.get("category"));
			break;
			
		case ADD_FLAGS:
			intent.addFlags(Integer.parseInt(conf.get("flags")));
			break;
			
		case PUTEXTRA_STRING:
			if(source.equals("null"))
				intent.putExtra(conf.get("name"), conf.get("value"));
			else
				intent.putExtra(conf.get("name"), srcIntent.getStringExtra(conf.get("value")));
			break;
			
		case PUTEXTRA_INT:
			if(source.equals("null")) 
				intent.putExtra(conf.get("name"), Integer.parseInt(conf.get("value")));
			else
				intent.putExtra(conf.get("name"), srcIntent.getIntExtra(conf.get("value"), 0));
			break;
			
		case PUTEXTRA_DOUBLE:
			if(source.equals("null"))
				intent.putExtra(conf.get("name"), Double.parseDouble(conf.get("value")));
			else
				intent.putExtra(conf.get("name"), srcIntent.getDoubleExtra(conf.get("value"), 0));
			break;
			
		case PUTEXTRA_PARCELABLE:
			if(source.equals("null")) 
				break;
			else
				intent.putExtra(conf.get("name"), srcIntent.getParcelableExtra(conf.get("value")));
			break;
			
		case PUTEXTRAS:
			if(source.equals("null"))
				break;
			else
				intent.putExtras(srcIntent.getExtras());
			break;
			
		case SET_ACTION:
			intent.setAction(conf.get("action"));
			break;
			
		case SET_CLASSNAME:
			intent.setClassName(conf.get("packageName"), conf.get("className"));
			break;
			
		case SET_DATA:
			intent.setData(Uri.parse(conf.get("data")));
			break;
			
		case SET_DATAANDNORMALIZE:
			intent.setData(Uri.parse(conf.get("data")));
			break;	
			
		case SET_DATAANDTYPE:
			intent.setDataAndType(Uri.parse(conf.get("data")),conf.get("type"));
			break;
			
		case SET_DATAANDTYPEANDNORMALIZE:
			intent.setDataAndType(Uri.parse(conf.get("data")),conf.get("type"));
			break;
			
		case SET_FLAGS:
			intent.setFlags(Integer.parseInt(conf.get("flags")));
			break;
			
		case SET_PACKAGE:
			intent.setPackage(conf.get("packageName"));
			break;
			
		case SET_TYPE:
			intent.setType(conf.get("type"));
			break;	
			
		case SET_TYPEANDNORMALIZE:
			intent.setType(conf.get("type"));
			break;

		default:
			Log.v("myhome", "can't handle the setmethod");
			break;
		}
	}
	
	/*public static Object getArgsFromSrc(int getflag,String key,Intent src,HashMap<String, String> conf){
		
		switch (getflag) {
		case GETINTEXTRA:
			return src.getIntExtra(conf.get(key),0);
			
		

		default:
			return null;
			
		}
		
	}*/
	

}
