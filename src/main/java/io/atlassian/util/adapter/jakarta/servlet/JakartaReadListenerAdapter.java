package io.atlassian.util.adapter.jakarta.servlet;

import jakarta.servlet.ReadListener;

import java.io.IOException;

import static java.util.Objects.requireNonNull;

public class JakartaReadListenerAdapter implements ReadListener {
    private final javax.servlet.ReadListener delegate;

    public JakartaReadListenerAdapter(javax.servlet.ReadListener delegate) {
        this.delegate = requireNonNull(delegate);
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
}
