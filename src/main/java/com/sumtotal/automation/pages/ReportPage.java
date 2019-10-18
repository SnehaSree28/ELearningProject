package main.java.com.sumtotal.automation.pages;

import main.java.com.sumtotal.automation.base.waiting.Waiting;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;

public class ReportPage {
    WebDriver driver;
    Logger log = Logger.getLogger(SearchPage.class);
    ResourceBundle bundle = ResourceBundle.getBundle("envCredentials");
    WebDriverWait wait ;

    @FindBy(id="self_header")
    public WebElement selfHeader;

    @FindBy(xpath = "//span[text()='Reporting']")
    public WebElement reporting;

    @FindBy(xpath = "//span[text()='Advanced Reporting']")
    public WebElement advancedReporting;

    @FindBy(xpath = "//*[text()='Reports']")
    public WebElement reports;


    @FindBy(xpath = "//*[text()='Audience User Association Summary']")
    public WebElement userAssociationSummary;

    @FindBy(xpath = "//b[@id='handler2']")
    public WebElement site_key_expansion;


    public ReportPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public WebDriverWait waiting(){
        wait= new WebDriverWait(driver,30);
        return wait;
    }

    public void navigateToAdminIcon(){
        selfHeader.click();

    }


    public void navigationPath(){
        waiting();
        wait.until(ExpectedConditions.visibilityOf(reporting));
        reporting.click();
        wait.until(ExpectedConditions.visibilityOf(advancedReporting));
        advancedReporting.click();

    }
    public void navigationMenu() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        log.info(" Trying to switch to frame ");
        WebElement frame =driver.findElement(By.id("productPillarFrame"));
       // js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",frame);
        driver.switchTo().frame("productPillarFrame");
        WebElement folderContent = driver.findElement(By.id("foldersPodContent"));
        waiting().until(ExpectedConditions.visibilityOf(folderContent));
        log.info("Switched to folder content");
        WebElement siteKeyExpansion=driver.findElement(By.id("handler2"));
        waiting().until(ExpectedConditions.visibilityOf(siteKeyExpansion));
        js.executeScript("arguments[0].click();", siteKeyExpansion);
        log.info("Clicked on Site_Key of reports");
        WebElement reportsExpansion= driver.findElement(By.id("handler8"));
        waiting().until(ExpectedConditions.visibilityOf(reportsExpansion));
        js.executeScript("arguments[0].click();",reportsExpansion);
        log.info("reports clicked");
        WebElement audience=driver.findElement(By.xpath("//*[text()='Audience']"));
        waiting().until(ExpectedConditions.visibilityOf(audience));
        audience.click();
        WebElement audienceAssociationSummary=driver.findElement(By.xpath("//*[text()='Audience User Association Summary']"));
        waiting().until(ExpectedConditions.visibilityOf(audienceAssociationSummary));
        audienceAssociationSummary.click();
        Waiting.staticWait(10000);
    }

    public boolean navigateAudienceUserAssociationSummary()  {


        WebElement apply=driver.findElement(By.xpath("//span[text()='Apply']"));
        waiting().until(ExpectedConditions.visibilityOf(apply));
        Actions actions = new Actions(driver);
        actions.moveToElement(apply).click().build().perform();
       //apply.click();
        log.info("Clicked on apply");
        WebElement Ok=driver.findElement(By.xpath("//button[@id='ok']"));
        waiting().until(ExpectedConditions.visibilityOf(Ok));
        actions.moveToElement(Ok).click().build().perform();
        //Ok.click();
        log.info("CLicked on OK");
        boolean text = driver.findElement(By.xpath("//span[text()='AUDIENCE USER ASSOCIATION']")).isDisplayed();
        log.info("Text has been displayed");
        return text;
    }


}
