package com.codereviewer.analyzer;

import com.codereviewer.model.codeIssue;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.stmt.*;

import java.util.ArrayList;
import java.util.List;

public class ComplexityAnalyzer implements CodeAnalyzer{

  private static final int MAX_COMPLEXITY = 10;
  @Override
  public List<codeIssue> analyze (CompilationUnit cu){
    List<codeIssue> issues = new ArrayList<>();

    cu.findAll(MethodDeclaration.class)
      .forEach(method -> {

        int complexity = 1;

        complexity += 
                  method.findAll(IfStmt.class)
                        .size();
        complexity +=
                  method.findAll(ForStmt.class)
                        .size();

        complexity +=
                  method.findAll(ForEachStmt.class)
                        .size();

        complexity +=
                  method.findAll(WhileStmt.class)
                        .size();

        complexity +=
                  method.findAll(DoStmt.class)
                        .size();

        complexity +=
                  method.findAll(CatchClause.class)
                        .size();

        complexity +=
                  method.findAll(SwitchEntry.class)
                        .size();

        if(complexity > MAX_COMPLEXITY){

          int line = 
                  method.getBegin()
                        .map(p -> p.line)
                        .orElse(-1);

          issues.add(
                new codeIssue(
                        "COMPLEXITY",
                        "MEDIUM",
                        line,
                        "Estimated cyclomatic complexity is "
                                  + complexity
                                  + ".",
                        "Split complex logic into smaller methods and simplify branching."
                )
          );
        }
      });

      return issues;

  }
  
}
