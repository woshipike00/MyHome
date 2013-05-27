package com.myhome.utils;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import android.content.Context;
import android.util.Log;


public class WorkFlowParser {
	ArrayList<String> appQueue=new ArrayList<String>();
	
	public WorkFlowParser(Context context,String workFLowName) {
		try{
			Log.v("myhome", workFLowName);
			//InputStream fXmlFile=context.getAssets().open(workFLowName +".xml");
	        //Log.v("myhome",fXmlFile.toString());

			SAXReader reader = new SAXReader();			
	        //Document  doc = reader.read(fXmlFile);
	        Document  doc = reader.read(context.getAssets().open(workFLowName+".xml"));

	        
			Log.v("myhome", "dkfj"); 

			Element rootElement=doc.getRootElement();
			
			for(Iterator i=rootElement.elementIterator(); i.hasNext();) {
				Element element=(Element) i.next();	
				Log.v("myhome", element.getName());
				if(element.getName().equalsIgnoreCase("sequence")) {
					for(Iterator sequenceIterator=element.elementIterator(); sequenceIterator.hasNext();) {
						Element e=(Element)sequenceIterator.next();
						Log.v("myhome", e.attributeValue("partnerLink"));
						appQueue.add(e.attributeValue("partnerLink"));
					}
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
	    }	
	}
	
	public ArrayList<String> getAppQueue() {
		return appQueue;
	}
}
