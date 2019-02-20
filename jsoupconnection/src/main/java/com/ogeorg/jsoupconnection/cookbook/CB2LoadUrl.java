package com.ogeorg.jsoupconnection.cookbook;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class CB2LoadUrl
{
	public static void main(String[] args) throws IOException
	{
		getWithSystemProxy();
		getWithJSoupProxy();
	}

	/**
	 * @throws IOException
	 */
	public static void getWithSystemProxy() throws IOException
	{
		setProxy();
		
		Document doc = Jsoup.connect("http://example.com/").get();
		String title = doc.title();
		System.out.println(title);

		clearProxy();
	}

	/**
	 * 
	 */
	public static void clearProxy()
	{
		System.clearProperty("http.proxyHost");
		System.clearProperty("http.proxyPort");
	}

	/**
	 * 
	 */
	public static void setProxy()
	{
		System.setProperty("http.proxyHost", "proxy.sare.gipuzkoa.net");
		System.setProperty("http.proxyPort", "8080");
	}
	
	/**
	 * @throws IOException
	 */
	public static void getWithJSoupProxy() throws IOException
	{
		Document doc = Jsoup
				.connect("http://example.com/")
				.proxy("proxy.sare.gipuzkoa.net", 8080)
				.get();
		String title = doc.title();
		System.out.println(title);
	}

}
