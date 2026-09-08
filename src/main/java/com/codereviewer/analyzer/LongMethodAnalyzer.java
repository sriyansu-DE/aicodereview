package com.codereviewer.analyzer;

import com.codereviewer.model.codeIssue;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;
import java.util.ArrayList;
import java.util.List;


public class LongMethodAnalyzer implements CodeAnalyzer {
  private static final int MAX_STATEMENTS = 30;

  @Override 

  public List<codeIssue> analyze (CompilationUnit cu){

    List<codeIssue> issues = new ArrayList<>();

    cu.findAll(MethodDeclaration.class)
      .forEach(Method -> {

        int count = 
                Method.getBody()
                .map(body -> body.getStatements()
                            .size())
                .orElse(0);

        if(count > MAX_STATEMENTS){
          int line = Method.getBegin()
                          .map(p -> p.line)
                          .orElse(-1);


          issues.add(
            new codeIssue(

              "LONG_METHOD",
              "MEDIUM",
              line,
              "Method '"
                                                + Method.getNameAsString()
                                                + "' has "
                                                + count
                                                + " top-level statements.",
                                        "Split the method into smaller methods with one clear responsibility."
            )
          );
        }
      });

      return issues;
  }

  
}
