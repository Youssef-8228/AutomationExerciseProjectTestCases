package com.AutomationExerciseProjectTestCases.pages;

import com.AutomationExerciseProjectTestCases.drivers.MyDriver;
import com.AutomationExerciseProjectTestCases.utils.CustomSoftAssertion;
import com.AutomationExerciseProjectTestCases.utils.PropertiesLoader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class InvoicePage {
    private MyDriver driver;
    private final By confirmationMessage = By.xpath("//p[contains(text(), 'Your order has been confirmed')]");
    private final By continueButton = By.className("btn-primary");
    private final By downloadInvoiceButton = By.className("check_out");

    public InvoicePage(MyDriver driver){
        this.driver = driver;
    }
    //Actions
    @Step("Click on continue button")
    public HomePage clickOnContinueButton(){
        driver.elementsActions().clickOn(continueButton);
        return new HomePage(driver);
    }
    private String getConfirmationMessage(){
        return driver.elementsActions().getData(confirmationMessage);
    }
    //Validations
    @Step("Validate the invoice and the order place holder")
    public InvoicePage validateConfirmationMessageOfPlaceHolder(){
        CustomSoftAssertion.softAssertion.assertTrue(getConfirmationMessage().
                contains(PropertiesLoader.getPropertyValue("confirmationMessage")));
        return this;
    }
}
