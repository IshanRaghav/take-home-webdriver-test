import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


import java.awt.AWTException;
import java.io.File;
import java.io.IOException;

public class FileDownloadPage extends BasePageObject {
    
    private By file_hyperlink = By.xpath("//*[@id='content']/div/a");
    private final String FILE_NAME = "some-file.txt";
    private String path = System.getProperty("user.dir");
    private String file_path = path + "/downloads/" + FILE_NAME;

    public FileDownloadPage(WebDriver driver) throws AWTException {
    	openBrowser();
        driver.navigate().to(driver.getCurrentUrl() + "download");
    }

    public FileDownloadPage click_on_file_link_to_download() throws InterruptedException {
        delete_if_file_existing(file_path);
        driver.findElement(file_hyperlink).click();
        Thread.sleep(100);
        return this;
    }

    private boolean is_file_existing(String path) {
        File file = new File(path);
        return file.exists();
    }

    private void delete_if_file_existing(String path) {
        File file = new File(path);
        if (is_file_existing(path)) file.delete();
    }

    public void verify_file_is_downloaded() {
        delete_if_file_existing(path);
    }
}