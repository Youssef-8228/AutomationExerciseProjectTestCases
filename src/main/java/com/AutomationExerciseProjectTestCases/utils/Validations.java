package com.AutomationExerciseProjectTestCases.utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

import static org.testng.Assert.*;

public class Validations {
    private WebDriver driver;
    private BrowserActions browserActions;

    public Validations(WebDriver driver) {
        this.driver = driver;
        browserActions = new BrowserActions(driver);
    }

    // Assert that a condition is true
    @Step("Validate true")
    public void validateTrue(boolean condition, String message) {
        assertTrue(condition, message);
    }

    // Assert that a condition is false
    @Step("Validate false")
    public void validateFalse(boolean condition, String message) {
        assertFalse(condition, message);
    }

    // Assert that two values are equal
    @Step("Validate equals")
    public void validateEquals(Object actual, Object expected, String message) {
        assertEquals(actual, expected, message);
    }

    // Assert that two values are not equal
    @Step("Validate not equals")
    public void validateNotEquals(Object actual, Object expected, String message) {
        assertNotEquals(actual, expected, message);
    }

    @Step("Validate page URL")
    public void validatePageURL(Object expected, String message) {
        assertEquals(browserActions.getCurrentURL(), expected, message);
    }

    // Assert that an object is not null
    @Step("Validate is not null")
    public void validateNotNull(Object object, String message) {
        assertNotNull(object, message);
    }

    // Assert that an object is null
    @Step("Validate null")
    public void validateNull(Object object, String message) {
        assertNull(object, message);
    }
}
