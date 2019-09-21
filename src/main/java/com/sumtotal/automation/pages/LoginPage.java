package main.java.com.sumtotal.automation.pages;

import main.java.com.sumtotal.automation.base.BaseSetup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ResourceBundle;

public class LoginPage {
    WebDriver driver;


    @FindBy(id="BodyContent_MainContent_MainContentPlaceHolder_UserName")
    public WebElement userName;

    @FindBy(id="BodyContent_MainContent_MainContentPlaceHolder_Password")
    public WebElement passWord;

    @FindBy(id="BodyContent_MainContent_MainContentPlaceHolder_LoginButton")
    public WebElement signIn;

    @FindBy(xpath = "//input[@value='I Agree']")
    public WebElement iAgree;


    public  LoginPage(WebDriver driver){
        this.driver=driver;

        PageFactory.initElements(driver, this);
    }



    public DashboardPage  login(String username, String password) {
        userName.sendKeys(username);
        passWord.sendKeys(password);
        signIn.click();
        if(isEnabled("//input[@value='I Agree']")){
            driver.findElement(By.xpath("//input[@value='I Agree']")).click();
        }
        return new DashboardPage(driver);

    }

    public boolean isEnabled(String locator){
        try{
            driver.findElement(By.xpath(locator));
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
