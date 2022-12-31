//package com.dotdash.pageobject;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BasePageObject {

    public WebDriver driver;

	public void openBrowser() throws AWTException
	{
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\drapat1\\Downloads\\webdriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://localhost:7080/");
		driver.manage().window().maximize();
	}
    
	
    
}