package main.java.com.sumtotal.automation.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class BaseSetup {


   private static ResourceBundle bundle = ResourceBundle.getBundle("envCredentials");


    public static WebDriver browserSetup(WebDriver driver){

        System.setProperty("webdriver.chrome.driver","src/driver/chromedriver.exe");
        driver= new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(bundle.getString("env"));
        return driver;
    }
}


