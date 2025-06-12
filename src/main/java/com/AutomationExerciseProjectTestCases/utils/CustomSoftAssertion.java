package com.AutomationExerciseProjectTestCases.utils;

import com.AutomationExerciseProjectTestCases.drivers.MyDriver;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.asserts.SoftAssert;


public class CustomSoftAssertion extends SoftAssert {

    public static CustomSoftAssertion softAssertion = new CustomSoftAssertion();

    public static void assertAllSoft (ITestResult result){
        try {
            softAssertion.assertAll();
            LogsUtil.info("Assert all is performed");
        }catch (AssertionError error){
            LogsUtil.error("Custome soft assertion is failed: "+error.getMessage());
            result.setStatus(ITestResult.FAILURE);
            result.setThrowable(error);
        }
        finally {
            reIntitializeSoftAssertion();
        }
    }
    private static void reIntitializeSoftAssertion(){
        softAssertion = new CustomSoftAssertion();
    }
}
