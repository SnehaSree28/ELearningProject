package test.java.com.sumtotal.automation.tests;

import main.java.com.sumtotal.automation.base.BaseSetup;
import main.java.com.sumtotal.automation.pages.DashboardPage;
import main.java.com.sumtotal.automation.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ResourceBundle;

//Checking functionality of MErge
public class LoginTest extends BaseTest {

    ResourceBundle bundle = ResourceBundle.getBundle("envCredentials");

    //Created this page for just checking

//    @BeforeTest
    public void login(){


// Checking Merge functionality
    }


    @Test
    public void test(){
        System.out.println("testing merge fuctionality");

    }
}
