package com.codereviewer.analyzer;

import com.codereviewer.model.codeIssue;
import com.github.javaparser.ast.CompilationUnit;

import java.util.List;

public interface CodeAnalyzer {

    List<codeIssue> analyze(CompilationUnit cu);

}
