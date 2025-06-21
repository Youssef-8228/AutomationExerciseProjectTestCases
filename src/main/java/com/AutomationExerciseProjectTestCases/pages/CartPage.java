package com.AutomationExerciseProjectTestCases.pages;

import com.AutomationExerciseProjectTestCases.drivers.MyDriver;
import com.AutomationExerciseProjectTestCases.utils.CustomSoftAssertion;
import com.AutomationExerciseProjectTestCases.utils.PropertiesLoader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private MyDriver driver;
    private final By cartProductName = By.cssSelector("tbody tr td.cart_description h4 a");
    private final By checkOutButton = By.cssSelector("a.check_out");

    public CartPage(MyDriver driver){
        this.driver = driver;
    }
    //Actions
    private String getProductName (){
        return driver.elementsActions().getData(cartProductName);
    }
    @Step("Click on the checkout button")
    public CheckOutPage clickOnCheckOutButton(){
        driver.elementsActions().clickOn(checkOutButton);
        return new CheckOutPage(driver);
    }

    //Validations
    @Step("Validate the redirection to the cart page")
    public CartPage validateRedirectionToCartPage(){
        CustomSoftAssertion.softAssertion.assertEquals(driver.browserActions().getCurrentURL(),
                PropertiesLoader.getPropertyValue("cartPageURL"),
                "Redirection to cart page is failed");
        return this;
    }
    @Step("Validate that the product is added successfully to the cart")
    public CartPage validateTheAddedProduct(){
        CustomSoftAssertion.softAssertion.assertEquals(getProductName(),
                PropertiesLoader.getPropertyValue("productName"),
                "The product of the cart page mismatch the added product");
        return this;
    }
}
