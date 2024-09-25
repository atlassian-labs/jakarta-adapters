package io.atlassian.util.adapter.java.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnumerationAdapterTest {

    private Enumeration<String> originalEnumeration;
    private Enumeration<String> biAdaptedEnumeration;

    private void initEnumerations(List<String> list) {
        originalEnumeration = Collections.enumeration(list);
        biAdaptedEnumeration = new EnumerationAdapter<>(
                new EnumerationAdapter<>(originalEnumeration, s -> s.toCharArray()[0]),
                Object::toString);
    }

    @Test
    public void enumerate() {
        initEnumerations(List.of("a", "b", "c"));

        assertTrue(biAdaptedEnumeration.hasMoreElements());
        assertTrue(originalEnumeration.hasMoreElements());

        assertEquals("a", biAdaptedEnumeration.nextElement());
        assertEquals("b", originalEnumeration.nextElement());
        assertEquals("c", biAdaptedEnumeration.nextElement());

        assertFalse(biAdaptedEnumeration.hasMoreElements());
        assertFalse(originalEnumeration.hasMoreElements());
    }

    @Test
    public void nullValue() {
        List<String> list = new ArrayList<>();
        list.add(null);
        initEnumerations(list);

        assertNull(biAdaptedEnumeration.nextElement());
    }
}
