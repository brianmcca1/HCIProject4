package bmccarthy;

import java.util.HashMap;
import java.util.Map;

public class ManPage {
	String name;
	String synopsis;
	String description;
	HashMap<String, String> flagNamesToDescriptions;
	
	public ManPage(String name, String synopsis, String description, HashMap<String, String> flagNamesToDescriptions){
		this.name = name;
		this.synopsis = synopsis;
		this.description = description;
		this.flagNamesToDescriptions = flagNamesToDescriptions;
	}
	
	
	

}
