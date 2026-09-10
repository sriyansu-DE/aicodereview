package com.codereviewer.analyzer;

import java.util.ArrayList;
import java.util.List;

import com.codereviewer.model.codeIssue;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.StringLiteralExpr;

public class HardcodedSecretAnalyzer implements CodeAnalyzer {

    @Override
    public List<codeIssue> analyze(CompilationUnit cu) {

        List<codeIssue> issues = new ArrayList<>();

        cu.findAll(VariableDeclarator.class).forEach(variable -> {

            if (!variable.getInitializer().isPresent())
                return;

            if (!(variable.getInitializer().get() instanceof StringLiteralExpr))
                return;

            String name = variable.getNameAsString().toLowerCase();

            if (name.contains("password")
                    || name.contains("passwd")
                    || name.contains("secret")
                    || name.contains("apikey")
                    || name.contains("api_key")
                    || name.contains("token")
                    || name.contains("key")) {

                issues.add(new codeIssue(
                        "JAVA012",
                        "HIGH",
                        variable.getBegin().map(p -> p.line).orElse(-1),
                        "Hardcoded secret detected.",
                        "Store secrets in environment variables or configuration files."
                ));
            }
        });

        return issues;
    }
}