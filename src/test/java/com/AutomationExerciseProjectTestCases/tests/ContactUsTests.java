package com.AutomationExerciseProjectTestCases.tests;

import com.AutomationExerciseProjectTestCases.drivers.MyDriver;
import com.AutomationExerciseProjectTestCases.listeners.TestNGListeners;
import com.AutomationExerciseProjectTestCases.pages.HomePage;
import org.testng.annotations.*;

import static com.AutomationExerciseProjectTestCases.utils.PropertiesLoader.getPropertyValue;

@Listeners(TestNGListeners.class)
public class ContactUsTests {
    private MyDriver driver;

    @Test
    public void submitMessageSuccessfully() {
        new HomePage(driver)
                .clickOnContactUs()
                .validateDisplayingOfContactUsPage()
                .fillGetInTouchForm(
                        getPropertyValue("name"),
                        getPropertyValue("contactUsEmail"),
                        getPropertyValue("subject"),
                        getPropertyValue("message"),
                        getPropertyValue("filePath"))
                .clickOnSubmitButton()
                .confirmTheAlert()
                .validateSubmittingMessageSuccessfully()
                .clickOnHomePageButton()
                .validateRedirectionToHomePage();
    }
    @Test
    public void addItemToCart(){
        new HomePage(driver)
                .clickOnTheLoginSignUpLink()
                .validateDisplayingLoginForm()
                .enterEmail(getPropertyValue("Email"))
                .enterPassword(getPropertyValue("validPassword"))
                .clickLoginButton()
                .softAssertSuccessfulRedirectionToHomePageAsRegisteredUser()
                .clickOnAddToCartButton("Men Tshirt")
                .validateSuccessMessageOfAddingItemToCart()
                .clickOnContinueShoppingButton();
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
