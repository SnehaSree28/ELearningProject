package main.java.com.sumtotal.automation.pages;

import main.java.com.sumtotal.automation.base.waiting.Waiting;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage  {
    public WebDriver driver;

    public WebDriverWait wait;

    @FindBy(id = "txtSearch-main")
    public WebElement search;
    @FindBy(id = "search_header")
    public WebElement searchHeader;

    Logger log = Logger.getLogger(SearchPage.class);


    public SearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /*public WebDriverWait waitForVisibility() {
        try {
            wait = new WebDriverWait(driver, 30);

        } catch (NullPointerException e) {
            e.printStackTrace();
        }

        return wait;

    }
*/

    public boolean searchReport() {
        wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOf(searchHeader));
        searchHeader.click();
        Actions actions =new Actions(driver);
        actions.sendKeys(search,"*").build().perform();
        WebElement selectElementGetText = driver.findElement(By.xpath("(//*[text()='Select'])[2]"));
        boolean isDisplayed = selectElementGetText.isDisplayed();
        if (isDisplayed) {
            System.out.println("Search functionality has been completed");
            log.info("-------------------------------------");
        } else {
            System.out.println("Search functionality is not completed");
        }
        return isDisplayed;
       }
}

