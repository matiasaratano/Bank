package com.solvd.filetask;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class WordCounter {
    public static void main(String[] args) throws IOException {
        // Read the content
        String text = FileUtils.readFileToString(
                new File("./src/main/resources/txt/input.txt"), "UTF-8");
        System.out.println(text);
        // Split the string into an array of words
        String[] words = StringUtils.split(text);

        // Count the unique words by adding them to a set
        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));

        // Write the result to a new file
        FileUtils.writeStringToFile(
                new File("./src/main/resources/txt/outputcounter.txt"),
                "Number of unique words: " + uniqueWords.size(),
                "UTF-8");
        
    }
}