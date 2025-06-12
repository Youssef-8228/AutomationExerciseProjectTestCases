package com.AutomationExerciseProjectTestCases.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waits {
    // present - visible - clickable
    // Private constructor to prevent any instentiation of this calss
    // and all methodes are static to use it without take instance
    private WebDriver driver;
    public Waits(WebDriver driver){this.driver=driver;}
    // wait the elemnet to be present
    @Step("Waiting the element: {locator} to be present")
    public  WebElement waitElementToBePresent (By locator){
        LogsUtil.info("Waiting the element to be present " + locator.toString());
        return new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver1 -> driver1.findElement(locator));
    }
    // wait the element to be visible
    @Step("Waiting the element: {locator} to be visible")
    public  WebElement waitElementToBeVisible ( By locator){
        LogsUtil.info("Waiting the element to be visible " + locator.toString());
        return new WebDriverWait(driver,Duration.ofSeconds(10))
                .until(driver1 -> {
                   WebElement element = waitElementToBePresent(locator);
                   return element.isDisplayed() ? element:null;
                });
    }
    // wait the element to be clickable
    @Step("Waiting the element: {locator} to be clickable")
    public  WebElement waitElementToBeClickable ( By locator){
        LogsUtil.info("Waiting the element to be clickable " + locator.toString());
        return new WebDriverWait(driver,Duration.ofSeconds(10))
                .until(driver1 -> {
                    WebElement element = waitElementToBeVisible(locator);
                    return element.isEnabled() ? element:null;
                });
    }
    @Step("Wait the alert to be present")
    public void waitAlertToBePresent(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(driver1 -> {
            try {
                return driver1.switchTo().alert();
            } catch (NoAlertPresentException e) {
                LogsUtil.error("There is no alert to display");
                return null;
            }
        });
    }
}
