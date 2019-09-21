package main.java.com.sumtotal.automation.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.sql.Timestamp;

public class CommonMethods {
    public static void  uploadFile( String filePath) throws InterruptedException, AWTException {
       // wb.click(to)
        Thread.sleep(2000);
        StringSelection ss = new StringSelection(filePath);

        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
        Robot robot = new Robot();
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
    public static long getCurrentTimeStamp(){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        long time = timestamp.getTime();
        return time;
    }

    public static void select(WebElement element,String text){
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }
}
