package com.zycj.business.park.util;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlUtil {
	public static String toXml(SortedMap<String, String> params) {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		Iterator it = params.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append("<"+k+">");
			sb.append(v);
			sb.append("</"+k+">");
		}
		sb.append("</xml>");
		return sb.toString();
	}
	public static Map<String,String> decodeXml(String content) throws Exception {
		Map<String, String> xml = new HashMap<String, String>();
		try {
			SAXReader reader = new SAXReader(); 
			Document  document = reader.read(new StringReader(content));  
			Element root = document.getRootElement();  
			List nodes = root.elements(); 
		    for (Iterator it = nodes.iterator(); it.hasNext();) { 
		      Element elm = (Element) it.next(); 
		      xml.put(elm.getName(), elm.getText());
		   } 
		} catch (Exception e) {
			throw new Exception("解析xml异常",e);
		}
		return xml;
	}
	public static SortedMap<String, String> decodeXmlForSort(String content) throws Exception {
		SortedMap<String, String> xml=new TreeMap<String, String>();
		try {
			SAXReader reader = new SAXReader(); 
			Document  document = reader.read(new StringReader(content));  
			Element root = document.getRootElement();  
			List nodes = root.elements(); 
		    for (Iterator it = nodes.iterator(); it.hasNext();) { 
		      Element elm = (Element) it.next(); 
		      xml.put(elm.getName(), elm.getText());
		   } 
		} catch (Exception e) {
			throw new Exception("解析xml异常",e);
		}
		return xml;
	}
}
