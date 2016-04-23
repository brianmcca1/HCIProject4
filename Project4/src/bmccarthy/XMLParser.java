package bmccarthy;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class XMLParser {
	
	public ArrayList<ManPage> parseXML() throws ParserConfigurationException, SAXException, IOException{
		
		ArrayList<ManPage> pages = new ArrayList<ManPage>();
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
				
				if(page.getNodeType() == Node.ELEMENT_NODE){
					Element element = (Element) page;
					
					String pageName = element.getElementsByTagName("name").item(0).getTextContent();
					String pageSynopsis = element.getElementsByTagName("synopsis").item(0).getTextContent();
					String pageDescription = element.getElementsByTagName("description").item(0).getTextContent();
					
					
//					System.out.println("Name: " + element.getElementsByTagName("name").item(0).getTextContent());
//					System.out.println("Synopsis: " + element.getElementsByTagName("synopsis").item(0).getTextContent());
//					System.out.println("Description: "  + element.getElementsByTagName("description").item(0).getTextContent());
					
					NodeList flagList = doc.getElementsByTagName("flag");
					
					
					for(int j = 0; j < flagList.getLength(); j++){
						Node flag = flagList.item(j);
						
						if(flag.getNodeType() == Node.ELEMENT_NODE){
							Element flagElement = (Element) flag;
							
							Element parentPageElement = (Element) flagElement.getParentNode().getParentNode();
							
							String parentPageName = parentPageElement.getAttribute("pagename");
							
							
							//System.out.println("parentPageName is " + parentPageName);
							//System.out.println("pageName is " + pageName);
							
							if(parentPageName.equals(pageName)){
								String flagName = flagElement.getElementsByTagName("flagname").item(0).getTextContent();
								String flagDescription = flagElement.getElementsByTagName("flagdescription").item(0).getTextContent();
								
								flagMap.put(flagName, flagDescription);
//								System.out.println("Flag name: " + flagElement.getElementsByTagName("flagname").item(0).getTextContent());
//								System.out.println("Flag description: " + flagElement.getElementsByTagName("flagdescription").item(0).getTextContent());
							}
						}
					}
					
					thePage = new ManPage(pageName, pageSynopsis, pageDescription, flagMap);
					pages.add(thePage);
					//System.out.println("---------------------");
				}
				
			}
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return pages;
		
	}
}
