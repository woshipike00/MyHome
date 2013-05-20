package com.myhome.utils;

import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.content.Context;
import android.util.Log;


public class WorkFlowParser {
	
	//static Queue<String> appQueue=new LinkedList<String> ();
	
	public static void mainParser(Context context,ArrayList<String> appQueue) {
		try{
			
			InputStream fXmlFile=context.getAssets().open("main.xml");
			//File fXmlFile = new File("xml/main.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			doc.getDocumentElement().normalize();
	
			NodeList invokeList = doc.getElementsByTagName("invoke");	
			for (int i=0; i<invokeList.getLength(); i++) {	 
				Node invokeNode = invokeList.item(i);
				Element e = (Element) invokeNode;
				String appName=e.getAttribute("partnerLink");
				appQueue.add(appName);
			}			
			Log.v("myhome",appQueue+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
