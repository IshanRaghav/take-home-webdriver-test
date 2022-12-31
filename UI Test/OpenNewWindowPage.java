import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import javax.print.DocFlavor;

import java.awt.AWTException;
import java.util.ArrayList;

public class OpenNewWindowPage extends BasePageObject{
    private By hyperlink = By.linkText("Click Here");

    public OpenNewWindowPage(WebDriver driver) throws AWTException{
    	openBrowser();
        driver.navigate().to(driver.getCurrentUrl() + "windows");
    }

    public NewWinDowPage click_on_hyperlink_to_open_new_tab() throws AWTException{
        driver.findElement(hyperlink).click();
        ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver = driver.switchTo().window(tabs2.get(1));
        return new NewWinDowPage(driver);
    }
}