package com.AutomationExerciseProjectTestCases.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class BrowserActions {
    private WebDriver driver;
    public BrowserActions(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Navigating to the following URL: {URL}")
    public void navigateToURL(String URL) {
        driver.get(URL);
        LogsUtil.info("Navigation to: " + URL);
    }

    @Step("Getting the current URL")
    public String getCurrentURL() {
        String currentURL = driver.getCurrentUrl();
        LogsUtil.info("The current URL is: " + currentURL);
        return currentURL;
    }

    @Step("Quit from the browser")
    public void quitBrowser() {
        driver.quit();
    }

    @Step("Close the browser")
    public void closeBrowser() {
        driver.close();
    }

    @Step("Referesh the page")
    public void refereshPage() {
        driver.navigate().refresh();
    }
}
