package com.AutomationExerciseProjectTestCases.drivers;

import com.AutomationExerciseProjectTestCases.utils.*;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.fail;

public class MyDriver {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public MyDriver(String browserName){
        WebDriver driver = getDriver(browserName).startDriver();
        setDriver(driver);
    }
    public static WebDriver getInstance(){
        return driverThreadLocal.get();
    }
    private static void setDriver (WebDriver driver){
        driverThreadLocal.set(driver);
    }

    private static AbstractDriver getDriver (String browserName) {
     return switch (browserName.toLowerCase()){
         case "chrome" -> new ChromeFactory();
         case "firefox" -> new FirefoxFactory();
         case "edge" -> new EdgeFactory();
         default -> throw new IllegalArgumentException();
     };
    }
    public WebDriver get(){
        if(driverThreadLocal.get() == null){
            LogsUtil.error("The driver is null and not initialized");
            fail("Driver null!");
            return null;
        }
        return driverThreadLocal.get();
    }
    public ElementsActions elementsActions (){
        return new ElementsActions(get());
    }
    public BrowserActions browserActions (){
        return new BrowserActions(get());
    }
    public Validations validate (){
        return new Validations(get());
    }



}
