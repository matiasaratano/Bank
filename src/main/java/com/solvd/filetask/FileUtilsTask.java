package com.solvd.filetask;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtilsTask {

    public static void main(String[] args) throws IOException {

        // Read a file into a string
        String text = FileUtils.readFileToString(
                new File("./src/main/resources/txt/input.txt"), "UTF-8");

        // Remove whitespace from text
        String noWhitespace = StringUtils.deleteWhitespace(text);
        //System.out.println(noWhitespace);

        // Split the string into an array of words
        String[] words = StringUtils.split(text);
        //for (int i = 0; i < words.length; i++) {
        //    System.out.println(words[i]);
        //}

        // Reverse a word
        String reverse = StringUtils.reverse(words[0]);
        //System.out.println(reverse);

        // Join the words in the array into a single string, separated by commas
        String joined = StringUtils.join(words, ',');
        //System.out.println(joined);

        // Write the modified string to a new file
        FileUtils.writeStringToFile(
                new File("./src/main/resources/txt/outputjoined.txt"), joined, "UTF-8");

        //Copy the input file to a new location
        //FileUtils.copyFile(
        //        new File("./src/main/resources/txt/input.txt"),
        //        new File("./src/main/resources/txt/input-copy.txt"));

        // Rename the copied file
        //FileUtils.moveFile(
        //  new File("./src/main/resources/txt/input-copy.txt"),
        //   new File("./src/main/resources/txt/input-copy-renamed.txt"));

        // Delete the original input file
        //FileUtils.deleteQuietly(new File("./src/main/resources/txt/input.txt"));

        // Get the size of the output file in bytes
        long size = FileUtils.sizeOf(new File("./src/main/resources/txt/output.txt"));
        //System.out.println(size);

        Pattern pattern = Pattern.compile(words[0]); //This
        Matcher matcher = pattern.matcher("This is a test");

        while (matcher.find()) {
            System.out.println("I found the text " + matcher.group() + " starting at index " +
                    matcher.start() + " and ending at index " + matcher.end());

        }
        
    }
}