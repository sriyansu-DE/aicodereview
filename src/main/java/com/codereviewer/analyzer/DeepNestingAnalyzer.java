package com.codereviewer.analyzer;

import java.util.ArrayList;
import java.util.List;

import com.codereviewer.model.codeIssue;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.SwitchStmt;
import com.github.javaparser.ast.stmt.WhileStmt;

public class DeepNestingAnalyzer implements CodeAnalyzer {

    private static final int MAX_DEPTH = 3;

    @Override
    public List<codeIssue> analyze(CompilationUnit cu) {

        List<codeIssue> issues = new ArrayList<>();

        for (Node node : cu.findAll(Node.class)) {

            if (!isControlStatement(node))
                continue;

            int depth = calculateDepth(node);

            if (depth > MAX_DEPTH) {

                int line = node.getBegin()
                        .map(p -> p.line)
                        .orElse(0);

                 issues.add(new codeIssue(
        "JAVA007",
        "MEDIUM",
        line,
        "Deep nesting detected. Nesting depth: " + depth,
        "Reduce nesting by using early returns or extracting helper methods."
));
            }
        }

        return issues;
    }

    private boolean isControlStatement(Node node) {

        return node instanceof IfStmt
                || node instanceof ForStmt
                || node instanceof ForEachStmt
                || node instanceof WhileStmt
                || node instanceof DoStmt
                || node instanceof SwitchStmt;
    }

    private int calculateDepth(Node node) {

        int depth = 1;

        Node current = node.getParentNode().orElse(null);

        while (current != null) {

            if (isControlStatement(current))
                depth++;

            current = current.getParentNode().orElse(null);
        }

        return depth;
    }
}
