import java.awt.AWTException;
import java.awt.Desktop.Action;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
//import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;
//import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class demoTest{

	public static WebDriver driver;
	
	@BeforeTest
	public void openBrowser() throws AWTException
	{
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\drapat1\\Downloads\\webdriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://localhost:7080/");
		driver.manage().window().maximize();
		
	}
//	@Test 
//	public void loginSucess() throws AWTException
//	{
//		
////		Format used to login and authorized using windows alert box is https://tomsmith:SuperSecretPassword!@urlhttp//localhost:7080/basic_auth
//		
//		driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[3]/a")).click();
//		
//		String username = "tomsmith";
//		String password = "SuperSecretPassword!";
//		
//		String URL = "https://" + username + ":" + password + "@" + "http://localhost:7080/basic_auth/";
//		driver.get(URL);
//		Alert alert = driver.switchTo().alert();
//		alert.accept();
//        
//        driver.quit();
//	}
	
	@Test 
	public void checkbox() throws AWTException
	{
		openBrowser();
		driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[6]/a")).click();
		
		WebElement checkbox1 = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[1]"));
		if(!checkbox1.isSelected())
			checkbox1.click();
		
		
		WebElement checkbox2 = driver.findElement(By.xpath("//*[@id=\"checkboxes\"]/input[2]"));
		if(!checkbox2.isSelected())
			checkbox2.click();
		
		if(checkbox1.getAttribute("checked").contentEquals("true") || checkbox2.getAttribute("checked").contentEquals("true"))
			Assert.assertTrue(true,"Passed");
		else Assert.assertTrue(false,"Checkbox not checked");
		
		driver.quit();
		
	}
	
	
	@Test 
	public void contextMenu() throws AWTException
	{
		openBrowser();
		driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[7]/a")).click();
		
		Actions actions = new Actions(driver);
		WebElement elementLocator = driver.findElement(By.xpath("//*[@id=\"hot-spot\"]"));
		actions.contextClick(elementLocator).perform();
		
		String expectedErrorMsg = "You selected a context menu";
        String actualErrorMsg = driver.switchTo().alert().getText();
        
        Assert.assertEquals(actualErrorMsg, expectedErrorMsg); 
        driver.quit();
        
        
	}
	
//	@Test 
//	public void dragAndDrop() throws AWTException
//	{
//		openBrowser();
//		driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[10]/a")).click();
//     
//        Actions action = new Actions(driver); 
//		WebElement from = driver.findElement(By.id("column-a"));
//		WebElement to = driver.findElement(By.id("column-b"));	 
//		//Perform drag and drop
//		org.openqa.selenium.interactions.Action dragandDrop = action.dragAndDrop(from, to).build();
//		dragandDrop.perform();
//		
//		int x = from.getLocation().x; 
//		int y = to.getLocation().y;
//        
//        Actions actions = new Actions(driver); 
//        actions.moveToElement(from).pause(Duration.ofSeconds(1)).clickAndHold(from).pause(Duration.ofSeconds(1)).moveByOffset(x, y).moveToElement(to).moveByOffset(x,y).pause(Duration.ofSeconds(1)).release().build().perform(); 
//		
//		//verify text changed in to 'Drop here' box 
//		String textTo = to.getText();
//		System.out.println(textTo);
////		if(textTo.equals("Dropped!")) {
////			System.out.println("PASS: Source is dropped to target as expected");
////		}else {
////			System.out.println("FAIL: Source couldn't be dropped to target as expected");
////		}
//		
//		driver.quit();
//	}
	
	@Test 
	public void dropDown() throws AWTException
	{
		openBrowser();
		driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[11]/a")).click();
		
		WebElement testDropDown = driver.findElement(By.id("dropdown"));  
		Select dropdown = new Select(testDropDown);
		dropdown.selectByValue("1");
		dropdown.selectByValue("2");
		WebElement o = dropdown.getFirstSelectedOption();
	    String selectedoption = o.getText();
	    System.out.println("Option :" + selectedoption);

     
	    if(selectedoption.equals("Option 1"))
	    	Assert.assertEquals("Option 1", selectedoption); 
	    else Assert.assertEquals("Option 2", selectedoption); 

		driver.close();
	}
	
    
    public static boolean is_in_view_port(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean is_displayed = (Boolean) js.executeScript(
                "var elem = arguments[0],                 " +
                        "  box = elem.getBoundingClientRect(),    " +
                        "  cx = box.left + box.width / 2,         " +
                        "  cy = box.top + box.height / 2,         " +
                        "  e = document.elementFromPoint(cx, cy); " +
                        "for (; e; e = e.parentElement) {         " +
                        "  if (e === elem)                        " +
                        "    return true;                         " +
                        "}                                        " +
                        "return false;                            "
                , element
        );
        return is_displayed;
    }
    
	@Test
    public void dynamicContent() throws AWTException {
		openBrowser();

	    int time_to_refresh = 10;
	    int index=2;
	    driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[12]/a")).click();
	    driver.findElement(By.xpath("//*[@id=\"content\"]/div/p[2]/a")).click();
    
	    for (int i = 0; i < time_to_refresh; i++) {
            
            String beforeRefresh = driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[2]")).getText();
            driver.navigate().refresh();
           
            String afterRefresh = driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/div[2]")).getText(); 
            Assert.assertNotEquals(beforeRefresh,afterRefresh,"Dynamic Content was not changed.");
        }
        driver.close();
    }
	
	
	@Test
    public void floatingMenu() throws AWTException {
		openBrowser();
		driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[19]/a")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("window.scrollBy(0,-350)", "");
		Assert.assertTrue(is_in_view_port(driver, driver.findElement(By.id("menu"))));
		driver.close();
    }
	
	@Test
	public void iFrame() throws AWTException {
		
		openBrowser();
		driver.findElement(By.xpath("//*[@id=\"content\"]/ul/li[22]/a")).click();
		driver.findElement(By.xpath("//*[@id=\"content\"]/div/ul/li[2]/a")).click();
        driver.switchTo().frame(driver.findElement(By.id("mce_0_ifr")));
        String actualMsg = driver.findElement(By.id("tinymce")).getText();
        System.out.println("Actual Msg :"+ actualMsg);
        driver.switchTo().defaultContent();
        Assert.assertEquals("Your content goes here.", actualMsg);
        driver.close();
       
    }
	


	
	
	@AfterTest 
	public  void refreshBrowser()
	{
		driver.quit();
	}
}
