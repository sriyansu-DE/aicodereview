package com.codereviewer.analyzer;

import com.codereviewer.model.codeIssue;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.DoubleLiteralExpr;

import java.util.ArrayList;
import java.util.List;

public class MagicNumberAnalyzer implements CodeAnalyzer {
  @Override
  public List<codeIssue> analyze(CompilationUnit cu){
    List<codeIssue> issues = new ArrayList<>();
    cu.findAll(IntegerLiteralExpr.class)
      .forEach(number -> {

        String value = 
              number.getValue();

        if(!value.equals("0") && !value.equals("1")){

          int line = 
                  number.getBegin()
                        .map(p -> p.line)
                        .orElse(-1);

                  issues.add(
                         new codeIssue(
                                "MAGIC_NUMBER",
                                "LOW",
                                line,
                                "Unnamed numeric constant found: "
                                        + value,
                                "Replace it with a named constant that explains its meaning."
                         )     
                  );
        }
      });

      cu.findAll(DoubleLiteralExpr.class)
        .forEach(number -> {

          String value = 
                  number.getValue();

          if(!value.equals("0.0") && !value.equals("1.0")){

            int line = 
                    number.getBegin()
                            .map(p -> p.line)
                            .orElse(-1);

            issues.add(
              new codeIssue(
                        "MAGIC_NUMBER",
                        "LOW",
                        line,
                        "Unnamed floating-point constant found: "
                                + value,
                        "Replace it with a named constant."
              )
            );
          }
        });

        return issues;

  }
  
}
