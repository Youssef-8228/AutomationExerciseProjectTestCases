package com.AutomationExerciseProjectTestCases.pages;

import com.AutomationExerciseProjectTestCases.drivers.MyDriver;
import com.AutomationExerciseProjectTestCases.utils.CustomSoftAssertion;
import com.AutomationExerciseProjectTestCases.utils.PropertiesLoader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class CheckOutPage {
    private MyDriver driver;
    private final By placeHolder = By.cssSelector("a.check_out");

    public CheckOutPage(MyDriver driver){this.driver = driver;}

    //Actions
    @Step("Click on the place holder button")
    public PaymentPage clickOnPlaceHolderButton(){
        driver.elementsActions().clickOn(placeHolder);
        return new PaymentPage(driver);
    }
    //Validations
    @Step("Validate the redirection to the checkout page")
    public CheckOutPage validateRedirectionToCheckOutPage(){
        CustomSoftAssertion.softAssertion.assertEquals(driver.browserActions().getCurrentURL(),
                PropertiesLoader.getPropertyValue("checkOutPageURL"),
                "Redirection to checkout page is failed");
        return this;
    }
}
