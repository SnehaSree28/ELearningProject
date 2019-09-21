package main.java.com.sumtotal.automation.pages;

import main.java.com.sumtotal.automation.base.waiting.Waiting;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

public class DataImportPage {
    WebDriver driver;

    WebDriverWait wait;
    private static Logger logger = Logger.getLogger(FileUploadPage.class);

    @FindBy(xpath = "//i[@class='fa fa-admin-custom']")
    public WebElement adminIncon;

    @FindBy(xpath = "//*[text()='Common Objects']")
    public WebElement commonObjects;

    @FindBy(xpath = "//*[text()='Data']")
    public WebElement data;

    @FindBy(xpath = "//*[text()='Data Import']")
    public WebElement dataImport;

    @FindBy(xpath = "//span[@title='Default Data Import Job']//ancestor::tr//button[text()='Upload Files']")
    public WebElement dataImportJob;


    public DataImportPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public WebDriverWait waiting() {
        wait = new WebDriverWait(driver, 30);
        return wait;
    }

    public DashboardPage clickOnUploadfiles(){
        dataImportJob.click();
        return new DashboardPage(driver);
    }

    public   void highLighElement(WebElement element){
        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }
    public void uploadFilesDataImport() throws InterruptedException {
        waiting().until(ExpectedConditions.visibilityOf(dataImportJob));
        highLighElement(dataImportJob);
        dataImportJob.click();
        Waiting.staticWait(20);
    }
}
