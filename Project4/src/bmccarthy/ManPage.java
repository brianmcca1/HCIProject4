package bmccarthy;

import java.util.Map;

public class ManPage {
	String name;
	String synopsis;
	String description;
	Map<String, String> flagNamesToDescriptions;
	
	public ManPage(String name, String synopsis, String description, Map<String, String> flagNamesToDescriptions){
		this.name = name;
		this.synopsis = synopsis;
		this.description = description;
		this.flagNamesToDescriptions = flagNamesToDescriptions;
	}
	
	
	

}
