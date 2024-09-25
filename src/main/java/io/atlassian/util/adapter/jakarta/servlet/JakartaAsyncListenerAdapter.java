package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.javax.servlet.JavaXAsyncEventAdapter;
import jakarta.servlet.AsyncEvent;
import jakarta.servlet.AsyncListener;

import java.io.IOException;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaAsyncListenerAdapter implements AsyncListener {
    private final javax.servlet.AsyncListener delegate;

    public JakartaAsyncListenerAdapter(javax.servlet.AsyncListener delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        delegate.onComplete(applyIfNonNull(event, JavaXAsyncEventAdapter::new));
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        delegate.onTimeout(applyIfNonNull(event, JavaXAsyncEventAdapter::new));
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        delegate.onError(applyIfNonNull(event, JavaXAsyncEventAdapter::new));
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
        delegate.onStartAsync(applyIfNonNull(event, JavaXAsyncEventAdapter::new));
    }
}
