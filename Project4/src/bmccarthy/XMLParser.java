package bmccarthy;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class XMLParser {
	
	public HashMap<String, ManPage> parseXML() throws ParserConfigurationException, SAXException, IOException{
		
		HashMap<String, ManPage> pages = new HashMap<String, ManPage>();
		try{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(new File("/Users/Brian/test.xml"));
			
			doc.getDocumentElement().normalize();
			
			NodeList pageList = doc.getElementsByTagName("page");
			
			for(int i = 0; i < pageList.getLength(); i++){
				ManPage thePage;
				Node page = pageList.item(i);
				HashMap<String, String> flagMap = new HashMap<String, String>();
				HashMap<String, String> exampleMap = new HashMap<String, String>();
				HashMap<String, String> exitMap = new HashMap<String, String>();
				
				if(page.getNodeType() == Node.ELEMENT_NODE){
					Element element = (Element) page;
					
					String pageName = element.getElementsByTagName("name").item(0).getTextContent();
					String pageSynopsis = element.getElementsByTagName("synopsis").item(0).getTextContent();
					String pageDescription = element.getElementsByTagName("description").item(0).getTextContent();
					
					NodeList flagList = doc.getElementsByTagName("flag");					
					
					for(int j = 0; j < flagList.getLength(); j++){
						Node flag = flagList.item(j);
						
						if(flag.getNodeType() == Node.ELEMENT_NODE){
							Element flagElement = (Element) flag;
							
							Element parentPageElement = (Element) flagElement.getParentNode().getParentNode();
							
							String parentPageName = parentPageElement.getAttribute("pagename");
						
							if(parentPageName.equals(pageName)){
								String flagName = flagElement.getElementsByTagName("flagname").item(0).getTextContent();
								String flagDescription = flagElement.getElementsByTagName("flagdescription").item(0).getTextContent();
								
								flagMap.put(flagName, flagDescription);
							}
						}
					}
					NodeList exitList = doc.getElementsByTagName("exitStatus");
					
					for(int j = 0; j < exitList.getLength(); j++){
						Node exitStatus = exitList.item(j);
						
						if(exitStatus.getNodeType() == Node.ELEMENT_NODE){
							Element exitElement = (Element) exitStatus;
							
							Element parentPageElement = (Element) exitElement.getParentNode().getParentNode();
							
							String parentPageName = parentPageElement.getAttribute("pagename");
							
							
							if(parentPageName.equals(pageName)){
								String exitValue = exitElement.getElementsByTagName("value").item(0).getTextContent();
								String exitMeaning = exitElement.getElementsByTagName("meaning").item(0).getTextContent();
								
								exitMap.put(exitValue, exitMeaning);

							}
						}
					}
					NodeList exampleList = doc.getElementsByTagName("example");
							
					for(int j = 0; j < exampleList.getLength(); j++){
						Node example = exampleList.item(j);
						
						if(example.getNodeType() == Node.ELEMENT_NODE){
							Element exampleElement = (Element) example;
							
							Element parentPageElement = (Element) exampleElement.getParentNode().getParentNode();
							
							String parentPageName = parentPageElement.getAttribute("pagename");
							
							
							if(parentPageName.equals(pageName)){
								String exampleCommand = exampleElement.getElementsByTagName("examplecommand").item(0).getTextContent();
								String exampleMeaning = exampleElement.getElementsByTagName("examplemeaning").item(0).getTextContent();
								
								exampleMap.put(exampleCommand, exampleMeaning);

							}
						}
					}
					String author = element.getElementsByTagName("author").item(0).getTextContent();
					thePage = new ManPage(pageName, pageSynopsis, pageDescription, flagMap, author);
					pages.put(pageName, thePage);

				}
				
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return pages;
		
	}
}
