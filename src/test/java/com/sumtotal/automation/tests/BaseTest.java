        package test.java.com.sumtotal.automation.tests;

        import main.java.com.sumtotal.automation.base.BaseSetup;
        import main.java.com.sumtotal.automation.common.CommonConstants;
        import main.java.com.sumtotal.automation.pages.DashboardPage;
        import main.java.com.sumtotal.automation.pages.LoginPage;
        import main.java.com.sumtotal.automation.pages.ODPMPPage;
        import org.codehaus.plexus.util.FileUtils;
        import org.openqa.selenium.OutputType;
        import org.openqa.selenium.TakesScreenshot;
        import org.openqa.selenium.WebDriver;
        import org.testng.annotations.AfterSuite;
        import org.testng.annotations.BeforeClass;
        import org.testng.annotations.BeforeMethod;
        import org.testng.annotations.BeforeSuite;

        import java.io.File;
        import java.io.IOException;
        import java.util.ResourceBundle;

        public class BaseTest {

            public static WebDriver driver;
            ResourceBundle bundle = ResourceBundle.getBundle("envCredentials");
            DashboardPage dashboardPage;


            @BeforeSuite
            public void launchDriver() {
                driver = BaseSetup.browserSetup(driver);
            }


   /*@AfterSuite
            public void quit
           (){

             driver.quit();
         }*/
        }
