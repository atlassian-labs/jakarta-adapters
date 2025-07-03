package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.JavaXWriteListenerAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.WriteListener;
import java.io.IOException;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaWriteListenerAdapter implements WriteListener, Adapted<javax.servlet.WriteListener> {
    private final javax.servlet.WriteListener delegate;

    public static WriteListener from(javax.servlet.WriteListener delegate) {
        if (delegate instanceof JavaXWriteListenerAdapter) {
            JavaXWriteListenerAdapter castDelegate = (JavaXWriteListenerAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaWriteListenerAdapter::new);
    }

    protected JakartaWriteListenerAdapter(javax.servlet.WriteListener delegate) {
        this.delegate = requireNonNull(delegate);
    }

    public javax.servlet.WriteListener getDelegate() {
        return delegate;
    }

    @Override
    public void onWritePossible() throws IOException {
        delegate.onWritePossible();
    }

    @Override
    public void onError(Throwable t) {
        delegate.onError(t);
    }

    @Override
    public boolean equals(Object obj) {
        return WrapperUtil.equals(this, obj);
    }

    @Override
    public int hashCode() {
        return WrapperUtil.hashCode(this);
    }
}
