package com.codereviewer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import com.codereviewer.analyzer.CodeReviewer;
import com.codereviewer.model.ReviewResult;

public class Main {

    public static void main(String[] args) {

        try {

            // File to review — accept from args or use default
            Path file = args.length > 0
                    ? Path.of(args[0])
                    : Path.of("src/main/java/com/codereviewer/TestCode.java");

            // Check if file exists
            if (!Files.exists(file)) {
                System.err.println("Error: " + file + " not found.");
                return;
            }

            // Read source code
            String sourceCode = Files.readString(file);

            // Check if file is empty
            if (sourceCode.isBlank()) {
                System.err.println("Error: " + file.getFileName() + " is empty.");
                return;
            }

            // Get file name
            String fileName = file.getFileName().toString();

            // Create CodeReviewer
            CodeReviewer reviewer = new CodeReviewer();

            // Perform static analysis
            ReviewResult result = reviewer.review(sourceCode, fileName);

            // Check result
            if (result == null) {
                System.err.println("Error: Review returned null.");
                return;
            }

            // Display result
            System.out.println("======================================");
            System.out.println("          AI CODE REVIEWER");
            System.out.println("======================================");
            System.out.println("File: " + fileName);
            System.out.println("--------------------------------------");
            System.out.println("Review Result:");
            System.out.println("--------------------------------------");
            System.out.println(result.getIssues());
            System.out.println("======================================");

        } catch (IOException e) {
            System.err.println("Error reading the file:");
            System.err.println(e.getMessage());

        } catch (Exception e) {
            System.err.println("Error during code analysis:");
            System.err.println(e.getMessage());
        }
    }
}