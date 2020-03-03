package com.test;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.db.MySqlCon;
import com.pageObjects.AtlasAdminPage;
import com.pojo.AtlasLoginModel;


public class AtlasLoginTest 
{
	WebDriver driver;
	@Test
	public void loginByConnectingDB() throws InterruptedException, ClassNotFoundException, SQLException
	{
		System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
	      driver= new ChromeDriver();
		  System.out.println("Chrome Driver Started");
		  driver.manage().window().maximize();
		AtlasAdminPage atlas=PageFactory.initElements(driver, AtlasAdminPage.class);
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(5000);
		System.out.println("****************");
		driver.get("http://a8206659cf96511e9a9240699a9b962e-1724146569.ap-southeast-1.elb.amazonaws.com:8080/spc/login");
		//MySqlCon.getData();
		AtlasLoginModel atls =MySqlCon.getData();
	    atlas.login.sendKeys(atls.getUsername());
	    atlas.password.sendKeys(atls.getPassword());
        atlas.loginbutton.click();		
	    Thread.sleep(3000);
	    driver.close();
		
	}

}
