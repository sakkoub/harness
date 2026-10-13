package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void greetsByName() {
        assertEquals("Hello, Harness!", App.greet("Harness"));
    }

    @Test
    void greetsWorldWhenNameMissing() {
        assertEquals("Hello, World!", App.greet(null));
        assertEquals("Hello, World!", App.greet("  "));
    }

    @Test
    void addsNumbers() {
        assertEquals(5, App.add(2, 3));
    }
}
