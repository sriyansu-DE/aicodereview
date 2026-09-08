package com.codereviewer.analyzer;

import com.codereviewer.model.codeIssue;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.stmt.CatchClause;

import java.util.ArrayList;
import java.util.List;


public class GenericCatchAnalyzer implements CodeAnalyzer {

  @Override 
  public List<codeIssue> analyze (CompilationUnit cu){

    List<codeIssue> issues = new ArrayList<>();

    cu.findAll(CatchClause.class)
    .forEach(catchClause -> {

      String type = catchClause
      .getParameter()
      .getType()
      .asString();

      if(type.equals("Exeption") || type.equals("Throwable")){

        int line = catchClause.getBegin().map(p -> p.line).orElse(-1);

        issues.add(
          new codeIssue(
            "GENERIC_CATCH",
            "MEDIUM",
            line,
            "A broad exception type is being caught: "
                  + type,
            "Catch the most specific exception type that the code can actually handle."
          )
        );
      }
    });

    return issues;
  }
  
}
