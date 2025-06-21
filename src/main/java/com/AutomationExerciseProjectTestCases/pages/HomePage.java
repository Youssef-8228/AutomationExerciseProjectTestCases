package com.AutomationExerciseProjectTestCases.pages;

import com.AutomationExerciseProjectTestCases.drivers.MyDriver;
import com.AutomationExerciseProjectTestCases.utils.CustomSoftAssertion;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.AutomationExerciseProjectTestCases.utils.PropertiesLoader.getPropertyValue;

public class HomePage {

    private final MyDriver driver;
    private final By logedInMessage = By.xpath("//a[contains(text(), 'Logged in as')]");
    private final By logoutButton = By.partialLinkText("Logout");
    private final By errorMessage = By.cssSelector("p[style]");
    private final By deleteAccountButton = By.partialLinkText("Delete Account");
    private final By deleteMessage = By.tagName("b");//Account Deleted!
    private final By continueButton = By.cssSelector("a[data-qa=\"continue-button\"]");
    private final By contactUsButton = By.partialLinkText("Contact");
    private final By loginSignUpButton = By.partialLinkText("Login");
    private final By continueShoppingButton = By.xpath("//button[text()='Continue Shopping']");
    private final By addedProductSuccessMessage = By.cssSelector("div.modal-body p.text-center");
    private final By viewCartLink = By.partialLinkText("View Cart");


    public HomePage(MyDriver driver) {
        this.driver = driver;
    }

    //Actions
    @Step("Navigate to the home page")
    public HomePage navigateToTheHomePage(){
        driver.browserActions().navigateToURL(getPropertyValue("homePageURL"));
        return this;
    }
    @Step("Naviate to the login/sign-up page")
    public LoginPage clickOnTheLoginSignUpLink(){
        driver.elementsActions().clickOn(loginSignUpButton);
        return new LoginPage(driver);
    }
    @Step("Navigate to the contact us page")
    public ContactUsPage clickOnContactUs(){
        driver.elementsActions().clickOn(contactUsButton);
        return new ContactUsPage(driver);
    }
    @Step("Click on the Logout Button")
    public HomePage clickOnLogout() {
        driver.elementsActions().clickOn(logoutButton);
        return this;
    }
    @Step("Click on the delete account button")
    public HomePage clickOnDeleteAccountButton(){
        driver.elementsActions().clickOn(deleteAccountButton);
        return this;
    }
    @Step("Click on the continue button")
    public HomePage clickOnContinueButton(){
        driver.elementsActions().clickOn(continueButton);
        return this;
    }
    @Step("Adding item: {productName} to my cart")
    public HomePage addItemToCart (String productName){
        driver.elementsActions().clickOn(By.xpath("//p[text()='" + productName + "']/following-sibling::a[contains(@class,'add-to-cart')]"));
        return this;
    }
    private String getSuccessMessageOfAddingItemToCart(){
        return driver.elementsActions().getData(addedProductSuccessMessage);
    }
    @Step("Click on continue shopping button")
    public HomePage clickOnContinueShoppingButton(){
        driver.elementsActions().clickOn(continueShoppingButton);
        return this;
    }
    @Step("Click on view cart button")
    public CartPage clickOnViewCart (){
        driver.elementsActions().clickOn(viewCartLink);
        return new CartPage(driver);
    }

    //Validations
    private HomePage softAssertHomeURL() {
        CustomSoftAssertion.softAssertion.assertEquals(driver.browserActions().getCurrentURL(),
                getPropertyValue("homePageURL"),
                "The successful login test is failed");
        return this;
    }

    private HomePage softAssertLoggedInMessage() {
        CustomSoftAssertion.softAssertion.assertTrue(driver.elementsActions().getData(logedInMessage).contains("Logged in as")
                , "The login is failed");
        return this;
    }

    @Step("Validate Successful Redirection to the home page as a logged OR rigistered user")
    public HomePage softAssertSuccessfulRedirectionToHomePageAsRegisteredUser() {
        softAssertHomeURL().softAssertLoggedInMessage();
        return this;
    }

    @Step("Unsuccessful login soft assertion")
    public LoginPage softAssertUnsuccessfulLogin() {
        CustomSoftAssertion.softAssertion.assertEquals(driver.elementsActions().getData(errorMessage),
                getPropertyValue("loginErrorMessage"),
                "The unsuccessful login test is failed");
        return new LoginPage(driver);
    }
    @Step("Validate successfully Logout")
    public LoginPage validateSuccessfullyLogOut() {
        CustomSoftAssertion.softAssertion.assertEquals(driver.browserActions().getCurrentURL(),
                getPropertyValue("loginPageURL"),
                "The logout assertion is failed");
        return new LoginPage(driver);
    }
    @Step("Validate Deleting the account")
    public HomePage validateDeleteingAccount(){
        CustomSoftAssertion.softAssertion.assertEquals(driver.elementsActions().getData(deleteMessage),
                getPropertyValue("deleteAccountMessage"),
                "The account does not deleted");
        return this;
    }
    @Step("Validate redirection to the home page")
    public HomePage validateRedirectionToHomePage(){
        softAssertHomeURL();
        return this;
    }
    @Step("Validate the success message after adding item to the cart")
    public HomePage validateSuccessMessageOfAddingItemToCart(){
        CustomSoftAssertion.softAssertion.assertTrue(getSuccessMessageOfAddingItemToCart().contains("Your product has been added to cart."),"The item is not added to the cart");
        return this;
    }
}
