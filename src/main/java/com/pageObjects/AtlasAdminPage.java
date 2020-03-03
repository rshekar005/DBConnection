package com.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AtlasAdminPage 
{
	@FindBy(how=How.CSS, using="#userName")
	public WebElement login;
	@FindBy(how=How.CSS, using="#password")
	public WebElement password;
	@FindBy(how=How.XPATH, using="//*[text()='Log In']")
	public WebElement loginbutton;
}
