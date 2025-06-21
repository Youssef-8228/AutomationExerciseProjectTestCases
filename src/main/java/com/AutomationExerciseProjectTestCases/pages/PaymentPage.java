package com.AutomationExerciseProjectTestCases.pages;

import com.AutomationExerciseProjectTestCases.drivers.MyDriver;
import com.AutomationExerciseProjectTestCases.utils.CustomSoftAssertion;
import com.AutomationExerciseProjectTestCases.utils.PropertiesLoader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class PaymentPage {
    private MyDriver driver;
    private final By nameOnCard = By.name("name_on_card");
    private final By cardNumber = By.name("card_number");
    private final By CVC = By.cssSelector("input.card-cvc");
    private final By expirationMonth = By.name("expiry_month");
    private final By expirationYear = By.name("expiry_year");
    private final By payAndConfirmOrderButton = By.id("submit");

    public PaymentPage(MyDriver driver){
        this.driver = driver;
    }

    //Actions
    @Step("Filling and entering the payment data")
    public PaymentPage enterPaymentData(String nameOnCard,String cardNumber,String CVC,
                                        String expirationMonth, String expirationYear){
        driver.elementsActions().type(this.nameOnCard,nameOnCard);
        driver.elementsActions().type(this.cardNumber,cardNumber);
        driver.elementsActions().type(this.CVC,CVC);
        driver.elementsActions().type(this.expirationMonth,expirationMonth);
        driver.elementsActions().type(this.expirationYear,expirationYear);
        return this;
    }
    @Step("Click on Pay and confirm button")
    public InvoicePage clickOnPayAndConfirmButton(){
        driver.elementsActions().clickOn(payAndConfirmOrderButton);
        return new InvoicePage(driver);
    }
    //Validations
    @Step("Validate the redirection to the payment page")
    public PaymentPage validateRedirectionToPaymentPage(){
        CustomSoftAssertion.softAssertion.assertEquals(driver.browserActions().getCurrentURL(),
                PropertiesLoader.getPropertyValue("paymentPageURL"),
                "Redirection to Payment page is failed");
        return this;
    }
}
