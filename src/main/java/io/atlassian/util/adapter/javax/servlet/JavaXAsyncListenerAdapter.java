package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.jakarta.servlet.JakartaAsyncEventAdapter;
import io.atlassian.util.adapter.jakarta.servlet.JakartaAsyncListenerAdapter;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import java.io.IOException;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXAsyncListenerAdapter implements AsyncListener {
    private final jakarta.servlet.AsyncListener delegate;

    public static AsyncListener from(jakarta.servlet.AsyncListener delegate) {
        if (delegate instanceof JakartaAsyncListenerAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXAsyncListenerAdapter::new);
    }

    JavaXAsyncListenerAdapter(jakarta.servlet.AsyncListener delegate) {
        this.delegate = requireNonNull(delegate);
    }

    public jakarta.servlet.AsyncListener getDelegate() {
        return delegate;
    }

    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        delegate.onComplete(JakartaAsyncEventAdapter.from(event));
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        delegate.onTimeout(JakartaAsyncEventAdapter.from(event));
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        delegate.onError(JakartaAsyncEventAdapter.from(event));
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
        delegate.onStartAsync(JakartaAsyncEventAdapter.from(event));
    }
}
