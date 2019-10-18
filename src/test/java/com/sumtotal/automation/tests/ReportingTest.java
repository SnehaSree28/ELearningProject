package test.java.com.sumtotal.automation.tests;

import main.java.com.sumtotal.automation.base.BaseSetup;
import main.java.com.sumtotal.automation.pages.BasePage;
import main.java.com.sumtotal.automation.pages.DashboardPage;
import main.java.com.sumtotal.automation.pages.LoginPage;
import main.java.com.sumtotal.automation.pages.ReportPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ResourceBundle;


public class ReportingTest extends BasePage {

    ReportPage reportPage;
    DashboardPage dashboardPage;
    Logger log = Logger.getLogger(ReportingTest.class);

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
    @Test(description = "To check the reports are displaying or not")
    public void navigatingToReports() throws InterruptedException {
        reportPage= new ReportPage(driver);
        reportPage.navigateToAdminIcon();
        reportPage.navigationPath();
        log.info("Navigated to menus option of reporting page");
        reportPage.navigationMenu();
        boolean b = reportPage.navigateAudienceUserAssociationSummary();
        Assert.assertTrue(b,"Report is not displayed");
    }
   /* @AfterClass
    public void quit(){
        driver.quit();
    }*/
}
