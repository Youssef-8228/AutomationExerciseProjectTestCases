package com.AutomationExerciseProjectTestCases.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeStampUtil {
    private TimeStampUtil(){}

    public static String getTimeStamp(){
        SimpleDateFormat formatter = new SimpleDateFormat("yy-MM-dd_HH-mm");
        LogsUtil.info("The time Stamp is: "+formatter.toString());
        return formatter.format(new Date());
    }
    public static String getUniqueEmail(){
       String email = PropertiesLoader.getPropertyValue("Email_TimeStamp") + "_" +getTimeStamp() + "@gmail.com";
       LogsUtil.info("The email is: "+email);
        return email;
    }
}

