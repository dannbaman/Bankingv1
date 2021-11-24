package com.inetbanking.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;

public class TC_loginDDD_002 extends BaseClass{

	@Test(dataProvider="getData")
	public void loginDDT(String user, String pwd) throws InterruptedException, IOException
	{
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(user);
		logger.info("username provided");
		lp.setPassword(pwd);
		logger.info("Password provided");
		lp.clickSubmit();
		logger.info("clicked submit");
		Thread.sleep(3000);
		if(isAlertPresent()==true)
		{
			Thread.sleep(2000);
			driver.switchTo().alert().accept();//close alert
			captureScreen(driver,"DataDriventest");
			driver.switchTo().defaultContent();
			Assert.assertTrue(false);
			logger.warn("failure to login");
		}
		else
		{ 
			Assert.assertTrue(true);
			logger.info("Login passed");
			Thread.sleep(3000);
			lp.clickLogout();
			driver.switchTo().alert().accept();//close alert
			driver.switchTo().defaultContent();
		}
		
	}
	 
	public boolean isAlertPresent()
	{
		try {
			driver.switchTo().alert();
			return true;
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	@DataProvider
	public Object[][] getData()
	{
		// row stands for how many different data types test should run
		Object[][] data = new Object[2][2];
		data[0][0]="mngr368148";
		data[0][1]="EmuhYsE";
		
		
		data[1][0]="restricted@gmail.com";
		data[1][1]="123456";
		
		
		return data;
	}
	}
