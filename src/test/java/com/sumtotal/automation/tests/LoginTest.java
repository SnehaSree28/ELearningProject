package test.java.com.sumtotal.automation.tests;

import main.java.com.sumtotal.automation.base.BaseSetup;
import main.java.com.sumtotal.automation.common.CommonConstants;
import main.java.com.sumtotal.automation.common.CommonMethods;
import main.java.com.sumtotal.automation.pages.BasePage;
import main.java.com.sumtotal.automation.pages.DashboardPage;
import main.java.com.sumtotal.automation.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ResourceBundle;

//Checking functionality of MErge
public class LoginTest extends BasePage {

    ResourceBundle bundle = ResourceBundle.getBundle("envCredentials");
    LoginPage login;

   /* @BeforeMethod
    public void launchAndLogin() {
        // BaseSetup.browserSetup2();
        if (bundle.getString("userName").equals("psadmin")) {
            LoginPage loginPage = new LoginPage(driver);
            dashboardPage = loginPage.login(bundle.getString("userName"), bundle.getString("password"));
        } else {
            LoginPage loginPage = new LoginPage(driver);
            dashboardPage = loginPage.login(bundle.getString("userName"), bundle.getString("password"));
        }
    }
*/
    @Test
    public void test() throws IOException {
        System.out.println("testing merge fuctionality");
        String path =  CommonConstants.screenshot+ CommonMethods.getCurrentTimeStamp();
        System.out.println(path);
        login= new LoginPage(driver);

    }
}
