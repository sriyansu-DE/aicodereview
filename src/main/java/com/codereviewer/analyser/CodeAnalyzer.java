package com.codereviewer.analyzer;

import com.codereviewer.model.CodeIssue;
import com.github.javaparser.ast.CompilationUnit;

import java.util.List;

public interface CodeAnalyzer {

    List<CodeIssue> analyze(CompilationUnit cu);

}
