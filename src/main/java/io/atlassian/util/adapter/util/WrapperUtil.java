package io.atlassian.util.adapter.util;

import io.atlassian.util.adapter.Adapted;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
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

    public static <T> T[] enumerationToArray(Enumeration<T> enumeration, Class<T> clazz) {
        if (enumeration == null) {
            return null;
        }
        List<T> list = Collections.list(enumeration);
        return list.toArray((T[]) Array.newInstance(clazz, list.size()));
    }

    public static boolean equals(Adapted<?> adapted, Object obj) {
        if (adapted == obj) {
            return true;
        }
        if (obj == null || adapted.getClass() != obj.getClass()) {
            return false;
        }
        Adapted<?> that = (Adapted<?>) obj;
        return adapted.getDelegate().equals(that.getDelegate());
    }

    public static int hashCode(Adapted<?> adapted) {
        return adapted.getDelegate().hashCode();
    }
}
