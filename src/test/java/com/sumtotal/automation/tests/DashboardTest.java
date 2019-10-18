package test.java.com.sumtotal.automation.tests;

import main.java.com.sumtotal.automation.base.BaseSetup;
import main.java.com.sumtotal.automation.pages.DashboardPage;
import main.java.com.sumtotal.automation.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ResourceBundle;

public class DashboardTest extends BaseTest{
//    WebDriver driver;
    DashboardPage dashboardPage;
    LoginPage loginPage;
    ResourceBundle bundle = ResourceBundle.getBundle("envCredentials");

//@BeforeTest
/*public void admin(){
    driver = BaseSetup.browserSetup(driver);
    if(bundle.getString("userName").equals("psadmin")){
        LoginPage loginPage = new LoginPage(driver);
        dashboardPage =  loginPage.login(bundle.getString("userName"),bundle.getString("password"));
    }else {
        LoginPage loginPage = new LoginPage(driver);
        dashboardPage =  loginPage.login(bundle.getString("userName"),bundle.getString("password"));
    }*/
//}

    @BeforeMethod
    public void dashboard(){

        if (bundle.getString("userName").equals("psadmin")) {
            loginPage = new LoginPage(driver);
        dashboardPage = loginPage.login(bundle.getString("userName"), bundle.getString("password"));

        } else {
            loginPage = new LoginPage(driver);
        dashboardPage=    loginPage.login(bundle.getString("userName"), bundle.getString("password"));

        }
        dashboardPage.adminIcon.click();
    }

    @Test
    public void test()
    {
        System.out.println("hello");
    }
}
