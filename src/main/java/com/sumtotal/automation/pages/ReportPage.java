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

    public DashboardPage navigateToAdminIcon(){
        selfHeader.click();
        return new DashboardPage(driver);
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
        waiting();
        System.out.println(" ....:Trying to switch to frame : ---");

        WebElement frame =driver.findElement(By.id("productPillarFrame"));
      //  js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",frame);

        driver.switchTo().frame("productPillarFrame");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement folderContent = driver.findElement(By.id("foldersPodContent"));
        wait.until(ExpectedConditions.visibilityOf(folderContent));
    //    js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",folderContent);
        System.out.println("Switched to folder content");

        WebElement siteKeyExpansion=driver.findElement(By.id("handler2"));
        wait.until(ExpectedConditions.visibilityOf(siteKeyExpansion));


       // js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",siteKeyExpansion);
        Actions actions = new Actions(driver);
        actions.click(siteKeyExpansion);
        System.out.println("Switched to siteKey");

      //  siteKeyExpansion.click();
     //   System.out.println("TestCase Passed");

        WebElement reportsExpansion= driver.findElement(By.id("handler8"));
        wait.until(ExpectedConditions.visibilityOf(reportsExpansion));
        actions.click(reportsExpansion);
       // reportsExpansion.click();
        System.out.println("reports clicked");
        Waiting.staticWait(2000);
        WebElement audience=driver.findElement(By.xpath("//*[text()='Audience']"));
        wait.until(ExpectedConditions.visibilityOf(audience));
        actions.click(audience);
        //audience.click();


        WebElement audienceAssociationSummary=driver.findElement(By.xpath("//*[text()='Audience User Association Summary']"));
        wait.until(ExpectedConditions.visibilityOf(audienceAssociationSummary));
        audienceAssociationSummary.click();
   //     Waiting.staticWait(10000);


    }


    public boolean navigateAudienceUserAssociationSummary() throws InterruptedException {

        waiting();
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        WebElement inputControls=driver.findElement(By.id("inputControls"));

    //    Waiting.staticWait(5000);
        WebElement audiName=driver.findElement(By.id("pAudNames"));


      /*  WebElement audienceReport=driver.findElement(By.xpath("//*[text()='ASP Audience']"));

        wait.until(ExpectedConditions.visibilityOf(audienceReport));
        audienceReport.click();
        log.info("Clicked on ASP Audience");
      */  WebElement apply=driver.findElement(By.xpath("//span[text()='Apply']"));
        wait.until(ExpectedConditions.visibilityOf(apply));
        apply.click();
        log.info("Clicked on apply");

        WebElement Ok=    driver.findElement(By.xpath("//button[@id='ok']"));
        wait.until(ExpectedConditions.visibilityOf(Ok));
        Ok.click();
        log.info("CLicked on OK");

        boolean text = driver.findElement(By.xpath("//span[text()='AUDIENCE USER ASSOCIATION']")).isDisplayed();
        log.info("Text has been displayed");
        return text;
    }

}
