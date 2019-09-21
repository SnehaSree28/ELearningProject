package main.java.com.sumtotal.automation.pages;

import main.java.com.sumtotal.automation.common.CommonMethods;
import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import test.java.com.sumtotal.automation.tests.SearchTest;

public class CreateUserPage {

    Logger log = Logger.getLogger(CreateUserPage.class);
WebDriver driver;
WebDriverWait wait;
JavascriptExecutor js;

@FindBy(xpath = "//input[@id='user_1']")
public WebElement loginName;

@FindBy(xpath = "//input[@id='user_2']")
public WebElement password;

@FindBy(xpath = "//select[@id='user_10']")
public WebElement userType;

@FindBy(id="user_12")
public WebElement firstName;

@FindBy(id="user_15")
public WebElement lastName;

@FindBy(id="user_14")
public WebElement userLang;

@FindBy(id="user_17")
public WebElement email;

@FindBy(xpath = "//input[@type='submit']")
public WebElement save;

@FindBy(xpath = "//*[text()='User account successfully created']")
public WebElement savedSuccessfullyAlert;




public CreateUserPage(WebDriver driver){
    this.driver=driver;
    PageFactory.initElements(driver,this);
}
public WebDriverWait waiting(){
    wait= new WebDriverWait(driver,30);
    return wait;
}


public void scrollIntoView(WebElement element){
   js=(JavascriptExecutor)driver;
   js.executeScript("arguments[0].scrollIntoView(true);",element);
}

public void createUser(){

 String loginname="User" +CommonMethods.getCurrentTimeStamp();
 String pwd= "Password"+CommonMethods.getCurrentTimeStamp();
 waiting().until(ExpectedConditions.visibilityOf(loginName));
 loginName.sendKeys(loginname);
 waiting().until(ExpectedConditions.visibilityOf(password));
 password.sendKeys(pwd);
}

public void selectUserType(){
    scrollIntoView(userType);
    Select select = new Select(userType);
    select.selectByVisibleText("Administrator");
}
public void selectUserLang(String emailText,String firstNameText,String lastNameText){
    firstName.sendKeys(firstNameText);
    lastName.sendKeys(lastNameText);
    CommonMethods.select(userLang,"English (United Kingdom)");
    email.sendKeys(emailText);
    save.click();
}
public boolean savedSuccessfullyAlert(){
    boolean displayed = savedSuccessfullyAlert.isDisplayed();
    if(displayed){
        log.info("User successfully created alert is displayed ");
    }
    else {
        log.info("user successfully not created");
    }
    return displayed;
}
}
