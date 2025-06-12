package com.AutomationExerciseProjectTestCases.pages;

import com.AutomationExerciseProjectTestCases.drivers.MyDriver;
import com.AutomationExerciseProjectTestCases.utils.CustomSoftAssertion;
import com.AutomationExerciseProjectTestCases.utils.PropertiesLoader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.AutomationExerciseProjectTestCases.utils.PropertiesLoader.getPropertyValue;

public class RegisterationPage {
    // Locators
    private final MyDriver driver;
    private final By password = By.id("password");
    private final By firstName = By.id("first_name");
    private final By lastName = By.id("last_name");
    private final By address = By.id("address1");
    private final By country = By.id("country");
    private final By state = By.id("state");
    private final By city = By.id("city");
    private final By zipCode = By.id("zipcode");
    private final By mobileNumber = By.id("mobile_number");
    private final By createAccountButton = By.cssSelector("button[data-qa=\"create-account\"]");
    private final By continueButton = By.cssSelector("a[data-qa=\"continue-button\"]");
    private final By successfullyCreatedMessage = By.tagName("b");
    private final By rigesterationErrorMessage = By.cssSelector("p[style]");

    //Constructor
    public RegisterationPage(MyDriver driver) {
        this.driver = driver;
    }

    // Actions
    @Step("Enter the password for registeration: {password}")
    public RegisterationPage enterAccountPassword(String password) {
        driver.elementsActions().type(this.password, password);
        return this;
    }
    @Step("Fill the address information form")
    public RegisterationPage fillAddressInformation(String firstName, String lastName,
                                                    String address,
                                                    String country,
                                                    String state,
                                                    String city,
                                                    String zipCode,
                                                    String mobileNumber) {
        driver.elementsActions().type(this.firstName, firstName);
        driver.elementsActions().type(this.lastName, lastName);
        driver.elementsActions().type(this.address, address);
        driver.elementsActions().selectFromDropDown(this.country, country);
        driver.elementsActions().type(this.state, state);
        driver.elementsActions().type(this.city, city);
        driver.elementsActions().type(this.zipCode, zipCode);
        driver.elementsActions().type(this.mobileNumber, mobileNumber);
        return this;
    }
    @Step("Click on the create account button")
    public RegisterationPage clickOnCreateAccount() {
        driver.elementsActions().clickOn(createAccountButton);
        return this;
    }
    @Step("Click on continue button")
    public HomePage clickContinueButton() {
        driver.elementsActions().clickOn(continueButton);
        return new HomePage(driver);
    }

    //Validations
    @Step("Validate that the account is registered successfully")
    public RegisterationPage successfullMessageSoftassertion() {
        CustomSoftAssertion.softAssertion.assertEquals(driver.elementsActions().getData(successfullyCreatedMessage),
                PropertiesLoader.getPropertyValue("successMessage"),
                "The account does not created successfully");
        return this;
    }
    @Step("Validate the system redirection to complete registeration")
    public RegisterationPage softAssertRedirectionToCompleteRegisteration(){
        CustomSoftAssertion.softAssertion.assertEquals(driver.browserActions().getCurrentURL(),
                getPropertyValue("signUpURL"),
                "The registeration redirection is failed");
        return this;
    }
    public LoginPage validateUnsuccessfulRegisteration(){
        CustomSoftAssertion.softAssertion.assertEquals(driver.elementsActions().getData(rigesterationErrorMessage),
                getPropertyValue("rigeterationErrorMessage"),
                "The assertion of the registeration with same email is failed");
        return new LoginPage(driver);
    }


}
