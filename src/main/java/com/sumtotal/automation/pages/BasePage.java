package main.java.com.sumtotal.automation.pages;

import main.java.com.sumtotal.automation.common.CommonConstants;
import main.java.com.sumtotal.automation.common.CommonMethods;
import org.apache.log4j.Logger;
import org.apache.maven.surefire.shade.common.org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import test.java.com.sumtotal.automation.tests.BaseTest;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;


public class BasePage extends BaseTest {
   // WebDriver driver;
    ResourceBundle bundle = ResourceBundle.getBundle("envCredentials");
    LoginPage loginPage;
    DashboardPage dashboardPage;
    WebDriverWait wait;
    public static Logger log = Logger.getLogger(BasePage.class);

    public WebDriverWait waiting() {
        wait = new WebDriverWait(driver, 30);
        return waiting();
    }

    @BeforeClass
    public void launchAndLogin() {
        if (bundle.getString("userName").equals("psadmin")) {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(bundle.getString("userName"), bundle.getString("password"));
        } else {
            ODPMPPage odpmpPage = new ODPMPPage(driver);
            odpmpPage.odpmpLogin(bundle.getString("userNme"), bundle.getString("pwd"));
            odpmpPage.launchSiteInAnotherTab();
        }
    }

    @AfterClass
    public void logout(){
        loginPage = new LoginPage(driver);
        loginPage.logout();
    }


    /*@AfterClass
    public void takescreenshot(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                String methodname = result.getName();
                log.info("Method  name is" + methodname);
                log.info("Trying to take screenshot");
                File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(file, new File(CommonConstants.screenshot + methodname + CommonMethods.getCurrentTimeStamp() + ".png"));
                log.info("Screenshot Taken");
            } catch (Exception e) {
                System.out.println("Exception while taking screenshot " + e.getMessage());
            }
        }

    }*/
}
           // log.info("Navigate to logout button");
       /* driver.switchTo().parentFrame();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebElement self_header=driver.findElement(By.id("self_header"));
        log.info("Clicked on self header");
        waiting().until(ExpectedConditions.visibilityOf(self_header));
        self_header.click();
        log.info("Self Header is clicked");
        WebElement sign_out=driver.findElement(By.xpath("//span[text()='Signout']"));
        waiting().until(ExpectedConditions.visibilityOf(sign_out));
        log.info("Clicked on Sign Out");
        sign_out.click();*/

//}
