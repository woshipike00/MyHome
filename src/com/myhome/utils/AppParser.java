package com.myhome.utils;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import android.content.Context;
import android.util.Log;


public class AppParser {
	String type;
	String message_action_type;
	ArrayList<HashMap<String, String>> elements= new ArrayList<HashMap<String, String>> ();
	
	public AppParser(String appName,Context context) {
		try{		
			SAXReader reader = new SAXReader();
			
	        Document  doc = reader.read(context.getAssets().open(appName +".xml"));
			
	        //Document  doc = reader.read(new File("xml/"+ appName +".xml"));
		
			Element rootElement=doc.getRootElement();
						
			Element sourceElement=rootElement.element("source");
			type=sourceElement.attributeValue("type");
			System.out.println(type);
						
			Element messageElement=rootElement.element("message");
			
			Element actionElement=messageElement.element("action");
			message_action_type=actionElement.attributeValue("type");
			System.out.println(message_action_type);
			
			Element elementsElement=messageElement.element("elements");
			
			List<?> ele = elementsElement.elements("element");
			for (int i=0; i<ele.size(); i++) {
				elements.add(new HashMap<String, String>());
				
				String flagString=((Element) ele.get(i)).attributeValue("flag");
				elements.get(i).put("flag", flagString);
				
				String sourceString=((Element) ele.get(i)).attributeValue("source");
				elements.get(i).put("source", sourceString);
				
				List<?> pars=((Element)ele.get(i)).elements("parameter");
				for (int j=0; j<pars.size(); j++) {
					String keyString=((Element)pars.get(j)).getText().split(":",2)[0];
					String valueString=((Element)pars.get(j)).getText().split(":",2)[1];
					elements.get(i).put(keyString, valueString);
					
				}
			}	
			
			Log.v("myhome", elements+"");
		} catch (Exception e) {
			e.printStackTrace();
	    }
	}	
	
	public String getType(){
		return type;
	}
	
	public String getActionType(){
		return message_action_type;
	}
	
	public ArrayList<HashMap<String, String>> getMethodList(){
		return elements;
	}
}
