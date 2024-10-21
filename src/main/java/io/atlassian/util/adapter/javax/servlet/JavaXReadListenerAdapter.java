package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.JakartaReadListenerAdapter;

import javax.servlet.ReadListener;
import java.io.IOException;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXReadListenerAdapter implements ReadListener, Adapted<jakarta.servlet.ReadListener> {

    private final jakarta.servlet.ReadListener delegate;

    public static ReadListener from(jakarta.servlet.ReadListener delegate) {
        if (delegate instanceof JakartaReadListenerAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXReadListenerAdapter::new);
    }

    JavaXReadListenerAdapter(jakarta.servlet.ReadListener delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.ReadListener getDelegate() {
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
}
