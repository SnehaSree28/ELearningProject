package main.java.com.sumtotal.automation.base.waiting;

import main.java.com.sumtotal.automation.base.BaseSetup;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import test.java.com.sumtotal.automation.tests.BaseTest;

public class Waiting extends BaseSetup {
    Logger log = Logger.getLogger(Waiting.class);

 // public static WebDriverWait wait = new WebDriverWait(driver,30);
   public static WebDriverWait wait ;

    public static void staticWait(int seconds) throws InterruptedException {
        Thread.sleep(seconds);

    }
    public  static WebDriverWait intializeWebDriverWait(int seconds){
        try {
            wait = new WebDriverWait(driver, seconds);
        }catch (NullPointerException e){
            e.printStackTrace();
        }
        return wait;
    }
    public static void waitForVisibility(WebElement element, int seconds){
        WebDriverWait wait = intializeWebDriverWait(seconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

}
