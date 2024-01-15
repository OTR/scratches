package com.github.otr.practical_tdd_for_java_programmers;

import org.junit.Test;

/**
 * Unit test for a class Main.
 */
public class MainTest {
    /**
     *
     */
    @Test
    public void shouldAnswerWithTrue() {
        ISBNValidator validator = new ISBNValidator();
        validator.checkISBN("helloworld!");
    }

    /**
     *
     */
    @Test
    public void shouldThrowAnException() {
        ISBNValidator validator = new ISBNValidator();
        validator.checkISBN("helloworld");
    }

}
