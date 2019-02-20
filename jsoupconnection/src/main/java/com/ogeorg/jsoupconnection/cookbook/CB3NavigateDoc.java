package com.ogeorg.jsoupconnection.cookbook;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CB3NavigateDoc
{
	public static void main(String[] args) throws IOException
	{
		setProxy();

		// String url = "http://example.com";
		String url = "http://m.20min.ch/ro/frontpage";
		Document doc = Jsoup.connect(url).get();
		Element content = doc.getElementById("content");
		content = doc.getElementsByTag("div").first();
		Elements links = content.getElementsByTag("a");
		for (Element link : links) {
			String linkHref = link.attr("href");
			String linkText = link.text();
			System.out.println(linkText + ": " + linkHref);
		}

		Elements pngs = doc.select("img[src$=.jpg]");
		for (Element png : pngs) {
			String text = png.attr("alt");
			System.out.println(text);
		}

		Elements headlines = doc.select(".storylistset article .storytitle .headline"); // direct a after h3
		for (Element headline : headlines) {
			System.out.println(headline);
		}
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
}
