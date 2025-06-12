package com.AutomationExerciseProjectTestCases.drivers;

import com.AutomationExerciseProjectTestCases.utils.PropertiesLoader;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.Map;

public class EdgeFactory extends AbstractDriver implements WebDriverOptions<EdgeOptions>{
    @Override
    public EdgeOptions getOptions() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.addArguments("--start-maximized");
        edgeOptions.addArguments("--disable-extensions");
        edgeOptions.addArguments("--disable-infobars");
        edgeOptions.addArguments("--disable-notifications");
        edgeOptions.addArguments("--remote-allow-origins=*");
        Map<String, Object> edgePrefs = Map.of(
                "profile.default_content_setting_values.notifications", 2,
                "credentials_enable_service", false,
                "profile.password_manager_enabled", false,
                "autofill.profile_enabled", false
        );
        edgeOptions.setExperimentalOption("prefs", edgePrefs);
        if(!PropertiesLoader.getPropertyValue("excutionType").equalsIgnoreCase("local")){
            edgeOptions.addArguments("--headless");
        }
        edgeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        return edgeOptions;
    }

    @Override
    public WebDriver startDriver() {
        return new EdgeDriver(getOptions());
    }
}
