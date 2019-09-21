package main.java.com.sumtotal.automation.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage {
    WebDriver driver;
    @FindBy(id="self_header")
    public WebElement selfHeader;

    @FindBy(id="search_header")
    public WebElement searchHeader;

    @FindBy(xpath = "//i[@class='fa fa-admin-custom']")
    public WebElement adminIcon;

    @FindBy(xpath = "//*[text()='Common Objects']")
    public WebElement commonObjects;

    @FindBy(xpath = "//*[text()='Data']")
    public WebElement data;

    @FindBy(xpath = "//*[text()='Data Import']")
    public WebElement dataImport;

    @FindBy(xpath = "//*[text()='Users']")
    public WebElement users;

    @FindBy(xpath = "//*[text()='All Users']")
    public WebElement allUsers;

    @FindBy(xpath = "//*[text()='Create User']")
    public WebElement createUser;

    @FindBy(xpath = "//span[text()='Product Administration']")
    public WebElement productAdministration;

    @FindBy(xpath = "//span[text()='Learning']")
    public WebElement learning;

    @FindBy(xpath = "//span[text()='Learning Activities']")
    public WebElement learningActivities;



    public DashboardPage(WebDriver driver){
    this.driver=driver;
    PageFactory.initElements(driver,this);
    }

    public void self() {
    selfHeader.isDisplayed();

    }
   public void  goToSearch(){

       searchHeader.isDisplayed();
       searchHeader.click();

   }

   public void goToCommonObjects(){
       adminIcon.click();
       commonObjects.click();
   }

   public void goToDataImport(){

    data.click();
    dataImport.click();
   }

   public CreateUserPage goToCreateUser(){
        goToCommonObjects();
        users.click();
        allUsers.click();
        createUser.click();
     return    new CreateUserPage(driver);
   }

   public ActivityCreationPage goToLearningActivities(){
        goToCommonObjects();
        productAdministration.click();
        learning.click();
        learningActivities.click();
        return new ActivityCreationPage(driver);
   }


}
