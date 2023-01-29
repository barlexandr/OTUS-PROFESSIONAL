package ru.otus;

import ru.otus.annotation.After;
import ru.otus.annotation.Before;
import ru.otus.annotation.Test;

public class TestClass {

    private int count = 1;

    @Before
    public void beforeUp() {
        count += 2;
        System.out.println("Before: " + count);
    }

    @After
    public void after() {
        count += 20;
        System.out.println("After: " + count);
    }

    @Test
    public void equalsValue() throws Exception {
        if (count == 3) {
            System.out.println("Test 1 passed");
        } else {
            throw new Exception("Test 1 failed");
        }
    }

    @Test
    public void equalsValueTest2() throws Exception {
        if (count == 100) {
            System.out.println("Test 2 passed");
        } else {
            throw new Exception("Test 2 failed");
        }
    }
}
