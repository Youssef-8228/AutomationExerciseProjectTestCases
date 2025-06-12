package com.AutomationExerciseProjectTestCases.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

public class ElementsActions {
    private WebDriver driver;
    private Waits waits;

    public ElementsActions(WebDriver driver) {
        this.driver = driver;
        waits = new Waits(driver);
    }

    @Step("Clicking on the element: {locator}")
    public void clickOn (By locator) {
        waits.waitElementToBeClickable(locator);
        scrollingToElement(locator);
        findElement(locator).click();
        LogsUtil.info("Clicking on element: ", locator.toString());
    }

    @Step("Sending data: {data} for the element: locator")
    public void type (By locator, String data) {
        waits.waitElementToBeVisible(locator);
        scrollingToElement(locator);
        findElement(locator).sendKeys(data);
        LogsUtil.info("Sending " + data + " to " + locator.toString());
    }

    @Step("Getting data from the element: {locator}")
    public String getData(By locator) {
        waits.waitElementToBeVisible(locator);
        scrollingToElement(locator);
        LogsUtil.info("Getting the text from the " + locator.toString() + " Text: " + findElement(locator).getText());
        return findElement(locator).getText();
    }
    @Step("Select {visibleText} from the dropDown: {locator}")
    public void selectFromDropDown (By locator,String visibleText){
        waits.waitElementToBePresent(locator);
        scrollingToElement(locator);
        Select select = new Select(findElement(locator));
        select.selectByVisibleText(visibleText);
    }
    @Step("Accepting alert")
    public void confirmAlert(){
        waits.waitAlertToBePresent();
        driver.switchTo().alert().accept();
    }
    @Step("Dismiss alert")
    public void declineAlert(){
        waits.waitAlertToBePresent();
        driver.switchTo().alert().dismiss();
    }
    @Step("Getting text from the alert}")
    public String getTextFromAlert(){
        waits.waitAlertToBePresent();
        return driver.switchTo().alert().getText();
    }
    @Step("Enter: {text} in the alert")
    public void typeInAlert(String text){
        waits.waitAlertToBePresent();
        driver.switchTo().alert().sendKeys(text);
    }
    @Step("Finding the elemnt: {locator}")
    private WebElement findElement(By locator) {
            LogsUtil.info("Finding element: " + locator.toString());
            return driver.findElement(locator);
    }

    @Step("Scrolling to the element: {locator}")
    private void scrollingToElement(By locator) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView(true);",
                findElement(locator)
        );
        LogsUtil.info("Scrolling to " + locator.toString());
    }
}
