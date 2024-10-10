package io.atlassian.util.adapter.util;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

public class WrapperUtil {

    private WrapperUtil() {
    }

    public static <T, S> S applyIfNonNull(T delegate, Function<T, S> wrapper) {
        return delegate != null ? wrapper.apply(delegate) : null;
    }

    public static <T, S> List<S> transformListIfNonNull(List<T> delegates, Function<T, S> wrapper) {
        var coll = transformIfNonNull(delegates, wrapper);
        return coll != null ? List.copyOf(coll) : null;
    }

    public static <T, S> Collection<S> transformIfNonNull(Collection<T> delegates, Function<T, S> wrapper) {
        if (delegates == null) {
            return null;
        }
        return delegates.stream().map(delegate -> applyIfNonNull(delegate, wrapper)).toList();
    }
}
