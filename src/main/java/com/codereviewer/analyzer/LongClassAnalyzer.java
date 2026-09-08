package com.codereviewer.analyzer;

import java.util.ArrayList;
import java.util.List;

import com.codereviewer.model.codeIssue;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;

public class LongClassAnalyzer implements CodeAnalyzer {

    private static final int MAX_METHODS = 20;
    private static final int MAX_LINES = 500;

    @Override
    public List<codeIssue> analyze(CompilationUnit cu) {

        List<codeIssue> issues = new ArrayList<>();

        for (ClassOrInterfaceDeclaration clazz :
                cu.findAll(ClassOrInterfaceDeclaration.class)) {

            int methods = clazz.getMethods().size();

            int start = clazz.getBegin()
                    .map(p -> p.line)
                    .orElse(0);

            int end = clazz.getEnd()
                    .map(p -> p.line)
                    .orElse(start);

            int lines = end - start + 1;

            if (methods > MAX_METHODS || lines > MAX_LINES) {

                issues.add(new codeIssue(
    "JAVA006",
    "MEDIUM",
    start,
    "Class '" + clazz.getNameAsString()
        + "' is too large. Methods: " + methods +
        ", Lines: " + lines,
    "Split the class into smaller classes following the Single Responsibility Principle."
));
            }
        }

        return issues;
    }
}