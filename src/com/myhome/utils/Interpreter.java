package com.myhome.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import android.content.Context;
import android.util.Log;

public class Interpreter {
	
	private static Context context;
	
	public Interpreter(Context context){
		this.context=context;
	}
	
	//解析bpmn文件
	public static void XMLParse(String filename,ArrayList<HashMap<String, Object>> workflow) throws DocumentException, IOException{
		Log.v("myhome", "xmlparse");
		SAXReader reader=new SAXReader();
		org.dom4j.Document document=reader.read(context.getAssets().open(filename));
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
