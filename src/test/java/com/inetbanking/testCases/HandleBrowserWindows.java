package com.inetbanking.testCases;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
	@Test
	public void tableHandling()
	{
		System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
		driver = new ChromeDriver();
		
		driver.get("http://ratings.fide.com");
		int rows = driver.findElements(By.xpath("//table/tbody/tr")).size();
		System.out.println(rows);
		int cols = driver.findElements(By.xpath("//table/thead/tr/th")).size();
		System.out.println(cols);
		/*
		 * for (int i = 1; i <= rows; i++) { for (int c = 1; c <= cols; c++) { String
		 * data =
		 * driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td["+c+"]")).getText();
		 * System.out.println(data + "      "); } }
		 */
		
		for (int r = 1; r <= rows; r++) 
		{
			String rank = driver.findElement(By.xpath("//table/tbody/tr["+r+"]/td[1]")).getText();
			if(rank.equals("100"))
			{
				String name = driver.findElement(By.xpath("//table/tbody/tr["+r+"]/td[2]")).getText();
				String country = driver.findElement(By.xpath("//table/tbody/tr["+r+"]/td[3]")).getText();
				System.out.println(name+" " + country);
			}
		
	}
		driver.quit();

	}
	
	@Test
	public void ManageWait()
	{
		System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
		driver = new ChromeDriver();
		
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		
		//implicit wait
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.findElement(By.name("q")).sendKeys("Selenium");
		driver.findElement(By.name("q")).sendKeys(Keys.RETURN);
		driver.findElement(By.xpath("//h3[text()='Selenium']")).click();
		WebDriverWait mywait = new WebDriverWait(driver, 3);
		WebElement element = mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[text()='Selenium']")));
		element.click();
	}
	@Test
	public void DatePicker() throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
		driver = new ChromeDriver();
		
		driver.get("http://www.redbus.in/");
		driver.manage().window().maximize();
		
		String year ="2022";
		String month ="July";
		String date = "10";
		
		driver.findElement(By.xpath("//input[@id='onward_cal']")).click();
		while(true)
		{
			String monthyear = driver.findElement(By.xpath("//td[@class='monthTitle']")).getText();
			String arr[]=monthyear.split(" ");
			String mon = arr[0];
			String yr = arr[1];
			Thread.sleep(2000);
			if(mon.equalsIgnoreCase(month) && yr.equals(year))
			{
				break;
			}
			else
			{
				driver.findElement(By.xpath("//td[@class='next']")).click();
			}
		}
		List<WebElement> alldates = driver.findElements(By.xpath("//table[@class='rb-monthTable first last']//td"));
		System.out.println(alldates.size());
		for(WebElement ele:alldates)
		{
			String dt=ele.getText();
			if(dt.equals(date))
			{
				ele.click();
				break;
			}
		}
		driver.quit();
		}
	}












