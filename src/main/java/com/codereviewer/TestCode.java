package com.codereviewer;

public class TestCode {

    // 1. SystemOutAnalyzer
    public void printSomething() {
        System.out.println("Debug message");
        System.out.println("Another debug message");
    }

    // 2. EmptyCatchAnalyzer
    // 3. GenericCatchAnalyzer
    // 4. TodoAnalyzer
    public void exceptionTest() {
        try {
            int divisor = 0;
            int result = 100 / divisor;
            System.out.println(result);
        } catch (Exception e) {
            // Empty catch block
        }

        // TODO: Fix exception handling later
    }

    // 5. TooManyArgumentsAnalyzer
    public void tooManyArguments(
            String name,
            int age,
            String address,
            String city,
            String state,
            String country,
            String phone,
            String email) {

        System.out.println(name);
    }

    // 6. EmptyMethodAnalyzer
    public void emptyMethod() {
    }

    // 7. MagicNumberAnalyzer
    public void magicNumbers() {
        int age = 25;
        int salary = 50000;
        int number = 12345;

        if (age > 18) {
            salary = salary + 10000;
        }

        if (number == 12345) {
            System.out.println("Magic number detected");
        }
    }

    // 8. HardcodedSecretAnalyzer
    public void hardcodedSecret() {
        String password = "SuperSecretPassword123";
        String apiKey = "ABC123XYZ987";
        String secret = "mySecretKey123";

        System.out.println(password);
        System.out.println(apiKey);
        System.out.println(secret);
    }

    // 9. SqlInjectionAnalyzer
    public void sqlInjection(String username) {
        String query = "SELECT * FROM users WHERE username = '" + username + "'";

        System.out.println(query);
    }

    // 10. DeepNestingAnalyzer
    public void deepNesting(int value) {
        if (value > 0) {
            if (value < 100) {
                if (value % 2 == 0) {
                    if (value > 10) {
                        if (value < 90) {
                            if (value != 50) {
                                System.out.println("Very deeply nested code");
                            }
                        }
                    }
                }
            }
        }
    }

    // 11. ComplexityAnalyzer
    public void complexMethod(
            int a,
            int b,
            int c,
            int d,
            int e) {

        if (a > 0) {
            if (b > 0) {
                if (c > 0) {
                    for (int i = 0; i < 10; i++) {
                        if (i % 2 == 0) {
                            System.out.println(i);
                        } else if (i % 3 == 0) {
                            System.out.println("Three");
                        } else if (i % 5 == 0) {
                            System.out.println("Five");
                        } else {
                            System.out.println("Other");
                        }
                    }
                } else {
                    System.out.println("C is zero");
                }
            } else {
                System.out.println("B is zero");
            }
        } else {
            System.out.println("A is zero");
        }

        if (d > 10 && e > 10) {
            System.out.println("Both are greater than 10");
        }

        if (d > 20 || e > 20) {
            System.out.println("At least one is greater than 20");
        }
    }

    // 12. LongMethodAnalyzer
    public void veryLongMethod() {
        int a = 1;
        int b = 2;
        int c = 3;
        int d = 4;
        int e = 5;
        int f = 6;
        int g = 7;
        int h = 8;
        int i = 9;
        int j = 10;

        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(e);
        System.out.println(f);
        System.out.println(g);
        System.out.println(h);
        System.out.println(i);
        System.out.println(j);

        int x1 = a + b;
        int x2 = b + c;
        int x3 = c + d;
        int x4 = d + e;
        int x5 = e + f;
        int x6 = f + g;
        int x7 = g + h;
        int x8 = h + i;
        int x9 = i + j;

        System.out.println(x1);
        System.out.println(x2);
        System.out.println(x3);
        System.out.println(x4);
        System.out.println(x5);
        System.out.println(x6);
        System.out.println(x7);
        System.out.println(x8);
        System.out.println(x9);

        for (int counter = 0; counter < 10; counter++) {
            System.out.println(counter);
        }

        for (int counter = 10; counter < 20; counter++) {
            System.out.println(counter);
        }

        for (int counter = 20; counter < 30; counter++) {
            System.out.println(counter);
        }
    }

    // 13. LongClassAnalyzer
    public void method01() {
        System.out.println("method01");
    }

    public void method02() {
        System.out.println("method02");
    }

    public void method03() {
        System.out.println("method03");
    }

    public void method04() {
        System.out.println("method04");
    }

    public void method05() {
        System.out.println("method05");
    }

    public void method06() {
        System.out.println("method06");
    }

    public void method07() {
        System.out.println("method07");
    }

    public void method08() {
        System.out.println("method08");
    }

    public void method09() {
        System.out.println("method09");
    }

    public void method10() {
        System.out.println("method10");
    }

    public void method11() {
        System.out.println("method11");
    }

    public void method12() {
        System.out.println("method12");
    }

    public void method13() {
        System.out.println("method13");
    }

    public void method14() {
        System.out.println("method14");
    }

    public void method15() {
        System.out.println("method15");
    }

    public void method16() {
        System.out.println("method16");
    }

    public void method17() {
        System.out.println("method17");
    }

    public void method18() {
        System.out.println("method18");
    }

    public void method19() {
        System.out.println("method19");
    }

    public void method20() {
        System.out.println("method20");
    }

    public void method21() {
        System.out.println("method21");
    }

    public void method22() {
        System.out.println("method22");
    }

    public void method23() {
        System.out.println("method23");
    }

    public void method24() {
        System.out.println("method24");
    }

    public void method25() {
        System.out.println("method25");
    }

    public void method26() {
        System.out.println("method26");
    }

    public void method27() {
        System.out.println("method27");
    }

    public void method28() {
        System.out.println("method28");
    }

    public void method29() {
        System.out.println("method29");
    }

    public void method30() {
        System.out.println("method30");
    }
}
