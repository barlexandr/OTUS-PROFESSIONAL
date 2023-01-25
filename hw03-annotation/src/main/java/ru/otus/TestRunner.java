package ru.otus;

import ru.otus.annotation.After;
import ru.otus.annotation.Before;
import ru.otus.annotation.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    public static void runTests(Class<?> clazz) {
        Method[] methods = clazz.getDeclaredMethods();
        int pass = 0, fail = 0, count = 0;
        List<Method> tests = new ArrayList<>();
        List<Method> beforeTests = new ArrayList<>();
        List<Method> afterTests = new ArrayList<>();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class)) {
                tests.add(method);
            } else if (method.isAnnotationPresent(Before.class)) {
                beforeTests.add(method);
            } else if (method.isAnnotationPresent(After.class)) {
                afterTests.add(method);
            }
        }

        try {
            for (Method test : tests) {
                Object obj = clazz.getDeclaredConstructor().newInstance();
                for (Method before : beforeTests) {
                    try {
                        before.invoke(obj);
                    } catch (Exception e) {
                        System.out.println("Create context before test failed");
                    }
                }
                try {
                    count++;
                    test.invoke(obj);
                    pass++;
                } catch (Exception e) {
                    System.out.println(e.getCause().getMessage());
                    fail++;
                }
                for (Method after : afterTests) {
                    try {
                        after.invoke(obj);
                    } finally {
                        System.out.println("Clean context after test");
                    }
                }
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            printStatistics(pass, fail, count);
        }
    }

    private static void printStatistics(int pass, int fail, int count) {
        System.out.printf("Passed: %s, failed: %s%nTotal tests: %s%n", pass, fail, count);
    }
}
