package com.myhome.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import android.content.Intent;
import android.net.Uri;

public class IntentUtil {
	
	public static final int ADD_CATEGORY=0;
	public static final int ADD_FLAGS=1;
	public static final int PUTEXTRA_STRING=2;
	public static final int PUTEXTRA_INT=3;
	public static final int PUTEXTRA_DOUBLE=4;
	public static final int PUTEXTRAS=5;
	public static final int SET_ACTION=6;
	public static final int SET_CLASSNAME=7;
	public static final int SET_DATA=8;
	public static final int SET_DATAANDNORMALIZE=9;
	public static final int SET_DATAANDTYPE=10;
	public static final int SET_DATAANDTYPEANDNORMALIZE=11;
	public static final int SET_FLAGS=12;
	public static final int SET_PACKAGE=13;
	public static final int SET_TYPE=14;
	public static final int SET_TYPEANDNORMALIZE=15;
	
	public static final int GETINTEXTRA=16;
	

	
	/**
	 * 
	 * @author pike
	 * @param intent intent to be set
	 * @param srcIntent intent get in the onActivityResult method
	 * @param confs methods to set the intent read from the xml file
	 */
	public static void confIntent(Intent intent, Intent srcIntent, ArrayList<HashMap<String, String>> confs){
		for (HashMap<String, String> conf:confs){
			setIntent(intent, conf);
		}
	}
	
	public static void setIntent(Intent intent, HashMap<String, String> conf){
		int flag=Integer.parseInt(conf.get("flag"));
		String arg1,arg2;
		switch (flag) {
		case ADD_CATEGORY:
			arg1=conf.get("category");
			intent.addCategory(arg1);
			break;
			
		case ADD_FLAGS:
			arg1=conf.get("flags");
			intent.addFlags(Integer.parseInt(arg1));
			break;
			
		case PUTEXTRA_STRING:
			arg1=conf.get("name");
			arg2=conf.get("value");
			intent.putExtra(arg1, arg2);
			break;
			
		case PUTEXTRA_INT:
			arg1=conf.get("name");
			arg2=conf.get("value");
			intent.putExtra(arg1, Integer.parseInt(arg2));
			break;
			
		case PUTEXTRA_DOUBLE:
			arg1=conf.get("name");
			arg2=conf.get("value");
			intent.putExtra(arg1, Double.parseDouble(arg2));
			break;
			
		case PUTEXTRAS:
			//arg1=conf.get("name");
			//arg2=conf.get("value");
			//intent.putExtra(arg1, arg2);
			break;
			
		case SET_ACTION:
			arg1=conf.get("action");
			intent.setAction(arg1);
			break;
			
		case SET_CLASSNAME:
			arg1=conf.get("packageName");
			arg2=conf.get("className");
			intent.setClassName(arg1, arg2);
			break;
			
		case SET_DATA:
			arg1=conf.get("data");
			intent.setData(Uri.parse(arg1));
			break;
			
		case SET_DATAANDNORMALIZE:
			arg1=conf.get("data");
			intent.setData(Uri.parse(arg1));
			break;	
			
		case SET_DATAANDTYPE:
			arg1=conf.get("data");
			arg2=conf.get("type");
			intent.setDataAndType(Uri.parse(arg1),arg2);
			break;
			
		case SET_DATAANDTYPEANDNORMALIZE:
			arg1=conf.get("data");
			arg2=conf.get("type");
			intent.setDataAndType(Uri.parse(arg1),arg2);
			break;
			
		case SET_FLAGS:
			arg1=conf.get("flags");
			intent.setFlags(Integer.parseInt(arg1));
			break;
			
		case SET_PACKAGE:
			arg1=conf.get("packageName");
			intent.setPackage(arg1);
			break;
			
		case SET_TYPE:
			arg1=conf.get("type");
			intent.setType(arg1);
			break;	
			
		case SET_TYPEANDNORMALIZE:
			arg1=conf.get("type");
			intent.setType(arg1);
			break;

		default:
			System.err.println("can't handle the setmethod");
			break;
		}
	}
	
	public static Object getArgsFromSrc(int getflag,String key,Intent src,HashMap<String, String> conf){
		
		switch (getflag) {
		case GETINTEXTRA:
			return src.getIntExtra(conf.get(key),0);
			
		

		default:
			return null;
			
		}
		
	}
	

}
