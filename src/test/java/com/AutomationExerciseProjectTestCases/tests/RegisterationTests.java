package com.AutomationExerciseProjectTestCases.tests;

import com.AutomationExerciseProjectTestCases.listeners.TestNGListeners;
import com.AutomationExerciseProjectTestCases.pages.HomePage;
import org.testng.annotations.*;

import static com.AutomationExerciseProjectTestCases.utils.PropertiesLoader.getPropertyValue;
import static com.AutomationExerciseProjectTestCases.utils.TimeStampUtil.getUniqueEmail;

@Listeners(TestNGListeners.class)
public class RegisterationTests extends TestBase {


    @Test (dependsOnMethods = "rigesterationWithSameEmail")
    public void successfulRegisteration() {
        new HomePage(driver)
                .clickOnTheLoginSignUpLink()
                .validateDisplayingSignUpForm()
                .enterRegisterationName(getPropertyValue("regiterationName"))
                .enterRegisterationEmail(getUniqueEmail())
                .clickOnSignUpButton()
                .softAssertRedirectionToCompleteRegisteration()
                .enterAccountPassword(getPropertyValue("registerationPassword"))
                .fillAddressInformation(getPropertyValue("firstName"),
                        getPropertyValue("lastName"),
                        getPropertyValue("address"),
                        getPropertyValue("country"),
                        getPropertyValue("state"),
                        getPropertyValue("city"),
                        getPropertyValue("zipCode"),
                        getPropertyValue("mobileNumber"))
                .clickOnCreateAccount()
                .successfullMessageSoftassertion()
                .clickContinueButton()
                .softAssertSuccessfulRedirectionToHomePageAsRegisteredUser()
                .clickOnDeleteAccountButton()
                .validateDeleteingAccount()
                .clickOnContinueButton()
                .validateRedirectionToHomePage();
    }
    @Test
    public void rigesterationWithSameEmail(){
        new HomePage(driver)
                .clickOnTheLoginSignUpLink()
                .validateDisplayingSignUpForm()
                .enterRegisterationName(getPropertyValue("regiterationName"))
                .enterRegisterationEmail(getPropertyValue("Email"))
                .clickOnSignUpButton()
                .validateUnsuccessfulRegisteration();
    }
}
