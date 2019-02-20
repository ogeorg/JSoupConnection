package com.ogeorg.jsoupconnection.joelmin;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Connection.KeyVal;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.FormElement;

/**
 * Class for testing connection 2 github
 * 
 * @author POLGEO0
 *
 */
public class Log2Github
{
	String	host			= "github.com";
	String	origin			= "https://github.com";
	String	USER_AGENT		= "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/72.0.3626.109 Safari/537.36";
	String	loginFormUrl	= "https://github.com/login";
	String	logoutActionUrl	= "https://github.com/logout";
	String	loginActionUrl	= "https://github.com/session";
	String	username		= "oliviergeorg@gmail.com";
	String	password		= "pa55w0rd";

	public static void main(String[] args) throws IOException
	{
		setProxy();
		new Log2Github().run();
		clearProxy();
	}

	public void run() throws IOException
	{
		Connection.Response loginForm = Jsoup.connect(loginFormUrl)
				.method(Connection.Method.GET).userAgent(USER_AGENT).execute();

		Map<String, String> cookies = loginForm.cookies();
		print(cookies);

		Document doc = loginForm.parse();
		FormElement form = form(doc);
		
		Map<String, String> formData = formData(form);
		formData.put("login", username);
		formData.put("password", password);
		
		print(formData);
		
		Connection.Response homePage = Jsoup.connect(loginActionUrl)
				.cookies(cookies).data(formData).method(Connection.Method.POST)
				.userAgent(USER_AGENT).execute();
		
		cookies = homePage.cookies();
		doc = homePage.parse();
		form = form(doc, "logout-form");
		formData = formData(form);
		
		print("=========== homePage ============");
		print(formData);
		
		Connection.Response logoutPage = Jsoup.connect(logoutActionUrl)
				.cookies(cookies).data(formData).method(Connection.Method.POST)
				.userAgent(USER_AGENT).execute();

		print(logoutPage.parse());
		
	}

	/**
	 * @param doc
	 * @return
	 */
	public FormElement form(Document doc)
	{
		FormElement form = (FormElement) doc.getElementsByTag("form")
				.first();
		return form;
	}
	
	public FormElement form(Document doc, String className)
	{
		FormElement form = (FormElement) doc.getElementsByClass(className)
				.first();
		return form;
	}

	

	private Map<String, String> formData(FormElement form) {
		List<KeyVal> listData = form.formData();
		Map<String, String> formData = new HashMap<>();
		for(KeyVal kv: listData) {
			formData.put(kv.key(), kv.value());
		}
		return formData;
	}
	private static void print(Object obj)
	{
		System.out.println(obj);
	}

	/**
	 * 
	 */
	public static void clearProxy()
	{
		System.clearProperty("http.proxyHost");
		System.clearProperty("https.proxyPort");

		System.clearProperty("https.proxyHost");
		System.clearProperty("http.proxyPort");
	}

	/**
	 * 
	 */
	public static void setProxy()
	{
		System.setProperty("https.protocols", "TLSv1.2");

		System.setProperty("http.proxyHost", "proxy.sare.gipuzkoa.net");
		System.setProperty("http.proxyPort", "8080");

		System.setProperty("https.proxyHost", "proxy.sare.gipuzkoa.net");
		System.setProperty("https.proxyPort", "8080");
	}
}
