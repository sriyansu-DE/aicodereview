package com.codereviewer;

import java.nio.file.Files;
import java.nio.file.Path;

import com.codereviewer.analyzer.CodeReviewer;
import com.codereviewer.model.ReviewResult;

public class Main {

    public static void main(String[] args) {

        try {
            // File that you want to review
            Path file = Path.of("TestCode.java");

            // Read source code from the Java file
            String sourceCode = Files.readString(file);

            // Get file name
            String fileName = file.getFileName().toString();

            // Create CodeReviewer
            CodeReviewer reviewer = new CodeReviewer();

            // Perform static code analysis
            ReviewResult result = reviewer.review(sourceCode, fileName);

            // Display the result
            System.out.println("======================================");
            System.out.println("        AI CODE REVIEWER");
            System.out.println("======================================");

            System.out.println("File: " + fileName);

            System.out.println("--------------------------------------");
            System.out.println("Review Result:");
            System.out.println("--------------------------------------");

            System.out.println(result);

            System.out.println("======================================");

        } catch (Exception e) {

            System.out.println("Error while reviewing code:");
            e.printStackTrace();
        }
    }
}
