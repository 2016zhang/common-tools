package com.baijob.commonTools;

import java.io.UnsupportedEncodingException;

import org.dom4j.Document;
import org.dom4j.Element;



public class CommonTest {
	public static void main(String[] args) throws UnsupportedEncodingException {
		Document doc = XmlUtil.createDoc("aaa");
		Element aaa = doc.getRootElement();
		aaa.addElement("bbb").addText("bbbText");
		
		System.out.println(doc.asXML());
	}
}
