package io.atlassian.util.adapter.util;

import java.util.function.Function;

public class WrapperUtil {

    private WrapperUtil() {
    }

    public static <T, S> S applyIfNonNull(T delegate, Function<T, S> wrapper) {
        return delegate != null ? wrapper.apply(delegate) : null;
    }
}
