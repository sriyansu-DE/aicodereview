package com.codereviewer.analyzer;

import java.util.List;

import com.codereviewer.model.ReviewResult;
import com.codereviewer.model.codeIssue;
import com.github.javaparser.ParseProblemException;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class CodeReviewer {

    private final List<CodeAnalyzer> analyzers;

    public CodeReviewer() {
        this.analyzers = List.of(
                new SystemOutAnalyzer(),
                new EmptyCatchAnalyzer(),
                new GenericCatchAnalyzer(),
                new TodoAnalyzer(),
                new LongClassAnalyzer(),
                new LongMethodAnalyzer(),
                new DeepNestingAnalyzer(),
                new TooManyArgumentsAnalyzer(),
                new EmptyMethodAnalyzer(),
                new MagicNumberAnalyzer(),
                new HardcodedSecretAnalyzer(),
                new SqlInjectionAnalyzer(),
                new ComplexityAnalyzer()
        );
    }

    public ReviewResult review(String sourceCode, String fileName) {
        ReviewResult result = new ReviewResult(fileName);

        try {
            CompilationUnit cu = StaticJavaParser.parse(sourceCode);

            for (CodeAnalyzer analyzer : analyzers) {
                List<codeIssue> issues = analyzer.analyze(cu);
                if (issues != null) {
                    issues.forEach(result::addIssue);
                }
            }
        } catch (ParseProblemException e) {
            throw e;
        }

        return result;
    }

    public List<CodeAnalyzer> getAnalyzers() {
        return analyzers;
    }
}