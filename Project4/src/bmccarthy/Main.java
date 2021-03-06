package bmccarthy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class Main {
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
		XMLParser parser = new XMLParser();
		
		
		HashMap<String, ManPage> manPages = new HashMap<String, ManPage>();
		manPages = parser.parseXML();
		

		HomeView home = new HomeView(manPages);
		home.launch();
		
	}
}
