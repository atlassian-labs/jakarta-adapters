package io.atlassian.util.adapter.java.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnumerationAdapterTest {

    private Enumeration<String> originalEnumeration;

    private Enumeration<String> biAdaptedEnumeration;

    @BeforeEach
    public void setUp() throws Exception {
        originalEnumeration = Collections.enumeration(List.of("a", "b", "c"));
        biAdaptedEnumeration = new EnumerationAdapter<>(new EnumerationAdapter<>(originalEnumeration, s -> s.toCharArray()[0]), Object::toString);
    }

    @Test
    public void enumerate() {
        assertTrue(biAdaptedEnumeration.hasMoreElements());
        assertTrue(originalEnumeration.hasMoreElements());

        assertEquals("a", biAdaptedEnumeration.nextElement());
        assertEquals("b", originalEnumeration.nextElement());
        assertEquals("c", biAdaptedEnumeration.nextElement());

        assertFalse(biAdaptedEnumeration.hasMoreElements());
        assertFalse(originalEnumeration.hasMoreElements());
    }
}
