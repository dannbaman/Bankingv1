package com.inetbanking.testCases;

import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.inetbanking.pageObjects.AddCustomerPage;
import com.inetbanking.pageObjects.LoginPage;

public class TC_AddCustomerTest_003 extends BaseClass {

	@Test
	public void addNewCustomer() throws InterruptedException, IOException
	{
		LoginPage lp = new LoginPage(driver);
		lp.setUserName(username);
		logger.info("Username is provided");
		lp.setPassword(password);
		logger.info("Password is provided");
		lp.clickSubmit();
		
		Thread.sleep(2000);
		
		AddCustomerPage addcust = new AddCustomerPage(driver);
		logger.info("Providing Customer details....");
		addcust.clickAddNewCustomer();
		addcust.custName("Daniel");
		addcust.custgender("male");
		addcust.custdob("10","15","1985");
		Thread.sleep(1000);
		addcust.custaddress("USA");
		addcust.custcity("Charlotte");
		addcust.custstate("NC");
		addcust.custpinno("5000074");
		addcust.custtelephoneno("987890091");
		
		String email = randomString()+"@gmail.com";
		addcust.custemailid(email);
		addcust.custpassword("adsasd");
		addcust.custsubmit();
		Thread.sleep(1000);
		logger.info("Validation started");
		//boolean res=driver.getPageSource().contains("Customerr Registered Successfully!!!");
		boolean res = addcust.PageSourcetext().getText().contains("Customer Registered Successfully!!!");
	
		if(res==true)
		{
			Assert.assertTrue(true);
			logger.info("test case passed....");
			
		}
		else
		{
			captureScreen(driver,"addNewCustomer");
			logger.info("test case failed....");
			//captureScreen(driver,"addNewCustomer");
			Assert.assertTrue(false);
		}
		
		
	}
	
 
}
