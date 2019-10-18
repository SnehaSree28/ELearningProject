package test.java.com.sumtotal.automation.tests;

import main.java.com.sumtotal.automation.pages.BasePage;
import main.java.com.sumtotal.automation.pages.BatchProcessPage;
import main.java.com.sumtotal.automation.pages.DashboardPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BatchProcessTest extends BasePage {
    BatchProcessPage batchProcessPage;
    DashboardPage dashboardPage;

    @Test(description = "To run batch process ",priority = 1)
    public void batchProcess(){
        dashboardPage = new DashboardPage(driver);
        batchProcessPage = dashboardPage.goToBatchProcess();
        try {
            batchProcessPage.gotoBatchJobs();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Test(description = "To validate batch process",priority = 2,dependsOnMethods = {"batchProcess"})
    public void validateBatchProcess(){

        try {
            batchProcessPage.validateUpgradeProcess();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String expectedResult=batchProcessPage.validateStatus("Complete");
        Assert.assertEquals("Complete",expectedResult,"Status of batch process is not updated to complete state");
    }
}
