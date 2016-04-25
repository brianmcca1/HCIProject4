package bmccarthy;

import java.util.HashMap;
import java.util.Map;

public class ManPage {
	String name;
	String synopsis;
	String description;
	HashMap<String, String> flagNamesToDescriptions;
	String author;
	
	public ManPage(String name, String synopsis, String description, HashMap<String, String> flagNamesToDescriptions, String author){
		this.name = name;
		this.synopsis = synopsis;
		this.description = description;
		this.flagNamesToDescriptions = flagNamesToDescriptions;
		this.author = author;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getSynopsis(){
		return this.synopsis;
	}
	
	public String getDescription(){
		return this.description;	
	}
	public HashMap<String, String> getFlags(){
		return this.flagNamesToDescriptions;
	}
	
	public String getAuthor(){
		return this.author;
	}
}
