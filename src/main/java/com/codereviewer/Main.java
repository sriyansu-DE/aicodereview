package com.codereviewer;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;

public class Main {

    public static void main(String[] args) {

        String code = """
                public class Test {
                    public void hello() {
                        System.out.println("Hello");
                    }
                }
                """;

        CompilationUnit cu = StaticJavaParser.parse(code);

        System.out.println(cu);
    }
}
