package test.java.com.sumtotal.automation.tests;

import main.java.com.sumtotal.automation.base.BaseSetup;
import main.java.com.sumtotal.automation.pages.DashboardPage;
import main.java.com.sumtotal.automation.pages.LoginPage;
import main.java.com.sumtotal.automation.pages.ODPMPPage;
import main.java.com.sumtotal.automation.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ResourceBundle;

public class SearchTest extends BaseTest {
    //WebDriver driver;
    SearchPage searchPage;
    DashboardPage dashboardPage;

    ResourceBundle bundle = ResourceBundle.getBundle("envCredentials");
    @BeforeMethod
    public void launchAndLogin() {
        driver = BaseSetup.browserSetup(driver);
        if (bundle.getString("userName").equals("psadmin")) {
            LoginPage loginPage = new LoginPage(driver);
            dashboardPage = loginPage.login(bundle.getString("userName"), bundle.getString("password"));
        } else {
            LoginPage loginPage = new LoginPage(driver);
            dashboardPage = loginPage.login(bundle.getString("userName"), bundle.getString("password"));
        }
    }
    @Test(description = "To search the reports")
    public void co_search()  {
        searchPage = new SearchPage(driver);
        boolean search=searchPage.searchReport();
        Assert.assertTrue(search,"searchSite_Key functionality is failed");

    }

}
