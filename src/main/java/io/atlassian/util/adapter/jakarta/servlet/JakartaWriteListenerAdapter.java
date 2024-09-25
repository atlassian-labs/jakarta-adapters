package io.atlassian.util.adapter.jakarta.servlet;

import jakarta.servlet.WriteListener;

import java.io.IOException;

import static java.util.Objects.requireNonNull;

public class JakartaWriteListenerAdapter implements WriteListener {
    private final javax.servlet.WriteListener delegate;

    public JakartaWriteListenerAdapter(javax.servlet.WriteListener delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public void onWritePossible() throws IOException {
        delegate.onWritePossible();
    }

    @Override
    public void onError(Throwable t) {
        delegate.onError(t);
    }
}
