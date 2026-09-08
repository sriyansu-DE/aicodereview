package com.codereviewer.analyzer;

import java.util.ArrayList;
import java.util.List;

import com.codereviewer.model.codeIssue;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

public class TooManyArgumentsAnalyzer implements CodeAnalyzer {

    private static final int MAX_ARGUMENTS = 5;

    @Override
    public List<codeIssue> analyze(CompilationUnit cu) {

        List<codeIssue> issues = new ArrayList<>();

        for (MethodDeclaration method : cu.findAll(MethodDeclaration.class)) {

            int count = method.getParameters().size();

            if (count > MAX_ARGUMENTS) {

                int line = method.getBegin()
                        .map(p -> p.line)
                        .orElse(0);

                issues.add(new codeIssue(
                        "JAVA008",
                        "MEDIUM",
                        line,
                        "Method '" + method.getNameAsString()
                                + "' has too many parameters (" + count + ").",
                        "Reduce the number of parameters by using a parameter object or splitting the method."
                ));
            }
        }

        return issues;
    }
}