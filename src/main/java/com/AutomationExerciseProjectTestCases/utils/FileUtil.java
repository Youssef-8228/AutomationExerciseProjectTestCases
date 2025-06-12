package com.AutomationExerciseProjectTestCases.utils;


import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;


public class FileUtil {

    private FileUtil(){}

    // using this methode to attache the logs with the allure report
    public static File getLatestFile(String folderPath){
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        if(files == null || files.length == 0){
            LogsUtil.warn("No files found in this directory: " + folderPath);
            return null;
        }
        File latestFile = files[0];
        for(File file: files){
            if(file.lastModified() > latestFile.lastModified()){
                latestFile = file;
            }
        }
        return latestFile;
    }

    public static void deleteFiles(File path){
        if(path == null || !path.exists()){
            LogsUtil.warn("Directory dose not exist: "+ path);
        }
        File[] filesList = path.listFiles();
        if(filesList == null){
            LogsUtil.warn("Failed to list the files in: " + path);
        }
        for(File file:filesList){
            if(file.isDirectory()){
                deleteFiles(file);
            }else{
                try {
                    Files.delete(file.toPath());
                }catch (IOException e){
                    LogsUtil.error("Failed to delete file: "+file);
                }
            }
        }
    }

    public static void cleanDirectory(File path){
        try {
            FileUtils.deleteQuietly(path);
        }catch (Exception e){
            LogsUtil.error("The following error occur when clearing directory: " + e.getMessage());
        }
    }

    public static void renameFile(File oldName, File newName) {
        try {
            File targetFile = oldName.getParentFile().getAbsoluteFile();
            String targetDirectory = targetFile + File.separator + newName;
            FileUtils.copyFile(oldName,new File(targetDirectory));
            FileUtils.deleteQuietly(oldName);
            LogsUtil.info("The file is renamed to be" + newName.getName());
        } catch (Exception e) {
            LogsUtil.error("Failed to rename file: " + e.getMessage());
        }
    }
}
