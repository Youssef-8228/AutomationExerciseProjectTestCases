package com.AutomationExerciseProjectTestCases.pages;
import com.AutomationExerciseProjectTestCases.drivers.MyDriver;
import com.AutomationExerciseProjectTestCases.utils.CustomSoftAssertion;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import static com.AutomationExerciseProjectTestCases.utils.PropertiesLoader.getPropertyValue;

public class ContactUsPage {
    //Locators
    private final MyDriver driver;
    private final By getInTouchTitle = By.xpath("//h2[text()='Get In Touch']");
    private final By name = By.cssSelector("input[name=\"name\"]");
    private final By email = By.cssSelector("input[name=\"email\"]");
    private final By subject = By.cssSelector("input[name=\"subject\"]");
    private final By message = By.id("message");
    private final By file = By.cssSelector("input[type=\"file\"]");
    private final By submitButton = By.cssSelector("input[name=\"submit\"]");
    private final By successfullStatusMessage = By.cssSelector("div.status.alert.alert-success");
    private final By homePageButton = By.cssSelector("a.btn");

    //constructor
    public ContactUsPage(MyDriver driver) {
        this.driver = driver;
    }

    //Actions
    @Step("Fill the message us form")
    public ContactUsPage fillGetInTouchForm(String name,String email,String subject,String message,String filePath){
        driver.elementsActions().type(this.name,name);
        driver.elementsActions().type(this.email,email);
        driver.elementsActions().type(this.subject,subject);
        driver.elementsActions().type(this.message,message);
        driver.elementsActions().type(file,filePath);
        return this;
    }
    @Step("Click on the submit button of the message us form")
    public ContactUsPage clickOnSubmitButton(){
        driver.elementsActions().clickOn(submitButton);
        return this;
    }
    @Step("Confirm the alert of the message us form")
    public ContactUsPage confirmTheAlert(){
        driver.elementsActions().confirmAlert();
        return this;
    }
    @Step("Click on the home page button")
    public HomePage clickOnHomePageButton(){
        driver.elementsActions().clickOn(homePageButton);
        return new HomePage(driver);
    }


    //Validations
    @Step("Validate the displaying of the contact us page")
    public ContactUsPage validateDisplayingOfContactUsPage(){
        CustomSoftAssertion.softAssertion.assertEquals(driver.elementsActions().getData(getInTouchTitle),
                getPropertyValue("getInTouchText"),
                "The get in touch section is not displayed");
        return this;
    }
    @Step("Validate the success message of the submit contact us form")
    public ContactUsPage validateSubmittingMessageSuccessfully(){
        CustomSoftAssertion.softAssertion.assertEquals(driver.elementsActions().getData(successfullStatusMessage),
                getPropertyValue("successContactUsMessage"),
                "The submiting message of contact us is failed");
        return this;
    }
}
