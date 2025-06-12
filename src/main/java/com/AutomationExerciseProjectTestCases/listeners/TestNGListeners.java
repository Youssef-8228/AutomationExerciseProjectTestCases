package com.AutomationExerciseProjectTestCases.listeners;
import com.AutomationExerciseProjectTestCases.drivers.MyDriver;
import com.AutomationExerciseProjectTestCases.utils.*;
import org.testng.*;

import java.io.File;

public class TestNGListeners implements IExecutionListener, ITestListener, IInvokedMethodListener {
    private File allureResults = new File("test-outputs/allure-results");
    private File allureReport = new File("test-outputs/allure-report");
    private File logs = new File("test-outputs/Logs");
    private File screenshots = new File("test-outputs/screenshots");

    @Override
    public void onExecutionStart() {
        LogsUtil.info("Test execution is started");
        PropertiesLoader.loadProperties();
        FileUtil.deleteFiles(allureReport);
        FileUtil.deleteFiles(allureResults);
        FileUtil.cleanDirectory(logs);
        FileUtil.cleanDirectory(screenshots);
    }

    @Override
    public void onExecutionFinish() {
        LogsUtil.info("Test execution finish");
        AllureUtil.generateAllureReport();
        String reportName = AllureUtil.renameReport();
        AllureUtil.openAllureReport(reportName);
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
           CustomSoftAssertion.assertAllSoft(testResult);
            switch (testResult.getStatus()) {
                case ITestResult.SUCCESS -> ScreenshotsUtils.takeScreenshot(MyDriver.getInstance(),
                        "passed-" + testResult.getName());
                case ITestResult.FAILURE -> ScreenshotsUtils.takeScreenshot(MyDriver.getInstance(),
                        "failed-" + testResult.getName());
                case ITestResult.SKIP -> ScreenshotsUtils.takeScreenshot(MyDriver.getInstance(),
                        "skipped-" + testResult.getName());
            }
            AllureUtil.attacheLogsToAllureReport();
        }

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogsUtil.info("Test case ", result.getName(), " passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LogsUtil.info("Test case", result.getTestName(), " failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogsUtil.info("Test case", result.getTestName(), " skipped");
    }
}
