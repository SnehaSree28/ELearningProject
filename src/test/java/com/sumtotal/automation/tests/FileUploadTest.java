package test.java.com.sumtotal.automation.tests;

import main.java.com.sumtotal.automation.base.BaseSetup;
import main.java.com.sumtotal.automation.pages.DashboardPage;
import main.java.com.sumtotal.automation.pages.FileUploadPage;
import main.java.com.sumtotal.automation.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.util.ResourceBundle;

public class FileUploadTest {

    WebDriver driver;
    DashboardPage dashboardPage;
    ResourceBundle bundle = ResourceBundle.getBundle("envCredentials");

    @BeforeMethod
    public void admin(){

        if(bundle.getString("userName").equals("psadmin")){
            LoginPage loginPage = new LoginPage(driver);
            dashboardPage =  loginPage.login(bundle.getString("userName"),bundle.getString("password"));
        }else {
            LoginPage loginPage = new LoginPage(driver);
            dashboardPage =  loginPage.login(bundle.getString("userName"),bundle.getString("password"));

        }
    }
    @Test(description = "To verify whether file is uploading or not")
    public void co_fileUpload() throws InterruptedException, AWTException
    {
        FileUploadPage fileUpload = new FileUploadPage(driver);
        fileUpload.navigateLearningActivities();
        fileUpload.uploadFile();
        String activityCode = fileUpload.getActivityCode();
        System.out.println("Activity Code is " +activityCode);
        boolean aCodeStaging=  fileUpload.aNameVisibleOrNot(activityCode);
        Assert.assertTrue(aCodeStaging,"File is in Staging");
        fileUpload.close();
    }


}
