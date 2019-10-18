package main.java.com.sumtotal.automation.pages;

import main.java.com.sumtotal.automation.base.waiting.Waiting;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;


public class BatchProcessPage {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
    @FindBy(xpath = "(//span[text()='Batch Jobs'])[1]")
    public WebElement batchJobs;

    @FindBy(xpath = "//span[text()='Batch Processes']")
    public WebElement batchProcess;

    @FindBy(xpath = "//div[@id='suidlgpagedlist_filterbar_S']")
    public WebElement s;

    @FindBy(xpath = "//td[text()='System - Upgrade Post Process']/..//button[@aria-label='Expand']")
    public WebElement systemUpgradeProcess;

    @FindBy(xpath = "//td[text()='System - Upgrade Post Process']/..//button[@aria-label='Expand']//following::ul//span[text()='Run']")
    public WebElement run;

    @FindBy(xpath = "//button[text()='Yes']")
    public WebElement yes;

    @FindBy(xpath = "//td[text()='System - Upgrade Post Process']/..//button[@aria-label='Expand']//following::span[text()='View Log']")
    public WebElement viewLog;

    Logger log = Logger.getLogger(BatchProcessPage.class);

    public BatchProcessPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }
    public void javascriptHighLightELement(WebElement element){
         js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');",element);
    }
    public WebDriverWait waiting(){
        wait= new WebDriverWait(driver,120);
        return wait;
    }

    public void scrollDown(WebElement element){
        js=(JavascriptExecutor)driver;
      //  js.executeScript("window.scrollBy(0,1000)",element);
        js.executeScript("arguments[0].scrollIntoView(true);",element);
    }

    public void gotoBatchJobs() throws InterruptedException {

        driver.switchTo().frame("PillarIFrame_TM");
        log.info("Switched to pillar frame");
        driver.switchTo().frame("content");
        log.info("Switched to Content");
        waiting().until(ExpectedConditions.visibilityOf(batchJobs));
        batchJobs.click();
        log.info("Clicked on Batch Jobs");
        waiting().until(ExpectedConditions.visibilityOf(batchProcess));
        batchProcess.click();
        log.info("Clicked on batch process");
        driver.switchTo().frame("profilecontent");
        log.info("Switched to profile");
        waiting().until(ExpectedConditions.visibilityOf(s));
        javascriptHighLightELement(s);
        s.click();
        log.info("Clicked on S");
//        Thread.sleep(2000);
        Waiting.staticWait(2000);
        waiting().until(ExpectedConditions.visibilityOf(systemUpgradeProcess));
        scrollDown(systemUpgradeProcess);
        javascriptHighLightELement(systemUpgradeProcess);
        systemUpgradeProcess.click();
        /*Actions actions = new Actions(driver);
        actions.click(systemUpgradeProcess);*/
        log.info("Clicked on SystemUpgradeProcess");
        Waiting.staticWait(3000);
       // scrollDown(run);
        run.click();
        Waiting.staticWait(2000);
        yes.click();
        log.info("Clicked on yes");
    }

    public void validateUpgradeProcess() throws InterruptedException {
        Waiting.staticWait(2000);
        waiting().until(ExpectedConditions.visibilityOf(systemUpgradeProcess));
        scrollDown(systemUpgradeProcess);
        systemUpgradeProcess.click();
        waiting().until(ExpectedConditions.visibilityOf(viewLog));
        viewLog.click();
        log.info("Clicked on view log");


    }
    public String validateStatus(String text){
        WebElement complete=driver.findElement(By.xpath("//div[contains(text(),'"+text+"')]"));
        String status=complete.getText();
        String aClass = complete.getAttribute("class");
        log.info("Class Name is " +aClass);
        log.info("Status is " +status);
        return status;
    }
}