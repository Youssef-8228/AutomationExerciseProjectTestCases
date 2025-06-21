package com.AutomationExerciseProjectTestCases.tests;


import com.AutomationExerciseProjectTestCases.listeners.TestNGListeners;
import com.AutomationExerciseProjectTestCases.pages.HomePage;
import com.AutomationExerciseProjectTestCases.utils.PropertiesLoader;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static com.AutomationExerciseProjectTestCases.utils.PropertiesLoader.getPropertyValue;

@Listeners(TestNGListeners.class)
public class ShoppingTests extends TestBase {


    @Test
    public void E2E(){
        new HomePage(driver)
                .clickOnTheLoginSignUpLink()
                .validateDisplayingLoginForm()
                .enterEmail(getPropertyValue("Email"))
                .enterPassword(getPropertyValue("validPassword"))
                .clickLoginButton()
                .softAssertSuccessfulRedirectionToHomePageAsRegisteredUser()
                .addItemToCart(PropertiesLoader.getPropertyValue("productName"))
                .validateSuccessMessageOfAddingItemToCart()
                .clickOnViewCart()
                .validateRedirectionToCartPage()
                .validateTheAddedProduct()
                .clickOnCheckOutButton()
                .validateRedirectionToCheckOutPage()
                .clickOnPlaceHolderButton()
                .validateRedirectionToPaymentPage()
                .enterPaymentData(
                        getPropertyValue("nameOnCard"),getPropertyValue("cardNumber"),
                        getPropertyValue("CVC"),getPropertyValue("expirationMonth"),
                        getPropertyValue("expirationYear"))
                .clickOnPayAndConfirmButton()
                .validateConfirmationMessageOfPlaceHolder()
                .clickOnContinueButton()
                .clickOnLogout()
                .validateSuccessfullyLogOut();
    }
}

