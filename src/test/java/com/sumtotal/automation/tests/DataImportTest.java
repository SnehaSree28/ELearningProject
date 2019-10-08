package test.java.com.sumtotal.automation.tests;

import main.java.com.sumtotal.automation.base.BaseSetup;
import main.java.com.sumtotal.automation.pages.DashboardPage;
import main.java.com.sumtotal.automation.pages.DataImportPage;
import main.java.com.sumtotal.automation.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ResourceBundle;

public class DataImportTest {

    //Incomplete need to complete it//

    WebDriver driver;
    DashboardPage dashboardPage;
    ResourceBundle bundle= ResourceBundle.getBundle("envCredentials");
    @BeforeClass
    public void launchAndLogin() {

        if (bundle.getString("userName").equals("psadmin")) {
            LoginPage loginPage = new LoginPage(driver);
            dashboardPage = loginPage.login(bundle.getString("userName"), bundle.getString("password"));

        } else {
            LoginPage loginPage = new LoginPage(driver);
            dashboardPage = loginPage.login(bundle.getString("userName"), bundle.getString("password"));
        }
    }


    @Test
    public void dashboard() throws InterruptedException {
        dashboardPage=new DashboardPage(driver);
        dashboardPage.goToDataImport();
        DataImportPage dataImport = new DataImportPage(driver);
        dataImport.uploadFilesDataImport();
    }
}
