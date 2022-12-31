//package com.dotdash.pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.AWTException;
import java.util.List;

public class DynamicContentPage extends BasePageObject {
	private final int WAIT_TIME = 10;

    private final By remove_checkbox_btn = By.xpath("//*[@id='checkbox-example']/button");
    private final By check_box = By.id("checkbox");
    private final By text_field = By.xpath("//*[@id='input-example']/input");
    private final By disable_textfield_btn = By.xpath("//*[@id='input-example']/button");

    public DynamicContentPage(WebDriver driver) throws AWTException {
         openBrowser();
         driver.navigate().to(driver.getCurrentUrl() + "dynamic_controls");
    }

    public DynamicContentPage click_on_remove_checkbox_button() {
        driver.findElement(remove_checkbox_btn).click();
        return this;
    }

    public void verify_checkbox_is_removed() {
        boolean is_checkbox_visible = true;
        try {
            driver.findElement(check_box).isDisplayed();
        } catch (Exception e) {
            is_checkbox_visible = false;
        }
        Assert.assertFalse(is_checkbox_visible, "[ERR] Checkbox is still display after " + WAIT_TIME + "s");
    }

    public DynamicContentPage click_to_enable_text_field() {
        driver.findElement(disable_textfield_btn).click();
        return this;
    }

    public void verify_text_field_is_disable() {
        verify_text_field_state(false);
    }

    public void verify_text_field_is_enabled() {
        verify_text_field_state(true);
    }
    @Test
    private void verify_text_field_state(boolean isEnable) {
        boolean is_textfield_enabled = true;
        try {
            driver.findElement(text_field).isEnabled();
        } catch (Exception e) {
            is_textfield_enabled = !isEnable;
        }
        if (is_textfield_enabled) {
            Assert.assertTrue(is_textfield_enabled, "[ERR] Checkbox is still disable after " + WAIT_TIME + "s");
        } else {
            Assert.assertFalse(is_textfield_enabled, "[ERR] Checkbox is still enabled after " + WAIT_TIME + "s");
        }
    }
}