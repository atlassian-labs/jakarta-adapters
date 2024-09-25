package io.atlassian.util.adapter.javax.servlet;

import javax.servlet.WriteListener;
import java.io.IOException;

import static java.util.Objects.requireNonNull;

public class JavaXWriteListenerAdapter implements WriteListener {
    private final jakarta.servlet.WriteListener delegate;

    public JavaXWriteListenerAdapter(jakarta.servlet.WriteListener delegate) {
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
