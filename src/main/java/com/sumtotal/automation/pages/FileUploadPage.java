package main.java.com.sumtotal.automation.pages;

import main.java.com.sumtotal.automation.common.CommonConstants;
import main.java.com.sumtotal.automation.common.CommonMethods;
import org.openqa.selenium.*;
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
    JavascriptExecutor js;
    Logger log = Logger.getLogger(FileUploadPage.class);

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
  //  @FindBy(xpath = "(//span[text()='Browse...'])[1]")
    public WebElement browse;

    @FindBy(xpath = "//button[text()='Upload Files']")
    public WebElement uploadFilesButton;

    @FindBy(xpath = "//button[@title='Next' and text()='Next']")
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
        wait = new WebDriverWait(driver, 10000);
        return wait;
    }

    public   void highLighElement(WebElement element){

        js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }
    public boolean checkFrameAvailiablity(){
        try{
            driver.switchTo().parentFrame();
        }catch (NoSuchFrameException e){
            return false;
        }
        return true;
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

    public void uploadFile() throws InterruptedException{

        driver.switchTo().frame("productPillarFrame");
        fileUploadButton.click();
         driver.switchTo().frame("listActivitiesFrame");
         log.info("File Upload Button is clicked");
        highLighElement(browse);
        waiting().until(ExpectedConditions.visibilityOf(browse));
        browse.click();
        log.info("Browse Button has been clicked");
        try {
            CommonMethods.uploadFile(CommonConstants.uploadFile);
        } catch (AWTException e) {
            e.printStackTrace();
        }
        waiting().until(ExpectedConditions.visibilityOf(uploadFilesButton));
        log.info("File has been uploaded ");
        uploadFilesButton.click();
        waiting().until(ExpectedConditions.visibilityOf(clickOnFileUploadNext));
        Thread.sleep(10000);
        clickOnFileUploadNext.click();
        Thread.sleep(3000);
    }


    public String getActivityName()  {

        String aName="ActivitiyName"+ CommonMethods.getCurrentTimeStamp();
        activityName.sendKeys(aName);
        log.info("Activity Name has been entered");
        String aCode="ActivityCode"+CommonMethods.getCurrentTimeStamp();
        activityCode.sendKeys(aCode);
        log.info("Activity Code has been entered");
        clickOnFileUploadNext.click();
        upload.click();
        wait.until(ExpectedConditions.elementToBeClickable(viewInProgress));
        viewInProgress.click();
        return aName;
    }
    public boolean aNameVisibleOrNot(String aName) throws InterruptedException {
        Select select=new Select(driver.findElement(By.xpath("//select[@ng-model='selected']")));
        select.selectByVisibleText("View In Progress");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@title='"+aName+"' and //span[@title='In Progress']]")));
        select.selectByVisibleText("View Items in Staging");
        WebElement aCodeValueInStaging =driver.findElement(By.xpath("//*[@title='"+aName+"' and //span[@title='In Staging']]"));
        boolean displayedStaging =aCodeValueInStaging.isDisplayed();
        System.out.println("staging" +aCodeValueInStaging);
        WebElement checkBox=driver.findElement(By.xpath("//div[@title='"+aName+"']//../preceding-sibling::td//input[@type='checkbox']"));
        wait.until(ExpectedConditions.elementToBeClickable(checkBox));
        checkBox.click();
        Thread.sleep(5000);
        log.info("Checkbox has been clicked");
        return displayedStaging;
    }

    public  void close(){
        driver.switchTo().parentFrame();
        driver.findElement(By.xpath("//button[@aria-label='Close']")).click();
    }
    public void deleteUploadedFile(){
        WebElement delete = driver.findElement(By.xpath("//button[text()='Delete']"));
        delete.click();
        log.info("Clicked on delete button of file");
        Alert alert = driver.switchTo().alert();
        alert.accept();
        log.info("uploaded file has been deleted");
    }
}
