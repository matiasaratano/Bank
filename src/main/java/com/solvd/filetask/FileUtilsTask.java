package com.solvd.filetask;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileUtilsTask {
    public static void main(String[] args) {
        // Read
        File file = new File("./src/main/resources/file.txt");
        String content = "";
        try {
            content = FileUtils.readFileToString(file, "UTF-8");
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Write
        String newContent = "This is a test.";
        try {
            FileUtils.writeStringToFile(file, newContent, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}