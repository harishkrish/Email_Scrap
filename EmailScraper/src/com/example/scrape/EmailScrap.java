package com.example.scrape;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * Class to Extract email from link
 * @author Harish Krishnamurthi
 */
public class EmailScrap {
	//emails scrapped from the link
	private HashSet<String> emails;
	public EmailScrap(){
		emails = new HashSet<String>();
	}
	/*
	 * getter for emails 
	 * @return emails
	 */
	public HashSet<String> getEmails(){
		return this.emails;
	}
	/*
	 * method to scrap email from a given url
	 * @param url to scrap emails from
	 */
	public void setEmails(String strUrl){
		try {
			//reading the contents in the url
			URL url = new URL(strUrl);
			StringBuilder lines = new StringBuilder();
			String line="";
			BufferedReader read = new BufferedReader(new InputStreamReader(url.openStream()));
			while((line = read.readLine()) != null) {
                lines.append(line);
            }
			//email pattern matching
			String emailPattern = "\\b[a-zA-Z0-9.-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z0-9.-]+\\b";
			Pattern pattern = Pattern.compile(emailPattern);
			Matcher matcher = pattern.matcher(lines);
			while(matcher.find()) {
				emails.add(matcher.group());
	        }
			
		} catch (IOException e) {
			
		}
		catch(NullPointerException ne){
			System.out.println("URL not accessible");
		}
	}
}
