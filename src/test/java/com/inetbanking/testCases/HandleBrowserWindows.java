package com.inetbanking.testCases;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.inetbanking.utilities.ReadConfig;

public class HandleBrowserWindows {
	public static WebDriver driver;
	ReadConfig readconfig = new ReadConfig();
	
	@Test
	public void windows(){
		System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
		driver = new ChromeDriver();
		driver.get("http://opensource-demo.orangehrmlive.com/");
		driver.findElement(By.xpath("//a[text()='OrangeHRM, Inc']")).click();
		Set<String>windowIDs = driver.getWindowHandles(); // returns ids of multiple browser windows
		Iterator<String>it=windowIDs.iterator();
		String parentID= it.next();
		String childID = it.next();
		
		System.out.println(parentID);
		System.out.println(childID);
		driver.close();
		

	}
	@Test
	public void BrokenLinks() throws MalformedURLException {
		System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
		driver = new ChromeDriver();
		driver.get("http://www.deadlinkcity.com/");
		driver.manage().window().maximize();
		
		List<WebElement>links=driver.findElements(By.tagName("a"));
		System.out.println("Numbers of links "+links.size());
		/*
		 * for (int i = 0; i <= links.size(); i++) {
		 * System.out.println(links.get(i).getText());
		 * System.out.println(links.get(i).getAttribute("href")); }
		 */
		int brokenLinks=0;
		for(WebElement element:links)
		{
			String url = element.getAttribute("href");
			if(url==null || url.isEmpty())
			{
				System.out.println("URL is empty");
				continue;
			}
			URL link = new URL(url);
			try {
				HttpURLConnection httpconn=(HttpURLConnection) link.openConnection();
				httpconn.connect();
				if(httpconn.getResponseCode()>=400)
				{
					System.out.println(httpconn.getResponseCode()+" " + url + "is" +" Broken link");
					brokenLinks++;
				}
				else
				{
					System.out.println(httpconn.getResponseCode()+" " + url+  " is"+ " Valid Link");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		}
		System.out.println("Numbwe of broken links is " + brokenLinks);
		
		driver.quit();
	}

}
