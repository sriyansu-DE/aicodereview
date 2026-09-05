package com.codereviewer.analyzer;

import com.codereviewer.model.CodeIssue;
import com.codereviewer.model.ReviewResult;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

import java.util.List;

public class CodeReviewer {

    private final List<CodeAnalyzer> analyzers = List.of(

            new SystemOutAnalyzer(),

            new EmptyCatchAnalyzer(),

            new GenericCatchAnalyzer(),

            new TodoAnalyzer(),

            new LongLineAnalyzer(),

            new LongMethodAnalyzer(),

            new DeepNestingAnalyzer(),

            new TooManyArgumentsAnalyzer(),

            new EmptyMethodAnalyzer(),

            new MagicNumberAnalyzer(),

            new HardcodedSecretAnalyzer(),

            new SqlInjectionAnalyzer(),

            new ComplexityAnalyzer()
    );

    public ReviewResult review(
            String sourceCode,
            String fileName) {

        CompilationUnit cu =
                StaticJavaParser.parse(sourceCode);

        ReviewResult result =
                new ReviewResult(fileName);

        for (CodeAnalyzer analyzer : analyzers) {

            List<CodeIssue> issues =
                    analyzer.analyze(cu);

            issues.forEach(result::addIssue);
        }

        return result;
    }
}