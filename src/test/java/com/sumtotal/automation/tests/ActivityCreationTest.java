package test.java.com.sumtotal.automation.tests;

import main.java.com.sumtotal.automation.base.BaseSetup;
import main.java.com.sumtotal.automation.common.CommonMethods;
import main.java.com.sumtotal.automation.pages.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ResourceBundle;

public class ActivityCreationTest extends BaseTest{

 //   WebDriver driver;
    ResourceBundle bundle = ResourceBundle.getBundle("envCredentials");
    DashboardPage dashboardPage;
    ActivityCreationPage activityCreationPage;
    @BeforeMethod
    public void loginToSite() {
        if (bundle.getString("userName").equals("psadmin")) {
           LoginPage loginPage= new LoginPage(driver);
            dashboardPage = loginPage.login(bundle.getString("userName"), bundle.getString("password"));

        } else {
           ODPMPPage odpmpPage= new ODPMPPage(driver);
           odpmpPage.odpmpLogin(bundle.getString("userName"), bundle.getString("password"));
        }
    }


    @Test
    public void activityCreation() throws InterruptedException {
        activityCreationPage = new ActivityCreationPage(driver);
        ActivityCreationPage activityCreationPage = dashboardPage.goToLearningActivities();
        activityCreationPage.goToNewActivity();
        activityCreationPage.createNewActivity("NewActivity"+CommonMethods.getCurrentTimeStamp(),
                "ActivityCode"+CommonMethods.getCurrentTimeStamp());
        activityCreationPage.searchAudienceName("ASP Audience");
    }
}
