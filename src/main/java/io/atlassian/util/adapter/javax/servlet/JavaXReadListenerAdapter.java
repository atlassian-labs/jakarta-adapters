package io.atlassian.util.adapter.javax.servlet;

import javax.servlet.ReadListener;
import java.io.IOException;

import static java.util.Objects.requireNonNull;

public class JavaXReadListenerAdapter implements ReadListener {
    private final jakarta.servlet.ReadListener delegate;

    public JavaXReadListenerAdapter(jakarta.servlet.ReadListener delegate) {
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
