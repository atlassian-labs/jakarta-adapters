package io.atlassian.util.adapter.java.util;

import java.util.Enumeration;
import java.util.function.Function;

import static java.util.Objects.requireNonNull;

public class EnumerationAdapter<S, T> implements Enumeration<T> {

    private final Enumeration<S> delegate;
    private final Function<S, T> adapter;

    public EnumerationAdapter(Enumeration<S> delegate, Function<S, T> adapter) {
        this.delegate = requireNonNull(delegate);
        this.adapter = requireNonNull(adapter);
    }

    @Override
    public boolean hasMoreElements() {
        return delegate.hasMoreElements();
    }

    @Override
    public T nextElement() {
        return adapter.apply(delegate.nextElement());
    }
}
