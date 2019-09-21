package main.java.com.sumtotal.automation.pages;

import main.java.com.sumtotal.automation.common.CommonConstants;
import main.java.com.sumtotal.automation.common.CommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.log4testng.Logger;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class FileUploadPage {
    WebDriver driver;

    WebDriverWait wait;
    private static Logger logger = Logger.getLogger(FileUploadPage.class);

    @FindBy(xpath = "//i[@class='fa fa-admin-custom']")
    public WebElement adminIncon;

    @FindBy(xpath = "//span[text()='Product Administration']")
    public WebElement productAdministration;

    @FindBy(xpath = "//span[text()='Learning']")
    public WebElement learning;

    @FindBy(xpath = "//span[text()='Learning Activities']")
    public WebElement learningActivities;

    @FindBy(id = "fileUpload_Button")
    public WebElement fileUploadButton;

    @FindBy(xpath = "//div[(text() = 'Browse...' or . = 'Browse...')]")
    public WebElement browse;

    @FindBy(xpath = "//button[text()='Upload Files']")
    public WebElement uploadFilesButton;

    @FindBy(xpath = "//button[@title = 'Next' and (text() = 'Next' or . = 'Next')]")
    public WebElement clickOnFileUploadNext;

    @FindBy(id = "txt-activityName")
    public WebElement activityName;

    @FindBy(name = "txt-activityCode")
    public WebElement activityCode;

    @FindBy(xpath = "//button[@title='Upload' and text()='Upload']")
    public WebElement upload;

    @FindBy(xpath = "//button[@title='View In Progress']")
    public WebElement viewInProgress;

    public FileUploadPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriverWait waiting() {
        wait = new WebDriverWait(driver, 30);
        return wait;
    }

    public   void highLighElement(WebElement element){

        JavascriptExecutor js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }


    public void navigateLearningActivities() {

        adminIncon.click();
        waiting().until(ExpectedConditions.visibilityOf(productAdministration));
        productAdministration.click();
        waiting().until(ExpectedConditions.visibilityOf(learning));
        learning.click();
        waiting().until(ExpectedConditions.visibilityOf(learningActivities));
        learningActivities.click();
    }

    public void uploadFile() throws InterruptedException, AWTException {
        driver.switchTo().frame("productPillarFrame");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        waiting().until(ExpectedConditions.visibilityOf(fileUploadButton));
        Thread.sleep(5000);
        fileUploadButton.click();
        WebDriver listActivitiesFrame = driver.switchTo().frame("listActivitiesFrame");
        System.out.println("---"+listActivitiesFrame.getTitle());
        highLighElement(browse);
        waiting().until(ExpectedConditions.elementToBeClickable(browse));
        browse.click();
        Thread.sleep(3000);
        System.out.println(CommonConstants.uploadFile);
        CommonMethods.uploadFile(CommonConstants.uploadFile);
        Thread.sleep(3000);
        uploadFilesButton.click();
        Thread.sleep(10000);
        clickOnFileUploadNext.click();
        Thread.sleep(3000);
    }


    public String getActivityCode() throws InterruptedException {

        String aName="ActivitiyName"+ CommonMethods.getCurrentTimeStamp();
        activityName.sendKeys(aName);
        String aCode="ActivityCode"+CommonMethods.getCurrentTimeStamp();
        activityCode.sendKeys(aCode);
        Thread.sleep(10000);
        clickOnFileUploadNext.click();
        Thread.sleep(5000);
        upload.click();
        wait.until(ExpectedConditions.elementToBeClickable(viewInProgress));
        viewInProgress.click();
        Thread.sleep(3000);
        return aCode;
    }
    public boolean  aNameVisibleOrNot(String aCode) throws InterruptedException {
        Select select=new Select(driver.findElement(By.xpath("//select[@ng-model='selected']")));
        select.selectByVisibleText("View In Progress");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@title='"+aCode+"' and //span[@title='In Progress']]")));
        select.selectByVisibleText("View Items in Staging");
        WebElement aCodeValueInStaging =driver.findElement(By.xpath("//*[@title='"+aCode+"' and //span[@title='In Staging']]"));
        boolean displayedStaging =aCodeValueInStaging.isDisplayed();
        System.out.println("staging" +aCodeValueInStaging);
        WebElement checkBox=driver.findElement(By.xpath("//div[@title='"+aCode+"']//../preceding-sibling::td//input[@type='checkbox']"));
        System.out.println("CheckBox" +checkBox.getText());
        wait.until(ExpectedConditions.elementToBeClickable(checkBox));
        checkBox.click();
        Thread.sleep(5000);
        return displayedStaging;
    }

    public  void close(){
        driver.switchTo().parentFrame();
        driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
    }

}
