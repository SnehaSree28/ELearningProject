package test.java.com.sumtotal.automation.tests;

import main.java.com.sumtotal.automation.pages.*;
import org.apache.log4j.Logger;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ResourceBundle;

public class ODPMPTest extends BaseTest{
  //  WebDriver driver;
    ResourceBundle bundle= ResourceBundle.getBundle("envCredentials");
    DashboardPage dashboardPage;
    ODPMPPage odpmpPage;
    LoginPage loginPage;
    ReportPage reportPage;

    Logger log=Logger.getLogger(ODPMPTest.class);

    @BeforeClass
    public void loginODPMP(){

        if (bundle.getString("userName").equals("psadmin")) {
            loginPage = new LoginPage(driver);
            dashboardPage = loginPage.login(bundle.getString("userName"), bundle.getString("password"));

        } else {
            odpmpPage = new ODPMPPage(driver);
                odpmpPage.odpmpLogin(bundle.getString("userNme"), bundle.getString("pwd"));
                odpmpPage.launchSiteInAnotherTab();
        }
    }

   /* public void launchSiteInAnotherTab() {
      odpmpPage= new ODPMPPage(driver);
      odpmpPage.searchSite_Key(bundle.getString("odpmp.search"));
      odpmpPage.openBrowserInNewTab();
    }*/

    @Test(priority = 2)
    public void co_search(){

        SearchPage searchPage = new SearchPage(driver);
        boolean search=searchPage.searchReport();
        Assert.assertTrue(search,"searchSite_Key functionality is failed");
    }
    @Test(priority = 3)
    public void co_report() throws InterruptedException {

        reportPage= new ReportPage(driver);
        DashboardPage dashboardPage = reportPage.navigateToAdminIcon();
        reportPage.navigationPath();
        log.info("Navigated to menus option of reporting page");
        reportPage.navigationMenu();
        boolean b = reportPage.navigateAudienceUserAssociationSummary();
        Assert.assertTrue(b,"Report is not displayed");
    }
    @Test(priority = 4)
    public void co_fileUpload(){

    }
}
