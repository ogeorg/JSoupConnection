package com.ogeorg.jsoupconnection.cookbook;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 * Cookbook - parsing document
 * 
 * @author POLGEO0
 *
 */
public class CB1ParseDoc
{
	public static void main(String[] args)
	{
		String html = "<html><head><title>First parse</title></head>"
				+ "<body><p>Parsed HTML into a doc.</p></body></html>";
		Document doc = Jsoup.parse(html);
		System.out.println(doc.title());
	}
}
