package com.example.scrape;

import java.util.HashSet;
/**
 * Main class
 * @author Harish Krishnamurthi
 */
public class FindEmail {
	// email Addresses of the link and sublinks
	private HashSet<String> emailAddresses;
	/*
	 * Constructor
	 */
	public FindEmail(){
		emailAddresses = new HashSet<String>();
	}
	/*
	 * getter for all emails 
	 */
	public HashSet<String> getEmails(){
		return this.emailAddresses;
	}
	/*
	 * setter for all emails
	 */
	public void setEmails(String email){
		this.emailAddresses.add(email);
	}
	/*
	 * print the emails
	 */
	public void printEmails(){
		if(this.emailAddresses.size() ==0)
			System.out.println("No Emails found");
		else{
			System.out.println("Email addresses in this website :"+this.emailAddresses.size());
			for(String email: this.emailAddresses)
				System.out.println(email);
		}
	}
	/*
	 * main method
	 */
	public static void main(String args[]){
		// if no url is provided
		if(args.length==0){
			System.out.println("Please enter the url");
			return;
		}	
		FindEmail findEmail = new FindEmail();
		String url = args[0];
		String https ="https://",http = "http://";
		if(!url.startsWith(http) && !url.startsWith(https)){
			url = https+url;
		}
		//Scraping URL for all links
		WebCrawl webCrawl = new WebCrawl();
		webCrawl.setSubLinks(url);
		//Scrapping emails in each sublink
		for(String subLink : webCrawl.getSubLinks()){
			EmailScrap emailScrap = new EmailScrap();
			emailScrap.setEmails(subLink);
			HashSet<String> emails = emailScrap.getEmails();
			for(String email: emails){
				findEmail.setEmails(email);
			}
			
		}
		//print emails
		findEmail.printEmails();
	}
}
