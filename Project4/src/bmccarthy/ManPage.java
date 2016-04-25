package bmccarthy;

import java.util.HashMap;
import java.util.Map;

public class ManPage {
	String name;
	String synopsis;
	String brief;
	String description;
	HashMap<String, String> flagNamesToDescriptions;
	String author;
	HashMap<String, String> exitCodes;
	HashMap<String, String> examples;
	public ManPage(String name, String synopsis, String brief, String description, HashMap<String, String> flagNamesToDescriptions, String author, HashMap<String, String> exitCodes, HashMap<String, String> examples){
		this.name = name;
		this.synopsis = synopsis;
		this.brief = brief;
		this.description = description;
		this.flagNamesToDescriptions = flagNamesToDescriptions;
		this.author = author;
		this.exitCodes = exitCodes;
		this.examples = examples;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getSynopsis(){
		return this.synopsis;
	}
	
	public String getBrief(){
		return this.brief;
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
	
	public HashMap<String, String> getExitCodes(){
		return this.exitCodes;
	}
	
	public HashMap<String, String> getExamples(){
		return this.examples;
	}
}
