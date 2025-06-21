package com.AutomationExerciseProjectTestCases.tests;

import com.AutomationExerciseProjectTestCases.listeners.TestNGListeners;
import com.AutomationExerciseProjectTestCases.pages.HomePage;
import org.testng.annotations.*;

import static com.AutomationExerciseProjectTestCases.utils.PropertiesLoader.getPropertyValue;

@Listeners(TestNGListeners.class)
public class ContactUsTests extends TestBase {
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
}
