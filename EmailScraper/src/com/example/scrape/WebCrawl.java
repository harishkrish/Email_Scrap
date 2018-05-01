package com.example.scrape;

import java.io.IOException;
import java.util.HashSet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Class to scrape the URL
 * @author Harish Krishnamurthi
 */
public class WebCrawl {
	private HashSet<String> subLinks;
	/*
	 * Default constructor to initialize url links
	 */
	public WebCrawl(){
		this.subLinks = new HashSet<String>();
	}
	/* This method is used to set the sublinks within the given url
	 * @param url 
	 */
	public HashSet<String> getSubLinks(){
		return this.subLinks;
	}
	public void setSubLinks(String url){
		String http = "http://",https = "https://";
		String url1,url2;
		if(!url.startsWith(http) && !url.startsWith(https)){
			url1 = https+url;
			url2 = http+url;
			
		}
		else{
			if(url.startsWith(https)){
				url1 = url;
				url2 = http+url.substring(8);
			}
			else{
				url1 = https+url.substring(7);
				url2 = url;
			}
		}
		try {
			this.subLinks.add(url);
			Document doc = Jsoup.connect(url1).get();
			Elements links = doc.select("a[href]");
			for(Element link : links){
				String subLink = link.attr("abs:href");
				if((subLink.startsWith(url1) || subLink.startsWith(url2))&& !subLink.contains("?"))
					subLinks.add(subLink);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
