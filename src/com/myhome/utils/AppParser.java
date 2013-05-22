package com.myhome.utils;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import android.content.Context;

public class AppParser {
	String type;
	ArrayList<HashMap<String, String>> elements= new ArrayList<HashMap<String, String>> ();
	String androidStartActivity;
	String packageName;
	String downloadLink;
	int index=0;
	
	public AppParser(String appName,Context context) {
		try{
			SAXReader reader = new SAXReader();
			
	         Document  doc = reader.read(context.getAssets().open(appName +".xml"));
					
			Element rootElement=doc.getRootElement();
			
			for(Iterator i=rootElement.elementIterator(); i.hasNext();) {
				Element element=(Element) i.next();
				
				if(element.getName().equalsIgnoreCase("source")) {
					type=element.attributeValue("type");
				}
				
				if(element.getName().equalsIgnoreCase("intent")) {
					for(Iterator intentIterator=element.elementIterator(); intentIterator.hasNext();) {
						Element intentElement=(Element) intentIterator.next();
						
						if(intentElement.getName().equalsIgnoreCase("androidStartType")) {
//							elements.add(new HashMap<String, String>());
//							elements.get(index).put("androidStartType", intentElement.attributeValue("name"));
//							index++;
							androidStartActivity=intentElement.attributeValue("name");
						}
						
						if(intentElement.getName().equalsIgnoreCase("extras")) {
							for(Iterator extrasIterator=intentElement.elementIterator(); extrasIterator.hasNext();) {
								Element extraElement=(Element) extrasIterator.next();
								elements.add(new HashMap<String, String>());
								
								String flagString=extraElement.attributeValue("flag");
								elements.get(index).put("flag", flagString);
								
								String sourceString=extraElement.attributeValue("source");
								elements.get(index).put("source", sourceString);
								
								List<?> pars=extraElement.elements("parameter");
								for (int j=0; j<pars.size(); j++) {
									String keyString=((Element)pars.get(j)).getText().split(":",2)[0];
									String valueString=((Element)pars.get(j)).getText().split(":",2)[1];
									elements.get(index).put(keyString, valueString);									
								}
								index++;
							}
						}
					}
				}
				
				if(element.getName().equalsIgnoreCase("partnerLinkType")) {
					for(Iterator partnerLinkTypeIterator=element.elementIterator(); partnerLinkTypeIterator.hasNext();) {
						Element partnerLinkTypeElement=(Element) partnerLinkTypeIterator.next();
						
						if(partnerLinkTypeElement.getName().equalsIgnoreCase("packageName")) {
//							elements.add(new HashMap<String, String>());
//							elements.get(index).put("packageName", partnerLinkTypeElement.attributeValue("value"));
//							index++;
							packageName=partnerLinkTypeElement.attributeValue("value");
							
						}
						
						if(partnerLinkTypeElement.getName().equalsIgnoreCase("downloadLink")) {
//							elements.add(new HashMap<String, String>());
//							elements.get(index).put("downloadLink", partnerLinkTypeElement.attributeValue("value"));
//							index++;
							downloadLink=partnerLinkTypeElement.attributeValue("value");
						}
						
						if(partnerLinkTypeElement.getName().equalsIgnoreCase("intentFilters")) {
							for(Iterator intentFiltersIterator=partnerLinkTypeElement.elementIterator(); intentFiltersIterator.hasNext();) {
								Element intentFilterElement=(Element) intentFiltersIterator.next();
								elements.add(new HashMap<String, String>());
								
								String flagString=intentFilterElement.attributeValue("flag");
								elements.get(index).put("flag", flagString);
								
								String sourceString=intentFilterElement.attributeValue("source");
								elements.get(index).put("source", sourceString);
								
								List<?> pars=intentFilterElement.elements("parameter");
								for (int j=0; j<pars.size(); j++) {
									String keyString=((Element)pars.get(j)).getText().split(":",2)[0];
									String valueString=((Element)pars.get(j)).getText().split(":",2)[1];
									elements.get(index).put(keyString, valueString);									
								}
								index++;
							}
						}
					}
				}											
			}
			
		} catch (Exception e) {
			e.printStackTrace();
	    }
	}
	
	public String getType(){
		return type;
	}
	
	public String getStartType(){
		return androidStartActivity;
	}
	
	public String getDownloadLink(){
		return downloadLink;
	}
	
	public String getPkgName(){
		return packageName;
	}
	
	public ArrayList<HashMap<String, String>> getMethodList(){
		return elements;
	}

}