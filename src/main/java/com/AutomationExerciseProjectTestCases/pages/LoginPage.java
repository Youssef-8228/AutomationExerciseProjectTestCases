package com.AutomationExerciseProjectTestCases.pages;

import com.AutomationExerciseProjectTestCases.drivers.MyDriver;
import com.AutomationExerciseProjectTestCases.utils.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.AutomationExerciseProjectTestCases.utils.PropertiesLoader.getPropertyValue;

public class LoginPage {
    //Locators
    private final MyDriver driver;

    private final By loginEmail = By.cssSelector("input[data-qa='login-email']");
    private final By password = By.cssSelector("input[data-qa='login-password']");
    private final By loginButton = By.cssSelector("button[data-qa=\"login-button\"]");
    private final By regiterationName = By.cssSelector("input[name=\"name\"]");
    private final By registerationEmail = By.cssSelector("input[data-qa=\"signup-email\"]");
    private final By signUpButton = By.cssSelector("button[data-qa='signup-button']");
    private final By loginFormTitle = By.cssSelector(".login-form h2");
    private final By signUpFormTitle = By.cssSelector(".signup-form h2");


    // Constructor
    public LoginPage(MyDriver driver) {
        this.driver = driver;
    }

    //Actions
    @Step("Enter Email")
    public LoginPage enterEmail(String email) {
        driver.elementsActions().type(loginEmail, email);
        return this;
    }

    @Step("Enter password")
    public LoginPage enterPassword(String password) {
        driver.elementsActions().type(this.password, password);
        return this;
    }

    @Step("Clicking on the login button")
    public HomePage clickLoginButton() {
        driver.elementsActions().clickOn(loginButton);
            return new HomePage(driver);
    }
    @Step("Enter the registeration name: {name}")
    public LoginPage enterRegisterationName (String name){
        driver.elementsActions().type(regiterationName,name);
        return this;
    }
    @Step("Enter the registeration Email: {email}")
    public LoginPage enterRegisterationEmail (String email){
        driver.elementsActions().type(registerationEmail,email);
        return this;
    }
    @Step("Click on the sign up button")
    public RegisterationPage clickOnSignUpButton (){
        driver.elementsActions().clickOn(signUpButton);
        return new RegisterationPage(driver);
    }
    //validations Soft assert
    private LoginPage validateLoginAndSignUpPageURL(){
        CustomSoftAssertion.softAssertion.assertEquals(driver.browserActions().getCurrentURL(),
                getPropertyValue("loginPageURL"),
                "The redirection to login page is failed");
        return this;
    }
    private LoginPage validateLoginFormTitle(){
        CustomSoftAssertion.softAssertion.assertEquals(driver.elementsActions().getData(loginFormTitle),
                getPropertyValue("loginFormTitle"),
                "The login form title is not displayed");
        return this;
    }
    private LoginPage validateSignUpFormTitle(){
        CustomSoftAssertion.softAssertion.assertEquals(driver.elementsActions().getData(signUpFormTitle),
                getPropertyValue("signUpFormTitle"),
                "The sign up form title is not displayed");
        return this;
    }

    @Step("Validate the displaying of the login form")
    public LoginPage validateDisplayingLoginForm(){
        validateLoginAndSignUpPageURL().validateLoginFormTitle();
        return this;
    }
    @Step("Validate the displaying of the sign up form")
    public LoginPage validateDisplayingSignUpForm(){
        validateLoginAndSignUpPageURL().validateSignUpFormTitle();
        return this;
    }


}
