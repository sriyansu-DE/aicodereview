package com.codereviewer.analyzer;

import com.codereviewer.model.codeIssue;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.CatchClause;

import java.util.ArrayList;
import java.util.List;

public class EmptyCatchAnalyzer implements CodeAnalyzer {

  @Override 

  public List<codeIssue> analyze (CompilationUnit cu){

    List<codeIssue> issues = new ArrayList<>();
    cu.findAll(CatchClause.class)
      .forEach(catchClause -> {
        if (catchClause
              .getBody()
              .getStatements()
              .isEmpty()) {

          int line = 
                  catchClause.getBegin()
                  .map(p -> p.line)
                  .orElse(-1);


          issues.add(
            new codeIssue(
              "EMPTY_CATCH",
              "HIGH",
              line,
              "Exception is caught but the catch block is empty.",
              "Handle, log, or rethrow the exception instead of silently ignoring it."
            
            )
          );
        }
      });

      return issues;
  }
  
}
