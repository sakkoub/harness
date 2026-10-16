package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class AppTest {

    @Test
    void greetsByName() {
        assertEquals("Welcome, Harness!", App.greet("Harness"));
    }

    @Test
    void greetsWorldWhenNameMissing() {
        assertEquals("Welcome, World!", App.greet(null));
        assertEquals("Welcome, World!", App.greet("  "));
    }

    @Test
    void addsNumbers() {
        assertEquals(5, App.add(2, 3));
    }

    @Test
    void readsNameFromQuery() {
        assertEquals("Harness", App.nameFromQuery("foo=1&name=Harness"));
        assertEquals("Jane Doe", App.nameFromQuery("name=Jane+Doe"));
        assertNull(App.nameFromQuery(null));
        assertNull(App.nameFromQuery("foo=1"));
    }
}
