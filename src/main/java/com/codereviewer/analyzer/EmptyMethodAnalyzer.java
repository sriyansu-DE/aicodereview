package com.codereviewer.analyzer;

import com.codereviewer.model.codeIssue;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

import java.util.ArrayList;
import java.util.List;

public class EmptyMethodAnalyzer implements CodeAnalyzer {

  public List<codeIssue> analyze (CompilationUnit cu){
    List<codeIssue> issues = new ArrayList<>();

    cu.findAll(MethodDeclaration.class)
      .forEach(method -> {

        boolean empty = 
                method.getBody()
                      .map(body -> 
                                body.getStatements()
                                    .isEmpty())
                      .orElse(false);


        if(empty && !method.isAbstract()){

          int line = 
                  method.getBegin()
                          .map(p -> p.line)
                          .orElse(-1);

          issues.add(
                  new codeIssue(
                            "EMPTY_METHOD",
                            "LOW",
                            line,
                            "Method '"
                                    + method.getNameAsString()
                                    + "' has an empty body.",
                            "Implement the method, document why it is intentionally empty, or remove it."
                  ) 
          );
        }
                      
      });

      return issues;
  }
  
}
