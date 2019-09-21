package main.java.com.sumtotal.automation.common;


import org.apache.log4j.Logger;

public class CommonConstants {
    private static Logger log = Logger.getLogger(CommonConstants.class);
    public static String uploadFile= System.getProperty("user.dir")+"\\src\\resources\\sample.txt";
    public static String screenshot= System.getProperty("user.dir")+"\\ScreenShot";

    public static void u(){
        log.info("File Path is " +uploadFile);
        System.out.println("File Path is" +uploadFile);
    }


}
