package com.inetbanking.testCases;

import java.io.IOException;

import org.junit.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.LoginPage;
 
public class TC_LoginTest_001 extends BaseClass{

	@Test
	public void loginTest() throws IOException
	{
		//setup("firefox");
		//driver.get(baseUrl);
		//System.out.println(username);
		logger.info("URL is opened");
		LoginPage lp = new LoginPage(driver);
		logger.info("Entered user name");
		lp.setUserName(username);
		logger.info("Entered user password");
		lp.setPassword(password);
		
		lp.clickSubmit();
		
		if(driver.getTitle().equals("Guru99 Bank Manager HomePage"))
		{
			Assert.assertTrue(true);
			logger.info("Login test passed");
		}
		else
		{
			captureScreen(driver,"loginTest");
			Assert.assertTrue(false);
			logger.info("login test failed");
		}
		
	}
}
