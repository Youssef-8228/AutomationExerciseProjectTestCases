package com.AutomationExerciseProjectTestCases.utils;

public class TerminalUtils {
    private TerminalUtils(){};

    public static void executeTerminalCommand(String...commands){
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(commands);
            processBuilder.inheritIO();
            Process process = processBuilder.start();
            process.waitFor();
            LogsUtil.info("Command executed successfully" + String.join("",commands));
        }catch (Exception e){
            LogsUtil.error("Failed to execute the command: "+ e.getMessage());
        }
    }
}
