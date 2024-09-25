package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.jakarta.servlet.JakartaAsyncEventAdapter;

import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import java.io.IOException;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXAsyncListenerAdapter implements AsyncListener {
    private final jakarta.servlet.AsyncListener delegate;

    public JavaXAsyncListenerAdapter(jakarta.servlet.AsyncListener delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public void onComplete(AsyncEvent event) throws IOException {
        delegate.onComplete(applyIfNonNull(event, JakartaAsyncEventAdapter::new));
    }

    @Override
    public void onTimeout(AsyncEvent event) throws IOException {
        delegate.onTimeout(applyIfNonNull(event, JakartaAsyncEventAdapter::new));
    }

    @Override
    public void onError(AsyncEvent event) throws IOException {
        delegate.onError(applyIfNonNull(event, JakartaAsyncEventAdapter::new));
    }

    @Override
    public void onStartAsync(AsyncEvent event) throws IOException {
        delegate.onStartAsync(applyIfNonNull(event, JakartaAsyncEventAdapter::new));
    }
}
