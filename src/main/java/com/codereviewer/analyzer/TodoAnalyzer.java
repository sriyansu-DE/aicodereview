package com.codereviewer.analyzer;

import com.codereviewer.model.codeIssue;
import com.github.javaparser.ast.CompilationUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TodoAnalyzer implements CodeAnalyzer {

    // Matches TODO / FIXME as whole words (case-insensitive),
    // optionally followed by a ticket reference like "TODO(JIRA-123):" or "TODO #45:"
    private static final Pattern MARKER_PATTERN = Pattern.compile(
            "\\b(TODO|FIXME)\\b" +                 // the marker itself
            "\\s*" +
            "(?:\\(([A-Za-z0-9\\-_#]+)\\)|#(\\w+))?" + // optional (TICKET-ID) or #ticket
            "\\s*:?",
            Pattern.CASE_INSENSITIVE
    );

    @Override
    public List<codeIssue> analyze(CompilationUnit cu) {
        List<codeIssue> issues = new ArrayList<>();

        cu.getAllContainedComments().forEach(comment -> {
            String text = comment.getContent();
            Matcher matcher = MARKER_PATTERN.matcher(text);

            while (matcher.find()) {
                String marker = matcher.group(1).toUpperCase();
                String ticketId = matcher.group(2) != null
                        ? matcher.group(2)
                        : matcher.group(3);

                int line = comment.getBegin()
                        .map(p -> p.line)
                        .orElse(-1);

                String severity = marker.equals("FIXME") ? "MEDIUM" : "LOW";

                String message = marker + " comment found";
                if (ticketId != null) {
                    message += " (ref: " + ticketId + ")";
                }
                message += ": " + text.trim();

                String suggestion = marker.equals("FIXME")
                        ? "This marks known broken/incorrect behavior — prioritize fixing before release."
                        : "Convert unfinished work into a tracked task or complete it before release.";

                issues.add(new codeIssue(
                        marker,
                        severity,
                        line,
                        message,
                        suggestion
                ));
            }
        });

        return issues;
    }
}