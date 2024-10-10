package io.atlassian.util.adapter.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class WrapperUtilTest {

    @Test
    void applyIfNonNull() {
        Function<String, Integer> stringLength = String::length;

        assertEquals(5, WrapperUtil.applyIfNonNull("hello", stringLength));
        assertNull(WrapperUtil.applyIfNonNull(null, stringLength));
    }

    @Test
    void transformListIfNonNull() {
        Function<String, Integer> stringLength = String::length;
        List<String> input = Arrays.asList("a", "bb", "ccc");

        List<Integer> result = WrapperUtil.transformListIfNonNull(input, stringLength);
        assertEquals(Arrays.asList(1, 2, 3), result);

        assertNull(WrapperUtil.transformListIfNonNull(null, stringLength));
    }

    @Test
    void transformIfNonNull() {
        Function<String, Integer> stringLength = String::length;
        List<String> input = Arrays.asList("a", "bb", "ccc", null);

        Collection<Integer> result = WrapperUtil.transformIfNonNull(input, stringLength);
        assertEquals(Arrays.asList(1, 2, 3, null), result);

        assertNull(WrapperUtil.transformIfNonNull(null, stringLength));
    }

    @Test
    void transformIfNonNullWithAllNullElements() {
        Function<String, Integer> stringLength = String::length;
        List<String> input = Arrays.asList(null, null, null);

        Collection<Integer> result = WrapperUtil.transformIfNonNull(input, stringLength);
        assertEquals(Arrays.asList(null, null, null), result);
    }
}
