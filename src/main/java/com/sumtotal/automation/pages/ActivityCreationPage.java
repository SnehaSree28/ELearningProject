package main.java.com.sumtotal.automation.pages;

import main.java.com.sumtotal.automation.base.waiting.Waiting;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class ActivityCreationPage {
    Logger log = Logger.getLogger(ActivityCreationPage.class);
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;


    @FindBy(id = "ddlistActivitiesSrch_goButton")
    public WebElement newActivity;

    @FindBy(xpath = "//a[text()='Course']")
    public WebElement course;

    @FindBy(name="txtActivityName")
    public WebElement activityName;

    @FindBy(name="txtCode")
    public WebElement activityCode;

    @FindBy(id="btnadd61")
    public WebElement addAudienceButton;
    @FindBy(xpath = "//*[text()='Add Audiences']")
    public WebElement audiences;

    @FindBy(id="idSearchStrInput")
    public WebElement searchAudienceName;

    @FindBy(id="idtask_Next")
    public WebElement clickOnNext;


    public ActivityCreationPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public WebDriverWait waiting(){
        wait= new WebDriverWait(driver,60);
        return wait;
    }
    public void scrollIntoView(WebElement element)
    {
        js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView();",element);
    }
    public void clickUsingJavaScriptExceutor(WebElement element){
        js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].click();",element);

    }

    public   void highLighElement(WebElement element){

         js=(JavascriptExecutor)driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }
    public void goToNewActivity() throws InterruptedException {
        driver.switchTo().frame("productPillarFrame");

       // waitForVisibility().until(ExpectedConditions.visibilityOf(newActivity));
        newActivity.click();
        log.info("Navigated to New Activity");
        Actions actions = new Actions(driver);
       // actions.moveToElement(newActivity).perform();
        course.click();
        log.info("Hovered on course");
        waiting().until(ExpectedConditions.visibilityOf(activityName));
        log.info("Clicked on course ");
    }

    public void createNewActivity(String activityNameTxt, String activityCodeTxt) throws InterruptedException {
        log.info("Navigated to NewActivityPage");
        waiting().until(ExpectedConditions.visibilityOf(activityName));
        activityName.clear();
        activityName.sendKeys(activityNameTxt);
        log.info("Activity Name has entered");
        scrollIntoView(activityCode);
        activityCode.sendKeys(activityCodeTxt);
        log.info("Activity Code has entered");
        log.info("Started scrolling to add button");
        scrollAddAudienceButton();
        highLighElement(addAudienceButton);
        clickUsingJavaScriptExceutor(addAudienceButton);
        log.info("Clicked On Add Button");
        waiting().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("idDialogFrame"));
        waiting().until(ExpectedConditions.visibilityOf(audiences));
       // driver.switchTo().frame("idDialogFrame");

        String text = audiences.getText();
        log.info("--"+text);
    }
    public void scrollAddAudienceButton(){
        WebElement element = driver.findElement(By.id("btnadd61"));
        Coordinates coordinates = ((Locatable)element).getCoordinates();
        coordinates.inViewPort();
        element.click();
    }

    public void searchAudienceName(String audienceName){
        WebElement element = driver.findElement(By.xpath("//td[@title='" + audienceName + "']//parent::tr//child::input[@type='checkbox']"));
        waiting().until(ExpectedConditions.visibilityOf(element));
        if(!element.isSelected()){
            element.click();
        }else {
            element.click();
        }
        clickOnNext.click();
    }

}
