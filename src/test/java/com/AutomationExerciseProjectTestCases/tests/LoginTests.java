package com.AutomationExerciseProjectTestCases.tests;

import com.AutomationExerciseProjectTestCases.drivers.MyDriver;
import com.AutomationExerciseProjectTestCases.listeners.TestNGListeners;
import com.AutomationExerciseProjectTestCases.pages.HomePage;
import org.testng.annotations.*;

import static com.AutomationExerciseProjectTestCases.utils.PropertiesLoader.getPropertyValue;

@Listeners(TestNGListeners.class)
public class LoginTests {
    private MyDriver driver;

    @Test(dependsOnMethods = "unsuccessfulLogin")
    public void successfulLogin() {
        new HomePage(driver)
                .clickOnTheLoginSignUpLink()
                .validateDisplayingLoginForm()
                .enterEmail(getPropertyValue("Email"))
                .enterPassword(getPropertyValue("validPassword"))
                .clickLoginButton()
                .softAssertSuccessfulRedirectionToHomePageAsRegisteredUser();
    }

    @Test(dependsOnMethods = "successfulLogin")
    public void successfulLogOut() {
        new HomePage(driver)
                .clickOnLogout()
                .validateSuccessfullyLogOut();
    }

    @Test
    public void unsuccessfulLogin() {
        new HomePage(driver)
                .clickOnTheLoginSignUpLink()
                .validateDisplayingLoginForm()
                .enterEmail(getPropertyValue("invalidEmail"))
                .enterPassword(getPropertyValue("validPassword"))
                .clickLoginButton()
                .softAssertUnsuccessfulLogin();
    }


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
