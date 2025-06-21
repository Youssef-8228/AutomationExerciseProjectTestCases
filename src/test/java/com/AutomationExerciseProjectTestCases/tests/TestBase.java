package com.AutomationExerciseProjectTestCases.tests;

import com.AutomationExerciseProjectTestCases.drivers.MyDriver;
import com.AutomationExerciseProjectTestCases.pages.HomePage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static com.AutomationExerciseProjectTestCases.utils.PropertiesLoader.getPropertyValue;

public class TestBase {
    protected MyDriver driver;
    @BeforeClass
    public void setUp() {
        driver = new MyDriver(getPropertyValue("browserName"));
        new HomePage(driver).navigateToTheHomePage();
    }

    @AfterClass
    public void tearDown() {
        driver.browserActions().quitBrowser();
    }
}
