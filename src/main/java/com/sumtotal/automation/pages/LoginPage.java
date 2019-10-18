package main.java.com.sumtotal.automation.pages;

import main.java.com.sumtotal.automation.base.BaseSetup;
import main.java.com.sumtotal.automation.base.waiting.Waiting;
import main.java.com.sumtotal.automation.common.CommonConstants;
import main.java.com.sumtotal.automation.common.CommonMethods;
import org.apache.log4j.Logger;
import org.apache.maven.surefire.shade.common.org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;


public class LoginPage {
  public WebDriver driver;
  public WebDriverWait wait;

    @FindBy(id="BodyContent_MainContent_MainContentPlaceHolder_UserName")
    public WebElement userName;

    @FindBy(id="BodyContent_MainContent_MainContentPlaceHolder_Password")
    public WebElement passWord;

    @FindBy(id="BodyContent_MainContent_MainContentPlaceHolder_LoginButton")
    public WebElement signIn;

    @FindBy(xpath = "//input[@value='I Agree']")
    public WebElement iAgree;
    Logger log= Logger.getLogger(LoginPage.class);

    public  LoginPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

        public DashboardPage  login(String username, String password) throws InterruptedException {
            waiting(driver).until(ExpectedConditions.visibilityOf(userName));
            userName.sendKeys(username);
            waiting(driver).until(ExpectedConditions.visibilityOf(passWord));
            Waiting.staticWait(2000);
            passWord.sendKeys(password);
            signIn.click();
            if(isEnabled("//input[@value='I Agree']")){
                driver.findElement(By.xpath("//input[@value='I Agree']")).click();
            }
            return new DashboardPage(driver);
        }

    public boolean isEnabled(String locator){
        try{
            driver.findElement(By.xpath(locator));
        }catch (Exception e){
            return false;
        }
        return true;
    }

    public WebDriverWait waiting(WebDriver driver){
      wait= new WebDriverWait(driver,30);

      return wait;
    }
    public void takeScreenShot() throws IOException {

            File file =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File(CommonConstants.screenshot+ CommonMethods.getCurrentTimeStamp() +".png"));
    }

    public void logout(){
        log.info("Navigate to logout button");
        driver.switchTo().parentFrame();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebElement self_header=driver.findElement(By.id("self_header"));
        log.info("Clicked on self header");
        waiting(driver).until(ExpectedConditions.visibilityOf(self_header));
        self_header.click();
        log.info("Self Header is clicked");
        WebElement sign_out=driver.findElement(By.xpath("//span[text()='Signout']"));
        waiting(driver).until(ExpectedConditions.visibilityOf(sign_out));
        log.info("Clicked on Sign Out");
        sign_out.click();
        driver.navigate().refresh();
    }

}
