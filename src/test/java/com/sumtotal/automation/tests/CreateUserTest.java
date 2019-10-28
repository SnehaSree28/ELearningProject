package test.java.com.sumtotal.automation.tests;

import main.java.com.sumtotal.automation.base.BaseSetup;
import main.java.com.sumtotal.automation.common.CommonMethods;
import main.java.com.sumtotal.automation.pages.CreateUserPage;
import main.java.com.sumtotal.automation.pages.DashboardPage;
import main.java.com.sumtotal.automation.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ResourceBundle;

public class CreateUserTest {
    WebDriver driver;
    ResourceBundle bundle = ResourceBundle.getBundle("envCredentials");
    DashboardPage dashboardPage;
    CreateUserPage createUserPage;

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
    @Test(description = "To create user ")
    public void creteUserTest(){
        dashboardPage = new DashboardPage(driver);
        createUserPage= new CreateUserPage(driver);
        CreateUserPage createUserPage = dashboardPage.goToCreateUser();
        createUserPage.createUser();
        createUserPage.selectUserType();
        createUserPage.selectUserLang("SampleMail"+ CommonMethods.getCurrentTimeStamp()+"@gmail.com","SampleFirstName"+CommonMethods.getCurrentTimeStamp(),
                "SampleLastNmme"+CommonMethods.getCurrentTimeStamp());
        boolean b = createUserPage.savedSuccessfullyAlert();
        Assert.assertTrue(b,"User succesffuly created");

    }
}
