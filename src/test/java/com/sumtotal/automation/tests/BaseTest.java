package test.java.com.sumtotal.automation.tests;



import main.java.com.sumtotal.automation.base.BaseSetup;

import main.java.com.sumtotal.automation.pages.DashboardPage;
import main.java.com.sumtotal.automation.pages.LoginPage;
import main.java.com.sumtotal.automation.pages.ODPMPPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
;

import java.util.ResourceBundle;

public class BaseTest {
public WebDriver driver;
ResourceBundle bundle = ResourceBundle.getBundle("envCredentials");
DashboardPage dashboardPage;
LoginPage loginPage;
ODPMPPage odpmpPage;

 @BeforeSuite
    public void launchDriver() {
     driver = BaseSetup.browserSetup(driver);

 }/*
    @AfterSuite

    public void quit(){
     driver.quit();
    }
*/


}