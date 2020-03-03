package com.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.twilio.Twilio;

public class TwilioOTP
{
	static WebDriver driver;
	public static final String Authname="AC9915259d1e8d256a5edd729a58fe03f0";
	public static final String Authkey="92006f9219bda8eaa252ae3faefae990";
	
	public static void maain(String args[])
	{
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
	      driver= new ChromeDriver();
		  System.out.println("Chrome Driver Started");
		  
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  driver.get("https://www.amazon.in/ap/register?showRememberMe=true&openid.pape.max_auth_age=0&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&pageId=inflex&openid.return_to=https%3A%2F%2Fwww.amazon.in%2Fgp%2Fyourstore%2Fhome%3Fie%3DUTF8%26action%3Dsign-out%26path%3D%252Fgp%252Fyourstore%252Fhome%26ref_%3Dnav_youraccount_signout%26signIn%3D1%26useRedirectOnSuccess%3D1&prevRID=EAPTHTSYCRG4X8XHDFK7&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.ns.pape=http%3A%2F%2Fspecs.openid.net%2Fextensions%2Fpape%2F1.0&prepopulatedLoginId=&failedSignInCount=0&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");
		  
		  driver.findElement(By.name("customerName")).sendKeys("Rajashekar");
		  driver.findElement(By.id("auth-country-picker-container")).click();
		  driver.findElement(By.xpath("//ul[@role='application']//li//a[contains(text(),'United States +1')]")).click();
		  
		  driver.findElement(By.id("ap_phone_number")).sendKeys("2039028656");
		  driver.findElement(By.id("ap_password")).sendKeys("2323232");
		  driver.findElement(By.id("continue")).click();
		  
		  Twilio.init(Authname, Authkey);
          		  
	}

}
