package com.AutomationExerciseProjectTestCases.drivers;

import com.AutomationExerciseProjectTestCases.utils.PropertiesLoader;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxFactory extends AbstractDriver implements WebDriverOptions<FirefoxOptions>{
    @Override
    public FirefoxOptions getOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--start-maximized");
        firefoxOptions.addArguments("--disable-extensions");
        firefoxOptions.addArguments("--disable-infobars");
        firefoxOptions.addArguments("--disable-notifications");
        firefoxOptions.addArguments("--remote-allow-origins=*");
        if(!PropertiesLoader.getPropertyValue("excutionType").equalsIgnoreCase("local")){
            firefoxOptions.addArguments("--headless");
        }
        firefoxOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        firefoxOptions.setAcceptInsecureCerts(true);
        return firefoxOptions;
    }

    @Override
    public WebDriver startDriver() {
        return new FirefoxDriver(getOptions());
    }
}
