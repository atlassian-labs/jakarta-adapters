package io.atlassian.util.adapter.jakarta.servlet;

import io.atlassian.util.adapter.Adapted;
import io.atlassian.util.adapter.javax.servlet.JavaXAsyncEventAdapter;
import io.atlassian.util.adapter.util.WrapperUtil;

import jakarta.servlet.AsyncContext;
import jakarta.servlet.AsyncEvent;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

import static io.atlassian.util.adapter.jakarta.JakartaAdapters.asJakarta;
import static io.atlassian.util.adapter.util.WrapperUtil.applyIfNonNull;
import static java.util.Objects.requireNonNull;

public class JakartaAsyncEventAdapter extends AsyncEvent implements Adapted<javax.servlet.AsyncEvent> {

    private final javax.servlet.AsyncEvent delegate;

    public static AsyncEvent from(javax.servlet.AsyncEvent delegate) {
        if (delegate instanceof JavaXAsyncEventAdapter castDelegate) {
            return castDelegate.getDelegate();
        }
        return applyIfNonNull(delegate, JakartaAsyncEventAdapter::new);
    }

    protected JakartaAsyncEventAdapter(javax.servlet.AsyncEvent delegate) {
        super(null, null, null);
        this.delegate = requireNonNull(delegate);
    }

    @Override
    public javax.servlet.AsyncEvent getDelegate() {
        return delegate;
    }

    @Override
    public AsyncContext getAsyncContext() {
        return JakartaAsyncContextAdapter.from(delegate.getAsyncContext());
    }

    @Override
    public ServletRequest getSuppliedRequest() {
        return asJakarta(delegate.getSuppliedRequest());
    }

    @Override
    public ServletResponse getSuppliedResponse() {
        return asJakarta(delegate.getSuppliedResponse());
    }

    @Override
    public Throwable getThrowable() {
        return delegate.getThrowable();
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
