package main.java.com.sumtotal.automation.pages;

import main.java.com.sumtotal.automation.base.waiting.Waiting;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;


public class ODPMPPage {

    @FindBy(id = "username")
    public WebElement userName;
    @FindBy(id = "password")
    public WebElement password;
    @FindBy(id = "FRHost_Search")
    public WebElement search;
    @FindBy(xpath = "//em[contains(text(),'Login')]")
    public WebElement login;
    @FindBy(xpath = "//*[text()='Open URL in browser']")
    public WebElement openBrowser;
    Logger log = Logger.getLogger(ODPMPPage.class);
    ResourceBundle bundle = ResourceBundle.getBundle("envCredentials");
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;


    public ODPMPPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement getSearch() {
        return search;
    }

    public WebDriverWait waiting() {
        wait = new WebDriverWait(driver, 30);
        return wait;
    }

    public void scrollIntoView(WebElement element) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void highLightElement(WebElement element) {
        js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
    }

    public void odpmpLogin(String username, String pwd) {
        userName.sendKeys(username);
        password.sendKeys(pwd);
        login.click();
    }

    public void searchSite_Key(String search_siteKey) {
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
        driver.findElement(By.id("FRHost_Search")).sendKeys(search_siteKey);
        driver.findElement(By.id("FRHost_Search")).sendKeys(Keys.ENTER);
    }


    public boolean isEnabled(By by) {
        try {
            driver.findElement(by);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    public void launchSiteInAnotherTab(){
        searchSite_Key(bundle.getString("odpmp.search"));
        openBrowserInNewTab();
    }

    public void openBrowserInNewTab() {
        if (isEnabled(By.xpath("//div[@id='retrievepass0'])"))) {
            WebElement admin_pwd = driver.findElement(By.xpath("//div[@id='retrievepass0'])"));
            admin_pwd.click();
            log.info("Admin password clicked");
        } else {
            WebElement user = driver.findElement(By.xpath("//span[@id='PasswordRetreival_0']"));
            user.click();
            log.info("User pwd clicked");
            WebElement img_Icon = driver.findElement(By.xpath("(//span[@class='ember-table-content']/a[1]/span[@class='icon-open-connection iconlf'])[2]"));
            waiting().until(ExpectedConditions.visibilityOf(img_Icon));
            img_Icon.click();
        }
        openBrowser.click();
        log.info("Site has been launched");
        ArrayList<String> tab = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tab.get(1));
        log.info("Switched to Another Tab");
    }

}




