package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.javax.servlet.JavaXAsyncEventAdapter;
import io.atlassian.util.adapter.javax.servlet.JavaXAsyncListenerAdapter;
import jakarta.servlet.AsyncEvent;
import jakarta.servlet.AsyncListener;

import java.io.IOException;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaAsyncListenerAdapter implements AsyncListener {
    private final javax.servlet.AsyncListener delegate;

    public static AsyncListener from(javax.servlet.AsyncListener delegate) {
        if (delegate instanceof JavaXAsyncListenerAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaAsyncListenerAdapter::new);
    }

    JakartaAsyncListenerAdapter(javax.servlet.AsyncListener delegate) {
        this.delegate = requireNonNull(delegate);
    }

    public javax.servlet.AsyncListener getDelegate() {
        return delegate;
    }

    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        delegate.onComplete(JavaXAsyncEventAdapter.from(event));
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        delegate.onTimeout(JavaXAsyncEventAdapter.from(event));
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        delegate.onError(JavaXAsyncEventAdapter.from(event));
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
        delegate.onStartAsync(JavaXAsyncEventAdapter.from(event));
    }
}
