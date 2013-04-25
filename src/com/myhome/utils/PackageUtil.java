package com.myhome.utils;

import java.util.List;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class PackageUtil {
	
	private static PackageManager packageManager;
	
	//find the existence of the given package
	public static boolean findpackage(Context context,String packageName) {
		
		packageManager=context.getPackageManager();
		List<PackageInfo> infos=packageManager.getInstalledPackages(0);
		if(infos!=null){
			int i=0;
			for(;i<infos.size();i++){
				if(infos.get(i).packageName.equals(packageName))
					break;
			}
			if(i<infos.size()) 
				return true;
			return false;
		}
		return false;
			
	}

}
