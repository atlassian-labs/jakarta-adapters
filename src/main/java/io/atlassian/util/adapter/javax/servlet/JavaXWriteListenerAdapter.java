package io.atlassian.util.adapter.javax.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.jakarta.servlet.JakartaWriteListenerAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import javax.servlet.WriteListener;
import java.io.IOException;

import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JavaXWriteListenerAdapter implements WriteListener, Adapted<jakarta.servlet.WriteListener> {
    private final jakarta.servlet.WriteListener delegate;

    public static WriteListener from(jakarta.servlet.WriteListener delegate) {
        if (delegate instanceof JakartaWriteListenerAdapter) {
            JakartaWriteListenerAdapter castDelegate = (JakartaWriteListenerAdapter) delegate;
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JavaXWriteListenerAdapter::new);
    }

    protected JavaXWriteListenerAdapter(jakarta.servlet.WriteListener delegate) {
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public jakarta.servlet.WriteListener getDelegate() {
        return delegate;
    }

    @Override
    public void onWritePossible() throws IOException {
        delegate.onWritePossible();
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
