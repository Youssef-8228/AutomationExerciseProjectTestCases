package com.AutomationExerciseProjectTestCases.drivers;

import com.AutomationExerciseProjectTestCases.utils.PropertiesLoader;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.Map;

public class ChromeFactory extends AbstractDriver implements WebDriverOptions<ChromeOptions>{
    @Override
    public ChromeOptions getOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--disable-infobars");
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--remote-allow-origins=*");
        Map<String, Object> chromePrefs = Map.of(
                "profile.default_content_setting_values.notifications", 2,
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "autofill.profile_enabled", false
        );
        if(!PropertiesLoader.getPropertyValue("excutionType").equalsIgnoreCase("local")){
            chromeOptions.addArguments("--headless");
        }
        chromeOptions.setExperimentalOption("prefs", chromePrefs);
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        return chromeOptions;
    }

    @Override
    public WebDriver startDriver() {
        return new ChromeDriver(getOptions());
    }
}
