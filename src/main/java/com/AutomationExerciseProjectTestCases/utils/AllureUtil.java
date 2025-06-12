package com.AutomationExerciseProjectTestCases.utils;

import io.qameta.allure.Allure;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class AllureUtil {
    private AllureUtil(){}
    private static String ALLURE_REPORT_PATH = PropertiesLoader.getPropertyValue("allure.report.directory");
    private static String ALLURE_RESULTS_PATH = PropertiesLoader.getPropertyValue("allure.results.directory");
    private static String USER_HOME = System.getProperty("user.home");
    private static String ALLURE_PATH = USER_HOME + File.separator + "scoop"+File.separator+"apps"
            +File.separator+"allure"+ File.separator+"2.34.0"
            +File.separator +"bin"+File.separator+"allure";

    public static void generateAllureReport(){
        if(PropertiesLoader.getPropertyValue("os.name").toLowerCase().contains("win"))
        {
            String winPath = ALLURE_PATH+".bat";
            TerminalUtils.executeTerminalCommand(winPath,"generate", ALLURE_RESULTS_PATH,"-o",ALLURE_REPORT_PATH,"clean","--single-file");
            LogsUtil.info("Allure generated successfully on windows");
        }else {
            TerminalUtils.executeTerminalCommand(ALLURE_PATH,"generate", ALLURE_RESULTS_PATH,"-o",ALLURE_REPORT_PATH,"clean","--single-file");
            LogsUtil.info("Allure generated successfully on: "+System.getProperty("os.name"));
        }
    }
    public static void openAllureReport(String reportName){
        String reportPath = ALLURE_REPORT_PATH + File.separator+ reportName;
        if (PropertiesLoader.getPropertyValue("openALlureReportAutomatically").equalsIgnoreCase("true"))
        {
            if (System.getProperty("os.name").toLowerCase().contains("win"))
            {
                TerminalUtils.executeTerminalCommand("cmd.exe","/c","start",reportPath);
            }else
            {
                TerminalUtils.executeTerminalCommand("open",reportPath);
            }
        }
    }
    public static String renameReport(){
        File newName = new File("Report_" + TimeStampUtil.getTimeStamp() + ".html");
        File oldName = new File(ALLURE_REPORT_PATH + File.separator + "index.html");
        FileUtil.renameFile(oldName,newName);
        return newName.getName();
    }
    public static void attacheLogsToAllureReport() {
        try {
            File logFile = FileUtil.getLatestFile(LogsUtil.LOGS_PATH);
            if (!logFile.exists()) {
                LogsUtil.warn("Log file does not exist: " + LogsUtil.LOGS_PATH);
                return;
            }

            Allure.addAttachment("logs.log", Files.readString(Path.of(logFile.getPath())));
            LogsUtil.info("Logs attached to Allure report");
        } catch (Exception e) {
            LogsUtil.error("Failed to attach logs to Allure report: " + e.getMessage());
        }
    }
    public static void attachScreenShotToAllure(String screenshotName , String screenshotPath){
        try {
            Allure.addAttachment(screenshotName,Files.newInputStream(Path.of(screenshotPath)));
        }catch (Exception e){
            LogsUtil.error("Failed to attach screenshot to allure report: " + e.getMessage());
        }
    }

}
