package com.example.myhome;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import android.app.Application;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.provider.MediaStore;
import android.util.Log;

public class HomeApp extends Application{
	
	private static int[] status;
	private ArrayList<HashMap<String, Object>> workflow;
	private static PackageManager pManager;
	
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		Log.v("myhome", "appstart");
		super.onCreate();
		
		workflow=new ArrayList<HashMap<String,Object>>();
		/*try {
			XMLParse("bpmn01.bpmn");
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		pManager=getPackageManager();
		
		
		
		
		

		
		
	}
	
	
	//查看系统中是否存在包
	public static boolean findpackage(String packageName) {
		List<PackageInfo> infos=pManager.getInstalledPackages(0);
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

	public static int[] getstatus(){
		return status;
	}
	
	public ArrayList<HashMap<String, Object>> getWorkFlow(){
		return workflow;
	}
	
	
	public void setworkflow(){
		
	}
	
	//解析bpmn文件
	public void XMLParse(String filename) throws DocumentException, IOException{
		Log.v("myhome", "xmlparse");
		SAXReader reader=new SAXReader();
		org.dom4j.Document document=reader.read(getAssets().open(filename));
		Element root=document.getRootElement();
		Log.v("myhome", "root name: "+root.getName());
		Element process=root.element("process");	
		List<Element> nodes=process.elements("task");

		Log.v("myhome", "node size: "+nodes.size());
		for (Iterator<Element> iterator=nodes.iterator();iterator.hasNext();){
			Element task=iterator.next();
			//System.out.println("1");
			String intentname=task.attribute("name").getText();
			System.out.println(intentname);
			HashMap<String,Object> map=new HashMap<String, Object>();
			map.put("intent", intentname);
			workflow.add(map);
		}
		
		
	}

}
