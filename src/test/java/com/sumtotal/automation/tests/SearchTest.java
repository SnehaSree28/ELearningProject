package test.java.com.sumtotal.automation.tests;

import main.java.com.sumtotal.automation.base.BaseSetup;
import main.java.com.sumtotal.automation.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.util.ResourceBundle;

public class SearchTest extends BasePage {
    //WebDriver driver;
    SearchPage searchPage;
    DashboardPage dashboardPage;

    ResourceBundle bundle = ResourceBundle.getBundle("envCredentials");
  /*  @BeforeMethod
    public void launchAndLogin() {
        if (bundle.getString("userName").equals("psadmin")) {
            LoginPage loginPage = new LoginPage(driver);
            dashboardPage = loginPage.login(bundle.getString("userName"), bundle.getString("password"));
        } else {
            LoginPage loginPage = new LoginPage(driver);
            dashboardPage = loginPage.login(bundle.getString("userName"), bundle.getString("password"));
        }
    }*/



    @Test(description = "To search the reports")
    public void co_search()
    {
        /*ITestNGMethod method = result.getMethod();
        log.info("Result is"+method.getMethodName());*/
        searchPage = new SearchPage(driver);
        boolean search=searchPage.searchReport();
        Assert.assertTrue(search,"searchSite_Key functionality is failed");
    }
}
