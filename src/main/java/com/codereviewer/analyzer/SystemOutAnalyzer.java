package com.codereviewer.analyzer;

import com.codereviewer.model.codeIssue;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.MethodCallExpr;

import java.util.ArrayList;
import java.util.List;

public class SystemOutAnalyzer implements CodeAnalyzer {
  @Override 

  public List<codeIssue> analyze(CompilationUnit cu){
    List<codeIssue> issues = new ArrayList<>();

    cu.findAll(MethodCallExpr.class).forEach(call -> {

      String text = call.toString();

      if(text.startsWith("System.out")){
        int line = call.getBegin().map(p -> p.line).orElse(-1);

        issues.add(
          new codeIssue(
            "SYSTEM OUT",
            "LOW",
            line,
            "System.out output is used in the code.",
            "Use a logging framework such as java.util.logging or SLF4J."
          )
        );
      }
    });

    return issues;
  }
}
