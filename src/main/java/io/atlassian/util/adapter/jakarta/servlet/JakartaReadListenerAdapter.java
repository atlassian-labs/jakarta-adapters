package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.JavaXReadListenerAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.ReadListener;
import java.io.IOException;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaReadListenerAdapter implements ReadListener, Adapted<javax.servlet.ReadListener> {
    private final javax.servlet.ReadListener delegate;

    public static ReadListener from(javax.servlet.ReadListener delegate) {
        if (delegate instanceof JavaXReadListenerAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaReadListenerAdapter::new);
    }

    protected JakartaReadListenerAdapter(javax.servlet.ReadListener delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.ReadListener getDelegate() {
        return delegate;
    }

    @Override
    public void onDataAvailable() throws IOException {
        delegate.onDataAvailable();
    }

    @Override
    public void onAllDataRead() throws IOException {
        delegate.onAllDataRead();
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
